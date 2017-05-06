package com.example.daiki.androidtemplate.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.daiki.androidtemplate.BR;
import com.example.daiki.androidtemplate.entity.UserEntity;
import com.example.daiki.androidtemplate.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/05/05.
 */

public class MainFragmentViewModel extends BaseObservable {

    private UserEntity userEntity;
    private ToastUtil toastUtil;

    @Inject
    public MainFragmentViewModel(ToastUtil toastUtil){
        this.toastUtil = toastUtil;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
        if(this.userEntity != null){
            notifyPropertyChanged(BR.thumbnail);
            notifyPropertyChanged(BR.name);
            notifyPropertyChanged(BR.email);
        }
    }

    @Bindable
    public String getThumbnail(){
        return this.userEntity != null ? this.userEntity.thumbnail : "";
    }

    @Bindable
    public String getName(){
        return this.userEntity != null ? this.userEntity.name : "";
    }

    @Bindable
    public String getEmail(){
        return this.userEntity != null ? this.userEntity.email : "";
    }


}
