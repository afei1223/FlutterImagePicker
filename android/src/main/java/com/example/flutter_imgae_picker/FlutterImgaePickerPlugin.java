package com.example.flutter_imgae_picker;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.lcw.library.imagepicker.ImagePicker;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterImgaePickerPlugin */
public class FlutterImgaePickerPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

  private static MethodChannel.Result result1;
  private MethodChannel channel;
  private Application mApplication;
  private WeakReference<Activity> mActivity;

  public static void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 2357) {

      if (resultCode == Activity.RESULT_OK) {

        ArrayList<String> path = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);

        result1.success(path);
      } else {
        result1.notImplemented();
      }
    }
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(),"flutter_imgae_picker" );
    mApplication = (Application) flutterPluginBinding.getApplicationContext();
    channel.setMethodCallHandler(this);
  }

  public FlutterImgaePickerPlugin initPlugin(MethodChannel methodChannel, Registrar registrar) {
    channel = methodChannel;
    mApplication = (Application) registrar.context().getApplicationContext();
    mActivity = new WeakReference<>(registrar.activity());
    return this;
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_imgae_picker");
    channel.setMethodCallHandler(new FlutterImgaePickerPlugin().initPlugin(channel,registrar));
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method == "pickImage") {
      Log.i("TAG","pick nice");
      result1 = result;
      FlutterImgaePickerPlugin.result1 = result;
//      FlutterimagepickerpluginPlugin.result = result
      //                  String regex="^[A-Fa-f0-9]+$";
      String regex = "^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$";

      String color = call.argument("color");
      if (color.matches(regex)) {
//        Log.i("TAG", color)
      } else {
        color = "";
      }

      //确认按钮颜色
      String buttonColor = call.argument("buttonColor");
      if(buttonColor.matches(regex)){
        buttonColor = "";
      }

      String title = call.argument("title");
      int maxCount = call.argument("maxCount");

      if (maxCount < 1) {
        maxCount = 9;
      }
//      var intent:Intent = Intent(mActivity?.get(),ImagePickActivity::class.java)
//      intent.putExtra("buttonColor",buttonColor)
//      intent.putExtra("color",color)
//      intent.putExtra("title",title)
//      intent.putExtra("maxCount",maxCount)
////      intent.putExtra("result",result)
//      mActivity?.get()?.startActivity(intent)
      ImagePicker.getInstance()
              .changeButtonColor(buttonColor)
              .changeColor(color)//设置上下颜色
              .setTitle(title)//设置标题
              .showCamera(true)//设置是否显示拍照按钮
              .showImage(true)//设置是否展示图片
              .showVideo(false)//设置是否展示视频
              .filterGif(false)//设置是否过滤gif图片
              .setMaxCount(maxCount)//设置最大选择图片数目(默认为1，单选)
              .setSingleType(true)//设置图片视频不能同时选择
              //                          .setImagePaths(mImagePaths)//设置历史选择记录
              .setImageLoader(new GlideLoader())//设置自定义图片加载器
              .start(mActivity.get(), 2357);

    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
    channel = null;
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    mActivity = new WeakReference<>(binding.getActivity());
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {
    mActivity = null;
  }
}
