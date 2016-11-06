package com.xk.xiaomiweather.comm;

import android.app.Application;
import android.os.Handler;

import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;
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
        ScreenManager.getInstance().initScreenUtils(getApplicationContext());
        ExecutorUtil.getInstance().init(getApplicationContext());
    }

    public void runTask(Runnable runnable){
        Handler handler = new Handler(getMainLooper());
        handler.post(runnable);
    }
}
