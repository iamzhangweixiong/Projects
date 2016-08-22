package com.zhangwx.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.zhangwx.myapplication.view.CapsuleView;

public class MainActivity extends AppCompatActivity {


    private CapsuleView mCapsuleView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCapsuleView = (CapsuleView) findViewById(R.id.baseview);





    }
}
