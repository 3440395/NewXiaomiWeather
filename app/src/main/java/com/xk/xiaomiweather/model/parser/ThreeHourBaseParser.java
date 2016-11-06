package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.comm.Constant;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 未来每三小时base天气解析者（根据城市名）
 * Created by xk on 2016/11/2 20:56.
 */

public class ThreeHourBaseParser  extends  BaseParser<List<ThreeHourBaseWeather>,JSONObject> {
    String url= Constant.BASE_JUHE_URL+"forecast3h.php?cityname=";
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url=url+ URLEncoder.encode((String) params[0])+"&key="+Constant.juheID;
        request = NoHttp.createJsonObjectRequest(url, method);

    }

    @Override
    protected List<ThreeHourBaseWeather> parser(Response<JSONObject> response)  {
        if (response.get() != null) {
            JSONObject object = response.get();
            try {
                if (object.getInt("resultcode")==200) {
                    JSONArray result = object.getJSONArray("result");
                    List<ThreeHourBaseWeather> threeHourBaseWeathers = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject threeHourBaseBean = (JSONObject) result.get(i);
                        ThreeHourBaseWeather threeHourBaseWeather = new ThreeHourBaseWeather();
                        threeHourBaseWeather.setWeatherid(threeHourBaseBean.getString("weatherid"));
                        threeHourBaseWeather.setWeather(threeHourBaseBean.getString("weather"));
                        threeHourBaseWeather.setTemp1(threeHourBaseBean.getString("temp1"));
                        threeHourBaseWeather.setTemp2(threeHourBaseBean.getString("temp2"));
                        threeHourBaseWeather.setSh(threeHourBaseBean.getString("sh"));
                        threeHourBaseWeather.setEh(threeHourBaseBean.getString("eh"));
                        threeHourBaseWeather.setDate(threeHourBaseBean.getString("date"));
                        threeHourBaseWeather.setSfdate(threeHourBaseBean.getString("sfdate"));
                        threeHourBaseWeather.setEfdate(threeHourBaseBean.getString("efdate"));

                        threeHourBaseWeathers.add(threeHourBaseWeather);
                    }
                    return threeHourBaseWeathers;
                }
            } catch (JSONException e) {
                Log.e("ThreeHourBaseParser","返回的json"+object.toString());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
