package com.xk.xiaomiweather.model.parser;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by xuekai on 2016/11/3.
 */

public abstract class BaseParser<T,U> {
    protected Request<U> request;

    public abstract void setRequestParams(Object... params);


    /**
     * 同步方法 这样可以保证所有的数据请求结束之后 断定，刷新成功，在做后续操作：比如更新ui
     * @return
     */
    public  T getData(){
        if (request == null) {
            throw new IllegalStateException("请先调用setRequestParams");
        }
        Response<U> response = NoHttp.startRequestSync(request);
        if (response.isSucceed()) {
                return parser(response);
        }else{
            return null;
        }
    }

    /**
     * 解析数据
     * @param response
     * @return
     */
    protected abstract T parser(Response<U> response) ;
}
