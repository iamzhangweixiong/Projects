package com.zhangwx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button recycleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleBtn = $(R.id.RecycleActivity);
        recycleBtn.setOnClickListener(this);
    }


    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.RecycleActivity:
                intent.setClass(this, RecycleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
