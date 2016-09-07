package com.zhangwx.myapplication.view;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhangwx on 2016/9/5.
 */
public class WaveView extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = "WaveViewThread";
    private final Object mSurfaceLock = new Object();

    private DrawThread mThread;
    private SurfaceHolder mSurfaceHolder;
    private WaveViewDrawer mWaveViewDrawer;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mWaveViewDrawer = new WaveViewDrawer();
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new DrawThread(holder);
        mThread.setAllowDraw(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //这里可以获取SurfaceView的宽高等信息
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //这里需要加锁，否则 drawWave 绘制过程中有可能会crash
        synchronized (mSurfaceLock) {
            mThread.setAllowDraw(false);
        }
    }

    private class DrawThread extends Thread {
        private SurfaceHolder mHolder;
        private boolean mIsAllowDraw = false;
        private final long REFRESH_TIME = 10;

        public DrawThread(SurfaceHolder holder) {
            super(TAG);
            mHolder = holder;
        }

        @Override
        public void run() {
            long startAt = System.currentTimeMillis();
            while (true) {
                synchronized (mSurfaceLock) {
                    if (!mIsAllowDraw) {
                        return;
                    }
                    Canvas canvas = mHolder.lockCanvas();
                    if (canvas != null) {
                        mWaveViewDrawer.drawWave(canvas, System.currentTimeMillis() - startAt);
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    Thread.sleep(REFRESH_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setAllowDraw(boolean isAllowDraw) {
            this.mIsAllowDraw = isAllowDraw;
        }
    }
}
