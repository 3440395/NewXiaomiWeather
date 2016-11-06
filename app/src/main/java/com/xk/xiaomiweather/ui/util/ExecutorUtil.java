package com.xk.xiaomiweather.ui.util;

import android.content.Context;
import android.util.Log;

import com.xk.xiaomiweather.comm.MApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xk on 2016/11/6 21:39.
 */

public class ExecutorUtil {
    public Context mContext;
    private static ExecutorUtil executorUtil;
    private static ExecutorService executorService;
    private ExecutorUtil(){}
    public static ExecutorUtil getInstance(){
        if (executorUtil==null) {
            executorUtil=new ExecutorUtil();
        }
        return executorUtil;
    }
    public void init(Context context){
        Log.e("ExecutorUtil","init"+context);
        this.mContext=context;
        executorService = Executors.newSingleThreadExecutor();

    }
    public void runOnUiThread(Runnable runnable){
        Log.e("ExecutorUtil","runOnUiThread"+mContext);
        ((MApp)mContext).runTask(runnable);
    }

    public void runOnSingleThread(Runnable runnable){
        executorService.execute(runnable);
    }
}
