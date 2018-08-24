//import 'dart:async';

import 'package:feature_discovery/Layout.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

final feature1 = "FEATURE_1";
final feature2 = "FEATURE_2";
final feature3 = "FEATURE_3";
final feature4 = "FEATURE_4";

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Feature Discovery',
      theme: new ThemeData(primarySwatch: Colors.blue),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return FeatureDiscovery(
      child: Scaffold(
        appBar: AppBar(
          title: Text(''),
          backgroundColor: Colors.green,
          leading: DescribedFeatureOverlay(
//            showOverlay: false,
              featureId: feature1,
              //显示左上方的圆
              icon: Icons.menu,
              color: Colors.green,
              title: 'The title',
              description: 'The Description',
              child: IconButton(icon: Icon(Icons.menu), onPressed: null)),
          actions: <Widget>[
            DescribedFeatureOverlay(
//              showOverlay: false,
                featureId: feature2,
                //显示右上方的圆
                icon: Icons.search,
                color: Colors.green,
                title: 'The title',
                description: 'The Description',
                child: IconButton(icon: Icon(Icons.search), onPressed: null))
          ],
        ),
        body: Center(),
        floatingActionButton: DescribedFeatureOverlay(
//          showOverlay: false,
            featureId: feature3,
            icon: Icons.add,
            color: Colors.blue,
            title: 'The Title',
            description: 'The Description',
            child: FloatingActionButton(
                child: new Icon(Icons.add), onPressed: () {})),
      ),
    );
  }

//  @override
//  void didChangeDependencies() {
//    // TODO: implement didChangeDependencies
//    super.didChangeDependencies();
//
//    OverlayEntry overlayEntry = OverlayEntry(builder: (BuildContext context) {
//      return CenterAbout(
//        position: new Offset(200.0, 500.0),
//        child: Container(
//          width: 50.0,
//          height: 50.0,
//          decoration:
//              BoxDecoration(shape: BoxShape.circle, color: Colors.purple),
//        ),
//      );
//    }
// );
//
//    addToOverlay(overlayEntry);
//  }
//
//  //todo async
//  void addToOverlay(OverlayEntry entry) async {
//    Overlay.of(context).insert(entry);
//  }
}

class Center extends StatefulWidget {
  @override
  CenterState createState() => CenterState();
}

class CenterState extends State<Center> {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        Column(
          children: <Widget>[
            Image.network(
              'https://www.balboaisland.com/wp-content/uploads/Starbucks-Balboa-Island-01.jpg',
              fit: BoxFit.cover,
              width: double.infinity,
              height: 200.0,
            ),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(16.0),
              color: Colors.blue,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.only(bottom: 8.0),
                    child: Text('Starbucks Coffee',
                        style: TextStyle(color: Colors.white, fontSize: 24.0)),
                  ),
                  Text('Coffee shop',
                      style: TextStyle(color: Colors.white, fontSize: 16.0)),
                ],
              ),
            ),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(16.0),
              child: RaisedButton(
                  child: Text('Do feature discorvery'),
                  onPressed: () {
                    FeatureDiscovery.discoverFeatures(
                        context, [feature1, feature2, feature3, feature4]);
                  }),
            )
          ],
        ),
        Positioned(
          top: 200.0,
          right: 0.0,
          child: FractionalTranslation(
            translation: const Offset(-0.5, -0.5),
            child: DescribedFeatureOverlay(
//                showOverlay: true,
              featureId: feature4,
              icon: Icons.drive_eta,
              color: Colors.blue,
              title: 'The Title',
              description: 'The Description',
              child: FloatingActionButton(
                backgroundColor: Colors.white,
                foregroundColor: Colors.blue,
                child: Icon(
                  Icons.drive_eta,
                ),
                onPressed: () {
                  FeatureDiscovery.discoverFeatures(
                      context, [feature1, feature2, feature3, feature4]);
                },
              ),
            ),
          ),
        )
      ],
    );
  }
}

class FeatureDiscovery extends StatefulWidget {
  static String activeStep(BuildContext context) {
    return (context.inheritFromWidgetOfExactType(_InheritedFeatureDiscovery)
            as _InheritedFeatureDiscovery)
        .activeStepId;
  }

  static void discoverFeatures(BuildContext context, List<String> steps) {
    _FeatureDiscoveryState state = context.ancestorRenderObjectOfType(
        TypeMatcher<_FeatureDiscoveryState>()) as _FeatureDiscoveryState;

    state.discoverFeatures(steps);
  }

  static void markStepComplete(BuildContext context, String stepId) {
    _FeatureDiscoveryState state = context.ancestorRenderObjectOfType(
        TypeMatcher<_FeatureDiscoveryState>()) as _FeatureDiscoveryState;

    state.markStepComplete(stepId);
  }

  static void dismiss(BuildContext context) {
    _FeatureDiscoveryState state = context.ancestorRenderObjectOfType(
        TypeMatcher<_FeatureDiscoveryState>()) as _FeatureDiscoveryState;

    state.dismiss();
  }

  final Widget child;

  FeatureDiscovery({this.child});

  @override
  _FeatureDiscoveryState createState() => _FeatureDiscoveryState();
}

class _FeatureDiscoveryState extends State<FeatureDiscovery> {
  List<String> steps;
  int activeStepIndex;

  void discoverFeatures(List<String> steps) {
    setState(() {
      this.steps = steps;
      activeStepIndex = 0;
    });
  }

  void markStepComplete(String stepId) {
    if (steps != null && steps[activeStepIndex] == stepId) {
      setState(() {
        ++activeStepIndex;

        if (activeStepIndex >= steps.length) {
          _cleanupAfterSteps();
        }
      });
    }
  }

