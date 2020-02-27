package com.lewisgreaves.myretrofitapplication;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/*
 * Created by @Mayakovsky28 on 09 02 2020.
 */
public class AnyResponse {

    @SerializedName("results")
    private List<Story> results;

    public List<Story> getResults() {
        return results;
    }

}
