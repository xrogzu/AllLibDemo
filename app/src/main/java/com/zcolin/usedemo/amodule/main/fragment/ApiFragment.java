/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午1:47
 **********************************************************/

package com.zcolin.usedemo.amodule.main.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.zcolin.frame.app.BaseFrameLazyLoadFrag;
import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.gui.ZConfirm;
import com.zcolin.gui.ZDialog;
import com.zcolin.libamaplocation.LocationUtil;
import com.zcolin.usedemo.R;

import java.util.ArrayList;

import cn.sharesdk.util.ShareSocial;


/**
 * 个人演示页
 */
public class ApiFragment extends BaseFrameLazyLoadFrag implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();
    private TextView tvMessage;

    public static ApiFragment newInstance() {
        Bundle args = new Bundle();
        ApiFragment fragment = new ApiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        init();
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }


    private void init() {
        tvMessage = getView(R.id.tvMessage);
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("定位"));
        listButton.add(addButton("分享"));

        for (Button btn : listButton) {
            btn.setOnClickListener(this);
        }
    }

    private Button addButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        llContent.addView(button, params);
        return button;
    }

    private void location() {
        PermissionHelper.requestPermission(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionsResultAction() {
            @Override
            public void onGranted() {

                final LocationUtil location = new LocationUtil(mActivity);
                location.startLocation(new LocationUtil.OnGetLocation() {

                    @Override
                    public void getLocation(AMapLocation location) {

				/*设置位置描述*/
                        String desc = null;
                        Bundle locBundle = location.getExtras();
                        if (locBundle != null) {
                            desc = locBundle.getString("desc");
                        }
                        tvMessage.setText(locBundle == null ? location.getCity() + location.getDistrict() : desc);
                    }

                    @Override
                    public void locationFail() {
                        ZConfirm dlg = new ZConfirm(mActivity);
                        dlg.setTitle("定位失败, 是否尝试再次定位？")
                           .addSubmitListener(new ZDialog.ZDialogSubmitInterface() {

                               @Override
                               public boolean submit() {
                                   location();
                                   return true;
                               }
                           });
                        dlg.addCancelListener(new ZDialog.ZDialogCancelInterface() {
                            @Override
                            public boolean cancel() {
                                tvMessage.setText("定位失败");
                                return true;
                            }
                        });
                        dlg.show();
                    }
                });
            }

            @Override
            public void onDenied(String permission) {
                ToastUtil.toastShort("请授予本程序[定位]和[写SD卡权限]");
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            location();
        }
        if (v == listButton.get(1)) {
            new ShareSocial(mActivity).setTitle("分享")
                                      .setContent("分享内容")
                                      .setTargetUrl("http://www.baidu.com")
                                      .setImgUrl("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg")
                                      .share();
        }
    }
}