  void dismiss() {
    setState(() {
      _cleanupAfterSteps();
    });
  }

  void _cleanupAfterSteps() {
    steps = null;
    activeStepIndex = null;
  }

  @override
  Widget build(BuildContext context) {
    return _InheritedFeatureDiscovery(
      activeStepId: steps?.elementAt(activeStepIndex), //TODO
      child: widget.child,
    );
  }
}

class _InheritedFeatureDiscovery extends InheritedWidget {
  final String activeStepId;

  _InheritedFeatureDiscovery({this.activeStepId, child}) : super(child: child);

  @override
  bool updateShouldNotify(_InheritedFeatureDiscovery oldWidget) {
    return oldWidget.activeStepId != activeStepId;
  }
}

//-----------------------
class DescribedFeatureOverlay extends StatefulWidget {
  final String featureId;
  final IconData icon;
  final Color color;
  final String title;

  final String description;
  final Widget child;

  DescribedFeatureOverlay(
      {this.featureId,
      this.description,
      this.icon,
      this.color,
      this.title,
      this.child});

  @override
  _DescribedFeatureOverlayState createState() =>
      _DescribedFeatureOverlayState();
}

class _DescribedFeatureOverlayState extends State<DescribedFeatureOverlay> {
  Size screenSize;

  bool showOverlay = false;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();

    screenSize = MediaQuery.of(context).size;

    showOverlayIfActiveStep();
  }

  void showOverlayIfActiveStep() {
    String activeStep = FeatureDiscovery.activeStep(context);
    setState(() => showOverlay = activeStep == widget.featureId);
  }

  bool isCloseToTopDrBotton(Offset position) {
    return position.dy <= 88.0 || (screenSize.height - position.dy) <= 88.0;
  }

  bool isOnTopHalfOfScreen(Offset position) {
    return position.dy < (screenSize.height / 2.0);
  }

  bool isOnLeftHalfOfScreen(Offset position) {
    return position.dx < (screenSize.width / 2.0);
  }

  DescribedFeatureContentOrientation getContentOrientation(Offset position) {
    if (isCloseToTopDrBotton(position)) {
      if (isOnTopHalfOfScreen(position)) {
        return DescribedFeatureContentOrientation.below;
      } else {
        return DescribedFeatureContentOrientation.above;
      }
    } else {
      if (isOnTopHalfOfScreen(position)) {
        return DescribedFeatureContentOrientation.above;
      } else {
        return DescribedFeatureContentOrientation.below;
      }
    }
  }

  void active() {
    FeatureDiscovery.markStepComplete(context, widget.featureId);
  }

  void dismiss() {
    FeatureDiscovery.dismiss(context);
  }

  @override
  Widget build(BuildContext context) {
    return AnchoredOverlay(
      showOverlay: showOverlay,
      child: widget.child,
      overlayBuilder: (BuildContext layoutContext, Offset anchor) {
        final touchTargetRadius = 44.0;

        final contentDrientation = getContentOrientation(anchor);
        final contentOffsetMultiplier =
            contentDrientation == DescribedFeatureContentOrientation.below
                ? 1.0
                : -1.0;

        final contentY =
            anchor.dy + (contentOffsetMultiplier * (touchTargetRadius + 20.0));

        final contentFractionsOffset = contentOffsetMultiplier.clamp(-1.0, 0.0);

        final isBackgroundCentered = isCloseToTopDrBotton(anchor);

        final backgroundRadius =
            screenSize.width * (isBackgroundCentered ? 1.0 : 0.75);

        final backgroundPosition = isBackgroundCentered
            ? anchor
            : Offset(
                screenSize.width / 2.0 +
                    (isOnLeftHalfOfScreen(anchor) ? -20.0 : 20.0),
                anchor.dy +
                    (isOnTopHalfOfScreen(anchor)
                        ? -(screenSize.width / 2.0) + 40.0
                        : (screenSize.width / 2.0) - 40.0));

        return Stack(
          children: <Widget>[
            GestureDetector(
              onTap: dismiss,
              child: Container(
                width: double.infinity,
                height: double.infinity,
                color: Colors.transparent,
              ),
            ),

            //大圆圈
            CenterAbout(
              position: backgroundPosition,
              child: Container(
                width: 2 * backgroundRadius,
                height: 2 * backgroundRadius,
                decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: widget.color.withOpacity(0.96)),
              ),
            ),

            Positioned(
              top: contentY,
              child: FractionalTranslation(
                translation: Offset(0.0, contentFractionsOffset),
                child: Material(
                  color: Colors.transparent,
                  child: Padding(
                    padding: const EdgeInsets.only(left: 40.0, right: 40.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Padding(
                          padding: const EdgeInsets.only(bottom: 8.0),
                          child: Text(
                            widget.title,
                            style: TextStyle(
                              fontSize: 20.0,
                              color: Colors.white,
                            ),
                          ),
                        ),
                        Text(
                          widget.description,
                          style: TextStyle(
                              fontSize: 18.0,
                              color: Colors.white.withOpacity(0.9)),
                        )
                      ],
                    ),
                  ),
                ),
              ),
            ),

            //小圆圈
            CenterAbout(
              position: anchor,
              child: Container(
                width: 2 * touchTargetRadius,
                height: 2 * touchTargetRadius,
                child: RawMaterialButton(
                    shape: CircleBorder(),
                    fillColor: Colors.white,
                    child: Icon(
                      widget.icon,
                      color: widget.color,
                    ),
                    onPressed: active),
              ),
            )
          ],
        );
      },
    );
  }
}

enum DescribedFeatureContentOrientation { above, below }
