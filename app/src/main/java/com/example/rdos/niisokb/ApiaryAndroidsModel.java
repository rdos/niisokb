package com.example.rdos.niisokb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiaryAndroidsModel {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("img")
    @Expose
    private String mImg;

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImg() {
        return mImg;
    }

}
