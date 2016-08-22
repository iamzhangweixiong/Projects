package com.zhangwx.myapplication.view;

import android.graphics.Canvas;

import com.zhangwx.myapplication.utils.DimenUtils;
import com.zhangwx.myapplication.utils.WeatherAnimType;
import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CloudView implements ICapsuleAnim {

    private CloudShadowView mCloudShadowView = null;
    private CloudShadowView mCloudShadowView2 = null;
    private RectangleCloudView mRectanclCloud;

    private boolean mIsEnableAnim = false;
    private float mOffset = 0;
    private float mOffsetX = 0;
    private int mDefaultOffsetPer = 0;
    private long mStartTime = 0;
    private float mBaseAlpha = 0.5f;

    public CloudView() {
        mCloudShadowView = new CloudShadowView();
        mCloudShadowView2 = new CloudShadowView();
        mRectanclCloud = new RectangleCloudView();
        mDefaultOffsetPer = DimenUtils.dp2px(6);
    }

    @Override
    public void draw(Canvas canvas) {
        long time = System.currentTimeMillis();
        final int width = DimenUtils.getRealWidth();
        final int cloudLeftOffset = DimenUtils.dp2px(135);
        final int cloudTopOffset = DimenUtils.dp2px(40);
        mRectanclCloud.setAlpha((int) (mBaseAlpha * 155));
        mRectanclCloud.reset(-mOffset + width - cloudLeftOffset - DimenUtils.dp2px(25), cloudTopOffset + DimenUtils.dp2px(5), DimenUtils.dp2px(45), DimenUtils.dp2px(7));
        mRectanclCloud.drawRectangleCloud(canvas);

        canvas.save();
        mCloudShadowView.setBaseAlpha(mBaseAlpha);
        mCloudShadowView.reset(width - cloudLeftOffset, DimenUtils.dp2px(18), cloudTopOffset, DimenUtils.dp2px(6), DimenUtils.dp2px(10), false);
        canvas.translate(mOffset, 0);
        mCloudShadowView.drawRectangleCloud(canvas);

        canvas.translate(-mOffset * 2, 0);
        mCloudShadowView2.setBaseAlpha(mBaseAlpha);
        mCloudShadowView2.reset(width - DimenUtils.dp2px(80), DimenUtils.dp2px(150), cloudTopOffset, DimenUtils.dp2px(8f), DimenUtils.dp2px(10), true);
        mCloudShadowView2.drawRectangleCloud(canvas);
        canvas.restore();

        mRectanclCloud.setAlpha((int) (mBaseAlpha * 208));
        mRectanclCloud.reset(width - cloudLeftOffset + DimenUtils.dp2px(20), cloudTopOffset + DimenUtils.dp2px(10), DimenUtils.dp2px(50), DimenUtils.dp2px(12));
        mRectanclCloud.drawRectangleCloud(canvas);

        mRectanclCloud.setAlpha((int) (mBaseAlpha * 128));
//        mRectanclCloud.reset(mOffsetX, DimenUtils.dp2px(65), DimenUtils.dp2px(30), DimenUtils.dp2px(3));
//        mRectanclCloud.drawRectangleCloud(canvas);
//
//        mRectanclCloud.reset(mOffsetX, DimenUtils.dp2px(70), DimenUtils.dp2px(30), DimenUtils.dp2px(3));
//        mRectanclCloud.drawRectangleCloud(canvas);
//
//        mRectanclCloud.reset(mOffsetX  , DimenUtils.dp2px(80), DimenUtils.dp2px(30), DimenUtils.dp2px(3));
//        mRectanclCloud.drawRectangleCloud(canvas);

        mRectanclCloud.setAlpha((int) (mBaseAlpha * 102));
        mRectanclCloud.reset(mOffset - DimenUtils.dp2px(8), DimenUtils.dp2px(100), DimenUtils.dp2px(30), DimenUtils.dp2px(3));
        mRectanclCloud.drawRectangleCloud(canvas);

        mRectanclCloud.reset(-mOffset - DimenUtils.dp2px(8), DimenUtils.dp2px(105), DimenUtils.dp2px(30), DimenUtils.dp2px(3));
        mRectanclCloud.drawRectangleCloud(canvas);

        long t = (System.currentTimeMillis() - mStartTime);
        if (mIsEnableAnim) {
            if (t > 1000 * 8) {
                mStartTime = System.currentTimeMillis();
            }
            mOffset = (float) Math.sin(Math.PI / 2 * t / 1000) * mDefaultOffsetPer;
            mOffsetX += mDefaultOffsetPer * 0.2f;
            if (mOffsetX > width) {
                mOffsetX = 0;
            }
        } else {
            mOffset = 0;
        }
    }

    @Override
    public void start() {
        mStartTime = System.currentTimeMillis();
        mIsEnableAnim = true;
    }

    @Override
    public void stop() {
        mIsEnableAnim = false;
    }

    @Override
    public int getAnimType() {
        return WeatherAnimType.ANIM_TYPE_CLOUD;
    }

    @Override
    public void setBaseAlpha(float alpha) {
        mBaseAlpha = alpha;
    }
}


