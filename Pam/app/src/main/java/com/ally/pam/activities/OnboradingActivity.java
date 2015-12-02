package com.ally.pam.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Gallery.LayoutParams;

import com.ally.pam.R;
import com.ally.pam.fragment.OnboardingFragment;
import com.ally.pam.fragment.SlidePageFragment;

/**
 * Created by Ally on 12/2/2015.
 */
public class OnboradingActivity extends FragmentActivity {

    private Button btnStart;

    private ImageView ivFist;
    private ImageView ivSecond;

    private ViewPager viewPager;
    private PagerAdapter mPagerAdapter;

    private int NUM_PAGES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        btnStart = (Button) findViewById(R.id.start_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboradingActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivFist = (ImageView) findViewById(R.id.onbording_first);
        ivSecond = (ImageView) findViewById(R.id.onbording_second);

        viewPager = (ViewPager) findViewById(R.id.onboarding_viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ivFist.setImageResource(R.mipmap.onboarding_select);
                        ivSecond.setImageResource(R.mipmap.onboarding_unselect);
                        break;
                    case 1:
                        ivSecond.setImageResource(R.mipmap.onboarding_select);
                        ivFist.setImageResource(R.mipmap.onboarding_unselect);
                        break;
                }
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return OnboardingFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
