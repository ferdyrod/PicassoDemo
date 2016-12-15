package com.ferdyrodriguez.picassodemo.model;

import java.util.ArrayList;

/**
 * Created by ferdyrod on 12/7/16.
 */
public class Characters extends MarvelData<Character> {

    public ArrayList<Character> getAllCharacters() {
        return getResults();
    }

}
