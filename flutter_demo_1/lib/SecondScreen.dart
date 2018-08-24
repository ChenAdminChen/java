// Copyright 2015 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// import 'dart:async';

import 'package:flutter/widgets.dart';
import 'package:flutter/material.dart';
// import 'package:flutter/services.dart';

import 'main_1.dart';

// class SecondScreen extends StatelessWidget{
//   @override
//   Widget build (BuildContext context){
//     return new Scaffold(
//       appBar:new AppBar(
//         title: new Text('Second Screen test'),
//       ),
//       body:new Center(
//         child: new RaisedButton(
//           child: new Text('Go to main_1'),
//           onPressed:(){
//             Navigator.push(
//               context,
//               new MaterialPageRoute(builder: (context) => new FlutterView()),
//             );
//           },
//         // child: new Text('Go back'),
//         ),
//       ),
//     );
//   }
// }

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text("Second Screen"),
      ),
      body: new Center(
        child: new RaisedButton(
          onPressed: () {
            Navigator.pop(context);
          },
          // child: Text('Go back!'),
        ),
      ),
    );
  }
}