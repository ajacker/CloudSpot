package com.res.cloudspot.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.res.cloudspot.R;

public class TypeViewPagerAdapter extends PagerAdapter {
    private static int[] resources = {
            R.layout.type_first,
            R.layout.type_second,
            R.layout.type_third
    };
    private final Context mContext;

    public TypeViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, resources[position], null);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
