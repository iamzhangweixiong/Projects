package com.zhangwx.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import com.zhangwx.myapplication.view.Interface.ICapsuleAnim;

/**
 * Created by zhangwx on 2016/7/13.
 */
public class CapsuleView extends View {

    private ICapsuleAnim rainview = null;
    private ICapsuleAnim cloudview = null;
    private ICapsuleAnim sunnyview = null;
    private ICapsuleAnim snowview = null;
    private ICapsuleAnim thunderstormview = null;
    private ICapsuleAnim Overcastview = null;
    private ICapsuleAnim testView = null;

    public CapsuleView(Context context) {
        super(context);
        init();
    }

    public CapsuleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CapsuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        rainview = new RainView(this);
        rainview.start();

        snowview = new SnowView(this);
        snowview.start();

        sunnyview = new SunnyView(this);
        sunnyview.start();

        thunderstormview = new ThunderStormView(this);
        thunderstormview.start();

        cloudview = new CloudView();
        cloudview.start();

        Overcastview = new OvercastView(this);
        Overcastview.start();

        testView = new TestView();
        testView.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {

//        cloudview.draw(canvas);
//        thunderstormview.draw(canvas);
//        rainview.draw(canvas);
//        sunnyview.draw(canvas);
//        snowview.draw(canvas);
//        Overcastview.draw(canvas);
        testView.draw(canvas);
        postInvalidateDelayed(8);


    }


}
