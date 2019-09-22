package com.res.cloudspot.util;

import android.os.Handler;
import android.os.Message;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static final int UPLOAD_MESSAGE = 444;
    private static final String URL = "http://39.106.202.5:8888";
    private static MediaType mediaType = MediaType.parse("application/octet-stream");
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static void upLoadPic(File file, Handler handler) {
        String filename = file.getName();
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, RequestBody.create(file, mediaType))
                .build();
        final Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Message message = handler.obtainMessage(UPLOAD_MESSAGE);
                message.arg1 = -1;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                Message message = handler.obtainMessage(UPLOAD_MESSAGE);
                try {
                    message.arg1 = Integer.parseInt(body.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                    message.arg1 = -1;
                }
                handler.sendMessage(message);
            }
        });
    }
}
