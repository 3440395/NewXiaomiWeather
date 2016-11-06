package com.xk.xiaomiweather.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

public class ScreenManager {
    private int statusBarHeight=0;
    private static ScreenManager myScreenUtils = null;
    private Context mContext = null;

    private ScreenManager() {
    }

    private int screenHeigth = 0;
    private int screenWidth = 0;
    private float density = 0;

    public int getScreenHeigth() {
        return screenHeigth;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return 返回屏幕的宽度
     */
    public int getScreenWidth() {
        return screenWidth;
    }


    /**
     * MyScreenUtils 获取该工具类的単例
     *
     * @return MyScreenUtils
     */
    public static ScreenManager getInstance() {
        if (myScreenUtils == null) {
            myScreenUtils = new ScreenManager();
        }
        return myScreenUtils;
    }

    public void initScreenUtils(Context context) {
        this.mContext = context;
        getScreen();
    }

    private void getScreen() {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        density = dm.density;
        screenWidth = dm.widthPixels;
        screenHeigth = dm.heightPixels;
    }

    /**
     * 将dp转成px
     *
     * @param dpValue 需要的dp值
     * @return 对应得px
     */
    public int dpToPx(float dpValue) {
        return (int) (dpValue * density + 0.5f);
    }
    public int getStatusBarHeight() {
        if (statusBarHeight==0) {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0, sbar = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
                Log.e("TopView","TopView"+statusBarHeight);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusBarHeight;

    }




}
