package com.ldq.ltdd_cs92_nhom6_shoesshoppingapp.localdata;

import android.app.Application;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
