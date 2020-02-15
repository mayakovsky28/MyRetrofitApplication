package com.lewisgreaves.myretrofitapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/*
 * Created by @Mayakovsky28 on 15 02 2020.
 */
public class PageAdapter extends FragmentPagerAdapter {

    private List<Story> stories;

    public PageAdapter(FragmentManager fragmentManager, List<Story> stories) {
        super(fragmentManager);
        this.stories = stories;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(this.stories));
    }
}