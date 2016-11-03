package com.xk.xiaomiweather.comm;

import android.app.Application;

import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by xuekai on 2016/11/3.
 */

public class MApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttp");// 设置NoHttp打印Log的tag。

        WeatherManager.init(getApplicationContext());
        CityManager.init(getApplicationContext());
    }
}
