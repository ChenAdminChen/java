import 'package:flutter/material.dart';

import 'package:flutter/foundation.dart';

import 'dart:async';

import 'RestData.dart';

import 'package:flutter_blue/flutter_blue.dart';

class Home extends StatefulWidget {
  static String tag = 'home';

  @override
  HomeState createState() {
    return new HomeState();
  }
}

class HomeState extends State<Home> {
  FlutterBlue flutterBlue = FlutterBlue.instance;
  StreamSubscription scanSubscription;

  ScanResult scan;

  StreamSubscription deviceConnection;

  String status = '未连接';

  Set<String> set = new Set();

  @override
  Widget build(BuildContext context) {
//    final alucard = Hero(
//      tag: 'hero',
//      child: Padding(
//        padding: EdgeInsets.all(16.0),
//        child: CircleAvatar(
//          radius: 72.0,
//          backgroundColor: Colors.transparent,
//          backgroundImage: AssetImage('assets/imgase/home.png'),
//        ),
//      ),
//    );
//
//    final welcome = Padding(
//      padding: EdgeInsets.all(8.0),
//      child: Text(
//        'Welcome Alucard',
//        style: TextStyle(fontSize: 28.0, color: Colors.white),
//      ),
//    );
//
//    final lorem = Padding(
//      padding: EdgeInsets.all(8.0),
//      child: Text(
//        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, Donec hendrerit condimentum maruris id temor ${restData.user['name']}',
//        style: TextStyle(fontSize: 16.0, color: Colors.white),
//      ),
//    );
//
//    final body = Container(
//      width: MediaQuery
//          .of(context)
//          .size
//          .width,
//      padding: EdgeInsets.all(28.0),
//      decoration: BoxDecoration(
//          gradient: LinearGradient(
//              colors: [Colors.blue, Colors.lightBlueAccent])
//      ),
//      child:Column(
//        children: <Widget>[alucard,welcome,lorem],
//      ),
//    );

    return Scaffold(
        appBar: AppBar(
          title: Text('bluetooth'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: <Widget>[
              Row(
                children: <Widget>[
                  FlatButton(
//                    padding: EdgeInsets.symmetric(vertical: 16.0),
                    child: Text('scan'),
                    color: Colors.lightBlueAccent,
                    onPressed: () {
                      setState(() {
                        set.clear();
                      });

                      // Start scanning
                      scanSubscription =
                          flutterBlue.scan().listen(scanListener);
                    },
                  ),
                  Padding(
                    padding: const EdgeInsets.all(24.0),
                    child: FlatButton(
                      child: Text('stop scan'),
                      color: Colors.lightBlueAccent,
                      onPressed: () {
                        if (scanSubscription != null) {
                          scanSubscription.cancel();
                          print("scan cancelled");
                        }
                      },
                    ),
                  ),
                  FlatButton(
                      child: Text('connect'),
                      color: Colors.lightBlueAccent,
                      onPressed: () {

                        if (this.deviceConnection != null) {
                          deviceConnection.cancel();
                        }

                          deviceConnection = flutterBlue
                              .connect(scan.device)
                              .listen(connectionListener);
                          print(deviceConnection);

                      },
                    ),

                ],
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: new Text(
                  '$status!',
                  textAlign: TextAlign.center,
                  overflow: TextOverflow.ellipsis,
                  style: new TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
              Expanded(
                child: ListView.builder(
                  itemCount: set.length,
                  itemBuilder: (context, index) {
                    return ListTile(
                      title: Text(set.elementAt(index)),
                    );
                  },
                ),
              ),
            ],
          ),
        ));
  }

  //判断是否找到了相对就的bluetooth
  void scanListener(scanResult) {
    setState(() {
      set.add(scanResult.device.id.id);
    });

    print(scanResult.device.id);

    if (scanResult.device.id.toString() ==
        restData.user['safetyAID']['udid'].toString()) {
      scanSubscription.cancel();
      scan = scanResult;
    }
  }

  //判断是否连接正常
  void connectionListener(s) {
    if (s == BluetoothDeviceState.connected) {
      print(true);
      this.setState(() => status = "连接成功");
    } else {
      print(false);
      this.setState(() => status = "未连接");
    }
  }
}
