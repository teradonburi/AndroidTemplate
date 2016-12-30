package com.example.daiki.androidtemplate.inject;

/**
 * Created by daiki on 2016/11/27.
 */


import android.content.Context;

import com.example.daiki.androidtemplate.event.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context appContext;
    private Presenter presenter;

    public AppModule(Context context) {
        this.appContext = context;
        this.presenter = new Presenter();
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    Presenter providePresenter() {
        return presenter;
    }

}