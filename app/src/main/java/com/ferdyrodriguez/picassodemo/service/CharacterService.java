package com.ferdyrodriguez.picassodemo.service;

import com.ferdyrodriguez.picassodemo.model.Characters;
import com.ferdyrodriguez.picassodemo.model.MarvelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ferdyrod on 12/9/16.
 */

public interface CharacterService {

    @GET("/v1/public/characters")
    Call<MarvelResponse<Characters>> getCharacters(@Query("events") int event);

}
