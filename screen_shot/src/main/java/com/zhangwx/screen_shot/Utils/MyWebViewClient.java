package com.zhangwx.screen_shot.Utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhangwx on 2016/9/28.
 */
public class MyWebViewClient extends WebViewClient {


    public MyWebViewClient() {
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        // 6.0以上版本也可能调用这个方法导致重复
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return;
        }

    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url,
                                       boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

}
