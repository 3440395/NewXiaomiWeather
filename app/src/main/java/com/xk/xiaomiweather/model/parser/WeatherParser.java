package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.model.bean.Weather;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by xuekai on 2016/11/1.
 */

public class WeatherParser extends BaseParser<Weather,JSONObject>{

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected Weather parser(Response<JSONObject> response) {
        return null;
    }
}
