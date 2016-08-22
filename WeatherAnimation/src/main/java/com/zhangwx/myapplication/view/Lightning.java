package com.zhangwx.myapplication.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by zhangwx on 2016/7/22.
 */
public class Lightning {

    private Path mPath1 = null;
    private Paint mPaint = null;
    private float mBaseAlpha = 1f;

    public Lightning() {
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(252, 214, 3));
        mPaint.setAntiAlias(true);
        mPath1 = new Path();
    }

    public void drawLightning(Canvas canvas, int alpha, float[][] triangleAxis) {
        mPath1.reset();
        mPath1.moveTo(triangleAxis[0][0], triangleAxis[0][1]);
        mPath1.lineTo(triangleAxis[1][0], triangleAxis[1][1]);
        mPath1.lineTo(triangleAxis[2][0], triangleAxis[2][1]);
        mPath1.lineTo(triangleAxis[3][0], triangleAxis[3][1]);
        mPath1.lineTo(triangleAxis[4][0], triangleAxis[4][1]);
        mPath1.lineTo(triangleAxis[5][0], triangleAxis[5][1]);
        mPath1.lineTo(triangleAxis[0][0], triangleAxis[0][1]);
        mPath1.close();
        mPaint.setAlpha((int) (mBaseAlpha * alpha));
        canvas.drawPath(mPath1, mPaint);
    }

    public void setBaseAlpha(float baseAlpha) {
        this.mBaseAlpha = baseAlpha;
    }
}
