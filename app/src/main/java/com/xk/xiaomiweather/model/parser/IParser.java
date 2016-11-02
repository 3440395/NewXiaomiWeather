package com.xk.xiaomiweather.model.parser;

/**
 * Created by xk on 2016/11/2 20:46.
 */

public interface IParser<T> {
    void setUrl();

    /**
     * 同步方法 这样可以保证所有的数据请求结束之后 断定，刷新成功，在做后续操作：比如更新ui
     * @return
     */
    T getData();
}
