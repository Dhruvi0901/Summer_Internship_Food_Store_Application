package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class msgActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        getSupportActionBar().hide();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
        tabLayout = findViewById(R.id.chat_tab);
        viewPager = findViewById(R.id.chat_pager);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        viewPager.setAdapter(new MessageAdapter(getSupportFragmentManager()));
    }
    private class MessageAdapter extends FragmentPagerAdapter {
        public MessageAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
}
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Chat";
                case 1: return "Status";
                case 2: return "Call";

            }
            return super.getPageTitle(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new ChatFragment();
                case 1: return new StatusFragment();
                case 2: return new CallFragment();
            }
            return null;
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}