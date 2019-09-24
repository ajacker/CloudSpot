package com.res.cloudspot.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUIVerticalTextView;
import com.res.cloudspot.R;
import com.res.cloudspot.util.StringUtil;
import com.watermark.androidwm.WatermarkBuilder;
import com.watermark.androidwm.bean.WatermarkImage;
import com.watermark.androidwm.bean.WatermarkText;

public class TypeViewPagerAdapter extends PagerAdapter {
    private static int[] resources = {
            R.layout.type_first,
            R.layout.type_second,
            R.layout.type_third
    };
    private final Context mContext;
    public static Typeface typeface;
    private final Bitmap data;


    public TypeViewPagerAdapter(Context context, Bitmap data) {
        mContext = context;
        this.data = data;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/poem_font2.ttf");
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, resources[position], null);
        //添加图片
        ImageView imageView = view.findViewById(R.id.pic_imageView);
        WatermarkText watermarkText = new WatermarkText("卷云")
                .setPositionX(0.01)
                .setPositionY(0.92)
                .setTextAlpha(255)
                .setTextFont(R.font.siyuan)
                .setTextShadow(0.5f, 0.5f, 0.5f, Color.BLACK)
                .setTextColor(Color.WHITE);


        WatermarkImage watermarkImage = new WatermarkImage(mContext, R.mipmap.icon)
                .setPositionX(0.84)
                .setPositionY(0.84)
                .setSize(0.2)
                .setImageAlpha(255);

        Bitmap after = WatermarkBuilder.create(mContext, data)
                .loadWatermarkText(watermarkText)
                .loadWatermarkImage(watermarkImage)
                .getWatermark().getOutputImage();


        Glide.with(mContext).load(after).into(imageView);


        //添加诗词
        LinearLayout layout = view.findViewById(R.id.poem_layout);
        String[] lines = StringUtil.poems.get(position).split("\n");
        for (int i = lines.length - 1; i >= 0; i--) {
            QMUIVerticalTextView textView = new QMUIVerticalTextView(mContext);
            textView.setVerticalMode(true);
            textView.setTypeface(typeface);
            textView.setTextSize(16);
            textView.setText(lines[i]);
            layout.addView(textView);
        }
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
