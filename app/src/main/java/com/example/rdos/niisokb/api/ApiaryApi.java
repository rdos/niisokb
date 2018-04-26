package com.example.rdos.niisokb.api;

import com.example.rdos.niisokb.ApiaryAndroidsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiaryApi {

    @GET("/androids")
    Call<List<ApiaryAndroidsModel>> getData();
}
