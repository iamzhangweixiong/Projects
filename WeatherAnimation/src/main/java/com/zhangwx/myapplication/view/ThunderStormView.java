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
 * Created by zhangwx on 2016/7/19.
 */
public class ThunderStormView implements ICapsuleAnim {

    private int mLightning1Alpha = 0;
    private int mLightning2Alpha = 0;
    private int mLightning3Alpha = 0;
    private int mRainDropCount = 20;
    private float mBaseAlpha = 1f;
    private float mDegrees = 0;
    private boolean mIsEnableAnim = false;
    private boolean mIsTimeToLight = false;
    private boolean mIsDrawForLight = false;
    private Random mRandom = new Random();
    private View mParent = null;
    private Paint mPaint = null;
    private Lightning mLightning = null;
    private GradientCloud mGradient = null;
    private ArrayList<Raindrop> mRaindrops = null;
    private LinearGradient mLinearGradient = null;
    private LinearGradient mLinearGradient2 = null;

    private final float CLOUND_WIDTH = DimenUtils.dp2px(48);//云的厚度

    private final float CLOUND_LENGTH = DimenUtils.dp2px(130);//云的长度

    private final float CLOUND_ALPHA_LEFT_1 = 255 * 0.3f;//云的透明度，根据出现次序排序
    private final float CLOUND_ALPHA_LEFT_2 = 255 * 0.1f;
    private final float CLOUND_ALPHA_LEFT_3 = 255 * 0.1f;
    private final float CLOUND_ALPHA_RIGHT_1 = 255 * 0.1f;
    private final float CLOUND_ALPHA_RIGHT_2 = 255 * 0.3f;
    private final float CLOUND_ALPHA_RIGHT_3 = 255 * 0.1f;
    private final float CLOUND_ALPHA_RIGHT_4 = 255 * 0.3f;

    private final float STARTX_LEFT_1 = -DimenUtils.dp2px(30);//云在X轴的初始位置
    private final float STARTX_LEFT_2 = STARTX_LEFT_1 - DimenUtils.dp2px(15);
    private final float STARTX_LEFT_3 = STARTX_LEFT_1 + DimenUtils.dp2px(3);
    private final float STARTX_RIGHT_1 = DimenUtils.getWindowWidth() + DimenUtils.dp2px(30);
    private final float STARTX_RIGHT_2 = STARTX_RIGHT_1 + DimenUtils.dp2px(20);
    private final float STARTX_RIGHT_3 = STARTX_RIGHT_1 + DimenUtils.dp2px(65);
    private final float STARTX_RIGHT_4 = STARTX_RIGHT_1 + DimenUtils.dp2px(70);

    private final float AXISY_LEFT_1 = DimenUtils.dp2px(135);//云在Y轴的位置
    private final float AXISY_LEFT_2 = DimenUtils.dp2px(107);
    private final float AXISY_LEFT_3 = DimenUtils.dp2px(155);
    private final float AXISY_RIGHT_1 = DimenUtils.dp2px(55);
    private final float AXISY_RIGHT_2 = DimenUtils.dp2px(30);
    private final float AXISY_RIGHT_3 = DimenUtils.dp2px(103);
    private final float AXISY_RIGHT_4 = DimenUtils.dp2px(78);

    private final float OFFSETX_LEFT_1 = DimenUtils.dp2px(1.0f);//移动距离
    private final float OFFSETX_LEFT_2 = DimenUtils.dp2px(0.7f);
    private final float OFFSETX_LEFT_3 = DimenUtils.dp2px(0.9f);
    private final float OFFSETX_RIGHT_1 = DimenUtils.dp2px(0.7f);
    private final float OFFSETX_RIGHT_2 = DimenUtils.dp2px(1.4f);
    private final float OFFSETX_RIGHT_3 = DimenUtils.dp2px(1.2f);
    private final float OFFSETX_RIGHT_4 = DimenUtils.dp2px(1.4f);

