package com.example.daiki.androidtemplate.inject.request;

import com.example.daiki.androidtemplate.BuildConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daiki on 2017/04/28.
 */

@Module
public class RequestModule {

    private Interceptor interceptor;
    private String serverUrl;

    public RequestModule(String serverUrl,Interceptor interceptor){
        this.interceptor = interceptor;
        this.serverUrl = serverUrl;
    }

    @Request
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(BuildConfig.CONNECTION_TIMEOUT,TimeUnit.MILLISECONDS)
                .build();
    }

    @Request
    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

}
