package com.res.cloudspot.util;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.res.cloudspot.util.bean.ArticleData;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author ajacker
 */
public class HttpUtil {

    public static final int UPLOAD_MESSAGE = 444;
    public static final int ARTICLE_MESSAGE = 555;
    public static final int READ_MESSAGE = 666;
    private static MediaType mediaType = MediaType.parse("application/octet-stream");
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static void upLoadPic(File file, Handler handler) {
        final String url = "http://39.106.202.5:8888";
        String filename = file.getName();
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, RequestBody.create(file, mediaType))
                .build();
        final Request request = new Request.Builder()
                .url(url)
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

    public static void getArticleList(Handler handler) {
        final String url = "http://39.106.202.5:8080/articles";
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create("", null))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = handler.obtainMessage(ARTICLE_MESSAGE);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                message.obj = gson.<List<ArticleData>>fromJson(result, new TypeToken<List<ArticleData>>() {
                }.getType());
                handler.sendMessage(message);
            }
        });
    }


}
