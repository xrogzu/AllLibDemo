/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-11-15 下午1:57
 * ********************************************************
 */
package com.fosung.usedemo.amodule.demo_video.ijkplayer.base;

import android.content.res.Configuration;
import android.widget.RelativeLayout;

import com.fosung.frame.app.BaseFrameFrag;
import com.fosung.usedemo.amodule.demo_video.ijkplayer.adapter.SuperVideoAdapter;
import com.fosung.usedemo.amodule.demo_video.ijkplayer.bean.VideoListBean;
import com.superplayer.library.SuperListPlayer;

/**
 * 视频列表播放基类
 */
public abstract class BaseVideoRecycleViewFragment extends BaseFrameFrag {
    protected SuperListPlayer player;


    @Override
    protected void createView() {
        player = initPlayer();
    }

    /**
     * 初始化Player操作
     */
    protected abstract SuperListPlayer initPlayer();

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    public boolean onBackPressed() {
        return player != null && player.onBackPressed();
    }

    protected class AdapterOnPlayClick implements SuperVideoAdapter.PlayClickListener {
        @Override
        public void onPlayClick(int position, VideoListBean data, RelativeLayout image) {
            player.onPlayClick(position, data.getVideoUrl(), image);
        }
    }
}