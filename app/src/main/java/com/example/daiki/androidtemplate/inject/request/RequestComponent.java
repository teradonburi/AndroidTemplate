package com.example.daiki.androidtemplate.inject.request;

import com.example.daiki.androidtemplate.api.UserApiUsecase;

import dagger.Subcomponent;

/**
 * Created by daiki on 2017/05/05.
 */


@Request
@Subcomponent(modules = {RequestModule.class})
public interface RequestComponent {

    // 取得するAPI UseCaseを記述
    UserApiUsecase userApiUsecase();
}
