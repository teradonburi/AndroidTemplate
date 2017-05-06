package com.example.daiki.androidtemplate.entity;

import android.text.TextUtils;

import org.parceler.Parcel;

/**
 * Created by daiki on 2017/05/05.
 */

@Parcel
public class User {

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

    public Gender gender;
    public String name;
    public String email;
    public String phone;
    public String thumbnail;

}
