package com.example.daiki.androidtemplate.service;

/**
 * Created by daiki on 2017/05/06.
 */


import android.util.Log;

import com.example.daiki.androidtemplate.BuildConfig;
import com.example.daiki.androidtemplate.R;
import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by daiki on 2016/12/07.
 */

import android.util.Log;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

// upload to slack
public final class LeakUploadService extends DisplayLeakService {


    public interface SlackApi {

        @Multipart
        @POST("/api/files.upload")
        Call<Void> uploadFile(@Part("token") RequestBody token,
                              @Part("file\"; filename=leak.hprof") RequestBody file, // file
                              @Part("filename") RequestBody filename,
                              @Part("title") RequestBody title,
                              @Part("initial_comment") RequestBody initialComment,
                              @Part("channels") RequestBody channels);

    }


    private SlackApi slackApi;
    private String TOKEN;
    private String MEMORY_LEAK_CHANNEL;

    @Override
    public void onCreate() {
        super.onCreate();
        slackApi = new Retrofit.Builder()
                .baseUrl(BuildConfig.SLACK_URL)
                .build()
                .create(SlackApi.class);
        TOKEN = BuildConfig.SLACK_API_TOKEN;
        MEMORY_LEAK_CHANNEL = BuildConfig.SLACK_MEMORY_LEAK_CHANNEL;
    }


    private static String classSimpleName(String className) {
        int separator = className.lastIndexOf('.');
        return separator == -1 ? className : className.substring(separator + 1);
    }

    @Override
    protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {

        // 除外リストは無視する
        if (!result.leakFound || result.excludedLeak) {
            return;
        }
        super.afterDefaultHandling(heapDump, result, leakInfo);


        String name = classSimpleName(result.className);
        if (!heapDump.referenceName.equals("")) {
            name += "(" + heapDump.referenceName + ")";
        }


        String title = name + " has leaked";
        String initialComment = leakInfo;
        Log.d("leakinfo", leakInfo);
        Log.d("heapdump", heapDump.heapDumpFile.toString());
        Call<Void> request = slackApi.uploadFile(RequestBody.create(MediaType.parse("plain/text"), TOKEN),
                RequestBody.create(MediaType.parse("application/octet-stream"), heapDump.heapDumpFile),
                RequestBody.create(MediaType.parse("plain/text"), heapDump.heapDumpFile.getName()),
                RequestBody.create(MediaType.parse("plain/text"), title),
                RequestBody.create(MediaType.parse("plain/text"), initialComment),
                RequestBody.create(MediaType.parse("plain/text"), MEMORY_LEAK_CHANNEL));

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("SendRetrofit", response.toString());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ErrorRetrofit", t.toString());

            }
        });

    }


}