package com.levylin.draglayout;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by LinXin on 2017/4/19.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
