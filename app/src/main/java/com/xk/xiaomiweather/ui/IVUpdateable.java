package com.xk.xiaomiweather.ui;


/**
 * Created by xuekai on 2016/11/1.
 * 需要被提醒更新UI的view实现该接口
 */

public interface IVUpdateable<T> {
    /**
     * 每个要被提醒更新的view重写该方法，传入的是该view所需要的数据
     * @param data
     */
    void update(T data);
}
