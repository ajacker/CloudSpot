package com.res.cloudspot.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUIAnimationListView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIFollowRefreshOffsetCalculator;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ArticleListAdapter;
import com.res.cloudspot.base.BaseTabFragment;
import com.res.cloudspot.fragment.CloudFragment;
import com.res.cloudspot.util.HttpUtil;
import com.res.cloudspot.util.bean.ArticleData;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

import static com.res.cloudspot.util.HttpUtil.ARTICLE_MESSAGE;

/**
 * @author ajacker
 */
public class ArticleTabFragment extends BaseTabFragment {

    @BindView(R.id.article_list)
    QMUIAnimationListView articleList;
    @BindView(R.id.pull_to_refresh)
    QMUIPullRefreshLayout pullToRefresh;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;

    List<ArticleData> dataList;
    ArticleListAdapter adapter;
    private ArticleHandler handler;

    @Override
    public View onCreateView() {
        View view = super.onCreateView();
        initTitle();
        initContent();
        initVars();
        return view;
    }

    private void initVars() {
        handler = new ArticleHandler(this);
        dataList = new LinkedList<>();
        adapter = new ArticleListAdapter(requireContext(), dataList);
        articleList.setAdapter(adapter);
        articleList.post(() -> HttpUtil.getArticleList(handler));
    }

    private void initContent() {
        pullToRefresh.setRefreshOffsetCalculator(new QMUIFollowRefreshOffsetCalculator());
        pullToRefresh.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                HttpUtil.getArticleList(handler);

            }
        });
    }

    private void loadListView() {
        adapter.setDataList(dataList);
        pullToRefresh.finishRefresh();
        //提示成功信息
        QMUITipDialog dialog = new QMUITipDialog.Builder(requireContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("载入成功！")
                .create();
        dialog.show();
        requireView().postDelayed(dialog::dismiss, 1000);
    }

    @OnItemClick(R.id.article_list)
    void onArticleClicked(int pos) {
        ArticleData curData = dataList.get(pos);
        Context context = requireContext();
        Bundle data = new Bundle();
        data.putString("address", curData.title);
        Intent intent = MainActivity.of(context, CloudFragment.class, data);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    @Override
    protected View getRootView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_article, null);
    }

    private String getTitle() {
        return getResources().getString(R.string.tab_article);
    }

    private void initTitle() {
        topbar.setTitle(getTitle());
    }

    static class ArticleHandler extends Handler {
        private WeakReference<ArticleTabFragment> mOuter;

        ArticleHandler(ArticleTabFragment activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ArticleTabFragment outer = mOuter.get();
            if (outer != null) {
                if (msg.what == ARTICLE_MESSAGE) {
                    outer.dataList = (List<ArticleData>) msg.obj;
                    outer.loadListView();
                }
            }
        }
    }
}
