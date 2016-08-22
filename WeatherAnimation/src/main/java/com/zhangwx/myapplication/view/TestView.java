package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.zhangwx.myapplication.utils.DimenUtils;
import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

/**
 * Created by Administrator on 2016/8/10.
 */
public class TestView implements ICapsuleAnim {

    private Paint mPaint;
    private float mX = DimenUtils.getWindowWidth() / 4;
    private float mY = DimenUtils.getWindowWidth() / 5;
    private float dX = DimenUtils.dp2px(1f);
    private float dY = DimenUtils.dp2px(2);
    private boolean hasDraw = false;

    public TestView() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(DimenUtils.dp2px(5));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.translate(dX, dY);
        canvas.save();
//        if (!hasDraw) {
            canvas.drawPoint(mX, mY, mPaint);
            hasDraw = true;
//        }
        canvas.restore();
        dX += DimenUtils.dp2px(1f);
        dY += DimenUtils.dp2px(2f);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getAnimType() {
        return 0;
    }

    @Override
    public void setBaseAlpha(float alpha) {

    }
}
