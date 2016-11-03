package com.xk.xiaomiweather.model.parser;

import android.animation.ObjectAnimator;
import android.util.Log;

import com.xk.xiaomiweather.Constant;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import static android.R.attr.type;

/**
 * Created by xk on 2016/11/2 21:03.
 */

public class CurrentAQIParser extends BaseParser<CurrentAQIWeather,JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairlive/" + Constant.envicloudID;
    private RequestMethod method=RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        setRequest(method, params);
        for (Object param : params) {
            url += ("/" + param.toString());
        }
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected CurrentAQIWeather parser(Response<JSONObject> response) {
        if (response.get() != null) {
            JSONObject object = response.get();
            Log.e("CurrentAQIParser","parser"+object.toString());
        }
        return null;
    }
}
