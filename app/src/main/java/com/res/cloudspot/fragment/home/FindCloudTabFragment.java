package com.res.cloudspot.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;
import com.res.cloudspot.base.BaseTabFragment;
import com.res.cloudspot.fragment.AnalyseFragment;
import com.res.cloudspot.util.CameraUtil;
import com.res.cloudspot.util.HttpUtil;
import com.res.cloudspot.util.StringUtil;
import com.res.cloudspot.util.bean.CloudData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.res.cloudspot.util.CameraUtil.REQ_CROP;
import static com.res.cloudspot.util.HttpUtil.UPLOAD_MESSAGE;

/**
 * @author ajacker
 * 寻云页面的Fragment
 */
public class FindCloudTabFragment extends BaseTabFragment implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.search_button)
    QMUIRadiusImageView mImageButton;
    @BindView(R.id.imageView)
    ImageView mImageView;

    /**
     * 用作提示上传的小框框
     */
    private QMUITipDialog mUploadDialog;

    /**
     * 记录要申请的权限：相机和写文件权限
     */
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private File savePath;

    /**
     * 用作通知上传结果的Handler
     */
    private UploadHandler handler;
    private QMUIPopup mNormalPopup;

    @Override
    public View onCreateView() {
        View root = super.onCreateView();
        initTitle();
        handler = new UploadHandler(this);
        mImageButton.post(() -> {
            showPopup();
            mImageButton.postDelayed(() -> mNormalPopup.dismiss(), 1500);
        });
        return root;
    }

    /**
     * 打开分析页面
     *
     * @param type 云朵类型
     */
    private void openAnalyseFragment(int type) {
        Context context = requireContext();
        CloudData cloudData = null;
        try {
            cloudData = new CloudData(BitmapFactory.decodeStream(new FileInputStream(savePath)), StringUtil.titles.get(type));
        } catch (FileNotFoundException ignored) {
        }
        Bundle data = new Bundle();
        data.putSerializable("data", cloudData);
        Intent intent = MainActivity.of(context, AnalyseFragment.class, data);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private void showPopup() {
        initNormalPopupIfNeed();
        mNormalPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mNormalPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
        mNormalPopup.show(mImageButton);
    }

    private void initNormalPopupIfNeed() {
        if (mNormalPopup == null) {
            mNormalPopup = new QMUIPopup(requireContext(), QMUIPopup.DIRECTION_NONE);
            TextView textView = new TextView(requireContext());
            textView.setLayoutParams(mNormalPopup.generateLayoutParam(
                    QMUIDisplayHelper.dp2px(requireContext(), 250),
                    WRAP_CONTENT
            ));
            textView.setLineSpacing(QMUIDisplayHelper.dp2px(requireContext(), 4), 1.0f);
            int padding = QMUIDisplayHelper.dp2px(requireContext(), 20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText("来找朵云吧~");
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.app_color_description));
            mNormalPopup.setContentView(textView);
        }
    }

    /**
     * 点击寻云按钮的处理办法
     */
    @OnClick(R.id.search_button)
    void onSearchClicked() {
        getPermissions();
        new QMUIBottomSheet.BottomListSheetBuilder(getActivity())
                .addItem("使用相机拍照")
                .addItem("从相册选取")
                .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                    dialog.dismiss();
                    switch (position) {
                        case 0:
                            takePhoto();
                            break;
                        case 1:
                            selectFromAlbum();
                            break;
                        default:
                            break;
                    }
                })
                .build()
                .show();
    }

    @Override
    protected View getRootView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_findcloud, null);
    }

    private String getTitle() {
        return getResources().getString(R.string.tab_findcloud_title);
    }

    private void initTitle() {
        mTopBar.setTitle(getTitle());
    }

    /**
     * 处理裁剪 相册 拍照的结果
     *
     * @param requestCode 请求代码
     * @param resultCode  结果代码
     * @param data        数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraUtil.TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //将拍照结果拿去裁剪
                    cropPic(CameraUtil.getUriFromFile(requireContext(), savePath));
                }
                break;
            case CameraUtil.SELECT_PHOTO_FROM_ALBUM:
                if (resultCode == RESULT_OK) {
                    //将相册选取结果拿去裁剪
                    assert data != null;
                    Uri uri = data.getData();
                    savePath = new File(CameraUtil.getRealPathFromUri(requireContext(), uri));
                    cropPic(uri);
                }
                break;
            case REQ_CROP:
                if (resultCode == RESULT_OK) {
                    mUploadDialog = new QMUITipDialog.Builder(getContext())
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                            .setTipWord("识别中...")
                            .create();
                    mUploadDialog.show();
                    HttpUtil.upLoadPic(savePath, handler);
                }
                break;
            default:
                break;

        }
    }

    private void cropPic(Uri mImageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置要缩放的图片Uri和类型
        intent.setDataAndType(mImageUri, "image/*");
        //宽度比
        intent.putExtra("aspectX", 1);
        //高度比
        intent.putExtra("aspectY", 1);
        //输出图片的宽度
        intent.putExtra("outputX", 600);
        //输出图片的高度
        intent.putExtra("outputY", 600);
        //缩放
        intent.putExtra("scale", true);
        //前置摄像头
        intent.putExtra("noFaceDetection", false);
        savePath = CameraUtil.createImageFile(getResources().getConfiguration().locale, requireContext());
        Uri smallUri = CameraUtil.getUriFromFile(requireContext(), savePath);
        //将存储图片的uri读写权限授权给裁剪应用
        Activity activity = requireActivity();
        List<ResolveInfo> resInfoList = activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            activity.grantUriPermission(packageName, mImageUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.grantUriPermission(packageName, smallUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //设置剪裁后的图片保存的位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, smallUri);
        startActivityForResult(intent, REQ_CROP);
    }

    /**
     * 获得权限
     */
    private void getPermissions() {
        if (!EasyPermissions.hasPermissions(requireContext(), permissions)) {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、存储使用权限", 1, permissions);
        }
    }

    /**
     * 选择相册
     */
    private void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, CameraUtil.SELECT_PHOTO_FROM_ALBUM);
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        savePath = CameraUtil.createImageFile(getResources().getConfiguration().locale, requireContext());
        Uri fileUri = CameraUtil.getUriFromFile(requireContext(), savePath);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //设置文件保存位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CameraUtil.TAKE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //以下是申请权限的回调办法

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        QMUITipDialog dialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("权限申请成功！")
                .create();
        dialog.show();
        requireView().postDelayed(dialog::dismiss, 1000);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        QMUITipDialog dialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("权限申请失败，将无法使用相机！")
                .create();
        dialog.show();
        requireView().postDelayed(dialog::dismiss, 1000);
    }

    static class UploadHandler extends Handler {
        private WeakReference<FindCloudTabFragment> mOuter;

        UploadHandler(FindCloudTabFragment activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            FindCloudTabFragment outer = mOuter.get();
            if (outer != null) {
                outer.mUploadDialog.dismiss();
                QMUITipDialog.Builder builder = new QMUITipDialog.Builder(outer.requireContext())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS);
                if (msg.what == UPLOAD_MESSAGE) {
                    if (msg.arg1 >= 1 && msg.arg1 <= 5) {
                        builder.setTipWord(StringUtil.titles.get(msg.arg1));
                    } else {
                        builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL);
                        builder.setTipWord("失败！请检查网络或者重新上传图片！");
                    }
                    Dialog dialog = builder.create();
                    dialog.show();
                    outer.requireView().postDelayed(dialog::dismiss, 1000);
                    //识别成功才跳转到分析页面
                    if (msg.arg1 != -1) {
                        outer.openAnalyseFragment(msg.arg1);
                    }

                }
            }
        }
    }
}
