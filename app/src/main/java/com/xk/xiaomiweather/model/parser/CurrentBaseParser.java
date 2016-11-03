package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.model.bean.CurrentBaseWeather;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by xk on 2016/11/2 20:59.
 */

public class CurrentBaseParser extends BaseParser<CurrentBaseWeather,JSONObject> {

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected CurrentBaseWeather parser(Response<JSONObject> response) {
        return null;
    }
}
