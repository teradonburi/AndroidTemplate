package com.example.daiki.androidtemplate.inject;


import com.example.daiki.androidtemplate.MainActivity;
import com.example.daiki.androidtemplate.MainFragment;
import com.example.daiki.androidtemplate.SubFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(SubFragment mainFragment);
}