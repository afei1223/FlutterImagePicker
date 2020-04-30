package com.lcw.library.imagepicker.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import androidx.annotation.NonNull;

/**
 * 公用线程池，执行一些耗时操作
 * Create by: chenWei.li
 * Date: 2018/8/24
 * Time: 下午7:53
 * Email: lichenwei.me@foxmail.com
 */
public class CommonExecutor {

    private static volatile com.lcw.library.imagepicker.executors.CommonExecutor mCommonExecutor;
    private ExecutorService mExecutorService;

    private CommonExecutor() {
        mExecutorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("CommonExecutor");
                return thread;
            }
        });
    }

    public static com.lcw.library.imagepicker.executors.CommonExecutor getInstance() {
        if (mCommonExecutor == null) {
            synchronized (com.lcw.library.imagepicker.executors.CommonExecutor.class) {
                if (mCommonExecutor == null) {
                    mCommonExecutor = new com.lcw.library.imagepicker.executors.CommonExecutor();
                }
            }
        }
        return mCommonExecutor;
    }


    public void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }

}
