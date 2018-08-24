import 'package:flutter/material.dart';

final list = [
  new Page(viewModel: new PageViewModel(Colors.blue, 'assets/images/home.png', 'this is body', null, 'flutter')),
  new Page(viewModel: new PageViewModel(Colors.blue, 'assets/images/home.png', 'this is body', null, 'flutter')),
  new Page(viewModel: new PageViewModel(Colors.blue, 'assets/images/home.png', 'this is body', null, 'flutter')),
  new Page(viewModel: new PageViewModel(Colors.blue, 'assets/images/home.png', 'this is body', null, 'flutter')),
];

class Page extends StatelessWidget {

  final PageViewModel viewModel;
  final double percentVisible;

  Page({
    this.viewModel,
    this.percentVisible = 1.0
});

  @override
  Widget build(BuildContext context) {
    return new Container(
      width: double.INFINITY,
      color: viewModel.color,
      child: new Opacity(opacity: percentVisible,

        child: new Column(
          mainAxisAlignment: MainAxisAlignment.center,

          children: [

            new Padding(
              padding: new EdgeInsets.only(bottom: 25.0),
              child: new Image.asset(viewModel.hereAssetPath,
                  width: 200.0, height: 200.0
              ),
            ),

            new Padding(
              padding: new EdgeInsets.only(top:10.0, bottom: 10.0),
              child:new Text(viewModel.title,
                style: new TextStyle(
                  color: Colors.white,
                  fontFamily: 'Raleway',
                  fontSize: 34.0,
                ),
              ),
            ),

            new Padding(
                padding: new EdgeInsets.only(bottom:25.0),
                child: new Text(viewModel.body,
                    textAlign: TextAlign.center,
                    style: new TextStyle(
                      color: Colors.white,
                      fontFamily: 'Raleway',
                      fontSize: 18.0,
                    )
                )
            ),
          ],
        ),
      )
    );
  }
}

class PageViewModel{
  final Color color;
  final String title;
  final String hereAssetPath;
  final String body;
  final String iconAssetIcon;

  PageViewModel(
    this.color,
    this.hereAssetPath,
      this.body,
      this.iconAssetIcon,
      this.title
      );

}


