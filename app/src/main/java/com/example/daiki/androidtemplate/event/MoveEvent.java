package com.example.daiki.androidtemplate.event;

import android.support.v4.app.Fragment;

import com.example.daiki.androidtemplate.event.BaseEvent;

/**
 * Created by daiki on 2016/12/04.
 */

public class MoveEvent implements BaseEvent {
    public Fragment fragment;

    public MoveEvent(Fragment fragment){
        this.fragment = fragment;
    }

}
