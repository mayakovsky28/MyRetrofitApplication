package com.lewisgreaves.myretrofitapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), Collections.emptyList()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        this.configureDrawerLayout();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.activity_main_drawer_news:
//                this.showFragment(FRAGMENT_NEWS);
//                break;
//            case R.id.activity_main_drawer_profile:
//                this.showFragment();
//                break;
//            case R.id.activity_main_drawer_settings:
//                this.showFragment(FRAGMENT_SETTINGS);
//                break;
//            default:
//                break;
//
//        }

//        this.drawerLayout.closeDrawer(GravityCompat.START);
//
//        return true;
//   }

        private void configureDrawerLayout () {
            this.drawerLayout = findViewById(R.id.activity_main_drawer);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

}