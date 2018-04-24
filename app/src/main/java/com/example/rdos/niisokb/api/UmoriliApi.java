package com.example.rdos.niisokb.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.rdos.niisokb.PostModel;

/**
 * Created by misha on 31.10.2016.
 */
public interface UmoriliApi {

    @GET("/api/get")
    Call<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int count);
}