    private float mStartXLeft1 = STARTX_LEFT_1;
    private float mStartXLeft2 = STARTX_LEFT_2;
    private float mStartXLeft3 = STARTX_LEFT_3;
    private float mStartXRight1 = STARTX_RIGHT_1;
    private float mStartXRight2 = STARTX_RIGHT_2;
    private float mStartXRight3 = STARTX_RIGHT_3;
    private float mStartXRight4 = STARTX_RIGHT_4;

    private float mStopXLeft1 = STARTX_LEFT_1 - CLOUND_LENGTH;
    private float mStopXLeft2 = STARTX_LEFT_2 - CLOUND_LENGTH;
    private float mStopXLeft3 = STARTX_LEFT_3 - CLOUND_LENGTH;
    private float mStopXRight1 = STARTX_RIGHT_1 + CLOUND_LENGTH;
    private float mStopXRight2 = STARTX_RIGHT_2 + CLOUND_LENGTH;
    private float mStopXRight3 = STARTX_RIGHT_3 + CLOUND_LENGTH;
    private float mStopXRight4 = STARTX_RIGHT_4 + CLOUND_LENGTH;

    private final float LIGHTNING_AXIS[][] = {
            {DimenUtils.dp2px(254), -DimenUtils.dp2px(20)},
            {DimenUtils.dp2px(211), DimenUtils.dp2px(46)},
            {DimenUtils.dp2px(226), DimenUtils.dp2px(46)},
            {DimenUtils.dp2px(204), DimenUtils.dp2px(99)},
            {DimenUtils.dp2px(246), DimenUtils.dp2px(37)},
            {DimenUtils.dp2px(231), DimenUtils.dp2px(37)}};

    public ThunderStormView(View parent) {
        this.mParent = parent;
        mParent.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mPaint = new Paint();
        mGradient = new GradientCloud();
        mLightning = new Lightning();

        mRaindrops = new ArrayList<>(mRainDropCount);
        for (int i = 0; i < mRainDropCount; i++) {
            mRaindrops.add(new Raindrop(mRandom));
        }

        mLinearGradient = new LinearGradient(0, 0, 0, DimenUtils.getWindowHeight() / 2,
                Color.argb(255, 4, 22, 54), Color.argb(255, 19, 63, 120),
                Shader.TileMode.CLAMP);
        mLinearGradient2 = new LinearGradient(0, 0, 0, DimenUtils.getWindowHeight() / 2,
                0, 0, Shader.TileMode.CLAMP);
    }

