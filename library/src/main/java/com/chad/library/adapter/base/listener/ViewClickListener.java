package com.chad.library.adapter.base.listener;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.IdRes;

/**
 * 避免在1秒内触发多次点击
 *
 * @author chengqian
 * Created on 2018/10/12
 */
public abstract class ViewClickListener implements OnClickListener {
    /**
     * 最小的点击时间间隔：毫秒值
     */
    private static int MIN_CLICK_DURATION = 1000;
    /**
     * 上次View被点击的时间：毫秒值
     */
    private long mLastClickTime = 0;
    /**
     * 控件的资源id
     */
    @IdRes
    private int mViewId = -1;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        int id = v.getId();
        if (mViewId != id) {
            mViewId = id;
            mLastClickTime = currentTime;
            onViewClick(v);
            return;
        }
        if (currentTime - mLastClickTime > MIN_CLICK_DURATION) {
            mLastClickTime = currentTime;
            onViewClick(v);
        }
    }

    /**
     * 设置最小的点击时间间隔：毫秒值
     *
     * @param minClickDuration 点击间隔
     */
    public static void setMinClickDuration(int minClickDuration) {
        MIN_CLICK_DURATION = minClickDuration;
    }

    /**
     * 点击回调
     *
     * @param v 被点击的View对象
     */
    public abstract void onViewClick(View v);
}
