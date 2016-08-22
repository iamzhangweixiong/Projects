package com.zhangwx.myapplication.view;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CloudBridge {
    public static Path getBridge(float mLeft, float mTop, int mWidth, int mHeight, int bridgeWidth) {
        RectF mRectF = new RectF(mLeft, mTop, mLeft + mWidth, mTop + mHeight);

        float[] leftRound = new float[] {0, 0, mHeight / 2, mHeight / 2, mHeight / 2, mHeight / 2, 0, 0}; //圆角
        float[] rightRound = new float[] {mHeight / 2, mHeight / 2, 0, 0, 0, 0, mHeight / 2, mHeight / 2}; //圆角
        Path bridgePath = new Path();
        bridgePath.reset();
        bridgePath.addRect(mRectF, Path.Direction.CW);

        //掏空的左边部分
        int mLeftPadding = mHeight;

        RectF mLeftRoundRect = new RectF(mLeft  , mTop, mLeft + mLeftPadding, mTop + mHeight); //掏空的左边部分
        Path mLeftRoundPath  = new Path();
        mLeftRoundPath.reset();
        mLeftRoundPath.addRoundRect(mLeftRoundRect, leftRound, Path.Direction.CCW);
        bridgePath.addPath(mLeftRoundPath);

        RectF mRightRoundRect = new RectF(mLeftRoundRect.right + bridgeWidth , mTop, mLeft + mWidth , mTop + mHeight); //从左边开始，加上设定的宽度，在最后结束
        Path mRightRoundPath = new Path();
        mRightRoundPath.reset();
        mRightRoundPath.addRoundRect(mRightRoundRect, rightRound, Path.Direction.CCW);
        bridgePath.addPath(mRightRoundPath);
        return bridgePath;
    }
}
