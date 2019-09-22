package com.res.cloudspot.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ajacker
 */
public class CloudData implements Serializable {

    private static final long serialVersionUID = 1L;
    public byte[] byteBitmap;
    public String type;
    public String comment;
    public Date date;

    public CloudData(Bitmap bitmap, String type) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            this.byteBitmap = baos.toByteArray();
        } catch (Exception ignored) {
        }
        this.type = type;
        this.date = new Date();
    }

    public Bitmap getBitmap() {
        return BitmapFactory.decodeByteArray(byteBitmap, 0, byteBitmap.length);
    }
}
