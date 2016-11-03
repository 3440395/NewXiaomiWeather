package com.xk.xiaomiweather.model.parser;

import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by xk on 2016/11/2 21:03.
 */

public class LastHourAQIParser extends BaseParser<Map<String,String>,JSONObject> {

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected Map<String, String> parser(Response<JSONObject> response) {
        return null;
    }
}
