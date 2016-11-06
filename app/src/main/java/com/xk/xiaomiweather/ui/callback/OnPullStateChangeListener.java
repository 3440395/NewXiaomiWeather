package com.xk.xiaomiweather.ui.callback;

/**
 * 下拉刷新中可见高度,以及是否为刷新中的状态的监听
 * Created by xk on 2016/11/5 14:01.
 */

public interface OnPullStateChangeListener {
    void onChange(int height,boolean isLoading);
}
