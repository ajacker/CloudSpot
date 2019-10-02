package com.res.cloudspot.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUIAnimationListView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIFollowRefreshOffsetCalculator;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;
import com.res.cloudspot.adapter.ImageListAdapter;
import com.res.cloudspot.base.BaseTabFragment;
import com.res.cloudspot.fragment.AnalyseFragment;
import com.res.cloudspot.util.CloudData;
import com.res.cloudspot.util.ListDataSave;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;


/**
 * @author ajacker
 */
public class HistoryTabFragment extends BaseTabFragment {

    public static List<CloudData> dataList;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.history_list)
    QMUIAnimationListView mHistoryList;
    @BindView(R.id.pull_to_refresh)
    QMUIPullRefreshLayout mPullToRefresh;
    private ImageListAdapter adapter;
    private ListDataSave dataSave;
    private boolean deleteMode;
    private List<CloudData> selectedList = new LinkedList<>();


    @Override
    public View onCreateView() {
        View view = super.onCreateView();
        initTitle();
        initContent();
        initVars();
        return view;
    }

    private void initVars() {
        //读取数据
        dataSave = new ListDataSave(requireContext(), "clouds");
        dataList = dataSave.getDataList("history");
        //初始化list
        adapter = new ImageListAdapter(getContext(), dataList);
        mHistoryList.setAdapter(adapter);
    }

    private void changeDeleteMode() {
        if (!deleteMode) {
            Button deleteBtn = mTopBar.addRightTextButton("删除", QMUIViewHelper.generateViewId());
            deleteBtn.setTextColor(getResources().getColor(R.color.qmui_config_color_white));
            deleteBtn.setOnClickListener(v -> {
                dataList.removeAll(selectedList);
                selectedList.clear();
                deleteMode = false;
                mTopBar.removeAllRightViews();
                for (int i = 0; i < mHistoryList.getChildCount(); i++) {
                    mHistoryList.getChildAt(i).setBackground(null);
                }
                loadListView();
            });
            Button cancelBtn = mTopBar.addRightTextButton("取消", QMUIViewHelper.generateViewId());
            cancelBtn.setTextColor(getResources().getColor(R.color.qmui_config_color_white));
            cancelBtn.setOnClickListener(v -> {
                deleteMode = false;
                mTopBar.removeAllRightViews();
            });
        }
        deleteMode = true;
    }

    @OnItemClick(R.id.history_list)
    void onItemClick(int position) {
        if (deleteMode) {
            if (selectedList.contains(dataList.get(position))) {
                System.out.println("取消选中");
                selectedList.remove(dataList.get(position));
                mHistoryList.getChildAt(position).setBackground(null);
            } else {
                System.out.println("选中");
                selectedList.add(dataList.get(position));
                mHistoryList.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.app_color_blue));
            }
        } else {
            Context context = requireContext();
            Bundle data = new Bundle();
            CloudData cloudData = dataList.get(position);
            data.putSerializable("data", cloudData);
            Intent intent = MainActivity.of(context, AnalyseFragment.class, data);
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }

    @OnItemLongClick(R.id.history_list)
    void onItemLongClick(int pos) {
        if (!deleteMode) {
            changeDeleteMode();
            onItemClick(pos);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        loadListView();
    }


    private void initContent() {
        mPullToRefresh.setRefreshOffsetCalculator(new QMUIFollowRefreshOffsetCalculator());
        mPullToRefresh.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                mPullToRefresh.postDelayed(() -> {
                    loadListView();
                    mPullToRefresh.finishRefresh();
                }, 2000);
            }
        });
    }

    private void loadListView() {
        try {
            dataSave.setDataList("history", dataList);
        } catch (IOException ignored) {
        }
        adapter.setDataList(dataList);
    }


    @Override
    protected View getRootView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_history, null);
    }

    private String getTitle() {
        return getResources().getString(R.string.tab_history_title);
    }

    private void initTitle() {
        mTopBar.setTitle(getTitle());
    }
}
