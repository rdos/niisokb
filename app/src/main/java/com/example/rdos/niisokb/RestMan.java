package com.example.rdos.niisokb;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.rdos.niisokb.api.ApiaryApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class RestMan {
    private final ApiaryApi mApiaryApi;
    private final Context mContext;
    private Callback mCallback;
    List<ApiaryAndroidsModel> mApiaryAndroids;

    public RestMan(Context context) {
        super();
        Log.i("RestMan", "super");
        mContext = context;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-db05-jsontest111.apiary-mock.com")
//                .baseUrl("http://thr.name")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mApiaryApi = retrofit.create(ApiaryApi.class);
        mApiaryAndroids = new ArrayList<>();
    }

    public void sendAndroids() {
        Log.i("RestMan", "sendAndroids");
        mApiaryApi.getData().enqueue(new retrofit2.Callback<List<ApiaryAndroidsModel>>() {
            @Override
            public void onResponse(Call<List<ApiaryAndroidsModel>> call, Response<List<ApiaryAndroidsModel>> response) {
                Log.i("RestMan", "sendAndroids.onResponse");
                mApiaryAndroids.addAll(response.body());
                mCallback.onResponse();
            }

            @Override
            public void onFailure(Call<List<ApiaryAndroidsModel>> call, Throwable t) {
                Log.i("RestMan", "sendAndroids.onFailure");
                Toast.makeText(mContext, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCallBack(Callback callback) {
        Log.i("RestMan", "setCallBack");
        mCallback = callback;
    }

    private List<ApiaryAndroidsModel> getAndroids() {
        Log.i("RestMan", "getAndroids");
        return mApiaryAndroids;
    }

    public String getAndroidsTitle(int position) {
        return mApiaryAndroids.get(position).getTitle();
    }

    public String getAndroidsImg(int position) {
        return mApiaryAndroids.get(position).getImg();
    }

    public int getAndroidsCount() {
        if (mApiaryAndroids == null) {
            return 0;
        }
        return mApiaryAndroids.size();
    }

    public interface Callback {
        void onResponse();
//        void onTimerFinish();
    }

}
