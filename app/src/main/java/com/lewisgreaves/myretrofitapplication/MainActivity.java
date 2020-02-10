package com.lewisgreaves.myretrofitapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView activityMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainTextView = findViewById(R.id.activity_main_text_view);

        String BASEURL = "https://api.nytimes.com/svc/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoryService storyService = retrofit.create(StoryService.class);
        String APIKEY = "y40F98cVAqyMCBFpoq3gdvGAdGXQjj61";
        Call<AnyResponse> call = storyService.getMostPopularStories(1, APIKEY);
        Call<AnyResponse> call1 = storyService.getTopStories("home", APIKEY);

        call.enqueue(new Callback<AnyResponse>() {

            @Override
            public void onResponse(Call<AnyResponse> call, Response<AnyResponse> response) {
                if(!response.isSuccessful()) {
                    activityMainTextView.setText("code: " + response.code());
                    return;
                }

                List<Story> stories = response.body().getResults();

                for(Story story : stories) {
                    String content = "";
                    content += "Title: " + story.getTitle() + "\n";
                    content += "By Line: " + story.getByLine() + "\n";
                    content += "URL: " + story.getUrl() + "\n";
                    content += "Abstract: " + story.getBriefDescription() + "\n";
                    content += "Published date: " + story.getPublishedDate() + "\n\n";
                    activityMainTextView.append(content);
                }
            }



            @Override
            public void onFailure(Call<AnyResponse> call, Throwable t) {
                activityMainTextView.setText(t.getMessage());
            }
        });
    }
}
