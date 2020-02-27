package com.lewisgreaves.myretrofitapplication;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * Created by @Mayakovsky28 on 02 02 2020.
 */

public interface StoryService {

    String BASEURL = "https://api.nytimes.com/svc/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("mostpopular/v2/shared/{period}/")
    Call<AnyResponse> getMostPopularStories(@Path("period") int period, @Query("api-key") String apiKey);

    @GET("topstories/v2/{section}/")
    Call<AnyResponse> getTopStories(@Path("section") String section, @Query("api-key") String apiKey);
}
