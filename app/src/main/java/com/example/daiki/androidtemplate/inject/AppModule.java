package com.example.daiki.androidtemplate.inject;

/**
 * Created by daiki on 2016/11/27.
 */


import android.content.Context;

import com.example.daiki.androidtemplate.event.Presenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final Context appContext;
    private Presenter presenter;

    public AppModule(Context context) {
        this.appContext = context;
        this.presenter = new Presenter();
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        // リクエスト共通パラメータ追加
                        HttpUrl url = original.url().newBuilder()
                                .addQueryParameter("common_param","hogehoge").build();

                        Request request = original.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                });
        builder.readTimeout(3000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(3000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(3000,TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    @Named("gson")
    public Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    // APIコールモジュール生成（Retriofit）
    @Provides
    @Singleton
    public Retrofit provideRetrofit(@Named("gson") Gson gson, OkHttpClient okHttpClient) {

        final String serverUrl = "https://randomuser.me/";

        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }

}