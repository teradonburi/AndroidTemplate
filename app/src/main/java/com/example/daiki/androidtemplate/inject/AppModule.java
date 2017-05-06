package com.example.daiki.androidtemplate.inject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daiki on 2017/04/28.
 */

@Module
public class AppModule {
    private static Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApp() {
        return app;
    }

    @AppContext
    @Provides
    @Singleton
    Context provideAppContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(@AppContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }


}