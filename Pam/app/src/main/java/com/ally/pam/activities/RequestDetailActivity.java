package com.ally.pam.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ally.pam.R;
import com.ally.pam.fragment.SlidePageFragment;

/**
 * Created by Ally on 11/30/2015.
 */
public class RequestDetailActivity extends FragmentActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView first;
    private ImageView second;

    private ViewPager viewPager;
    private PagerAdapter mPagerAdapter;

    private int NUM_PAGES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.request_detail);

        ivBack = (ImageView) findViewById(R.id.back_imageview);
        ivBack.setOnClickListener(this);

        first = (ImageView) findViewById(R.id.first_imageview);
        second = (ImageView) findViewById(R.id.second_imageview);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        first.setImageResource(R.mipmap.point);
                        second.setImageResource(R.mipmap.point_unselect);
                        break;
                    case 1:
                        second.setImageResource(R.mipmap.point);
                        first.setImageResource(R.mipmap.point_unselect);
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
            return SlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back_imageview:
                finish();
                break;
        }
    }
}
