package com.example.flutter_imgae_picker_example;

import android.content.Intent;

import com.example.flutter_imgae_picker.FlutterImgaePickerPlugin;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    FlutterImgaePickerPlugin.onActivityResult(requestCode,resultCode,data);
  }
}