    @Override
    public void draw(Canvas canvas) {
        mGradient.setRGB(0, 0, 0);
        mGradient.setBaseAlpha(mBaseAlpha);
        mLightning.setBaseAlpha(mBaseAlpha);

        canvas.save();
        if (!mIsDrawForLight) {
            mPaint.setShader(mLinearGradient);
            canvas.drawRect(0, 0, DimenUtils.getWindowWidth(), DimenUtils.getWindowHeight() / 2, mPaint);//渐变层
        }

        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_LEFT_1, CLOUND_WIDTH, mStartXLeft1, AXISY_LEFT_1, mStopXLeft1, AXISY_LEFT_1);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_LEFT_2, CLOUND_WIDTH, mStartXLeft2, AXISY_LEFT_2, mStopXLeft2, AXISY_LEFT_2);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_LEFT_3, CLOUND_WIDTH, mStartXLeft3, AXISY_LEFT_3, mStopXLeft3, AXISY_LEFT_3);

        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_RIGHT_1, CLOUND_WIDTH, mStartXRight1, AXISY_RIGHT_1, mStopXRight1, AXISY_RIGHT_1);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_RIGHT_2, CLOUND_WIDTH, mStartXRight2, AXISY_RIGHT_2, mStopXRight2, AXISY_RIGHT_2);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_RIGHT_3, CLOUND_WIDTH, mStartXRight3, AXISY_RIGHT_3, mStopXRight3, AXISY_RIGHT_3);
        mGradient.drawGradientCloud(canvas, CLOUND_ALPHA_RIGHT_4, CLOUND_WIDTH, mStartXRight4, AXISY_RIGHT_4, mStopXRight4, AXISY_RIGHT_4);

        if (mIsTimeToLight && mLightning1Alpha < 255) {
            mLightning.drawLightning(canvas, mLightning1Alpha, LIGHTNING_AXIS);
            mLightning1Alpha += 30;
            drawFlash(canvas, mLightning1Alpha);
        }

        if (mIsTimeToLight && mLightning1Alpha > 255 && mLightning2Alpha < 155) {
            canvas.translate(-DimenUtils.dp2px(60), DimenUtils.dp2px(150));
            mLightning.drawLightning(canvas, mLightning2Alpha, LIGHTNING_AXIS);
            mLightning2Alpha += 20;
            drawFlash(canvas, mLightning2Alpha);
        }

        if (mIsTimeToLight && mLightning2Alpha > 155 && mLightning3Alpha < 130) {
            canvas.translate(DimenUtils.dp2px(80), DimenUtils.dp2px(100));
            mLightning.drawLightning(canvas, mLightning3Alpha, LIGHTNING_AXIS);
            mLightning3Alpha += 20;
            drawFlash(canvas, mLightning3Alpha);
        }

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
        return WeatherAnimType.ANIM_TYPE_THUNDERSTORM;
    }

    @Override
    public void setBaseAlpha(float alpha) {
        this.mBaseAlpha = alpha;
    }

    private void resetAxis() {

        mDegrees += 1.3;
        if (Math.sin(Math.PI / 180 * mDegrees) > 0) {
            mStartXLeft1 += OFFSETX_LEFT_1;
            mStopXLeft1 += OFFSETX_LEFT_1;
            mStartXLeft2 += OFFSETX_LEFT_2;
            mStopXLeft2 += OFFSETX_LEFT_2;
            mStartXLeft3 += OFFSETX_LEFT_3;
            mStopXLeft3 += OFFSETX_LEFT_3;

            mStartXRight1 -= OFFSETX_RIGHT_1;
            mStopXRight1 -= OFFSETX_RIGHT_1;
            mStartXRight2 -= OFFSETX_RIGHT_2;
            mStopXRight2 -= OFFSETX_RIGHT_2;
            mStartXRight3 -= OFFSETX_RIGHT_3;
            mStopXRight3 -= OFFSETX_RIGHT_3;
            mStartXRight4 -= OFFSETX_RIGHT_4;
            mStopXRight4 -= OFFSETX_RIGHT_4;

            mIsTimeToLight = false;
            mLightning1Alpha = 0;
            mLightning2Alpha = 0;
            mLightning3Alpha = 0;
        } else {
            mStartXLeft1 -= OFFSETX_LEFT_1;
            mStopXLeft1 -= OFFSETX_LEFT_1;
            mStartXLeft2 -= OFFSETX_LEFT_2;
            mStopXLeft2 -= OFFSETX_LEFT_2;
            mStartXLeft3 -= OFFSETX_LEFT_3;
            mStopXLeft3 -= OFFSETX_LEFT_3;

            mStartXRight1 += OFFSETX_RIGHT_1;
            mStopXRight1 += OFFSETX_RIGHT_1;
            mStartXRight2 += OFFSETX_RIGHT_2;
            mStopXRight2 += OFFSETX_RIGHT_2;
            mStartXRight3 += OFFSETX_RIGHT_3;
            mStopXRight3 += OFFSETX_RIGHT_3;
            mStartXRight4 += OFFSETX_RIGHT_4;
            mStopXRight4 += OFFSETX_RIGHT_4;

            mIsTimeToLight = true;
        }
    }

    private void drawFlash(Canvas canvas, int lightningAlpha) {
        if (lightningAlpha < 100) {
            mPaint.setShader(mLinearGradient2);
            canvas.drawRect(0, 0, DimenUtils.getWindowWidth(), DimenUtils.getWindowHeight() / 2, mPaint);
            mIsDrawForLight = true;
        } else {
            mIsDrawForLight = false;
        }
    }
}

