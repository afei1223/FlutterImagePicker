import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class FlutterImgaePicker {
  static const MethodChannel _channel =
      const MethodChannel('flutter_imgae_picker');


  static Future<List> pickImage(
      { String color,
        String buttonColor,
        String title,
        int maxCount}) async {
    final List paths = await _channel.invokeMethod<List>(
      'pickImage',
      <String, dynamic>{
        'color':color,
        'maxCount':maxCount,
        'title':title,
        'buttonColor':buttonColor,
      },
    );
    List files =  List();
    for(var i = 0; i < paths.length; i++) {
      if(paths[i] != null) {
        files.add(File(paths[i]));
        print(paths[i]);
        print(i);
      }
    }
    return files;
  }

}
