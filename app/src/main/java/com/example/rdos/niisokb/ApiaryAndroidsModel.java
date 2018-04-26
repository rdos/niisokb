package com.example.rdos.niisokb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiaryAndroidsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img")
    @Expose
    private String img;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

}
