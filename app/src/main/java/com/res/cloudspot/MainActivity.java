package com.res.cloudspot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment;
import com.qmuiteam.qmui.arch.annotation.FirstFragments;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.res.cloudspot.base.BaseFragmentActivity;
import com.res.cloudspot.fragment.AnalyseFragment;
import com.res.cloudspot.fragment.CloudFragment;
import com.res.cloudspot.fragment.ImageViewerFragment;
import com.res.cloudspot.fragment.ShareFragment;
import com.res.cloudspot.fragment.home.HomeFragment;

/**
 * @author ajacker
 */
@FirstFragments(
        value = {
                HomeFragment.class,
                CloudFragment.class,
                AnalyseFragment.class,
                ShareFragment.class,
                ImageViewerFragment.class
        })
@DefaultFirstFragment(HomeFragment.class)
@LatestVisitRecord
public class MainActivity extends BaseFragmentActivity {

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment) {
        return QMUIFragmentActivity.intentOf(context, MainActivity.class, firstFragment);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment,
                            @Nullable Bundle fragmentArgs) {
        return QMUIFragmentActivity.intentOf(context, MainActivity.class, firstFragment, fragmentArgs);
    }

    @Override
    protected int getContextViewId() {
        return R.id.cloudspot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(getWindow());
    }
}
