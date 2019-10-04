package com.res.cloudspot.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageRecyclerAdapter;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.fragment.home.HistoryTabFragment;
import com.res.cloudspot.util.StringUtil;
import com.res.cloudspot.util.bean.CloudData;

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

        try {
            initTopBar();
        } catch (Exception ignored) {
        }
        initContentView();
        initVars();
        return root;
    }

    private void initVars() {
    }

    @OnClick(R.id.type_imageView)
    void openImageViewerFragment() {
        Context context = requireContext();
        Bundle data = new Bundle();
        data.putString("type", cloudData.type);
        Intent intent = MainActivity.of(context, ImageViewerFragment.class, data);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

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

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .centerCrop()
                .error(R.drawable.error);
        Glide.with(requireContext()).load(StringUtil.imgUrls.get(cloudData.type).get(0)).apply(options).into(typeImageView);
    }

    private void initTopBar() {
        mTopbar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());
        mTopbar.setTitle("分析结果");
        Button saveBtn = mTopbar.addRightTextButton("保存", QMUIViewHelper.generateViewId());
        saveBtn.setTextColor(getResources().getColor(R.color.qmui_config_color_white));
        saveBtn.setOnClickListener(v -> {
            HistoryTabFragment.dataList.add(cloudData);
            QMUITipDialog dialog = new QMUITipDialog.Builder(getContext())
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                    .setTipWord("保存成功！")
                    .create();
            dialog.show();
            requireView().postDelayed(() -> {
                dialog.dismiss();
                popBackStack();
            }, 700);
        });
    }
}
