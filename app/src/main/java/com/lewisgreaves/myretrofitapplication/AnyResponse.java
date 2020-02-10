package com.lewisgreaves.myretrofitapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by @Mayakovsky28 on 09 02 2020.
 */
public class AnyResponse {

    @SerializedName("results")
    private ArrayList<Story> results;

    public ArrayList<Story> getResults() {
        return results;
    }

    public void setResults(ArrayList<Story> results) {
        this.results = results;
    }
}