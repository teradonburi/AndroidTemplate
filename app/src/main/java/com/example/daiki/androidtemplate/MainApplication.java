package com.example.daiki.androidtemplate;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.daiki.androidtemplate.api.InterceptorBuilder;
import com.example.daiki.androidtemplate.inject.AppComponent;
import com.example.daiki.androidtemplate.inject.AppModule;
import com.example.daiki.androidtemplate.inject.DaggerAppComponent;
import com.example.daiki.androidtemplate.inject.request.RequestComponent;
import com.example.daiki.androidtemplate.inject.request.RequestModule;
import com.example.daiki.androidtemplate.service.LeakUploadService;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by daiki on 2017/04/28.
 */

public class MainApplication extends MultiDexApplication {

    // Applicatonで一意なインスタンス群
    private static AppComponent appComponent;
    private static RequestComponent requestComponent;

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

        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            installLeakCanary(this);
        }


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        requestComponent = appComponent.requestComponent(
                new RequestModule(BuildConfig.SERVER_URL, InterceptorBuilder.build()));

    }

    public static AppComponent getComponent() {
        return appComponent;
    }

    public static RequestComponent getRequestComponent() {
        return requestComponent;
    }


}