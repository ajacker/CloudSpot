package com.res.cloudspot.fragment.home;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUIAnimationListView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIFollowRefreshOffsetCalculator;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ArticleListAdapter;
import com.res.cloudspot.base.BaseTabFragment;
import com.res.cloudspot.util.bean.ArticleData;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

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

    @Override
    public View onCreateView() {
        View view = super.onCreateView();
        initTitle();
        initContent();
        initVars();
        return view;
    }

    private void initVars() {
        dataList = new LinkedList<>();
        dataList.add(new ArticleData("什么是卷云?", new Date()));
        dataList.add(new ArticleData("什么是积云？", new Date()));
        dataList.add(new ArticleData("什么是荚状云？", new Date()));
        dataList.add(new ArticleData("什么是云？", new Date()));
        adapter = new ArticleListAdapter(requireContext(), dataList);
        articleList.setAdapter(adapter);
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
                pullToRefresh.postDelayed(() -> {
                    //loadListView();
                    pullToRefresh.finishRefresh();
                    //提示成功信息
                    QMUITipDialog dialog = new QMUITipDialog.Builder(requireContext())
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                            .setTipWord("刷新成功！")
                            .create();
                    dialog.show();
                    requireView().postDelayed(dialog::dismiss, 1000);
                }, 1500);

            }
        });
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
}
