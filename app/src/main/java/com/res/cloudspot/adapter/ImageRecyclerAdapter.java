package com.res.cloudspot.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.res.cloudspot.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private RecyclerView recyclerView;
    private ArrayList<Bitmap> mData;
    private ImageRecyclerAdapter.OnItemClickListener onItemClickListener = null;

    public ImageRecyclerAdapter(Context mContext, ArrayList<Bitmap> mData) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        notifyDataSetChanged();

    }

    public void setmData(ArrayList<Bitmap> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.styleitem_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mData.get(position)).into(holder.imageView);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(position));
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 将RecycleView附加到Adapter上
     */
    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     * 将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public void setOnItemClickListener(ImageRecyclerAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        //参数（父组件，当前单击的View,单击的View的位置）
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        QMUIRadiusImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.style_imageView);
        }

        public QMUIRadiusImageView getImageView() {
            return imageView;
        }
    }
}
