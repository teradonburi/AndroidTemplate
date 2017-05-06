package com.example.daiki.androidtemplate.inject;

import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleModule;
import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleComponent;
import com.example.daiki.androidtemplate.inject.request.RequestComponent;
import com.example.daiki.androidtemplate.inject.request.RequestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daiki on 2017/04/28.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    // Activity、Fragment関連のコンポーネント
    LifecycleComponent lifecycleComponent(
            LifecycleModule lifecycleModule
    );

    // 通信コンポーネント
    RequestComponent requestComponent(
            RequestModule requestModule
    );


}