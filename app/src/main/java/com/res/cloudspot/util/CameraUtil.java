package com.res.cloudspot.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraUtil {
    public final static int TAKE_PHOTO = 788;
    public final static int SELECT_PHOTO_FROM_ALBUM = 789;
    public final static int REQ_CROP = 790;

    public static File createImageFile(Locale locale, Context context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", locale).format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File saveDir = context.getExternalFilesDir("clouds");
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    public static File createImageFile(Locale locale, Context context, String dir) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", locale).format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File saveDir = context.getExternalFilesDir(dir);
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }


    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 90;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 800) {
            // 重置baos即清空baos
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // 这里压缩options%，把压缩后的数据存放到baos中
            //设置最小值，防止低于0时出异常
            if (options > 10) {
                options -= 10;
            }
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(isBm, null, null);

    }

    public static Uri getUriFromFile(Context context, File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(context, "com.res.cloudspot.FileProvider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static String getRealPathFromUri(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) {
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
