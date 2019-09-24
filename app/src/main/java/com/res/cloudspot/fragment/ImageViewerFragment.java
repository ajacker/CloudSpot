package com.res.cloudspot.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageViewerAdapter;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewerFragment extends BaseFragment {
    @BindView(R.id.image_viewer)
    RecyclerView imageViewer;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    ImageViewerAdapter adapter;
    String type;
    @BindView(R.id.content_layout)
    LinearLayout layout;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_imgviewer, null);
        ButterKnife.bind(this, root);
        initTopBar();
        initVars();
        initContentView();
        return root;
    }

    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        type = data.getString("type");


        adapter = new ImageViewerAdapter(requireContext(), StringUtil.imgUrls.get(type));
        imageViewer.setAdapter(adapter);
    }

    private void initVars() {

    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());
        mTopBar.setTitle("览云");
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layout.getLayoutParams());
//        params.topMargin += QMUIStatusBarHelper.getStatusbarHeight(requireContext());
//        layout.setLayoutParams(params);
    }
}
