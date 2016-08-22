package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.view.View;

import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhangwx on 2016/7/29.
 */
public class SnowView implements ICapsuleAnim {


    private boolean mIsEnableAnim = false;
    private float mBaseAlpha = 1f;
    private Random mRandom = new Random();
    private View mParent = null;
    private ArrayList<Snowflake> mSnowflakes = null;
    private int mSmallSnowflakeCount = 10;
    private int mMediumSnowflakeCount = 5;
    private int mBigSnowflakeCount = 3;

    public SnowView(View parent) {
        this.mParent = parent;
        mSnowflakes = new ArrayList<>();
        for (int i = 0; i < mSmallSnowflakeCount; i++) {
            mSnowflakes.add(new Snowflake(mRandom, Snowflake.SMALL_SNOWFLAKE));
        }
        for (int i = 0; i < mMediumSnowflakeCount; i++) {
            mSnowflakes.add(new Snowflake(mRandom, Snowflake.MEDIUM_SNOWFLAKE));
        }
        for (int i = 0; i < mBigSnowflakeCount; i++) {
            mSnowflakes.add(new Snowflake(mRandom, Snowflake.BIG_SNOWFLAKE));
        }
    }

    @Override
    public void draw(Canvas canvas) {
//        if (!mIsEnableAnim) {
//            return;
//        }
        canvas.save();
        for (Snowflake snowflake : mSnowflakes) {
            snowflake.setBaseAlpha(mBaseAlpha);
            snowflake.drawSnowflake(canvas);
        }
        canvas.restore();
    }

    @Override
    public void start() {
        mIsEnableAnim = true;
    }

    @Override
    public void stop() {
        mIsEnableAnim = false;
    }

    @Override
    public int getAnimType() {
        return 0;
    }

    @Override
    public void setBaseAlpha(float alpha) {
        this.mBaseAlpha = alpha;
    }
}
