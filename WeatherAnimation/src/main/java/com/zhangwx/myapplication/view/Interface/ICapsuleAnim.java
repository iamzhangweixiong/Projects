package com.zhangwx.myapplication.view.Interface;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/7/13.
 */
public interface ICapsuleAnim {

    void draw(Canvas canvas);

    void start();

    void stop();

    int getAnimType();

    void setBaseAlpha(float alpha);
}
