package com.zhangwx.myapplication.view;

import android.graphics.Color;
import android.graphics.Path;

import com.zhangwx.myapplication.utils.DimenUtils;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ShadowCloud {
    private static final int mDefaultCloudColor = Color.argb(255, 252, 214, 58); //默认颜色

    private Path mFirstBridgePath = new Path();
    private Path mSecondBridgePath = new Path();
    private Path mThreeBridgePath = new Path();

    private RectangleCloudView mFirstRectangle = null;
    private RectangleCloudView mSecondRectangle = null;
    private RectangleCloudView mThreeRectangle = null;
    private RectangleCloudView mFourRectangle = null;

    int width = 0;
    int height = 0;
    Path path = new Path();
    Path path1 = new Path();

    public ShadowCloud() {
        mFirstRectangle = new RectangleCloudView();
        mFirstRectangle.setColor(mDefaultCloudColor);
        mSecondRectangle = new RectangleCloudView();
        mSecondRectangle.setColor(mDefaultCloudColor);
        mThreeRectangle = new RectangleCloudView();
        mThreeRectangle.setColor(mDefaultCloudColor);
        mFourRectangle = new RectangleCloudView();
        mFourRectangle.setColor(mDefaultCloudColor);
    }

    /**
     * 避免频繁调用，因为会造成过多的创建对象
     * @param mLeft
     * @param mTop
     * @param mWidth
     * @param mHeight
     * @param bridgeWidth
     */
    public void reset(float mLeft, float mTop, int mWidth, int mHeight, int bridgeWidth) {

        width = (int) (mWidth * 1.5f);
        height = mHeight;
        mFirstRectangle.reset(mLeft, mTop, mWidth, mHeight);
        mSecondRectangle.reset(mLeft + DimenUtils.dp2px(10), mTop + mHeight * 2, mWidth, mHeight);
        mThreeRectangle.reset(mLeft + DimenUtils.dp2px(20), mTop + mHeight * 4, mWidth, mHeight);
        mFourRectangle.reset(mLeft + DimenUtils.dp2px(10), mTop + mHeight * 6, mWidth, mHeight);
        mFirstBridgePath.reset();
        mFirstBridgePath = CloudBridge.getBridge(mLeft + mWidth  - mHeight * 2.3f, mTop + mHeight, mWidth, mHeight, bridgeWidth / 2);
        mSecondBridgePath.reset();
        mSecondBridgePath = CloudBridge.getBridge(mLeft + mWidth  - mHeight * 2 - DimenUtils.dp2px(6), mTop + mHeight * 3, mWidth, mHeight, bridgeWidth);
        mThreeBridgePath.reset();
        mThreeBridgePath = CloudBridge.getBridge(mLeft + mWidth - mHeight * 2 + DimenUtils.dp2px(3), mTop + mHeight * 5, mWidth, mHeight, bridgeWidth * 2 / 3);

        path.reset();
        path.addRoundRect(mFirstRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path.addRoundRect(mSecondRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path.addRoundRect(mThreeRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path.addRoundRect(mFourRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);

        path.addPath(mFirstBridgePath);
        path.addPath(mSecondBridgePath);
        path.addPath(mThreeBridgePath);

        mFirstRectangle.reset(mLeft - mHeight, mTop + mHeight, mWidth, mHeight);
        mSecondRectangle.reset(mLeft  - mHeight - DimenUtils.dp2px(6), mTop + mHeight * 3, mWidth, mHeight);
        mThreeRectangle.reset(mLeft - mHeight + DimenUtils.dp2px(3), mTop + mHeight * 5, mWidth, mHeight);
        mFourRectangle.reset(mLeft  - mHeight * 2 + DimenUtils.dp2px(20), mTop + mHeight * 7, mWidth, mHeight);
        mFirstBridgePath.reset();
        mFirstBridgePath = CloudBridge.getBridge(mLeft - mHeight, mTop + mHeight * 2, mWidth, mHeight, bridgeWidth);
        mSecondBridgePath.reset();
        mSecondBridgePath = CloudBridge.getBridge(mLeft - mHeight, mTop + mHeight * 4, mWidth - mHeight, mHeight, bridgeWidth * 2);
        mThreeBridgePath.reset();
        mThreeBridgePath = CloudBridge.getBridge(mLeft - mHeight, mTop + mHeight * 6, mWidth, mHeight, (int) (bridgeWidth * 1.3f));

        path1.reset();
        path1.addRoundRect(mFirstRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path1.addRoundRect(mSecondRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path1.addRoundRect(mThreeRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);
        path1.addRoundRect(mFourRectangle.getRect(), mHeight / 2, mHeight / 2, Path.Direction.CCW);

        path1.addPath(mFirstBridgePath);
        path1.addPath(mSecondBridgePath);
        path1.addPath(mThreeBridgePath);
    }

    public Path getRightPath() {
        return path;
    }

    public Path getLeftPath() {
        return path1;
    }
}
