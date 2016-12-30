package com.example.daiki.androidtemplate.event;

import android.os.Handler;
import android.support.v4.app.Fragment;

import com.example.daiki.androidtemplate.event.BackEvent;
import com.example.daiki.androidtemplate.event.MessageEvent;
import com.example.daiki.androidtemplate.event.MoveEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by daiki on 2016/12/04.
 */

public class Presenter {
    public Presenter() {
    }

    public void post(final String msg) {

        new Handler().postDelayed(() -> {
            EventBus.getDefault().post(new MessageEvent(msg));
        }, 3000);
    }

    public void move(Fragment fragment) {
        EventBus.getDefault().post(new MoveEvent(fragment));
    }

    public void back(){
        EventBus.getDefault().post(new BackEvent());
    }

}
