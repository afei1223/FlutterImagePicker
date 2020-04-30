package com.lcw.library.imagepicker;

import android.app.Activity;
import android.content.Intent;

import com.lcw.library.imagepicker.activity.ImagePickerActivity;
import com.lcw.library.imagepicker.manager.ConfigManager;
import com.lcw.library.imagepicker.utils.ImageLoader;

import java.util.ArrayList;

/**
 * 统一调用入口
 * Create by: chenWei.li
 * Date: 2018/8/26
 * Time: 下午6:31
 * Email: lichenwei.me@foxmail.com
 */
public class ImagePicker {

    public static final String EXTRA_SELECT_IMAGES = "selectItems";

    private static volatile com.lcw.library.imagepicker.ImagePicker mImagePicker;

    private ImagePicker() {
    }

    /**
     * 个人添加
     *
     * @param color
     * @return
     * 根据颜色切换
     */
    public com.lcw.library.imagepicker.ImagePicker changeColor(String color){
        ConfigManager.getInstance().setChangeColor(color);
        return mImagePicker;
    }

    public com.lcw.library.imagepicker.ImagePicker changeButtonColor(String color){
        ConfigManager.getInstance().setChangeButtonColor(color);
        return mImagePicker;
    }

    /**
     * 创建对象
     *
     * @return
     */
    public static com.lcw.library.imagepicker.ImagePicker getInstance() {
        if (mImagePicker == null) {
            synchronized (com.lcw.library.imagepicker.ImagePicker.class) {
                if (mImagePicker == null) {
                    mImagePicker = new com.lcw.library.imagepicker.ImagePicker();
                }
            }
        }
        return mImagePicker;
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker setTitle(String title) {
        ConfigManager.getInstance().setTitle(title);
        return mImagePicker;
    }

    /**
     * 是否支持相机
     *
     * @param showCamera
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker showCamera(boolean showCamera) {
        ConfigManager.getInstance().setShowCamera(showCamera);
        return mImagePicker;
    }

    /**
     * 是否展示图片
     *
     * @param showImage
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker showImage(boolean showImage) {
        ConfigManager.getInstance().setShowImage(showImage);
        return mImagePicker;
    }

    /**
     * 是否展示视频
     *
     * @param showVideo
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker showVideo(boolean showVideo) {
        ConfigManager.getInstance().setShowVideo(showVideo);
        return mImagePicker;
    }

    /**
     * 是否过滤GIF图片(默认不过滤)
     *
     * @param filterGif
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker filterGif(boolean filterGif) {
        ConfigManager.getInstance().setFilterGif(filterGif);
        return mImagePicker;
    }


    /**
     * 图片最大选择数
     *
     * @param maxCount
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker setMaxCount(int maxCount) {
        ConfigManager.getInstance().setMaxCount(maxCount);
        return mImagePicker;
    }

    /**
     * 设置单类型选择（只能选图片或者视频）
     *
     * @param isSingleType
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker setSingleType(boolean isSingleType) {
        ConfigManager.getInstance().setSingleType(isSingleType);
        return mImagePicker;
    }


    /**
     * 设置图片加载器
     *
     * @param imageLoader
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker setImageLoader(ImageLoader imageLoader) {
        ConfigManager.getInstance().setImageLoader(imageLoader);
        return mImagePicker;
    }

    /**
     * 设置图片选择历史记录
     *
     * @param imagePaths
     * @return
     */
    public com.lcw.library.imagepicker.ImagePicker setImagePaths(ArrayList<String> imagePaths) {
        ConfigManager.getInstance().setImagePaths(imagePaths);
        return mImagePicker;
    }

    /**
     * 启动
     *
     * @param activity
     */
    public void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ImagePickerActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

}
