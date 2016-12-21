package com.zhangwx.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import Z_ViewPager.ScaleTransFormer;
import Z_ViewPager.ViewPagerAdapter;

/**
 * Created by Administrator on 2016/12/21.
 */
public class ViewPagerActivity extends Activity {

    private ViewPager mViewPager;
    private static final int OFFSCREEN_PAGE_LIMIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = $(R.id.ViewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(this));
        mViewPager.setPageTransformer(false, new ScaleTransFormer());
        mViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    }

    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }

}
