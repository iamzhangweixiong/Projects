package com.zhangwx.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private AtomicInteger atomicInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
