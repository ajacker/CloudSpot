package com.res.cloudspot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.res.cloudspot.R;
import com.res.cloudspot.util.CloudData;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author ajacker
 */
public class ImageListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<CloudData> dataList;

    public ImageListAdapter(Context context, List<CloudData> dataList) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataList = dataList;
    }

    public void setDataList(List<CloudData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void add(int pos, CloudData item) {
        dataList.add(pos, item);
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        dataList.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int p) {
        return dataList.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.clouditem_layout, null);
            holder.imageView = convertView.findViewById(R.id.cloudRadiusImage);
            holder.titleText = convertView.findViewById(R.id.type_text);
            holder.timeText = convertView.findViewById(R.id.save_time_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //显示信息
        holder.titleText.setText(dataList.get(position).type);
        holder.timeText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(dataList.get(position).date));
        if (dataList.get(position).getBitmap() != null) {
            Glide.with(mContext).load(dataList.get(position).getBitmap()).into(holder.imageView);
        }
        return convertView;
    }

    public static class ViewHolder {
        public QMUIRadiusImageView imageView;
        public TextView titleText;
        public TextView timeText;
    }
}

