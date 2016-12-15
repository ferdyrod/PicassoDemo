package com.ferdyrodriguez.picassodemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ferdyrod on 12/7/16.
 */

public class MarvelResponse<T> {

    @SerializedName("data")
    private T response;

    public T getResponse() {
        return response;
    }

}
