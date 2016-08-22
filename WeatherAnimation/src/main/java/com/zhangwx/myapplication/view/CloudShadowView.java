package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CloudShadowView {
    private static final int mDefaultCloudColor = Color.argb(255, 156, 231, 246); //默认颜色
    private static final int mDefaultCloudColor2 = Color.argb(255, 135, 221, 239); //默认颜色
    private Paint mPaint;

    private float mLeft, mTop;
    private int mWidth, mHeight;

    private ShadowCloud mShadowCloud = null;

    private Picture mPicture = null;
    private CircleCloud mCircleCloud = null;
    private float mBaseAlpha = 1f;

    public CloudShadowView() {
        mPaint = new Paint();
        mPaint.setColor(mDefaultCloudColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mShadowCloud = new ShadowCloud();
        mCircleCloud = new CircleCloud();
    }

    public void setBaseAlpha(float alpha) {
        mBaseAlpha = alpha;
    }


    int width = 0;
    int height = 0;
    Path path = new Path();
    Path path1 = new Path();

    /**
     *
     * @param mLeft
     * @param mTop
     * @param mWidth
     * @param mHeight
     * @param bridgeWidth
     * @param twoCombine true 两个圆形构成的云， false 则是三个云构成
     */
    public void reset(float mLeft, float mTop, int mWidth, int mHeight, int bridgeWidth, boolean twoCombine) {
        if ((mPicture == null && mWidth > 0)) {
            this.mLeft = mLeft;
            this.mTop = mTop;
            this.mWidth = mWidth;
            this.mHeight = mHeight;

            width = (int) (mWidth * 1.5f);
            height = mHeight;
            mShadowCloud.reset(mLeft, mTop, mWidth, mHeight, bridgeWidth);
            path = mShadowCloud.getRightPath();
            path1 = mShadowCloud.getLeftPath();
            createPictrue(twoCombine);
        }
    }

    private void createPictrue(boolean twoCombine) {
        mPicture = new Picture();
        Canvas canvas1 = mPicture.beginRecording(mWidth * 2, mWidth * 2);
        mCircleCloud.reset((int) (mLeft + mWidth), (int) (mTop + mHeight * 2f), (int) (mWidth * 0.6f));
        Path mCloudPath;
        if (twoCombine) {
            mCloudPath = mCircleCloud.getThreePath();
            mCircleCloud.drawThreeCloud(canvas1);
        } else {
            mCloudPath = mCircleCloud.getFourCloud();
            mCircleCloud.drawFourCloud(canvas1);
        }
        canvas1.clipPath(mCloudPath);
        mPaint.setAlpha((int) (mBaseAlpha * 255));
        mPaint.setColor(mDefaultCloudColor);
        canvas1.drawPath(path, mPaint);
        mPaint.setColor(mDefaultCloudColor2);
        canvas1.drawPath(path1, mPaint);

        mPicture.endRecording();
    }

    public void drawRectangleCloud(Canvas canvas) {
        int restoreCount = canvas.saveLayerAlpha(mLeft, 0, mLeft + mWidth * 4, mTop + mHeight * 10, (int) (mBaseAlpha * 255), Canvas.ALL_SAVE_FLAG);
        mPicture.draw(canvas);
        canvas.restoreToCount(restoreCount);
    }
}
