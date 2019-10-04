package com.res.cloudspot.fragment;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qmuiteam.qmui.widget.QMUITopBar;
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
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {


            @TargetApi(21)
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {
                    view.loadUrl("about:blank");
                    view.loadDataWithBaseURL(null, "页面加载出错，请检查网络连接", "text/html", "utf-8", null);
                    //view.loadUrl(mErrorUrl);
                }
            }

            @TargetApi(23)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                int errorCode = error.getErrorCode();
                if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {
                    view.loadUrl("about:blank");
                    view.loadDataWithBaseURL(null, "页面加载出错，请检查网络连接", "text/html", "utf-8", null);
                    //view.loadUrl(mErrorUrl);
                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                int statusCode = errorResponse.getStatusCode();
                if (404 == statusCode || 500 == statusCode) {
                    view.loadUrl("about:blank");// 避免出现默认的错误界面
                    view.loadDataWithBaseURL(null, "错误代码:" + statusCode, "text/html", "utf-8", null);
                    //view.loadUrl(mErrorUrl);
                }
            }
        });

        webView.loadUrl("http://ajacker.tpddns.cn:5500/" + cloudData.type + ".html");

    }
}
