package com.xk.xiaomiweather.model.parser;

import com.xk.xiaomiweather.comm.Constant;
import com.xk.xiaomiweather.model.bean.BaseWeather;
import com.xk.xiaomiweather.model.bean.CurrentBaseWeather;
import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * 通过城市名称
 * Created by xuekai on 2016/11/1.
 */

public class BaseWeatherParser extends BaseParser<BaseWeather, JSONObject> {
    private String url = Constant.BASE_JUHE_URL + "index?format=2&cityname=";
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url=url+params[0]+"&key="+Constant.juheID;
        request = NoHttp.createJsonObjectRequest(url, method);
    }

    @Override
    protected BaseWeather parser(Response<JSONObject> response) throws JSONException {
        if (response.get() != null) {
            JSONObject object = response.get();
            if (object.getInt("resultcode")==200) {
                JSONObject result = object.getJSONObject("result");
                //解析当前天气
                JSONObject currentJson = result.getJSONObject("sk");
                CurrentBaseWeather currentBaseWeather = new CurrentBaseWeather();
                currentBaseWeather.setTemp(currentJson.getString("temp"));
                currentBaseWeather.setWind_direction(currentJson.getString("wind_direction"));
                currentBaseWeather.setWind_strength(currentJson.getString("wind_strength"));
                currentBaseWeather.setHumidity(currentJson.getString("humidity"));
                currentBaseWeather.setTime(currentJson.getString("time"));
                //解析今天的天气
                JSONObject todayJson = result.getJSONObject("today");
                TodayBaseWeather todayBaseWeather = new TodayBaseWeather();
                todayBaseWeather.setTemperature(todayJson.getString("temperature"));
                todayBaseWeather.setWeather(todayJson.getString("weather"));
                todayBaseWeather.setWeather_id(new String[]{todayJson.getJSONObject("weather_id").getString("fa"),
                        todayJson.getJSONObject("weather_id").getString("fb")});
                todayBaseWeather.setWind(todayJson.getString("wind"));
                todayBaseWeather.setWeek(todayJson.getString("week"));
                todayBaseWeather.setDate_y(todayJson.getString("date_y"));
                todayBaseWeather.setDressing_index(todayJson.getString("dressing_index"));
                todayBaseWeather.setDressing_advice(todayJson.getString("dressing_advice"));
                todayBaseWeather.setUv_index(todayJson.getString("uv_index"));
                todayBaseWeather.setComfort_index(todayJson.getString("comfort_index"));
                todayBaseWeather.setWash_index(todayJson.getString("wash_index"));
                todayBaseWeather.setTravel_index(todayJson.getString("travel_index"));
                todayBaseWeather.setExercise_index(todayJson.getString("exercise_index"));
                todayBaseWeather.setDrying_index(todayJson.getString("drying_index"));
                //解析未来几天的天气
                JSONArray futureJson = result.getJSONArray("future");
                ArrayList<FutureDayBaseWeather> futureDayBaseWeathers = new ArrayList<>();
                for (int i = 0; i <futureJson.length(); i++) {
                    JSONObject futureDayJson= (JSONObject) futureJson.get(i);
                    FutureDayBaseWeather futureDayBaseWeather = new FutureDayBaseWeather();
                    futureDayBaseWeather.setTemperature(futureDayJson.getString("temperature"));
                    futureDayBaseWeather.setWeather(futureDayJson.getString("weather"));
                    futureDayBaseWeather.setWeather_id(new String[]{futureDayJson.getJSONObject("weather_id").getString("fa"),
                            futureDayJson.getJSONObject("weather_id").getString("fb")});
                    futureDayBaseWeather.setWind(futureDayJson.getString("wind"));
                    futureDayBaseWeather.setWeek(futureDayJson.getString("week"));
                    futureDayBaseWeather.setDate(futureDayJson.getString("date"));
                    futureDayBaseWeathers.add(futureDayBaseWeather);
                }
                BaseWeather baseWeather = new BaseWeather();
                baseWeather.setFutureDayBaseWeathers(futureDayBaseWeathers);
                baseWeather.setCurrentBaseWeather(currentBaseWeather);
                baseWeather.setTodayBaseWeather(todayBaseWeather);

                return baseWeather;
            }
        }
        return null;
    }
}
