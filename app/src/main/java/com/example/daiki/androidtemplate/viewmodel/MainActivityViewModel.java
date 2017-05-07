package com.example.daiki.androidtemplate.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;

import com.example.daiki.androidtemplate.MainApplication;
import com.example.daiki.androidtemplate.api.UserApiUsecase;
import com.example.daiki.androidtemplate.entity.UserEntity;
import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;
import com.example.daiki.androidtemplate.store.UserStore;
import com.example.daiki.androidtemplate.util.TimerUtil;
import com.example.daiki.androidtemplate.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import icepick.Icepick;

/**
 * Created by daiki on 2017/04/28.
 */

@Lifecycle
public class MainActivityViewModel extends BaseObservable{

    //@State(UserBundler.class)
    //UserEntity mUserEntity;
    private ToastUtil toastUtil;
    private TimerUtil timerUtil;
    private UserStore userStore;

    @Inject
    public MainActivityViewModel(final ToastUtil toastUtil,final UserStore userStore){
        this.toastUtil = toastUtil;
        timerUtil = new TimerUtil(()->{
            fetchUser();
        },10000);
        this.userStore = userStore;


    }

    public void startTimer(){
        timerUtil.startTimer();
    }

    public void stopTimer(){
        timerUtil.stopTimer();
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
