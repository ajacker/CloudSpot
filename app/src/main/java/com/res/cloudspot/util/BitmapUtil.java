package com.res.cloudspot.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
}
