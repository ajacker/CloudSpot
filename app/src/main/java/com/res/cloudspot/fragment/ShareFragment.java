package com.res.cloudspot.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageRecyclerAdapter;
import com.res.cloudspot.adapter.TypeViewPagerAdapter;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.util.CloudData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopbar;
    @BindView(R.id.style_recyclerView)
    RecyclerView mStyleRecyclerView;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;


    private ImageRecyclerAdapter adapter;
    private TypeViewPagerAdapter pagerAdapter;
    private int lastSelected = 0;

    private CloudData cloudData;



    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_share, null);
        ButterKnife.bind(this, root);
        initTopBar();
        initVars();
        initContentView();
        return root;
    }


    private void initVars() {
        ArrayList<Bitmap> mData = new ArrayList<>();
        Bitmap map = Bitmap.createBitmap(500, 500,
                Bitmap.Config.ARGB_8888);
        map.eraseColor(Color.parseColor("#FF0000"));
        mData.add(map);
        mData.add(map);
        mData.add(map);
        mData.add(map);
        mData.add(map);
        mData.add(map);
        mData.add(map);
        mData.add(map);
        adapter = new ImageRecyclerAdapter(requireContext(), mData);
        mStyleRecyclerView.setAdapter(adapter);
    }

    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        cloudData = (CloudData) data.getSerializable("data");

        pagerAdapter = new TypeViewPagerAdapter(requireContext());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSwipeable(false);

        viewPager.post(() -> setSelectedOutline(0, true));

        //设置recycleView的选中事件
        adapter.setOnItemClickListener((position) -> {
            //设置选中边框
            if (position != lastSelected) {
                //取消之前选中的边框
                setSelectedOutline(lastSelected, false);
                //添加选中边框
                setSelectedOutline(position, true);
                lastSelected = position;
            }
            viewPager.setCurrentItem(position, true);
        });
    }

    /**
     * 设置item的选中边框
     *
     * @param index 序号
     * @param flag  是否选中
     */
    private void setSelectedOutline(int index, boolean flag) {
        View curChild = mStyleRecyclerView.getChildAt(index);
        ImageRecyclerAdapter.ViewHolder cur = (ImageRecyclerAdapter.ViewHolder) mStyleRecyclerView.getChildViewHolder(curChild);
        cur.getImageView().setBorderWidth(flag ? 4 : 0);
    }

    private void initTopBar() {
        mTopbar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());
        mTopbar.setTitle("分享美图");
    }

}

