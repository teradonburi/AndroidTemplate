package com.example.daiki.androidtemplate.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by daiki on 2017/04/28.
 */

public class InterceptorBuilder {

    public static Interceptor build(){
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // リクエスト共通パラメータ追加
//                KurashiruUtil.FirstLaunched firstLaunched = KurashiruUtil.getFirstLaunchedAt(appContext);
//                String firstLaunchedStr = KurashiruUtil.convertDateToString(firstLaunched.date);
//                HttpUrl url = original.url().newBuilder().addQueryParameter("first_launched_at",firstLaunchedStr).build();
//                Request request = original.newBuilder().url(url).build();
//                return chain.proceed(request);
                return chain.proceed(original);
            }
        };
    }
}
