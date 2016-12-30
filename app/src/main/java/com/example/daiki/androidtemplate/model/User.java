package com.example.daiki.androidtemplate.model;


import android.os.Bundle;

import org.parceler.Parcel;
import org.parceler.Parcels;

import icepick.Bundler;

@Parcel
public class User {

    String name;
    int age;

    public User() {}

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }


}
