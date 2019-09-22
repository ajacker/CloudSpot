package com.res.cloudspot;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class MainApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        QMUISwipeBackActivityManager.init(this);
    }
}
