// Copyright 2015 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'dart:async';

import 'package:flutter/widgets.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


//引入另一个.dart文件
import 'SecondScreen.dart';

// void main() => runApp(const Center(child: const Text('Hello, world! 1515',textDirection: TextDirection.ltr)));
void main() => runApp(new MaterialApp(
  title: 'navigation Basices',
  home: new FirstScreen(),
));

class FirstScreen extends StatelessWidget{

  @override
  Widget build(BuildContext context){
    return new Scaffold(
      appBar:  AppBar(
        title: Text('First Screen'),
        backgroundColor: Colors.green,
        elevation: 12.0,
      ),
      backgroundColor: Colors.grey[25],
      body: Center(
        child: new RaisedButton(
          child: new Text('Launch new screen'),
          onPressed: (){
             Navigator.push(
              context,
                 MaterialPageRoute(builder: (context) => new SecondScreen()),
            );
          },
        )
      ),
    );
  }
}

// class SecondScreen extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return new Scaffold(
//       appBar: new AppBar(
//         title: new Text("Second Screen"),
//       ),
//       body: new Center(
//         child: new RaisedButton(
//           onPressed: () {
//             Navigator.pop(context);
//           },
//           child: new Text('Go back!'),
//         ),
//       ),
//     );
//   }
// }