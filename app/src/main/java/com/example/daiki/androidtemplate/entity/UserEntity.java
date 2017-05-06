package com.example.daiki.androidtemplate.entity;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;

import org.parceler.Parcel;

/**
 * Created by daiki on 2017/05/05.
 */

@Parcel
public class UserEntity {

    public enum Gender{
        MALE("male"),
        FEMALE("female"),
        UNKNOWN("unknown");

        String gender;
        Gender(String gender){
            this.gender = gender;
        }

        public static Gender getGender(String gender){
            if(TextUtils.equals(MALE.gender,gender)){
                return Gender.MALE;
            }
            else if(TextUtils.equals(FEMALE.gender,gender)){
                return Gender.FEMALE;
            }
            else{
                return Gender.UNKNOWN;
            }
        }
    }

    @Expose
    public Gender gender;
    @Expose
    public String name;
    @Expose
    public String email;
    @Expose
    public String phone;
    @Expose
    public String thumbnail;

}
