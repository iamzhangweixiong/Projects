package com.zhangwx.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import Z_UI.ViewUtils;
import Z_ViewPager.ViewPagerAdapter;
import Z_ViewPager.transformer.ScaleTransFormer;

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

        mViewPager = ViewUtils.$(this, R.id.ViewPager);
        mPagerLayout = ViewUtils.$(this, R.id.pagerLayout);
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


}
