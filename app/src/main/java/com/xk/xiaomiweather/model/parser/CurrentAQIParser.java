package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.comm.Constant;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 通过环境云id
 * Created by xk on 2016/11/2 21:03.
 */

public class CurrentAQIParser extends BaseParser<CurrentAQIWeather, JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairlive/" + Constant.envicloudID;
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url = url + "/" + params[0];
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected CurrentAQIWeather parser(Response<JSONObject> response) {
        if (response.get() != null) {
            JSONObject object = response.get();
            try {
                if (object.getInt("rcode") == 200) {
                    CurrentAQIWeather currentAQIWeather = new CurrentAQIWeather();
                    currentAQIWeather.setPM25(object.getString("PM25"));
                    currentAQIWeather.setTime(object.getString("time"));
                    currentAQIWeather.setRdesc(object.getString("rdesc"));
                    currentAQIWeather.setPM10(object.getString("PM10"));
                    currentAQIWeather.setSO2(object.getString("SO2"));
                    currentAQIWeather.setO3(object.getString("o3"));
                    currentAQIWeather.setNO2(object.getString("NO2"));
                    currentAQIWeather.setPrimary(object.getString("primary"));
                    currentAQIWeather.setCO(object.getString("CO"));
                    return currentAQIWeather;
                }
            } catch (JSONException e) {
                Log.e("CurrentAQIParser","返回的json"+object.toString());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
