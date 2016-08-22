package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CircleCloud {
    private static final int mDefaultCloudColor = Color.argb(255, 180, 239, 249); //默认颜色

    private int mCloudCx; //云朵基准圆形x坐标
    private int mCloudCy; //云朵基准圆形y坐标

    private int mCloudRadius = 100; //云朵基准圆形半径
    private CloudCircle circle1 = new CloudCircle(); //基础圆，另两个圆形以此为基准
    private CloudCircle circle2 = new CloudCircle(); //右圆

    private CloudCircle circle3 = new CloudCircle(); //左圆
    private CloudCircle circle4 = new CloudCircle(); //右右圆
    private Path mWindPath = new Path(); //合并之后
    private Path mThreePath = new Path(); //合并之后
    private Path mFourPath = new Path(); //合并之后
    private Path circlePath1 = new Path();
    private Path circlePath2 = new Path();
    private Path circlePath3 = new Path();
    private Path circlePath4 = new Path();

    private Path fillPath = new Path(); //填充圆形之外的部分

    private Paint mPaint;

    public CircleCloud() {
        mPaint = new Paint();
        mPaint.setColor(mDefaultCloudColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     *
     * @param centerX 云朵基准圆心位置 X轴
     * @param centerY 云朵基准圆心位置 Y轴
     * @param cloudRaius 云朵基准圆半径
     */
    public CircleCloud reset(int centerX, int centerY, int cloudRaius) {
        this.mCloudCx = centerX;
        this.mCloudCy = centerY;
        this.mCloudRadius = cloudRaius;
        calculateTwoCirclWind();
        calculateThreeCirclWind();
        calculateFourCircleWind();
        return this;
    }

    /**
     * 设置云朵颜色
     * @param color
     */
    public CircleCloud setColor(int color) {
        mPaint.setColor(color);
        return this;
    }

    /**
     * 设置透明度
     * @param alpha
     */
    public CircleCloud setBaseAlpha(float alpha) {
        mPaint.setAlpha((int) (alpha * 255));
        return this;
    }

    /**
     * 计算两个圆形的组成的云朵
     */
    private void calculateTwoCirclWind() {
        circle1.set(mCloudCx, mCloudCy, mCloudRadius);
        final int circle2Radius = (int) (mCloudRadius * 0.7f); //圆形2的半径
        circle2.set(circle1.getmCenterX() + circle1.getmRadius() + circle2Radius / 3, circle1.getmCenterY() + circle1.getmRadius() - circle2Radius, circle2Radius); //以圆形1为基准计算右边圆形的位置

        circlePath1.reset();
        circlePath1.addCircle(circle1.getmCenterX(), circle1.getmCenterY(), circle1.getmRadius(), Path.Direction.CW);
        circlePath2.reset();
        circlePath2.addCircle(circle2.getmCenterX(), circle2.getmCenterY(), circle2.getmRadius(), Path.Direction.CW);

        fillPath.reset();
        fillPath.moveTo(circle1.getmCenterX(), circle1.getmCenterY());
        fillPath.lineTo(circle2.getmCenterX(), circle2.getmCenterY());
        fillPath.lineTo(circle2.getmCenterX(), circle2.getmCenterY() + circle2.getmRadius());
        fillPath.lineTo(circle1.getmCenterX(), circle1.getmCenterY() + circle1.getmRadius());
        fillPath.close();
        mWindPath.reset();
        mWindPath.addPath(circlePath1);
        mWindPath.addPath(circlePath2);
        mWindPath.addPath(fillPath);
    }

    private void calculateThreeCirclWind() {
        calculateTwoCirclWind();
        final int circle2Radius = (int) (mCloudRadius * 0.6f); //圆形3的半径
        circle3.set(circle1.getmCenterX() - circle1.getmRadius(), circle1.getmCenterY() + circle1.getmRadius() - circle2Radius, circle2Radius); //以圆形1为基准计算左边圆形的位置
        circlePath3.reset();
        circlePath3.addCircle(circle3.getmCenterX(), circle3.getmCenterY(), circle3.getmRadius(), Path.Direction.CW);

        fillPath.reset();
        fillPath.moveTo(circle3.getmCenterX(), circle3.getmCenterY());
        fillPath.lineTo(circle1.getmCenterX(), circle3.getmCenterY());
        fillPath.lineTo(circle1.getmCenterX(), circle1.getmCenterY() + circle1.getmRadius());
        fillPath.lineTo(circle3.getmCenterX(), circle3.getmCenterY() + circle3.getmRadius());
        fillPath.close();
        mThreePath.addPath(circlePath3);
        mThreePath.addPath(fillPath);
        mThreePath.addPath(mWindPath);
    }

    private void calculateFourCircleWind() {
        calculateTwoCirclWind();
        calculateThreeCirclWind();
        final int circle2Radius = (int) (mCloudRadius * 0.35f); //圆形4的半径
        circle4.set(circle2.getmCenterX() + circle2.getmRadius(), circle2.getmCenterY() + circle2.getmRadius() - circle2Radius, circle2Radius); //以圆形2为基准计算左边圆形的位置
        circlePath4.reset();
        circlePath4.addCircle(circle4.getmCenterX(), circle4.getmCenterY(), circle4.getmRadius(), Path.Direction.CCW);

        fillPath.reset();
        fillPath.lineTo(circle4.getmCenterX(), circle4.getmCenterY());
        fillPath.moveTo(circle2.getmCenterX(), circle2.getmCenterY());
        fillPath.lineTo(circle2.getmCenterX(), circle2.getmCenterY() + circle2.getmRadius());
        fillPath.lineTo(circle4.getmCenterX(), circle4.getmCenterY() + circle4.getmRadius());
        fillPath.close();
        mFourPath.addPath(mThreePath);
        mFourPath.addPath(fillPath);
        mFourPath.addPath(circlePath4);
        mFourPath.addPath(mWindPath);
    }

    public void drawTwoCloud(Canvas canvas) {
        canvas.drawPath(mWindPath, mPaint);
    }

    public void drawThreeCloud(Canvas canvas) {
        canvas.drawPath(mThreePath, mPaint);
    }

    public void drawFourCloud(Canvas canvas) {
        canvas.drawPath(mFourPath, mPaint);
    }

    public Path getTwoWindPath() {
        return mWindPath;
    }

    public Path getThreePath() {
        return mThreePath;
    }

    public Path getFourCloud() {
        return mThreePath;
    }
}
