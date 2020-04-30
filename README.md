# flutter_imgae_picker

A new Flutter plugin.

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.dev/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.

## 需要在mainActivity里添加


```

 @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    FlutterImgaePickerPlugin.onActivityResult(requestCode,resultCode,data);
  }

```