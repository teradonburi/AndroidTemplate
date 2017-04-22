package com.example.daiki.androidtemplate;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.daiki.androidtemplate.api.CallUserApi;
import com.example.daiki.androidtemplate.event.BackEvent;
import com.example.daiki.androidtemplate.event.BaseEvent;
import com.example.daiki.androidtemplate.event.MoveEvent;
import com.example.daiki.androidtemplate.inject.AppComponent;
import com.example.daiki.androidtemplate.model.User;
import com.example.daiki.androidtemplate.model.UserBundler;

import javax.inject.Inject;

import icepick.Icepick;
import icepick.State;

//import com.eccyan.optional.Optional;

public class MainActivity extends BaseActivity{

    @Inject
    Context appContext;
    @Inject
    CallUserApi userApi;

    @Override
    protected void inject(AppComponent component) {
        component.inject(this);
    }

    FragmentManager fragmentManager;

    @State(UserBundler.class) User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main);

        if(user == null) {
            user = new User(15, "名前");
        }
        else {
            user.setAge(18);
        }

        // 引数の順番は変数のアルファベット順になるので注意
        MainFragment mainFragment = new MainFragmentBuilder(user)
                .build();
        fragmentManager =  getSupportFragmentManager();
        // Backstackイベント
        //fragmentManager.addOnBackStackChangedListener(this);

        userApi.fetchUser()
                .subscribe(response -> {
                    Log.d("param",response.toString());
                },throwable -> {
                    Log.d("error:",throwable.toString());
                });

        if (savedInstanceState == null){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, mainFragment,MainFragment.class.getName())
                    .commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    // 戻るボタンが押された
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    // 画面遷移
    @Override
    public void onEvent(BaseEvent event){

        if(event instanceof MoveEvent){
            MoveEvent moveEvent = (MoveEvent) event;
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, moveEvent.fragment)
                    .addToBackStack(null)
                    .commit();
        }
        else if(event instanceof BackEvent){

            if(fragmentManager.getBackStackEntryCount() > 0){
                fragmentManager.popBackStack();
            }
        }

    }


}
