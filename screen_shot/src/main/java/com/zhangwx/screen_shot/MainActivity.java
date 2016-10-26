package com.zhangwx.screen_shot;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import com.zhangwx.screen_shot.Utils.FileUtils;
import com.zhangwx.screen_shot.Utils.MyWebViewClient;
import com.zhangwx.screen_shot.Utils.WebViewHelper;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;
    private Toolbar mToolbar;
    private Bitmap mBitmap;
    private EditText mAddress;
    private Button mLoadBtn;
    private WebViewHelper mWebViewHelper;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener((view) -> takeShot());

        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.setWillNotCacheDrawing(false);
        mWebview.setDrawingCacheEnabled(true);
        mWebViewHelper = new WebViewHelper();
        mWebViewHelper.setWebSettings(mWebview);
        mWebview.setWebViewClient(new MyWebViewClient());

        mAddress = (EditText) findViewById(R.id.address);
        mLoadBtn = (Button) findViewById(R.id.loadBtn);
        mLoadBtn.setOnClickListener((view) -> loadUrl());
    }

    private void takeShot() {
        mBitmap = mWebViewHelper.getViewDrawingCache(mWebview, true, Bitmap.Config.ARGB_8888);

        FileUtils.saveBitmapToFile(this, mBitmap,
                FileUtils.createImageFile(this, null).getPath(), true);
    }

    private void loadUrl() {
        mWebview.loadUrl("http://" + mAddress.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mWebview.destroyDrawingCache();
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
