import 'package:flutter/material.dart';
import 'home.dart';
import 'dart:io';
import 'dart:convert';

import 'package:http/http.dart' as http;

import 'RestData.dart';

/**
 * use login
 */
class Login extends StatefulWidget {
  static String tag = 'login';

  @override
  _LoginState createState() => new _LoginState();
}

class _LoginState extends State<Login> {
  @override
  void dispose() {
    // TODO: implement dispose

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    TextEditingController psd = new TextEditingController();
    TextEditingController eml = new TextEditingController();

    setState(() {
      psd.text = 'yf8541277';
      eml.text = '00000010@yf.com';
    });

    final logo = Hero(
        tag: 'hero',
        child: CircleAvatar(
            backgroundColor: Colors.transparent,
            radius: 48.0,
            child: Image.asset('assets/images/home.png')));

    final email = TextFormField(
      keyboardType: TextInputType.emailAddress,
      autofocus: false,

      //assert controller == null || initialValue == null

      controller: eml,
//      initialValue: '00000008@yf.com',

      decoration: InputDecoration(
        hintText: 'Email',
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final password = TextFormField(
      autofocus: false,
//      initialValue: 'yf8541277',
      controller: psd,
      obscureText: true,
      autovalidate: true,
      decoration: InputDecoration(
        hintText: 'Password',
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final loginButton = Padding(
      padding: EdgeInsets.symmetric(vertical: 16.0),
      child: Material(
        borderRadius: BorderRadius.circular(30.0),
        shadowColor: Colors.lightBlueAccent.shade100,
        elevation: 5.0,
        child: MaterialButton(
          minWidth: 200.0,
          height: 42.0,
          onPressed: () {
            print("==========================" + psd.text);
            print("==========================" + email.controller.value.text);

//            var passwordText = password.controller.value.text;
//            var emailText = email.controller.value.text;

            var url = "http://192.168.1.168:8181/cxf/access-token?cred=${eml
                .text}&pwd=${psd.text}&vd=app";

            http.get(url, headers: {'Content-Type': 'application/json'}).then(
                (response) {
              return json.decode(response.body);
            }).then((t) {
              if (t["result"] == 0) {
                //跳转到另一个页面
//                Navigator.of(context).pushNamed(Home.tag);

                restData.token = t['data']['token'];
                restData.user = t['data']['user'];

                Navigator
                    .of(context)
                    .push(MaterialPageRoute(builder: (context) => new Home()));
              } else {
                print("error");
              }
            });
          },
          color: Colors.lightBlueAccent,
          child: Text('Log In', style: TextStyle(color: Colors.white)),
        ),
      ),
    );

    final forgotLable = FlatButton(
      child: Text(
        'Forgot password?',
        style: TextStyle(color: Colors.black54),
      ),
      onPressed: () {},
    );

    return Scaffold(
        backgroundColor: Colors.white,
        body: Center(
            child: ListView(
          shrinkWrap: true,
          padding: EdgeInsets.only(left: 24.0, right: 24.0),
          children: <Widget>[
            logo,
            SizedBox(height: 48.0),
            email,
            SizedBox(height: 8.0),
            password,
            SizedBox(height: 24.0),
            loginButton,
            forgotLable
          ],
        )));
  }
}
