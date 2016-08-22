package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

import com.zhangwx.myapplication.utils.DimenUtils;
import com.zhangwx.myapplication.utils.WeatherAnimType;
import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhangwx on 2016/7/13.
 */
public class RainView implements ICapsuleAnim {
    private View mParent = null;
    private Paint mPaint = null;
    private GradientCloud mGradient = null;
    private ArrayList<Raindrop> mRaindrops = null;
    private LinearGradient mLinearGradient = null;
    private Random mRandom = new Random();
    private boolean mIsEnableAnim = false;
    private boolean mIsTimeToShow = true;
    private float mBaseAlpha = 1f;
    private int mRainDropCount = 15;

    private final float CLOUND_LENGTH1 = DimenUtils.dp2px(125);//云的长度
    private final float CLOUND_LENGTH2 = DimenUtils.dp2px(70);
    private final float CLOUND_LENGTH3 = DimenUtils.dp2px(350);
    private final float CLOUND_LENGTH4 = DimenUtils.dp2px(150);

    private final float CLOUND_WIDTH1 = DimenUtils.dp2px(35);//云的厚度
    private final float CLOUND_WIDTH2 = DimenUtils.dp2px(35);
    private final float CLOUND_WIDTH3 = DimenUtils.dp2px(45);
    private final float CLOUND_WIDTH4 = DimenUtils.dp2px(50);

    private final float AXIS_Y1 = DimenUtils.dp2px(0);//云在Y轴的位置
    private final float AXIS_Y2 = DimenUtils.dp2px(60);
    private final float AXIS_Y3 = DimenUtils.dp2px(20);
    private final float AXIS_Y4 = DimenUtils.dp2px(55);

    private float CLOUND_ALPHA1 = 1;//云的初始化透明度
    private float CLOUND_ALPHA2 = 1;
    private float CLOUND_ALPHA3 = 255 / 10;
    private float CLOUND_ALPHA4 = 255 / 4;

    private final float CLOUND_TARGET_ALPHA1 = 255 / 5;//云的终态透明度：20%
    private final float CLOUND_TARGET_ALPHA2 = 255 / 5;
    private final float CLOUND_TARGET_ALPHA3 = 1;
    private final float CLOUND_TARGET_ALPHA4 = 1;

    private final float START_X1 = DimenUtils.getWindowWidth() / 4;
    private final float START_X2 = START_X1 + DimenUtils.dp2px(65);
    private final float START_X3 = DimenUtils.getWindowWidth();
    private final float START_X4 = START_X3 + DimenUtils.dp2px(100);

    private float mStartX1 = START_X1;
    private float mStopX1 = mStartX1 + CLOUND_LENGTH1;

    private float mStartX2 = START_X2;
    private float mStopX2 = mStartX2 + CLOUND_LENGTH2;

    private float mStartX3 = START_X3;
    private float mStopX3 = mStartX3 + CLOUND_LENGTH3;

    private float mStartX4 = START_X4;
    private float mStopX4 = mStartX4 + CLOUND_LENGTH4;

    private final float OFFSET = 3;//移动距离


