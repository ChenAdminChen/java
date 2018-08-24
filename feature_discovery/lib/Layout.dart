
import 'package:flutter/material.dart';

class AnchoredOverlay extends StatelessWidget {
  final bool showOverlay;
  final Widget Function(BuildContext, Offset anchor) overlayBuilder;

  final Widget child;

  AnchoredOverlay({this.showOverlay, this.overlayBuilder, this.child});

  Widget build(BuildContext context) {
    return Container(
      child: LayoutBuilder(
        builder: (BuildContext context, BoxConstraints constraints) {
          return OverlayBuilder(
            showOverlay: showOverlay,
            overlayBuild: (BuildContext overlayContext) {
              RenderBox box = context.findRenderObject() as RenderBox;

              final center =
              box.size.center(box.localToGlobal(const Offset(0.0, 0.0)));

              return overlayBuilder(overlayContext, center);
            },
            child: child,
          );
        },
      ),
    );

//    return OverlayBulid(
//      showOverlay: showOverlay,
//      child: child,
//      overlayBuild: (BuildContext overlayContext) {
//
//
//        final center = box.size.center(box.localToGlobal(const Offset(0.0, 0.0)));
//
//
//
//        return overlayBuilder(overlayContext, center);
//      },
//    );
  }
}

class OverlayBuilder extends StatefulWidget {
  final bool showOverlay;
  final Function(BuildContext) overlayBuild;
  final Widget child;

  OverlayBuilder({this.showOverlay = false, this.overlayBuild, this.child});

  @override
  _OverlayBuilderState createState() => _OverlayBuilderState();
}

class _OverlayBuilderState extends State<OverlayBuilder> {
  OverlayEntry overlayEntry;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    if (widget.showOverlay) {
      showOverlay();
    }
  }

  @override
  void didUpdateWidget(OverlayBuilder oldWidget) {
    super.didUpdateWidget(oldWidget);

    syncWidgetAndOverlay();
  }

  @override
  void dispose() {
    super.dispose();

    if (isShowingOverlay()) {
      hideOverlay();
    }
  }

  bool isShowingOverlay() => overlayEntry != null;

  void showOverlay() {
    overlayEntry = new OverlayEntry(builder: widget.overlayBuild);

    addToOverlay(overlayEntry);
  }

  void hideOverlay() {
    overlayEntry.remove();
    overlayEntry = null;
  }

  void syncWidgetAndOverlay() {
    if (isShowingOverlay() && !widget.showOverlay) {
      hideOverlay();
    } else if (!isShowingOverlay() && widget.showOverlay) {
      showOverlay();
    }
  }

  void addToOverlay(OverlayEntry entry) async {
    Overlay.of(context).insert(entry);
  }

  @override
  Widget build(BuildContext context) {
    return widget.child;
  }
}

//用于创建
class CenterAbout extends StatelessWidget {
  final Offset position;
  final Widget child;

  CenterAbout({this.position, this.child});

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: position.dy,
      left: position.dx,
      child: FractionalTranslation(
        translation: const Offset(-0.5, -0.5),
        child: child,
      ),
    );
  }
}
