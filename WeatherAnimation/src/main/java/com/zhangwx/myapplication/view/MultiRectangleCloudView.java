package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;

import com.zhangwx.myapplication.utils.DimenUtils;

/**
 * Created by zhangwx on 2016/7/26.
 */
public class MultiRectangleCloudView {
    private static final int mDefaultCloudColor = Color.argb(255, 252, 214, 58); //默认颜色
    private static final int mDefaultCloudColor2 = Color.argb(255, 249, 195, 61); //默认颜色
    private static final int mSunColor = Color.argb(255, 255, 226, 85); //默认颜色
    private Paint mPaint;
    private RectF mRectF;

    private float mLeft, mTop;
    private int mWidth, mHeight;

    private ShadowCloud mShadowCloud = null;

    private Path mCirclePath = new Path();
    private Picture mPicture = null;
    private float mBaseAlpha = 1f;

    public MultiRectangleCloudView() {
        mPaint = new Paint();
        mPaint = new Paint();
        mPaint.setColor(mDefaultCloudColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mRectF = new RectF();
        mShadowCloud = new ShadowCloud();

    }

    public void setBaseAlpha(float alpha) {
        mBaseAlpha = alpha;
    }

    int width = 0;
    int height = 0;
    Path path = new Path();
    Path path1 = new Path();

    public void reset(float mLeft, float mTop, int mWidth, int mHeight, int bridgeWidth) {
        if ((mPicture == null && mWidth > 0) || this.mLeft != mLeft || this.mTop != mTop || this.mWidth != mWidth || this.mHeight != mHeight) {
            this.mLeft = mLeft;
            this.mTop = mTop;
            this.mWidth = mWidth;
            this.mHeight = mHeight;

            width = (int) (mWidth * 1.5f);
            height = mHeight;
            mShadowCloud.reset(mLeft, mTop - DimenUtils.dp2px(15), mWidth, mHeight + DimenUtils.dp2px(4), bridgeWidth + DimenUtils.dp2px(5.3f));
            path = mShadowCloud.getRightPath();
            path1 = mShadowCloud.getLeftPath();
            createPictrue();
        }
    }

    private void createPictrue() {
        mPicture = new Picture();
        Canvas canvas1 = mPicture.beginRecording(mWidth * 2, mWidth * 2);

        mCirclePath.addCircle(mLeft + mWidth, mTop + mHeight * 2.8f, mWidth, Path.Direction.CCW);
        mPaint.setAlpha((int) (mBaseAlpha * 255));
        mPaint.setColor(mSunColor);
        canvas1.drawPath(mCirclePath, mPaint);
        canvas1.clipPath(mCirclePath);

        mPaint.setColor(mDefaultCloudColor);
        canvas1.drawPath(path, mPaint);
        mPaint.setColor(mDefaultCloudColor2);
        canvas1.drawPath(path1, mPaint);

        mPicture.endRecording();
    }

    public void drawRectangleCloud(Canvas canvas) {
        int restoreCount = canvas.saveLayerAlpha(mLeft, mTop, mLeft + mWidth * 2, mTop + mHeight * 10, (int) (mBaseAlpha * 255), Canvas.ALL_SAVE_FLAG);
        mPicture.draw(canvas);
        canvas.restoreToCount(restoreCount);
    }
}