    public RainView(View parent) {
        this.mParent = parent;
        mRaindrops = new ArrayList<>(mRainDropCount);
        for (int i = 0; i < mRainDropCount; i++) {
            mRaindrops.add(new Raindrop(mRandom));
        }
        mGradient = new GradientCloud();
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {//坑
//            mParent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        } else {
        mParent.setLayerType(View.LAYER_TYPE_HARDWARE, null);

//        }
        mPaint = new Paint();
        mLinearGradient = new LinearGradient(0, 0, 0, DimenUtils.getWindowHeight() / 5,
                Color.argb(255, 4, 22, 54), Color.argb(255, 19, 63, 120),
                Shader.TileMode.CLAMP);

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.save();

        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0, 0, DimenUtils.getWindowWidth(), DimenUtils.getWindowHeight() / 5, mPaint);

        mGradient.setBaseAlpha(mBaseAlpha);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA1, CLOUND_WIDTH1, mStartX1, AXIS_Y1, mStopX1, AXIS_Y1);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA2, CLOUND_WIDTH2, mStartX2, AXIS_Y2, mStopX2, AXIS_Y2);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA3, CLOUND_WIDTH3, mStartX3, AXIS_Y3, mStopX3, AXIS_Y3);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA4, CLOUND_WIDTH4, mStartX4, AXIS_Y4, mStopX4, AXIS_Y4);

        for (Raindrop mRaindrop : mRaindrops) {
            mRaindrop.setBaseAlpha(mBaseAlpha);
            mRaindrop.drawRaindrop(canvas, mIsEnableAnim);
        }



        canvas.restore();

        if (mIsEnableAnim) {
            resetAxis();
        }
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
        return WeatherAnimType.ANIM_TYPE_RAIN;
    }

    @Override
    public void setBaseAlpha(float alpha) {
        this.mBaseAlpha = alpha;
    }

    private void resetAxis() {
        if (mIsTimeToShow) {
            if (mStopX2 > 0) {
                mStartX1 -= OFFSET;
                mStopX1 -= OFFSET;
                mStartX2 -= OFFSET;
                mStopX2 -= OFFSET;
                resetCloud2Alpha();
                resetCloud1Alpha();
            } else {
                mIsTimeToShow = false;
                mStartX1 = START_X1;
                mStopX1 = mStartX1 + CLOUND_LENGTH1;
                mStartX2 = START_X2;
                mStopX2 = mStartX2 + CLOUND_LENGTH2;
                CLOUND_ALPHA1 = 1;
                CLOUND_ALPHA2 = 1;
            }

//            if (mStopX2 > 0) {
//                mStartX2 -= OFFSET;
//                mStopX2 -= OFFSET;
//                resetCloud2Alpha();
//            } else {
//                mIsTimeToShow = false;
//                mStartX2 = START_X2;
//                mStopX2 = mStartX2 + CLOUND_LENGTH2;
//                CLOUND_ALPHA2 = 1;
//            }
        }

        if (mStopX4 > START_X3) {
            mStartX3 -= OFFSET;
            mStopX3 -= OFFSET;
            mStartX4 -= OFFSET;
            mStopX4 -= OFFSET;
            resetCloud3Alpha();
            resetCloud4Alpha();
        } else {
            mIsTimeToShow = true;
            mStartX4 = START_X4;
            mStopX4 = mStartX4 + CLOUND_LENGTH4;
            CLOUND_ALPHA4 = 255 / 4;
            mStartX3 = START_X3;
            mStopX3 = mStartX3 + CLOUND_LENGTH3;
            CLOUND_ALPHA3 = 255 / 10;
        }

//        if (mStopX4 > START_X3) {
//            mStartX4 -= OFFSET;
//            mStopX4 -= OFFSET;
//            resetCloud4Alpha();
//        } else {
//            mIsTimeToShow = true;
//            mStartX4 = START_X4;
//            mStopX4 = mStartX4 + CLOUND_LENGTH4;
//            CLOUND_ALPHA4 = 255 / 4;
//        }

    }

    private void resetCloud1Alpha() {
        if (mStartX2 > 0) {
            if (CLOUND_ALPHA1 < CLOUND_TARGET_ALPHA1) {
                CLOUND_ALPHA1 += 0.7;
            }
        }
    }

    private void resetCloud2Alpha() {
        if (mStartX2 > 0) {
            if (CLOUND_ALPHA2 < CLOUND_TARGET_ALPHA2) {
                CLOUND_ALPHA2 += 0.7;
            }
        }
    }

    private void resetCloud3Alpha() {
        if (mStopX4 - START_X3 < 200) {
            if (CLOUND_ALPHA3 > CLOUND_TARGET_ALPHA3) {
                CLOUND_ALPHA3 -= 0.7;
            }
        }
    }

    private void resetCloud4Alpha() {
        if (mStopX4 - START_X3 < 200) {
            if (CLOUND_ALPHA4 > CLOUND_TARGET_ALPHA4) {
                CLOUND_ALPHA4 -= 0.95;
            }
        }
    }
}
