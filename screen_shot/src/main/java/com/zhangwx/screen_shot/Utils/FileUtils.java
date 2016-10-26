package com.zhangwx.screen_shot.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by zhangwx on 2016/8/24.
 */
public class FileUtils {

    // 图片最大大小 100KB 超过此大小要进行压缩
    public static int MAX_PIC_MEMORY_SIZE = 1500;
    //最大尺寸 w
    public static int MAX_PIC_WIDTH = 720;
    //最大尺寸 h
    public static int MAX_PIC_HEIGHT = 480;
    //长图的最大宽度
    public static int LONG_PIC_MAX_WIDTH = 100;

    private static String getBasePath(Context context) {
        return getStoragePath("zhangCache");
    }

    private static String getStoragePath(String baseName) {
        String path = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(path)) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        }
        File file = new File(path + "/" + baseName);
        file.mkdirs();
        return file.getAbsolutePath() + "/";
    }

    public static File createImageFile(Context context) {
        return createImageFile(context, null);
    }

    public static File createImageFile(Context context, @Nullable String name) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        name = TextUtils.isEmpty(name) ? timeStamp : name;
        File file = new File(getBasePath(context) + name + ".jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * bitmap 保存到本地
     *
     * @param bitmap
     * @param filePath
     * @param saveHQ   高清图
     */
    public static void saveBitmapToFile(Context context, Bitmap bitmap, String filePath, boolean saveHQ) {
        ByteArrayOutputStream byteArrayOps = null;
        FileOutputStream fileOps = null;
        try {
            byteArrayOps = new ByteArrayOutputStream();
            int options = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, options, byteArrayOps);

            if (!saveHQ) {
                while (byteArrayOps.toByteArray().length / 1024 > MAX_PIC_MEMORY_SIZE && options > 0) {
                    byteArrayOps.reset();
                    options -= 10;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, byteArrayOps);
                }
            }

            fileOps = new FileOutputStream(filePath);
            fileOps.write(byteArrayOps.toByteArray());

            notifyScan(context, filePath);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOps != null) {
                    byteArrayOps.flush();
                    byteArrayOps.close();
                }
                if (fileOps != null) {
                    fileOps.flush();
                    fileOps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void notifyScan(Context context, String path) {
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }

}
