package com.res.cloudspot.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.res.cloudspot.util.bean.CloudData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public void setDataList(String tag, List<CloudData> datalist) throws IOException {
        if (null == datalist || datalist.size() <= 0) {
            return;
        }
        //先将序列化结果写到byte缓存中，事实上就分配一个内存空间
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        //将对象序列化写入byte缓存
        os.writeObject(datalist);
        //将序列化的数据转为16进制保存
        String temp = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
        editor.clear();
        editor.putString(tag, temp);
        editor.commit();
    }

    /**
     * 获取List
     *
     * @param key
     * @return
     */
    public ArrayList<CloudData> getDataList(String key) {
        String str = preferences.getString(key, null);
        try {
            assert str != null;
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(str.getBytes(), Base64.DEFAULT));
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(bais);
            return (ArrayList) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

}
