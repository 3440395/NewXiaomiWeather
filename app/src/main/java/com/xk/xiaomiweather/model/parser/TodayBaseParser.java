package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by xk on 2016/11/2 20:58.
 */

public class TodayBaseParser extends BaseParser<TodayBaseWeather,JSONObject> {

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected TodayBaseWeather parser(Response<JSONObject> response) {
        return null;
    }
}
