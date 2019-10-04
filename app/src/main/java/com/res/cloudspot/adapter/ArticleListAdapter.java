package com.res.cloudspot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.res.cloudspot.R;
import com.res.cloudspot.util.bean.ArticleData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArticleListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ArticleData> dataList;

    public ArticleListAdapter(Context context, List<ArticleData> dataList) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataList = dataList;
    }

    public void setDataList(List<ArticleData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArticleListAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ArticleListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.articleitem_layout, null);
            holder.titleText = convertView.findViewById(R.id.article_title_text);
            holder.timeText = convertView.findViewById(R.id.article_time_text);
            convertView.setTag(holder);
        } else {
            holder = (ArticleListAdapter.ViewHolder) convertView.getTag();
        }
        //装填数据
        holder.titleText.setText(dataList.get(position).title);
        Date date = dataList.get(position).date;
        holder.timeText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date));
        return convertView;
    }

    public static class ViewHolder {
        public TextView titleText;
        public TextView timeText;
    }
}
