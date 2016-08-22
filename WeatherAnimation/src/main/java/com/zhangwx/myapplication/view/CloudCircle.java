package com.zhangwx.myapplication.view;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CloudCircle {
    int mCenterX;
    int mCenterY;
    int mRadius;

    public CloudCircle() {

    }
    public CloudCircle(int mCenterX, int mCenterY, int mRadius) {
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mRadius = mRadius;
    }

    public void set(int mCenterX, int mCenterY, int mRadius) {
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mRadius = mRadius;
    }

    public int getmCenterX() {
        return mCenterX;
    }

    public int getmCenterY() {
        return mCenterY;
    }
    public int getmRadius() {
        return mRadius;
    }
}
