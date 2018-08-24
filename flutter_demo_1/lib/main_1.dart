// Copyright 2015 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'dart:async';

import 'package:flutter/widgets.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

// void main() => runApp(const Center(child: const Text('Hello, world! 1515',textDirection: TextDirection.ltr)));
void main() => runApp( FlutterView());


// StatelessWidget 为无状态的widget
class FlutterView extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      title: 'Flutter View',  //用于设备识别应用程序的标题
      theme:  ThemeData(   //用于主题调色
        primarySwatch: Colors.grey,
      ),
      home:  MyHomePage(),  //显示主页面的类
    );
  }
}

// StatefulWidget 仅用来表示控件的表现形式，随时可能发生改变
class MyHomePage extends StatefulWidget {

  //声明变量
  @override
  _MyHomePageState createState() =>  _MyHomePageState();

}

//state内部存储可变状态值，并通过实现build来构建组件
class _MyHomePageState extends State<MyHomePage> {

  static const String _channel = 'increment';
  static const String _pong = 'pong';
  static const String _emptyMessage = '';
  static const BasicMessageChannel<String> platform = const BasicMessageChannel<String>(_channel, const StringCodec());

  int _counter = 0;

  Vector v = Vector(19, 20);
  Vector v1 = Vector(10, 10);

  // static Vector vs = v + (v1);

  //整个软件初始时运行
  @override
  void initState() {
    super.initState();
    print('什么时候运行。。。。');
    platform.setMessageHandler(_handlePlatformIncrement);
  }

  Future<String> _handlePlatformIncrement(String message) async {

    print('message $message');

    setState(() {
      _counter++;

      print('come in......');

    });

    return _emptyMessage;
  }

  void _sendFlutterIncrement() {
    print(_pong);

    //改变状态值 与 react native 相似
    // setState((){
    //   _counter++;
    // });

    platform.send(_pong);
  }

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      body:  Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
           Expanded(
            child: Center(
              child: Text(
                'Platform button tapped $_counter time${ _counter == 1 ? '' : 's' }.',
                style: const TextStyle(fontSize: 17.0))
            ),
          ),
           Container(
            padding:  EdgeInsets.only(bottom: 15.0, left: 5.0),
            child:  Row(
              children: <Widget>[

                Image.asset('assets/flutter-mark-square-64.png', scale: 1.5),
                Text('Flutter test: ', style: const TextStyle(fontSize: 30.0)),
                Text('${(v+(v1)).x}, ${(v-(v1)).x}', style: const TextStyle(fontSize: 30.0)),
              ],
            ),
            
          ),
        ],
      ),
      floatingActionButton:  FloatingActionButton(
        onPressed: _sendFlutterIncrement,
        child: const Icon(Icons.add),
      ),
    );
  }
}

class Vector{
  final int x,y;

  const Vector(this.x,this.y);

  Vector operator +(Vector v){
    return  Vector(x+ v.x, y + v.y);
  }

  Vector operator -(Vector v){
    return  Vector(x- v.x, y- v.y);
  }
}

