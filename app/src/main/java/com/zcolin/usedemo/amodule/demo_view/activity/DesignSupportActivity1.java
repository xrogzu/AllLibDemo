/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-11 下午4:01
 * *********************************************************
 */

package com.zcolin.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zcolin.frame.app.BaseFrameActivity;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.demo_view.adapter.DesignSupportPagerAdapter;
import com.zcolin.gui.ZViewPager;

/**
 * MD风格的RecyclerView，PullRecycler和SuperRecycler的Demo
 * 尽量使用PullRecyclerView，如果实在要下拉的，则使用SuperRecyclerView
 */
public class DesignSupportActivity1 extends BaseFrameActivity {

    private Toolbar              toolbar;
    private TabLayout            tabLayout;
    private ZViewPager           viewPager;
    private FloatingActionButton fabTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport1);

        init();
    }

    @Override
    protected boolean isImmerse() {
        return false;
    }

    private void init() {
        toolbar = getView(R.id.toolbar);
        tabLayout = getView(R.id.tabs);
        viewPager = getView(R.id.viewpager);
        fabTest = getView(R.id.fab);

        toolbar.setTitle("DesignSupport");
        setSupportActionBar(toolbar);

        //设置TabLayout的模式  
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        FragmentPagerAdapter adapter = new DesignSupportPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        fabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FloatActionBar-click", Snackbar.LENGTH_LONG)
                        .setAction("toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.toastShort("button-click");
                            }
                        }).show();
            }
        });
    }
}
