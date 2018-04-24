package com.example.rdos.niisokb;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.rdos.niisokb.api.UmoriliApi;

/**
 * Created by misha on 31.10.2016.
 */

public class App extends Application {

    private static UmoriliApi umoriliApi;
    private Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://private-db05-jsontest111.apiary-mock.com/androids")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        umoriliApi = mRetrofit.create(UmoriliApi.class);
    }

    public static UmoriliApi getApi() {
        return umoriliApi;
    }
}
