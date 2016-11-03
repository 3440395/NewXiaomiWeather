package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.model.bean.City;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by xk on 2016/11/2 20:46.
 */

public class CityParser extends BaseParser<City,JSONObject> {

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected City parser(Response response) {
        return null;
    }
}
