package com.lewisgreaves.myretrofitapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
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

        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), Collections.emptyList()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


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

                viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), stories));
            }



            @Override
            public void onFailure(Call<AnyResponse> call, Throwable t) {
                activityMainTextView.setText(t.getMessage());
            }
        });
    }

}