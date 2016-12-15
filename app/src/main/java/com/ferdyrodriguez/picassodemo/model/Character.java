package com.ferdyrodriguez.picassodemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 12/9/16.
 */

public class Character {

    @SerializedName("name") private String name;
    @SerializedName("thumbnail") private InfoImage thumbnail;

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail.getFullPath();
    }

}
