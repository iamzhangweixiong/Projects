package com.zhangwx.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.zhangwx.myapplication.utils.DimenUtils;
import com.zhangwx.myapplication.utils.WeatherAnimType;
import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SunnyView implements ICapsuleAnim {
    private static final int mDefaultColor = Color.argb(255, 255, 255, 255); //默认颜色

    private Paint mPaint;
    private RectangleCloudView mRectanclCloud;
    private MultiRectangleCloudView multiRectangleCloudView;
    private boolean mIsEnableAnim = false;
    private float mOffset = 0;
    private int mDefaultOffsetPer = 0;
    private Bitmap mSunBit = null;
    private Path mMoveBridgePath = null;
    private long mStartTime = 0;
    private float mBaseAlpha = 1.0f;
    final int width = DimenUtils.getRealWidth();

    //太阳
    private final int LEFT_OF_SUN = width - DimenUtils.dp2px(126f);
    private final int TOP_OF_SUN = -DimenUtils.dp2px(20);
    private final int WIDTH_OF_SUN = DimenUtils.dp2px(63);
    private final int HEIGHT_OF_SUN = DimenUtils.dp2px(11.5f);
    private final int BRIGEWIDTH_OF_SUN = DimenUtils.dp2px(5);//桥接宽度

    //云
    private final int LEFT_OF_CLOUD1 = width - DimenUtils.dp2px(150);
    private final int TOP_OF_CLOUD1 = DimenUtils.dp2px(56);
    private final int WIDTH_OF_CLOUD1 = DimenUtils.dp2px(80);
    private final int HRIGHT_OF_CLOUD1 = DimenUtils.dp2px(18);

    private final int LEFT_OF_CLOUD2 = width - DimenUtils.dp2px(170);
    private final int TOP_OF_CLOUD2 = DimenUtils.dp2px(34);
    private final int WIDTH_OF_CLOUD2 = DimenUtils.dp2px(60);
    private final int HRIGHT_OF_CLOUD2 = DimenUtils.dp2px(14);

    private final int LEFT_OF_CLOUD3 = width - DimenUtils.dp2px(200);
    private final int TOP_OF_CLOUD3 = DimenUtils.dp2px(56);
    private final int WIDTH_OF_CLOUD3 = DimenUtils.dp2px(40);
    private final int HRIGHT_OF_CLOUD3 = DimenUtils.dp2px(10);

    private final int LEFT_OF_CLOUD4 = DimenUtils.dp2px(40) - width;
    private final int TOP_OF_CLOUD4 = DimenUtils.dp2px(126);
    private final int WIDTH_OF_CLOUD4 = DimenUtils.dp2px(60);
    private final int HRIGHT_OF_CLOUD4 = DimenUtils.dp2px(8);

    private final int LEFT_OF_CLOUD5 = width- DimenUtils.dp2px(15);
    private final int TOP_OF_CLOUD5 = DimenUtils.dp2px(130);
    private final int WIDTH_OF_CLOUD5 = DimenUtils.dp2px(30);
    private final int HRIGHT_OF_CLOUD5 = DimenUtils.dp2px(8);

    //桥
    private final int LEFT_OF_BRIGE = width - DimenUtils.dp2px(140);
    private final int TOP_OF_BRIGE = DimenUtils.dp2px(48);
    private final int WIDTH_OF_BRIGE = DimenUtils.dp2px(80);
    private final int HRIGHT_OF_BRIGE = DimenUtils.dp2px(8);
    private final int REAL_WIDTH_OF_BRIGE = DimenUtils.dp2px(6);


    private final float CLOUDALPHA1 = 153;//60%透明度
    private final float CLOUDALPHA2 = 102;//40%透明度

    public SunnyView(View parent) {
        parent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(mDefaultColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(208);

        mRectanclCloud = new RectangleCloudView();
        multiRectangleCloudView = new MultiRectangleCloudView();
        mDefaultOffsetPer = DimenUtils.dp2px(3);

    }

    @Override
    public void setBaseAlpha(float alpha) {
        mBaseAlpha = alpha;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.save();

        //太阳旁边的云
        mRectanclCloud.setAlpha((int) (mBaseAlpha * CLOUDALPHA1));
        mRectanclCloud.reset(mOffset + LEFT_OF_CLOUD1, TOP_OF_CLOUD1, WIDTH_OF_CLOUD1, HRIGHT_OF_CLOUD1);//
        mRectanclCloud.drawRectangleCloud(canvas);

        //太阳
        multiRectangleCloudView.setBaseAlpha(mBaseAlpha);
        multiRectangleCloudView.reset(LEFT_OF_SUN, TOP_OF_SUN, WIDTH_OF_SUN, HEIGHT_OF_SUN, BRIGEWIDTH_OF_SUN);
        multiRectangleCloudView.drawRectangleCloud(canvas);

        //桥接部分
        if (mMoveBridgePath == null) {
            mMoveBridgePath = CloudBridge.getBridge(LEFT_OF_BRIGE, TOP_OF_BRIGE, WIDTH_OF_BRIGE, HRIGHT_OF_BRIGE, REAL_WIDTH_OF_BRIGE);
        }
        mPaint.setAlpha((int) (mBaseAlpha * CLOUDALPHA1));
        canvas.drawPath(mMoveBridgePath, mPaint);

        mRectanclCloud.reset(-mOffset + LEFT_OF_CLOUD2, TOP_OF_CLOUD2, WIDTH_OF_CLOUD2, HRIGHT_OF_CLOUD2);
        mRectanclCloud.drawRectangleCloud(canvas);

        mRectanclCloud.setAlpha((int) (mBaseAlpha * CLOUDALPHA2));
        mRectanclCloud.reset(-mOffset + LEFT_OF_CLOUD3, TOP_OF_CLOUD3, WIDTH_OF_CLOUD3, HRIGHT_OF_CLOUD3);
        mRectanclCloud.drawRectangleCloud(canvas);


//        mRectanclCloud.reset(-mOffset + width - DimenUtils.dp2px(25), DimenUtils.dp2px(48), DimenUtils.dp2px(40), DimenUtils.dp2px(18));
//        mRectanclCloud.drawRectangleCloud(canvas);


        //太阳右边的云一起运动的云
        mRectanclCloud.reset(-mOffset - LEFT_OF_CLOUD4, TOP_OF_CLOUD4, WIDTH_OF_CLOUD4 , HRIGHT_OF_CLOUD4);
        mRectanclCloud.drawRectangleCloud(canvas);

        mRectanclCloud.setAlpha((int) (mBaseAlpha * CLOUDALPHA1));
        mRectanclCloud.reset(mOffset  + LEFT_OF_CLOUD5, TOP_OF_CLOUD5, WIDTH_OF_CLOUD5, HRIGHT_OF_CLOUD5);
        mRectanclCloud.drawRectangleCloud(canvas);

        long t = (System.currentTimeMillis() - mStartTime);
        if (mIsEnableAnim) {
            if (t > 1000 * 8) {
                mStartTime = System.currentTimeMillis();
            }
            mOffset = (float) Math.sin(Math.PI / 2 * t / 1000) * mDefaultOffsetPer;
        } else {
            mOffset = 0;
        }
    }

    @Override
    public void start() {
        mIsEnableAnim = true;
        mStartTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        mIsEnableAnim = false;
    }

    @Override
    public int getAnimType() {
        return WeatherAnimType.ANIM_TYPE_CLOUD;
    }
}
