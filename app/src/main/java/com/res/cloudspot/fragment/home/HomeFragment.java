package com.res.cloudspot.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.res.cloudspot.R;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.base.BaseTabFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    private HashMap<Pager, BaseTabFragment> mPages;
    private QMUIFragmentPagerAdapter mFragmentAdapter;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);

        mFragmentAdapter = new QMUIFragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mPages.size();
            }

            @Override
            public QMUIFragment createFragment(int position) {
                return mPages.get(Pager.getPagerFromPosition(position));
            }
        };


        initTabs();
        initPagers();
        return layout;
    }


    private void initTabs() {
        //设置样式
        int normalColor = QMUIResHelper.getAttrColor(requireActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(requireActivity(), R.attr.qmui_config_color_blue);
        mTabSegment.setHasIndicator(false);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        //设置切换栏的标题
        String titleFindCloud = getResources().getString(R.string.tab_findcloud_title);
        String titleHistory = getResources().getString(R.string.tab_history_title);
        String titleArticle = getResources().getString(R.string.tab_article);
        //设置图标
        QMUITabSegment.Tab findcloud = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(requireContext(), R.mipmap.tabbar_cloud),
                null,
                titleFindCloud, true
        );
        QMUITabSegment.Tab article = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(requireContext(), R.mipmap.tabbar_article),
                null,
                titleArticle, true
        );
        QMUITabSegment.Tab history = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(requireContext(), R.mipmap.tabbar_history),
                null,
                titleHistory, true
        );

        //添加标签页
        mTabSegment.addTab(findcloud)
                .addTab(article)
                .addTab(history);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        //设置图标位置在左侧
        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_LEFT);
    }

    private void initPagers() {
        //初始化页面容器
        mPages = new HashMap<>();
        //添加页面
        BaseTabFragment homeFindCloudFragment = new FindCloudTabFragment();
        mPages.put(Pager.FINDCLOUD, homeFindCloudFragment);

        BaseTabFragment articleFragment = new ArticleTabFragment();
        mPages.put(Pager.ARTICLE, articleFragment);

        BaseTabFragment homeHistoryFragment = new HistoryTabFragment();
        mPages.put(Pager.HISTORY, homeHistoryFragment);
        //设置adapter
        mViewPager.setAdapter(mFragmentAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
    }

    enum Pager {
        /**
         * 寻云页面，过往页面，文章页面
         */
        FINDCLOUD, HISTORY, ARTICLE;

        public static Pager getPagerFromPosition(int position) {
            switch (position) {
                case 0:
                    return FINDCLOUD;
                case 1:
                    return ARTICLE;
                case 2:
                default:
                    return HISTORY;
            }
        }
    }
}
