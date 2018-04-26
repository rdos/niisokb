package com.example.rdos.niisokb.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.rdos.niisokb.ApiaryAndroidsModel;

/**
 * Created by misha on 31.10.2016.
 */
public interface ApiaryApi {

    @GET("/androids")
    Call<List<ApiaryAndroidsModel>> getData();
}
