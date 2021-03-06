/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-1-13 上午11:48
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.zcolin.frame.app.BaseFrameActivity;


/**
 * 全屏Activity, 如init加载页，和{@link BaseNoToolBarActivity} 区别是不会空出顶部状态栏区域
 */
public class BaseFullScreenActivity extends BaseFrameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
