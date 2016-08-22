package com.zhangwx.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.zhangwx.myapplication.R;
import com.zhangwx.myapplication.utils.DimenUtils;
import com.zhangwx.myapplication.utils.MyApplication;

import java.util.Random;

/**
 * Created by zhangwx on 2016/8/1.
 */
public class Snowflake {

    public static final int BIG_SNOWFLAKE = 1;
    public static final int MEDIUM_SNOWFLAKE = 2;
    public static final int SMALL_SNOWFLAKE = 3;
    private Paint mPaint = null;
    private Random mRandom = null;
    private Bitmap mBitmap = null;
    private int mType = 0;
    private int mOffSet = 0;
    private int mFromHeight = 0;
    private int mFadeOutDis = 0;
    private int mGradientAlpha = 0;
    private float mAxisX, mAxisY;
    private float mBaseAlpha = 1f;
    private float mSnowDegrees = 0;
    private final int OFFSET1 = DimenUtils.dp2px(1.0f);//降落速度
    private final int OFFSET2 = DimenUtils.dp2px(1.5f);
    private final int OFFSET3 = DimenUtils.dp2px(3.0f);
    private final int SNOW_SIZE1 = DimenUtils.dp2px(7);//小雪的大小
    private final int WINDOW_WIDTH = DimenUtils.getWindowWidth();
    private final int WINDOW_HEIGHT = DimenUtils.getWindowHeight();

    public Snowflake(Random random, int type) {
        this.mRandom = random;
        this.mType = type;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        switch (mType) {
            case SMALL_SNOWFLAKE:
                mPaint.setStrokeWidth(SNOW_SIZE1);
                mOffSet = OFFSET1;
                mFromHeight = WINDOW_HEIGHT / 5;
                break;
            case MEDIUM_SNOWFLAKE:
                Drawable mediumSnow = MyApplication.getContext().getResources().getDrawable(R.mipmap.medium);
                BitmapDrawable mediumSnowBd = (BitmapDrawable) mediumSnow;
                mBitmap = mediumSnowBd.getBitmap();
                mOffSet = OFFSET2;
                mFromHeight = 1;
                break;
            case BIG_SNOWFLAKE:
                Drawable bigSnow = MyApplication.getContext().getResources().getDrawable(R.mipmap.snow);
                BitmapDrawable bigSnowBd = (BitmapDrawable) bigSnow;
                mBitmap = bigSnowBd.getBitmap();
                mOffSet = OFFSET3;
                mFromHeight = 1;
                break;
        }
        mAxisX = mRandom.nextInt(WINDOW_WIDTH);
        mAxisY = mRandom.nextInt(WINDOW_HEIGHT);
        mFadeOutDis = WINDOW_HEIGHT / 2 + mRandom.nextInt(WINDOW_HEIGHT / 3);
    }

    public void drawSnowflake(Canvas canvas) {
        if (mType == SMALL_SNOWFLAKE) {
            mPaint.setColor(Color.argb((int) (0.5 * mBaseAlpha * mGradientAlpha), 255, 255, 255));
        } else {
            mPaint.setColor(Color.argb((int) (mBaseAlpha * mGradientAlpha), 255, 255, 255));
        }
        canvas.drawPoint(mAxisX, mAxisY, mPaint);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, mAxisX, mAxisY, mPaint);
        }
        resetAxis();
    }

    public void setBaseAlpha(float alpha) {
        this.mBaseAlpha = alpha;
    }

    private void resetAxis() {
        if (mAxisY < WINDOW_HEIGHT) {
            mAxisY += mOffSet;
            if (mType == BIG_SNOWFLAKE) {
                mAxisX += 2 * (float) Math.sin(Math.PI / 180 * mSnowDegrees);
                mSnowDegrees += mRandom.nextInt(4);
            }
            if (mAxisY > mFadeOutDis && mGradientAlpha > 5) {
                mGradientAlpha -= 5;
                return;
            }
            if (mGradientAlpha < 245) {
                mGradientAlpha += 10;
            }

        } else {
            mAxisX = mRandom.nextInt(WINDOW_WIDTH);
            mAxisY = mRandom.nextInt(mFromHeight);
            mGradientAlpha = 0;
            mFadeOutDis = WINDOW_HEIGHT / 2 + mRandom.nextInt(WINDOW_HEIGHT / 2);
        }
    }
}
