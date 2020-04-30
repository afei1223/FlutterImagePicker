package com.example.flutter_imgae_picker;

import android.app.Application;

/**
 * Create by: chenWei.li
 * Date: 2019/1/24
 * Time: 11:31 AM
 * Email: lichenwei.me@foxmail.com
 */
public class MApplicatio extends Application {

    private static MApplicatio mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MApplicatio getContext() {
        return mApplication;
    }
}
