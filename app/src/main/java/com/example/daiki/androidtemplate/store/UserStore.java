package com.example.daiki.androidtemplate.store;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.daiki.androidtemplate.entity.UserEntity;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by daiki on 2017/05/06.
 */

@Singleton
public class UserStore {

    private static final String KEY = UserStore.class.getSimpleName();
    private Gson gson;
    private SharedPreferences sharedPreferences;

    @Inject
    public UserStore(Gson gson,SharedPreferences sharedPreferences){
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void save(UserEntity userEntity){
        String json = this.gson.toJson(userEntity);
        this.sharedPreferences.edit().putString(KEY,json).commit();
    }

    public UserEntity fetch(){
        String json = this.sharedPreferences.getString(KEY,"");
        if(!TextUtils.isEmpty(json)){
           return this.gson.fromJson(json,UserEntity.class);
        }
        return null;
    }

}
