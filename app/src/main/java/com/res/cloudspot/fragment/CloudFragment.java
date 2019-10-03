package com.res.cloudspot.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.res.cloudspot.R;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.util.CloudData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ajacker
 */
public class CloudFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.webView)
    QMUIWebView webView;

    private CloudData cloudData;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cloud, null);
        ButterKnife.bind(this, root);

        initTopBar();
        initContentView();
        return root;
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());

        mTopBar.setTitle("云朵介绍");

    }



    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        cloudData = (CloudData) data.getSerializable("data");
        assert cloudData != null;

        webView.loadUrl("http://ajacker.tpddns.cn:5500/" + cloudData.type + ".html");
    }
}
