package com.res.cloudspot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.res.cloudspot.R;

import java.util.ArrayList;

public class ImageViewerAdapter extends RecyclerView.Adapter<ImageViewerAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<String> urls;

    public ImageViewerAdapter(Context mContext, ArrayList<String> urls) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.urls = urls;
        notifyDataSetChanged();

    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.imgitem_layout, parent, false);
        return new ImageViewerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerAdapter.ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error);
        Glide.with(mContext).load(urls.get(position)).apply(options).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_peek);
        }
    }
}
