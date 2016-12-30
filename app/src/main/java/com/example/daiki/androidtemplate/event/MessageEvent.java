package com.example.daiki.androidtemplate.event;

import com.example.daiki.androidtemplate.event.BaseEvent;

/**
 * Created by daiki on 2016/12/04.
 */

public class MessageEvent implements BaseEvent {
    public String msg;

    public MessageEvent(String msg){
        this.msg = msg;
    }
}
