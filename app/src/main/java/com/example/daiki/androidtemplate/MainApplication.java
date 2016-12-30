package com.example.daiki.androidtemplate;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.daiki.androidtemplate.inject.AppComponent;
import com.example.daiki.androidtemplate.inject.AppModule;
import com.example.daiki.androidtemplate.inject.DaggerAppComponent;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by daiki on 2016/11/27.
 */


public class MainApplication extends MultiDexApplication {

    private static AppComponent component;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    protected RefWatcher installLeakCanary(Application application) {
        // Build a customized RefWatcher
        RefWatcher refWatcher = LeakCanary.refWatcher(application)
                .listenerServiceClass(LeakUploadService.class)
                .excludedRefs(AndroidExcludedRefs.createAppDefaults().build())
                .buildAndInstall();
        return refWatcher;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        StrictMode.enableDefaults();
        installLeakCanary(this);

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();


    }


    public static AppComponent getAppComponent() {
        return component;
    }
}



