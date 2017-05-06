package com.example.daiki.androidtemplate.inject.lifecycle;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daiki on 2017/04/28.
 */

@Module
public class LifecycleModule {

    private AppCompatActivity activity;

    public LifecycleModule(AppCompatActivity activity){
        this.activity = activity;
    }

    @Lifecycle
    @Provides
    AppCompatActivity appCompatActivity(){
        return activity;
    }
}
