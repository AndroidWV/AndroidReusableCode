package com.org.wvprojectstructure.utils;
import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref.init(this);
    }
}
