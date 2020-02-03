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
        Call<List<Story>> call = storyService.getStories(1, APIKEY);

        call.enqueue(new Callback<List<Story>>() {

            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if(!response.isSuccessful()) {
                    activityMainTextView.setText("code: " + response.code());
                    return;
                }

                List<Story> stories = response.body();

                for(Story story : stories) {
                    String content = "";
                    content += "Title: " + story.getTitle() + "\n";
                    content += "By Line: " + story.getByLine() + "\n\n";
                    activityMainTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                activityMainTextView.setText(t.getMessage());
            }
        });
    }
}
