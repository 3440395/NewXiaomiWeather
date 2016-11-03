package com.xk.xiaomiweather.model.parser;

import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.List;

/**
 * 未来每三小时base天气解析者
 * Created by xk on 2016/11/2 20:56.
 */

public class ThreeHourBaseParser  extends  BaseParser<List<ThreeHourBaseParser>,JSONObject> {

    @Override
    public void setRequestParams(Object... params) {

    }

    @Override
    protected List<ThreeHourBaseParser> parser(Response<JSONObject> response) {
        return null;
    }
}
