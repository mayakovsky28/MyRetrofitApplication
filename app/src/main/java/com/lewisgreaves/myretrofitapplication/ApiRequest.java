package com.lewisgreaves.myretrofitapplication;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by @Mayakovsky28 on 15 02 2020.
 */
public class ApiRequest {

    public static Call callMostPopularStories() {

        String BASEURL = "https://api.nytimes.com/svc/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoryService storyService = retrofit.create(StoryService.class);
        String APIKEY = "y40F98cVAqyMCBFpoq3gdvGAdGXQjj61";
        Call<AnyResponse> call = storyService.getMostPopularStories(1, APIKEY);
        return call;
    }

    public static Call callTopStories(String section) {
        String BASEURL = "https://api.nytimes.com/svc/";
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    StoryService storyService = retrofit.create(StoryService.class);
    String APIKEY = "y40F98cVAqyMCBFpoq3gdvGAdGXQjj61";
    Call<AnyResponse> call = storyService.getTopStories(section, APIKEY);
        return call;
    }
}
