package com.zhangwx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button recycleBtn;
    private Button viewPagerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleBtn = $(R.id.RecycleBtn);
        recycleBtn.setOnClickListener(this);
        viewPagerBtn = $(R.id.ViewPagerBtn);
        viewPagerBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.RecycleBtn:
                intent.setClass(this, RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.ViewPagerBtn:
                intent.setClass(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
        }
    }

    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }
}
