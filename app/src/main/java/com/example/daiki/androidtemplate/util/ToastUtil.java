package com.example.daiki.androidtemplate.util;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/04/28.
 */

@Lifecycle
public class ToastUtil {

    private final AppCompatActivity activity;

    @Inject
    public ToastUtil(
            AppCompatActivity activity
    ){
        this.activity = activity;
    }

    public void show(String text){
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
    }

}
