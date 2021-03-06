/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-19 下午3:45
 * *********************************************************
 */
package com.zcolin.usedemo.amodule.demo_view.adapter;


import android.widget.TextView;

import com.zcolin.usedemo.R;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;


/**
 * PullRecyclerAdapter示例
 * <p>
 * pullrecyclerView的Adapter
 */
public class ZRecyclerMultiItemAdapter extends BaseRecyclerAdapter<String> {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    @Override
    public int getItemLayoutId(int type) {
        return R.layout.view_recycler_item;
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public void setUpData(CommonHolder holder, int position, int viewType, String data) {
        if (viewType == TYPE_1) {
            TextView tvTitle = getView(holder, R.id.title);
            tvTitle.setText(data + "…………TYPE_1");
        } else {
            TextView tvTitle = getView(holder, R.id.title);
            tvTitle.setText(data + "—————TYPE_2");
        }
    }
}