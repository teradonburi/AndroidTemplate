package com.example.daiki.androidtemplate.event;

/**
 * Created by daiki on 2017/05/21.
 */

public class RefreshEvent {
    private Class target;

    public RefreshEvent(Class target){
        this.target = target;
    }

    public Class getTarget(){
        return this.target;
    }

}
