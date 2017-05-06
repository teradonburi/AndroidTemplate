package com.example.daiki.androidtemplate.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;

import com.example.daiki.androidtemplate.MainApplication;
import com.example.daiki.androidtemplate.api.UserApiUsecase;
import com.example.daiki.androidtemplate.entity.UserEntity;
import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;
import com.example.daiki.androidtemplate.store.UserStore;
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

    //@State(UserBundler.class)
    //UserEntity mUserEntity;
    private ToastUtil toastUtil;
    private Timer timer;
    private UserStore userStore;

    @Inject
    public MainActivityViewModel(final ToastUtil toastUtil,final UserStore userStore){
        this.toastUtil = toastUtil;
        timer = new Timer(()->{
            fetchUser();
        },10000);
        this.userStore = userStore;
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
                    this.userStore.save(user);
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

    public UserEntity getUser(){
        return this.userStore.fetch();
    }

}
