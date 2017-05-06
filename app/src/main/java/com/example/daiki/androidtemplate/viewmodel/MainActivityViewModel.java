package com.example.daiki.androidtemplate.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;

import com.example.daiki.androidtemplate.MainApplication;
import com.example.daiki.androidtemplate.api.UserApiUsecase;
import com.example.daiki.androidtemplate.entity.User;
import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;
import com.example.daiki.androidtemplate.util.Timer;
import com.example.daiki.androidtemplate.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import icepick.Icepick;
import icepick.State;
import com.example.daiki.androidtemplate.entity.icepick.UserBundler;

/**
 * Created by daiki on 2017/04/28.
 */

@Lifecycle
public class MainActivityViewModel extends BaseObservable{

    @State(UserBundler.class)
    User mUser;
    private ToastUtil toastUtil;
    private Timer timer;

    @Inject
    public MainActivityViewModel(final ToastUtil toastUtil){
        this.toastUtil = toastUtil;
        timer = new Timer(()->{
            fetchUser();
        },10000);
    }

    public void startTimer(){
        timer.startTimer();
    }

    public void stopTimer(){
        timer.stopTimer();
    }


    public void fetchUser(){

        UserApiUsecase userApi = MainApplication.getRequestComponent().userApiUsecase();
        userApi.getUser()
                .subscribe(user -> {
                    mUser = user;  // 永続化してしまう方がより良い
                    // ユーザ更新イベント送信
                    EventBus.getDefault().post(user);
                },throwable -> {
                    toastUtil.show(throwable.toString());
                });

    }

    public void saveState(Bundle outState){
        Icepick.saveInstanceState(this, outState);
    }

    public void restoreState(Bundle savedInstanceState){
        if(savedInstanceState != null){
            Icepick.restoreInstanceState(this,savedInstanceState);
        }
    }

    public User getUser(){
        return mUser;
    }

}
