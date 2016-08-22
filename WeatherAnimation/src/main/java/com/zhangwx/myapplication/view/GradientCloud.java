package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by zhangwx on 2016/7/15.
 */
public class GradientCloud {

    private Paint mPaint = null;
    private LinearGradient mLinearGradient = null;
    private float mBaseAlpha = 1f;
    private int mRed = 255;
    private int mGreen = 255;
    private int mBlue = 255;

    public GradientCloud() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void drawGradientCloud(Canvas canvas, float cloudAlpha, float strokeWidth,
                                  float startX, float startY, float stopX, float stopY) {
        if (mLinearGradient != null) {
            mLinearGradient = null;
        }
        mLinearGradient = new LinearGradient(startX, startY, stopX, stopY,
                Color.argb((int) (mBaseAlpha * cloudAlpha), mRed, mGreen, mBlue), 0,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    public void setBaseAlpha(float alpha) {
        this.mBaseAlpha = alpha;
    }

    public void setRGB(int red, int green, int blue) {
        this.mRed = red;
        this.mGreen = green;
        this.mBlue = blue;
    }
}
