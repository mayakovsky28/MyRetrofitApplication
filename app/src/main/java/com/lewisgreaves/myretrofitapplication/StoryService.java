package com.lewisgreaves.myretrofitapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
 * Created by @Mayakovsky28 on 02 02 2020.
 */

public interface StoryService {


    @GET("mostpopular/v2/shared/{period}/")
    Call<List<Story>> getStories(@Path("period") int period, @Query("api-key") String apiKey);
}
