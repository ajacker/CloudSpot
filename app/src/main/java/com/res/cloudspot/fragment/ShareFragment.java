package com.res.cloudspot.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.util.QMUIDrawableHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageRecyclerAdapter;
import com.res.cloudspot.adapter.TypeViewPagerAdapter;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.util.BitmapUtil;
import com.res.cloudspot.util.ViewPagerForScrollView;
import com.res.cloudspot.util.bean.CloudData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopbar;
    @BindView(R.id.style_recyclerView)
    RecyclerView mStyleRecyclerView;
    @BindView(R.id.viewPager)
    ViewPagerForScrollView viewPager;
    @BindView(R.id.sharePicView)
    ScrollView sharePicView;


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
        ArrayList<Integer> mData = new ArrayList<>();
//        Bitmap map1 = BitmapFactory.decodeResource(getResources(),R.mipmap.type1);
//        Bitmap map2 = BitmapFactory.decodeResource(getResources(),R.mipmap.type1);
//        Bitmap map3 = BitmapFactory.decodeResource(getResources(),R.mipmap.type1);
        mData.add(R.mipmap.type1);
        mData.add(R.mipmap.type2);
        mData.add(R.mipmap.type3);
        adapter = new ImageRecyclerAdapter(requireContext(), mData);
        mStyleRecyclerView.setAdapter(adapter);
    }

    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        cloudData = (CloudData) data.getSerializable("data");

        pagerAdapter = new TypeViewPagerAdapter(requireContext(), cloudData.getBitmap(), cloudData.type);
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

        Button btnShare = mTopbar.addRightTextButton("分享", QMUIViewHelper.generateViewId());
        btnShare.setTextColor(getResources().getColor(R.color.qmui_config_color_white));
        btnShare.setOnClickListener(v -> shareAsPic());
    }

    /**
     * 生成图片分享
     */
    private void shareAsPic() {
        QMUIDialog.CustomDialogBuilder dialogBuilder = new QMUIDialog.CustomDialogBuilder(getContext());
        dialogBuilder.setLayout(R.layout.sharepic_layout);
        final QMUIDialog dialog = dialogBuilder.setTitle("分享以下图片").create();

        ImageView displayImageView = dialog.findViewById(R.id.createFromViewDisplay);
        QMUIRoundButton shareButton = dialog.findViewById(R.id.shareButton);
        QMUIRoundButton cancelButton = dialog.findViewById(R.id.cancelButton);

        Bitmap createFromViewBitmap = QMUIDrawableHelper.createBitmapFromView(sharePicView);
        displayImageView.setImageBitmap(createFromViewBitmap);

        displayImageView.setOnClickListener(v -> dialog.dismiss());
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        shareButton.setOnClickListener(v -> BitmapUtil.share(requireContext(), createFromViewBitmap));

        dialog.show();
    }

}

