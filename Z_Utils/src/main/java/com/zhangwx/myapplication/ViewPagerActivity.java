package com.zhangwx.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import Z_ViewPager.ScaleTransFormer;
import Z_ViewPager.ViewPagerAdapter;

/**
 * Created by zhangwx on 2016/12/21.
 */
public class ViewPagerActivity extends Activity {

    private static final int OFFSCREEN_PAGE_LIMIT = 2;
    private ViewPager mViewPager;
    private GestureDetector mGestureDetector;
    private LinearLayout mPagerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = $(R.id.ViewPager);
        mPagerLayout = $(R.id.pagerLayout);
        mViewPager.setAdapter(new ViewPagerAdapter(this));
        mViewPager.setPageTransformer(false, new ScaleTransFormer());
        mViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);


        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {


                return super.onSingleTapUp(e);
            }
        });

    }

    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }

}
