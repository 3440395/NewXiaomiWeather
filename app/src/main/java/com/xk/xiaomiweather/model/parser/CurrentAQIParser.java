package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.comm.Constant;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 通过环境云id
 * Created by xk on 2016/11/2 21:03.
 */

public class CurrentAQIParser extends BaseParser<CurrentAQIWeather, JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairlive/" + Constant.envicloudID;
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url = url + "/" + URLEncoder.encode((String) params[0]);
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected CurrentAQIWeather parser(Response<JSONObject> response) {
        if (response.get() != null) {
            JSONObject object = response.get();
            try {
                if (object.getInt("rcode") == 200) {
                    Log.e("CurrentAQIParser","parser"+object);
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
                    currentAQIWeather.setAQI(object.getString("AQI"));
                    return currentAQIWeather;
                }else{
                    Log.e("api挂了","CurrentAQIWeather");
                    CurrentAQIWeather currentAQIWeather=null;
                    try {
                        JSONObject jsonObject = new JSONObject("{\"citycode\":\"101120201\",\"PM25\":\"44\",\"time\":\"2017052512\",\"rdesc\":\"Success\",\"PM10\":\"115\",\"SO2\":\"35.22\",\"o3\":\"165.78\",\"NO2\":\"39.89\",\"primary\":\"颗粒物(PM10)\",\"rcode\":200,\"CO\":\"0.90\",\"AQI\":\"90\"}\n");
                        currentAQIWeather = new CurrentAQIWeather();
                        currentAQIWeather.setPM25(jsonObject.getString("PM25"));
                        currentAQIWeather.setTime(jsonObject.getString("time"));
                        currentAQIWeather.setRdesc(jsonObject.getString("rdesc"));
                        currentAQIWeather.setPM10(jsonObject.getString("PM10"));
                        currentAQIWeather.setSO2(jsonObject.getString("SO2"));
                        currentAQIWeather.setO3(jsonObject.getString("o3"));
                        currentAQIWeather.setNO2(jsonObject.getString("NO2"));
                        currentAQIWeather.setPrimary(jsonObject.getString("primary"));
                        currentAQIWeather.setCO(jsonObject.getString("CO"));
                        currentAQIWeather.setAQI(jsonObject.getString("AQI"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    return currentAQIWeather;
                }
            } catch (JSONException e) {
                Log.e("api挂了","CurrentAQIWeather");
                CurrentAQIWeather currentAQIWeather=null;
                try {
                    JSONObject jsonObject = new JSONObject("{\"citycode\":\"101120201\",\"PM25\":\"44\",\"time\":\"2017052512\",\"rdesc\":\"Success\",\"PM10\":\"115\",\"SO2\":\"35.22\",\"o3\":\"165.78\",\"NO2\":\"39.89\",\"primary\":\"颗粒物(PM10)\",\"rcode\":200,\"CO\":\"0.90\",\"AQI\":\"90\"}\n");
                     currentAQIWeather = new CurrentAQIWeather();
                    currentAQIWeather.setPM25(jsonObject.getString("PM25"));
                    currentAQIWeather.setTime(jsonObject.getString("time"));
                    currentAQIWeather.setRdesc(jsonObject.getString("rdesc"));
                    currentAQIWeather.setPM10(jsonObject.getString("PM10"));
                    currentAQIWeather.setSO2(jsonObject.getString("SO2"));
                    currentAQIWeather.setO3(jsonObject.getString("o3"));
                    currentAQIWeather.setNO2(jsonObject.getString("NO2"));
                    currentAQIWeather.setPrimary(jsonObject.getString("primary"));
                    currentAQIWeather.setCO(jsonObject.getString("CO"));
                    currentAQIWeather.setAQI(jsonObject.getString("AQI"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                Log.e("CurrentAQIParser","返回的json"+object.toString());
                e.printStackTrace();
                return currentAQIWeather;
            }
        }
        return null;
    }
}
