package com.ferdyrodriguez.picassodemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ferdyrod on 12/7/16.
 */

public class MarvelData<T>{

    @SerializedName("results") private ArrayList<T> results = new ArrayList<>();

    protected ArrayList<T> getResults() {
        return results;
    }

}
