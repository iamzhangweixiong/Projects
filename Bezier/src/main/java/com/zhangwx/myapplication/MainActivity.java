package com.zhangwx.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.zhangwx.myapplication.view.SimplePathView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SimplePathView mSimplePathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimplePathView = (SimplePathView) findViewById(R.id.simpleView);
        mSimplePathView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
