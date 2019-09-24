package com.res.cloudspot.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDrawableHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.res.cloudspot.R;
import com.res.cloudspot.base.BaseFragment;
import com.res.cloudspot.fragment.home.HistoryTabFragment;
import com.res.cloudspot.util.CameraUtil;
import com.res.cloudspot.util.CloudData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ajacker
 */
public class CloudFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.cloudImageView)
    ImageView mCloudImageView;
    @BindView(R.id.titleTextView)
    TextView mTitleTextView;
    @BindView(R.id.commentTextView)
    TextView mCommentTextView;
    @BindView(R.id.cloudRootView)
    ScrollView cloudRootView;

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

        mTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_save_button)
                .setOnClickListener(v -> {
                    showBottomSheet();
                });
    }

    /**
     * 显示底部选项
     */
    private void showBottomSheet() {
        new QMUIBottomSheet.BottomListSheetBuilder(getContext())
                .addItem("保存云朵")
                .addItem("分享")
                .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                    switch (position) {
                        case 0:
                            saveCloud();
                            break;
                        case 1:
                            shareAsPic();
                            break;
                        default:
                            break;
                    }
                    dialog.dismiss();
                })
                .build().show();
    }

    /**
     * 生成图片分享
     */
    private void shareAsPic() {
        QMUIDialog.CustomDialogBuilder dialogBuilder = new QMUIDialog.CustomDialogBuilder(getContext());
        dialogBuilder.setLayout(R.layout.sharepic_layout);
        final QMUIDialog dialog = dialogBuilder.setTitle("分享以下图片").create();

        ImageView displayImageView = dialog.findViewById(R.id.createFromViewDisplay);
        QMUIRoundButton shareButton = dialog.findViewById(R.id.shareButton);
        QMUIRoundButton cancelButton = dialog.findViewById(R.id.cancelButton);

        Bitmap createFromViewBitmap = QMUIDrawableHelper.createBitmapFromView(cloudRootView);
        displayImageView.setImageBitmap(createFromViewBitmap);

        displayImageView.setOnClickListener(v -> dialog.dismiss());
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        shareButton.setOnClickListener(v -> share(createFromViewBitmap));

        dialog.show();
    }

    private void share(Bitmap bitmap) {
        File file = CameraUtil.createImageFile(Locale.getDefault(), requireContext(), "share");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.setPackage("com.tencent.mobileqq");
        shareIntent.putExtra(Intent.EXTRA_STREAM, CameraUtil.getUriFromFile(requireContext(), file));
        shareIntent.setType("image/jpeg");
        requireContext().startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    /**
     * 保存云朵图片和介绍
     */
    private void saveCloud() {
        CloudData data = new CloudData(cloudData.getBitmap(), cloudData.type);
        data.comment = cloudData.comment;
        HistoryTabFragment.dataList.add(data);
    }

    private void initContentView() {
        Bundle data = getArguments();
        assert data != null;
        cloudData = (CloudData) data.getSerializable("data");
        assert cloudData != null;
        mCloudImageView.setImageBitmap(cloudData.getBitmap());
        mTitleTextView.setText(cloudData.type);
        mCommentTextView.setText(cloudData.comment);
    }
}
