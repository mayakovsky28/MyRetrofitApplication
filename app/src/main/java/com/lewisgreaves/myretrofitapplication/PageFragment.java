package com.lewisgreaves.myretrofitapplication;

/*
 * Created by @Mayakovsky28 on 15 02 2020.
 */

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private StoryService storyService = StoryService.retrofit.create(StoryService.class);
    private List<Story> stories = new ArrayList<>();

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(String sectionName) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(constants.KEY_SECTION_NAME, sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = result.findViewById(R.id.my_recycler_view);

        myAdapter = new MyAdapter(stories);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        MyAdapter adapter = new MyAdapter(stories);
        recyclerView.setAdapter(adapter);

        ApiRequest.callMostPopularStories().enqueue(new Callback<AnyResponse>() {

            @Override
            public void onResponse(Call<AnyResponse> call, Response<AnyResponse> response) {
                stories.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AnyResponse> call, Throwable t) {
                Log.d("ERROR", t.toString());
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

        return result;
    }
}