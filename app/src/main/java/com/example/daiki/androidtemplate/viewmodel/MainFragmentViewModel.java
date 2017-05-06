package com.example.daiki.androidtemplate.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.daiki.androidtemplate.BR;
import com.example.daiki.androidtemplate.entity.User;
import com.example.daiki.androidtemplate.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/05/05.
 */

public class MainFragmentViewModel extends BaseObservable {

    private User user;
    private ToastUtil toastUtil;

    @Inject
    public MainFragmentViewModel(ToastUtil toastUtil){
        this.toastUtil = toastUtil;
    }

    public void setUser(User user){
        this.user = user;
        if(this.user != null){
            notifyPropertyChanged(BR.thumbnail);
            notifyPropertyChanged(BR.name);
            notifyPropertyChanged(BR.email);
        }
    }

    @Bindable
    public String getThumbnail(){
        return this.user != null ? this.user.thumbnail : "";
    }

    @Bindable
    public String getName(){
        return this.user != null ? this.user.name : "";
    }

    @Bindable
    public String getEmail(){
        return this.user != null ? this.user.email : "";
    }


}
