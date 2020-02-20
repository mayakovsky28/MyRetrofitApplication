package com.lewisgreaves.myretrofitapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView activityMainTextView;
    private String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), Collections.emptyList()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        activityMainTextView = findViewById(R.id.activity_main_text_view);

        ApiRequest.callMostPopularStories().enqueue(new Callback<AnyResponse>() {

            @Override
            public void onResponse(Call<AnyResponse> call, Response<AnyResponse> response) {
                if(!response.isSuccessful()) {
                    activityMainTextView.setText("code: " + response.code());
                    return;
                }

                List<Story> stories = response.body().getResults();
                viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), stories));
            }

            @Override
            public void onFailure(Call<AnyResponse> call, Throwable t) {
                activityMainTextView.setText(t.getMessage());
            }
        });

//        ApiRequest.callTopStories(section).enqueue(new Callback<AnyResponse>() {
//            @Override
//            public void onResponse(Call<AnyResponse> call, Response<AnyResponse> response) {
//                if(!response.isSuccessful()) {
//                    activityMainTextView.setText("code: " +response.code());
//                    return;
//                }
//                List<Story> stories = response.body().getResults();
//                viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), stories));
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                activityMainTextView.setText(t.getMessage());
//
//            }
//        });
    }

}