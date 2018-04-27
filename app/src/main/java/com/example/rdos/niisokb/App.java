package com.example.rdos.niisokb;

import android.app.Application;

public class App extends Application {

    public static RestMan restMan;

    @Override
    public void onCreate() {
        super.onCreate();
        restMan = new RestMan(getBaseContext());
    }
}
