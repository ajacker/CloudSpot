package com.res.cloudspot.base;


import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.res.cloudspot.fragment.home.HomeFragment;

/**
 * @author ajacker
 * fragment类的基类
 */
public abstract class BaseFragment extends QMUIFragment {
    public BaseFragment() {
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(requireContext(), 100);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Object onLastFragmentFinish() {
        return new HomeFragment();
    }

}
