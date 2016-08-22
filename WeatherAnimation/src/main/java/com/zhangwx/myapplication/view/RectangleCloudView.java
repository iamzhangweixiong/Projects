package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Administrator on 2016/7/13.
 */
public class RectangleCloudView {
    private static final int mDefaultCloudColor = Color.rgb(255, 255, 255); //默认颜色
    private Paint mPaint;
    private RectF mRectF;

    private float mLeft;
    private float mTop;
    private int mWidth;
    private int mHeight;

    public RectangleCloudView() {
        mPaint = new Paint();
        mPaint = new Paint();
        mPaint.setColor(mDefaultCloudColor);
        mPaint.setAlpha(208);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mRectF = new RectF();
    }

    public RectF getRect() {
        return mRectF;
    }

    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public void reset(float mLeft, float mTop, int mWidth, int mHeight) {
        this.mLeft = mLeft;
        this.mTop = mTop;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        mRectF.set(mLeft, mTop, mLeft + mWidth, mTop + mHeight);
    }

    public void drawRectangleCloud(Canvas canvas) {
        canvas.drawRoundRect(mRectF, mHeight / 2, mHeight / 2, mPaint);
    }

}
