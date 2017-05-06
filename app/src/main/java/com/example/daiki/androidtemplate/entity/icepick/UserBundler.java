package com.example.daiki.androidtemplate.entity.icepick;

import android.os.Bundle;

import com.example.daiki.androidtemplate.entity.UserEntity;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Created by daiki on 2017/05/05.
 */

public class UserBundler implements Bundler<UserEntity> {


    @Override
    public void put(String s, UserEntity example, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(example));
    }

    @Override
    public UserEntity get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
