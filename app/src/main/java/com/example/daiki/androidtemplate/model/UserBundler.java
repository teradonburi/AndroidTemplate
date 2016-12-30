package com.example.daiki.androidtemplate.model;

import android.os.Bundle;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Created by daiki on 2016/12/04.
 */

// Icepick
public class UserBundler implements Bundler<User> {
    @Override
    public void put(String s, User example, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(example));
    }

    @Override
    public User get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}