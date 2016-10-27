package com.lee.httpsrequest;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by AA on 2016/10/27.
 */

public class AppApcation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
    }
}
