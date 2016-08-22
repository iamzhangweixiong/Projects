package com.zhangwx.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhangwx.myapplication.view.SimplePathView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private SimplePathView mSimplePathView;
    private ImageView imageview;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mSimplePathView = (SimplePathView) findViewById(R.id.simpleView);
        imageview = (ImageView) findViewById(R.id.change);
        imageview.setOnClickListener(this);
        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        imageview.setColorFilter(Color.YELLOW);
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        imageview.setColorFilter(Color.BLACK);
                        break;
                }


                return false;
            }
        });
//        mSimplePathView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.change:

                break;
        }
    }
}
