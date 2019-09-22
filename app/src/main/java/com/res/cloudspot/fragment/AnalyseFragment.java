package com.res.cloudspot.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageRecyclerAdapter;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.util.CloudData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnalyseFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopbar;
    @BindView(R.id.cloudImageView)
    QMUIRadiusImageView cloudImageView;
    @BindView(R.id.type_text)
    TextView typeText;
    @BindView(R.id.type_imageView)
    QMUIRadiusImageView typeImageView;
    @BindView(R.id.infoBtn)
    QMUIRoundButton infoBtn;
    @BindView(R.id.shareBtn)
    QMUIRoundButton shareBtn;

    private ImageRecyclerAdapter adapter;

    private CloudData cloudData;


    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cloudanalyse, null);
        ButterKnife.bind(this, root);

        initTopBar();
        initContentView();
        initVars();
        return root;
    }

    private void initVars() {
    }

    @OnClick(R.id.shareBtn)
    void openShareFragment() {
        Context context = requireContext();
        Bundle data = new Bundle();
        data.putSerializable("data", cloudData);
        Intent intent = MainActivity.of(context, ShareFragment.class, data);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @OnClick(R.id.infoBtn)
    void openCloudFragment() {
        Context context = requireContext();
        Bundle data = new Bundle();
        data.putSerializable("data", cloudData);
        Intent intent = MainActivity.of(context, CloudFragment.class, data);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        cloudData = (CloudData) data.getSerializable("data");
        assert cloudData != null;
        cloudImageView.setImageBitmap(cloudData.getBitmap());
        typeText.setText(cloudData.type);

    }

    private void initTopBar() {
        mTopbar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());
        mTopbar.setTitle("分析结果");
    }
}
