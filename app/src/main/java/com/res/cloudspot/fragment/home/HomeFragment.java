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
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        mTabSegment.setHasIndicator(false);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);

        String titleFindCloud = getResources().getString(R.string.tab_findcloud_title);
        String titleHistory = getResources().getString(R.string.tab_history_title);

        QMUITabSegment.Tab findcloud = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component),
                null,
                titleFindCloud, true
        );

        QMUITabSegment.Tab history = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab),
                null,
                titleHistory, true
        );
        mTabSegment.addTab(findcloud)
                .addTab(history);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_LEFT);
    }

    private void initPagers() {


        mPages = new HashMap<>();

        BaseTabFragment homeFindCloudFragment = new FindCloudTabFragment();
        mPages.put(Pager.FINDCLOUD, homeFindCloudFragment);

        BaseTabFragment homeHistoryFragment = new HistoryTabFragment();
        mPages.put(Pager.HISTORY, homeHistoryFragment);


        mViewPager.setAdapter(mFragmentAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
    }

    enum Pager {
        FINDCLOUD, HISTORY;

        public static Pager getPagerFromPosition(int position) {
            switch (position) {
                case 1:
                    return HISTORY;
                case 0:
                default:
                    return FINDCLOUD;
            }
        }
    }
}
