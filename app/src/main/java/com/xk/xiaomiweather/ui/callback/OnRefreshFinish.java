package com.xk.xiaomiweather.ui.callback;

import android.support.annotation.UiThread;
import android.view.View;

import com.xk.xiaomiweather.model.bean.Weather;

/**
 * Created by xk on 2016/11/6 20:56.
 */

public interface OnRefreshFinish {
    @UiThread
    void onRefresh(Weather weather, View fromWhere);
}
