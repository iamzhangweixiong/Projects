package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zhangwx.myapplication.utils.DimenUtils;

import java.util.Random;

/**
 * Created by zhangwx on 2016/7/13.
 */
public class Raindrop {

    private Random mRandom;
    private int mAlphaType;
    private final int TYPE_LIGHT = 1;
    private final int TYPE_DARK = 2;
    private float mBaseAlpha = 1f;
    private float mTempAlpha = 1f;
    boolean mFrom;//雨滴从X轴或者Y轴出来
    private static final int mDefaultCloudColor = Color.rgb(255, 255, 255); //默认颜色
    private Paint mPaint;
    private final int MAX_LENGTH = DimenUtils.dp2px(10);//雨滴长度
    private final int MIN_LENGTH = DimenUtils.dp2px(6);//雨滴长度
    private final int RAINDROP_WIDTH = DimenUtils.dp2px(2);
    private final float RAINDROP_DEGREES = 75;//雨滴倾斜角度
    private final float DEFAULT_OFFSET = 5;//动画偏移量
    private float mOffsetX;
    private float mOffsetY;
    private float mLengthX;//雨滴长度x轴分量
    private float mLengthY;//雨滴长度y轴分量
    private int mMaxX = DimenUtils.getWindowWidth();
    private int mMaxY = DimenUtils.getWindowHeight();
    private float mStartX, mStopX, mStartY, mStopY;

    public Raindrop(Random random) {
        this.mRandom = random;
        mPaint = new Paint();
        mFrom = mRandom.nextBoolean();
        mPaint.setColor(mDefaultCloudColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(RAINDROP_WIDTH);

        if (mRandom.nextBoolean()) {
            mLengthX = (float) Math.sin(Math.PI / 180 * RAINDROP_DEGREES) * MAX_LENGTH;
        } else {
            mLengthX = (float) Math.sin(Math.PI / 180 * RAINDROP_DEGREES) * MIN_LENGTH;
        }

        mLengthY = (float) Math.tan(Math.PI / 180 * RAINDROP_DEGREES) * mLengthX;
        mOffsetX = DEFAULT_OFFSET;
        mOffsetY = (float) Math.tan(Math.PI / 180 * RAINDROP_DEGREES) * mOffsetX;
        resetRain();
    }

    public void setBaseAlpha(float alpha) {
        mTempAlpha = mBaseAlpha;
        this.mBaseAlpha = alpha;
    }

    public void drawRaindrop(Canvas canvas, boolean isEnableAnim) {
        if (mTempAlpha != mBaseAlpha) {
            if (mAlphaType == TYPE_LIGHT) {
                mPaint.setAlpha((int) (mBaseAlpha * 255 / 2));
            } else {
                mPaint.setAlpha((int) (mBaseAlpha * 255 / 5));
            }
        }
        canvas.drawLine(mStartX, mStartY, mStopX, mStopY, mPaint);

        if (!isEnableAnim) {
            return;
        }
        if (isOutOfBound()) {
            resetRain();
        } else {
            rain();
        }
    }

    private void resetRain() {
        if (mRandom.nextBoolean()) {
            mPaint.setAlpha((int) (mBaseAlpha * 255 / 2));
            mAlphaType = TYPE_LIGHT;
        } else {
            mPaint.setAlpha((int) (mBaseAlpha * 255 / 5));
            mAlphaType = TYPE_DARK;
        }
        if (mRandom.nextBoolean()) {//增加雨滴从顶部出现的概率，使画面平衡 -_-||...
            resetRainFromX();
            return;
        }
        if (mRandom.nextBoolean()) {//增加雨滴从顶部出现的概率，使画面平衡 -_-||...
            resetRainFromX();
            return;
        }
        resetRainFromY();
    }

    private void rain() {
        mStartX -= mOffsetX;
        mStartY += mOffsetY;
        mStopX -= mOffsetX;
        mStopY += mOffsetY;
    }

    private void resetRainFromX() {
        mStartX = mRandom.nextInt(mMaxX);
        mStopX = mStartX - mLengthX;
        mStartY = 0;
        mStopY = mStartY + mLengthY;
    }

    private void resetRainFromY() {
        mStartX = mMaxX;
        mStopX = mStartX - mLengthX;
        mStartY = mRandom.nextInt(mMaxY);
        mStopY = mStartY + mLengthY;
    }

    private boolean isOutOfBound() {
        if (mStartX < 0 || mStartY > mMaxY) {
            return true;
        }
        return false;
    }
}
