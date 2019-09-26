package com.res.cloudspot.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

public class BitmapUtil {
    /**
     * 获取资源图片的宽度或者高度
     *
     * @param resources 资源
     * @param id        id
     * @param type      0是宽度，其它为高度
     * @return 宽度或者高度
     */
    public static int getImageWidthHeight(Resources resources, int id, int type) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, id, options);
        return type == 0 ? options.outWidth : options.outHeight;
    }

    /**
     * 分享图片
     *
     * @param context
     * @param bitmap
     */
    public static void share(Context context, Bitmap bitmap) {
        File file = CameraUtil.createImageFile(Locale.getDefault(), context, "share");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.setPackage("com.tencent.mobileqq");
        shareIntent.putExtra(Intent.EXTRA_STREAM, CameraUtil.getUriFromFile(context, file));
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }
}
