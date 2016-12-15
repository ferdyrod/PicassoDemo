package com.ferdyrodriguez.picassodemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 12/9/16.
 */
public class InfoImage {

    private String DOT = ".";

    @SerializedName("path")
    public String path;

    @SerializedName("extension")
    public String extension;


    public String getFullPath(){
        return path + DOT + extension;
    }
}
