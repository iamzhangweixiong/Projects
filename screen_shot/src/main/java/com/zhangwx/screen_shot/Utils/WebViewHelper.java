package com.zhangwx.screen_shot.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.View.MeasureSpec;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.zhangwx.screen_shot.R;

/**
 * Created by zhangwx on 2016/9/28.
 */
public class WebViewHelper {

    public final static String TAG_USER_AGENT = "zhangwx";
    private final static int MAX_Q = 100;

    public void setWebSettings(WebView webview) {
        WebSettings settings = webview.getSettings();
        if (settings != null) {
            settings.setUserAgentString(settings.getUserAgentString() + " " + TAG_USER_AGENT);
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(false);   // 不支持手势缩放
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setDomStorageEnabled(true);
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            settings.setDatabaseEnabled(false);
            settings.setAppCacheEnabled(false);
            settings.setAllowFileAccess(false);
            settings.setSavePassword(false);
            settings.setSupportMultipleWindows(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                settings.setAllowUniversalAccessFromFileURLs(true);//支持跨域
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {   // 隐藏缩放块
                settings.setDisplayZoomControls(false);
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                settings.setPluginState(WebSettings.PluginState.ON);
                settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            }
        }
    }

    /**
     * 获取 View 的 drawingCache
     * @param view
     * @param useCache
     * @param config
     * @return
     */
    public Bitmap getViewDrawingCache(View view, boolean useCache, Bitmap.Config config) {
        Bitmap bitmap = (Bitmap) view.getTag(R.id.CACHE_BITMAP_KEY);
        Boolean dirty = (Boolean) view.getTag(R.id.CACHE_BITMAP_DIRTY_KEY);

        //设置最大宽高
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(
                0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        if (bitmap == null || bitmap.getWidth() != viewWidth || bitmap.getHeight() != viewHeight) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            bitmap = Bitmap.createBitmap(viewWidth, viewHeight, config);
            view.setTag(R.id.CACHE_BITMAP_KEY, bitmap);
            dirty = true;
        }
        if (dirty == true || !useCache) {
            bitmap.eraseColor(Color.WHITE);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            view.setTag(R.id.CACHE_BITMAP_DIRTY_KEY, false);
        }
        return bitmap;
    }


    public void cleanWebViewContent(WebView webView) {
        if (webView != null) {
            webView.loadUrl("about:blank");
        }
    }
}
