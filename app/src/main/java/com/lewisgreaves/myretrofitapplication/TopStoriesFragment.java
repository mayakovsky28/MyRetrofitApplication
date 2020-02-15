package com.lewisgreaves.myretrofitapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopStoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView topStoriesFragmentTextView;

    public TopStoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopStoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopStoriesFragment newInstance(String param1, String param2) {
        TopStoriesFragment fragment = new TopStoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            topStoriesFragmentTextView = getView().findViewById(R.id.top_stories_fragment_text_view);

            String BASEURL = "https://api.nytimes.com/svc/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            StoryService storyService = retrofit.create(StoryService.class);
            String APIKEY = "y40F98cVAqyMCBFpoq3gdvGAdGXQjj61";
            Call<AnyResponse> call = storyService.getTopStories("home", APIKEY);

            call.enqueue(new Callback<AnyResponse>() {

                @Override
                public void onResponse(Call<AnyResponse> call, Response<AnyResponse> response) {
                    if (!response.isSuccessful()) {
                        topStoriesFragmentTextView.setText("code: " + response.code());
                        return;
                    }

                    List<Story> stories = response.body().getResults();

                    for (Story story : stories) {
                        String content = "";
                        content += "Title: " + story.getTitle() + "\n";
                        content += "By Line: " + story.getByLine() + "\n";
                        content += "URL: " + story.getUrl() + "\n";
                        content += "Abstract: " + story.getBriefDescription() + "\n";
                        content += "Published date: " + story.getPublishedDate() + "\n\n";
                        topStoriesFragmentTextView.append(content);
                    }
                }


                @Override
                public void onFailure(Call<AnyResponse> call, Throwable t) {
                    topStoriesFragmentTextView.setText(t.getMessage());
                }
            });
        }


    }
}

