package com.zhangwx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Z_UI.ViewUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.$(this, R.id.RecycleBtn).setOnClickListener(this);
        ViewUtils.$(this, R.id.ViewPagerBtn).setOnClickListener(this);
        ViewUtils.$(this, R.id.HandlerThreadBtn).setOnClickListener(this);
        ViewUtils.$(this, R.id.TouchTestBtn).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.RecycleBtn:
                intent.setClass(this, RecycleActivity.class);
                break;
            case R.id.ViewPagerBtn:
                intent.setClass(this, ViewPagerActivity.class);
                break;
            case R.id.HandlerThreadBtn:
                intent.setClass(this, HandlerThreadActivity.class);
                break;
            case R.id.TouchTestBtn:
                intent.setClass(this, TouchTestActivity.class);
                break;
        }
        startActivity(intent);
    }

}
