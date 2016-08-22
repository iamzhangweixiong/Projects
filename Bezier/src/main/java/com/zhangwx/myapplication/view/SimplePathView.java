package com.zhangwx.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import com.zhangwx.myapplication.utils.DimenUtils;

/**
 * Created by zhangwx on 2016/8/11.
 */
public class SimplePathView extends View {


    private Paint mPaint;
    private Path mPath;
    private float X1 = DimenUtils.getWindowWidth() / 10;
    private float Y1 = DimenUtils.getWindowHeight() / 10;
    private float X2 = DimenUtils.getWindowWidth() / 2;
    private float Y2 = DimenUtils.getWindowHeight() / 10;
    private float X3 = DimenUtils.getWindowWidth();
    private float Y3 = DimenUtils.getWindowHeight() / 2;
    private float X4 = DimenUtils.getWindowWidth() / 2;
    private float Y4 = DimenUtils.getWindowHeight();

    private float X5 = DimenUtils.getWindowWidth() / 5;
    private float Y5 = DimenUtils.getWindowHeight() / 5;
    private float X6 = DimenUtils.getWindowWidth();
    private float Y6 = DimenUtils.getWindowHeight();


    public SimplePathView(Context context) {
        super(context);
        init();
    }

    public SimplePathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimplePathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimenUtils.dp2px(2));
        mPaint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);

        mPath = new Path();
//        mPath.moveTo(X1, Y1);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        mPath.moveTo(X1, Y1);

        mPath.quadTo(X5, Y5, X6, Y6);
//        mPath.cubicTo(X2, Y2, X3, Y3, X4, Y4);

        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        postInvalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        X2 = event.getX();
        Y2 = event.getY();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {


        return super.onDragEvent(event);
    }
}
