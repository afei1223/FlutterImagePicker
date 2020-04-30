import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_imgae_picker/flutter_imgae_picker.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[

              MaterialButton(
                color: Colors.blue,
                onPressed: pickImage,
              ),

            ],
          )
        ),
      ),
    );
  }

  Future<void> pickImage() async {
    await FlutterImgaePicker.pickImage(
        color: "#0000FF",
        buttonColor: "#0000FF",
        title: "选择图片",
        maxCount: 3);
  }
}
