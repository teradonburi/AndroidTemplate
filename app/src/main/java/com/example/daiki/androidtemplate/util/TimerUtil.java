package com.example.daiki.androidtemplate.util;

import android.os.Handler;

/**
 * Created by daiki on 2017/05/05.
 */

public class TimerUtil {

    private final static int INFINITE = -1;
    private int requestInterval;
    private int loopCount;
    private Handler handler;
    private Runnable runnable;
    private Callback callback;

    public interface Callback{
        void execute();
    }


    public TimerUtil(Callback callback, int requestInterval){
        this(callback,requestInterval,INFINITE);
    }
    public TimerUtil(Callback callback, int requestInterval, int loopCount){
        this.requestInterval = requestInterval;
        this.loopCount = loopCount;
        this.callback = callback;

        handler = new Handler();
        runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if(loopCount == INFINITE){
                    if(callback != null){
                        callback.execute();
                    }
                }else{
                    if(count < loopCount){
                        if(callback != null){
                            callback.execute();
                        }
                    }else{
                        stopTimer();
                        return;
                    }
                    count++;
                }
                handler.postDelayed(this, requestInterval);
            }
        };
    }

    public void startTimer(){
        handler.post(runnable);
    }

    public void stopTimer(){
        handler.removeCallbacks(runnable);
    }

}
