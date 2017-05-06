package com.example.daiki.androidtemplate.api;

import com.google.gson.JsonObject;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by daiki on 2017/05/05.
 */

public interface UserApiClient {

    @GET("api")
    Flowable<JsonObject> getUser();

}
