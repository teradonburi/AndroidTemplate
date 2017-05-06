package com.example.daiki.androidtemplate.api;

import com.example.daiki.androidtemplate.entity.User;
import com.example.daiki.androidtemplate.inject.request.Request;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by daiki on 2017/05/05.
 */

@Request
public class UserApiUsecase {


    private Retrofit retrofit;

    @Inject
    public UserApiUsecase(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<User> getUser(){
        UserApiClient userApiClient = retrofit.create(UserApiClient.class);
        return userApiClient.getUser()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(obj ->{

                    JsonObject result = obj.get("results").getAsJsonArray().get(0).getAsJsonObject();
                    String gender = result.get("gender").getAsString();
                    String name = result.get("name").getAsJsonObject()
                            .get("title").getAsString();
                    String email = result.get("email").getAsString();
                    String phone = result.get("phone").getAsString();
                    String thumbnail = result.get("picture").getAsJsonObject()
                            .get("large").getAsString();

                    User user = new User();
                    user.gender = User.Gender.getGender(gender);
                    user.name = name;
                    user.email = email;
                    user.phone = phone;
                    user.thumbnail = thumbnail;
                    return user;
                }); // UIスレッドに渡す
    }

}
