package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.Constant;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过名称
 * Created by xk on 2016/11/2 21:01.
 */
public class FutureDayAQIParser extends BaseParser<Map<String, String>, JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairforecast/"+Constant.envicloudID+"/";
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url=url+params[0];
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected Map<String, String> parser(Response<JSONObject> response) throws JSONException {
        if (response.get() != null) {
            JSONObject object = response.get();
            Log.e("FutureDayAQIParser","parser"+object.toString());
            if (object.getInt("rcode")==200) {
                JSONArray futureDaysJson = object.getJSONArray("forecast");
                HashMap<String, String> futureDaysAQIBean = new HashMap<>();
                for (int i = 0; i < futureDaysJson.length(); i++) {
                    JSONObject futuredayJson = (JSONObject) futureDaysJson.get(i);
                    futureDaysAQIBean.put(futuredayJson.getString("date"),futuredayJson.getString("aqi_avg"));
                }
                return futureDaysAQIBean;
            }
        }
        return null;
    }
}
