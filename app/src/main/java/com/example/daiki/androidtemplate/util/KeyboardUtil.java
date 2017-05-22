package com.example.daiki.androidtemplate.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.daiki.androidtemplate.inject.AppContext;
import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/05/07.
 */

@Lifecycle
public class KeyboardUtil {

    //     <activity
    //          android:windowSoftInputMode="stateVisible|adjustPan">

    private AppCompatActivity activity;

    @Inject
    public KeyboardUtil(AppCompatActivity activity){
        this.activity = activity;
    }

    // キーボード入力表示
    public void showInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);;
    }

    // キーボード入力非表示
    public void hideInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
