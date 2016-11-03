package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.comm.Constant;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过环境云id
 * Created by xk on 2016/11/2 21:03.
 */

public class LastHourAQIParser extends BaseParser<Map<String, String>, JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairhistory/" + Constant.envicloudID;
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        for (Object param : params) {
            url += ("/" + param.toString());
        }
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected Map<String, String> parser(Response<JSONObject> response) throws JSONException {
        if (response.get() != null) {
            JSONObject object = response.get();
            if (object.getInt("rcode")==200) {
                JSONArray historyJson = object.getJSONArray("history");
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                for (int i = 0; i < historyJson.length(); i++) {
                    JSONObject aqi = historyJson.getJSONObject(i);
                    stringStringHashMap.put(aqi.getString("time"),aqi.getString("AQI"));
                }
                return stringStringHashMap;
            }
        }
        return null;
    }
}
