package com.res.cloudspot.base;


import android.view.View;

import butterknife.ButterKnife;

/**
 * @author ajacker
 * 用作tab切换的fragment类的基类
 */
public abstract class BaseTabFragment extends BaseFragment {


    @Override
    public View onCreateView() {
        View root = getRootView();
        ButterKnife.bind(this, root);
        return root;
    }

    /**
     * 得到试图用于加载fragment
     *
     * @return View
     */
    abstract protected View getRootView();


}
