package com.xk.xiaomiweather.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.xk.xiaomiweather.Constant;
import com.xk.xiaomiweather.model.bean.AQIWeather;
import com.xk.xiaomiweather.model.bean.BaseWeather;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.model.bean.CurrentBaseWeather;
import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.model.parser.BaseWeatherParser;
import com.xk.xiaomiweather.model.parser.CurrentAQIParser;
import com.xk.xiaomiweather.model.parser.FutureDayAQIParser;
import com.xk.xiaomiweather.model.parser.LastHourAQIParser;
import com.xk.xiaomiweather.model.parser.ThreeHourBaseParser;
import com.xk.xiaomiweather.provider.WeatherProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuekai on 2016/11/1.
 */
public class WeatherManager {
    private static WeatherManager instance;
    private static WeatherProvider weatherProvider;
    private static Context mContext;

    private WeatherManager() {
    }

    public static void init(Context context) {
        mContext = context;
        instance = new WeatherManager();
    }

    public static WeatherManager getInstance() {
        if (mContext == null) {
            throw new IllegalStateException("请在application中初始化");
        }
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }

    /**
     * 从内容提供者中获取数据
     *
     * @author xk
     * @time 2016/11/1 13:31
     */
    @WorkerThread
    public Weather getWeather(City city) {
        Weather weather = new Weather();
        weather.setCity(city);
        BaseWeather baseWeather = getBaseWeather(city);
        AQIWeather aqiWeather = getAQIWeather(city);
        weather.setBaseWeather(baseWeather);
        weather.setAqiWeather(aqiWeather);
        return weather;
    }

    private AQIWeather getAQIWeather(City city) {
        AQIWeather aqiWeather = new AQIWeather();
        ContentResolver contentResolver = mContext.getContentResolver();
        //给AQIWeather设置 currentAQIWeather
        Cursor query = contentResolver.query(Uri.parse(Constant.baseuri + "current_aqi/" + city.getEnvicloudId()), null, null, null, null);
        if (query.moveToNext()) {
            CurrentAQIWeather currentAQIWeather = new CurrentAQIWeather();
            currentAQIWeather.setPM25(query.getString(query.getColumnIndex("PM25")));
            currentAQIWeather.setTime(query.getString(query.getColumnIndex("time ")));
            currentAQIWeather.setRdesc(query.getString(query.getColumnIndex("rdesc ")));
            currentAQIWeather.setPM10(query.getString(query.getColumnIndex("PM10 ")));
            currentAQIWeather.setSO2(query.getString(query.getColumnIndex("SO2 ")));
            currentAQIWeather.setO3(query.getString(query.getColumnIndex("o3 ")));
            currentAQIWeather.setNO2(query.getString(query.getColumnIndex("NO2 ")));
            currentAQIWeather.setPrimary(query.getString(query.getColumnIndex("primaryPollute ")));
            currentAQIWeather.setRcode(query.getString(query.getColumnIndex("rcode ")));
            currentAQIWeather.setCO(query.getString(query.getColumnIndex("CO ")));
            currentAQIWeather.setAQI(query.getString(query.getColumnIndex("AQI ")));

            aqiWeather.setCurrentAQIWeather(currentAQIWeather);
        }
        //给AQIWeather设置futureDayAQIs
        query = contentResolver.query(Uri.parse(Constant.baseuri + "futureday_aqi/" + city.getDistrict()), null, null, null, null);
        HashMap<String, String> futureDayAQIs = new HashMap<>();
        while (query.moveToNext()) {
            futureDayAQIs.put(query.getString(query.getColumnIndex("date")), query.getString(query.getColumnIndex("value")));
        }
        aqiWeather.setFutureDayAQIs(futureDayAQIs);
        //给AQIWeather设置lastHourAQIs
        query = contentResolver.query(Uri.parse(Constant.baseuri + "lasthour_aqi/" + city.getEnvicloudId()), null, null, null, null);
        HashMap<String, String> lastHourAQIs = new HashMap<>();
        while (query.moveToNext()) {
            futureDayAQIs.put(query.getString(query.getColumnIndex("date")), query.getString(query.getColumnIndex("value")));
        }
        aqiWeather.setFutureDayAQIs(lastHourAQIs);
        query.close();
        return aqiWeather;
    }

    private BaseWeather getBaseWeather(City city) {
        BaseWeather baseWeather = new BaseWeather();

        ContentResolver contentResolver = mContext.getContentResolver();

        //设置futureDayBaseWeathers
        Cursor query = contentResolver.query(Uri.parse(Constant.baseuri + "futureday_base/" + city.getDistrict()), null, null, null, null);
        List<FutureDayBaseWeather> futureDayBaseWeathers = new ArrayList<>();
        while (query.moveToNext()) {
            FutureDayBaseWeather futureDayBaseWeather = new FutureDayBaseWeather();
            String weatherid = query.getString(query.getColumnIndex("weatherid"));
            String[] weatherId = weatherid.split(",");
            futureDayBaseWeather.setTemperature(query.getString(query.getColumnIndex("temperature")));
            futureDayBaseWeather.setWeather(query.getString(query.getColumnIndex("weather ")));
            futureDayBaseWeather.setWeather_id(weatherId);
            futureDayBaseWeather.setWind(query.getString(query.getColumnIndex("wind ")));
            futureDayBaseWeather.setWeek(query.getString(query.getColumnIndex("week ")));
            futureDayBaseWeather.setDate(query.getString(query.getColumnIndex("date ")));
            futureDayBaseWeathers.add(futureDayBaseWeather);
        }
        baseWeather.setFutureDayBaseWeathers(futureDayBaseWeathers);
        //设置threeHourBaseWeathers
        query = contentResolver.query(Uri.parse(Constant.baseuri + "futurehour_base/" + city.getDistrict()), null, null, null, null);
        List<ThreeHourBaseWeather> threeHourBaseWeathers = new ArrayList<>();
        while (query.moveToNext()) {
            ThreeHourBaseWeather threeHourBaseWeather = new ThreeHourBaseWeather();

            threeHourBaseWeather.setWeatherid(query.getString(query.getColumnIndex("weatherid")));
            threeHourBaseWeather.setWeather(query.getString(query.getColumnIndex("weather")));
            threeHourBaseWeather.setTemp1(query.getString(query.getColumnIndex("temp1 ")));
            threeHourBaseWeather.setTemp2(query.getString(query.getColumnIndex("temp2 ")));
            threeHourBaseWeather.setSh(query.getString(query.getColumnIndex("sh ")));
            threeHourBaseWeather.setEh(query.getString(query.getColumnIndex("eh ")));
            threeHourBaseWeather.setDate(query.getString(query.getColumnIndex("date ")));
            threeHourBaseWeather.setSfdate(query.getString(query.getColumnIndex("sfdate ")));
            threeHourBaseWeather.setEfdate(query.getString(query.getColumnIndex("efdate ")));
            threeHourBaseWeathers.add(threeHourBaseWeather);
        }
        baseWeather.setThreeHourBaseWeathers(threeHourBaseWeathers);
        //设置todayBaseWeather
        query = contentResolver.query(Uri.parse(Constant.baseuri + "today_base/" + city.getDistrict()), null, null, null, null);
        if (query.moveToNext()) {
            TodayBaseWeather todayBaseWeather = new TodayBaseWeather();
            String weatherid = query.getString(query.getColumnIndex("weather_id"));
            String[] weatherId = weatherid.split(",");

            todayBaseWeather.setDate_y(query.getString(query.getColumnIndex("date_y ")));
            todayBaseWeather.setWeek(query.getString(query.getColumnIndex("week ")));
            todayBaseWeather.setTemperature(query.getString(query.getColumnIndex("temperature ")));
            todayBaseWeather.setWeather(query.getString(query.getColumnIndex("weather ")));
            todayBaseWeather.setWeather_id(weatherId);
            todayBaseWeather.setWind(query.getString(query.getColumnIndex("wind ")));
            todayBaseWeather.setDressing_index(query.getString(query.getColumnIndex("dressing_index ")));
            todayBaseWeather.setDressing_advice(query.getString(query.getColumnIndex("dressing_advice ")));
            todayBaseWeather.setUv_index(query.getString(query.getColumnIndex("uv_index ")));
            todayBaseWeather.setComfort_index(query.getString(query.getColumnIndex("comfort_index ")));
            todayBaseWeather.setWash_index(query.getString(query.getColumnIndex("wash_index ")));
            todayBaseWeather.setTravel_index(query.getString(query.getColumnIndex("travel_index ")));
            todayBaseWeather.setExercise_index(query.getString(query.getColumnIndex("exercise_index ")));
            todayBaseWeather.setDrying_index(query.getString(query.getColumnIndex("drying_index ")));
            baseWeather.setTodayBaseWeather(todayBaseWeather);
        }
        //设置currentBaseWeather
        query = contentResolver.query(Uri.parse(Constant.baseuri + "current_base/" + city.getDistrict()), null, null, null, null);
        if (query.moveToNext()) {
            CurrentBaseWeather currentBaseWeather = new CurrentBaseWeather();

            currentBaseWeather.setTemp(query.getString(query.getColumnIndex("temp ")));
            currentBaseWeather.setWind_direction(query.getString(query.getColumnIndex("wind_direction ")));
            currentBaseWeather.setWind_strength(query.getString(query.getColumnIndex("wind_strength ")));
            currentBaseWeather.setHumidity(query.getString(query.getColumnIndex("humidity ")));
            currentBaseWeather.setTime(query.getString(query.getColumnIndex("time ")));
            baseWeather.setCurrentBaseWeather(currentBaseWeather);
        }

        return baseWeather;
    }

    /**
     * 同步方法(耗时操作)
     *
     * @author xk
     * @time 2016/11/1 13:32
     */
    @WorkerThread
    public Weather refreshWeather(City city) {
        Weather weather = new Weather();
        weather.setCity(city);
        //请求baseweather
        BaseWeatherParser baseWeatherParser = new BaseWeatherParser();
        baseWeatherParser.setRequestParams(city.getDistrict());
        BaseWeather baseWeather = baseWeatherParser.getData();
        ThreeHourBaseParser threeHourBaseParser = new ThreeHourBaseParser();
        threeHourBaseParser.setRequestParams(city.getDistrict());
        List<ThreeHourBaseWeather> threeHourBaseWeathers = threeHourBaseParser.getData();
        baseWeather.setThreeHourBaseWeathers(threeHourBaseWeathers);
        weather.setBaseWeather(baseWeather);

        //请求AQIWeather
        AQIWeather aqiWeather = new AQIWeather();

        CurrentAQIParser currentAQIParser = new CurrentAQIParser();
        currentAQIParser.setRequestParams(city.getEnvicloudId());
        CurrentAQIWeather currentAQIWeather = currentAQIParser.getData();
        aqiWeather.setCurrentAQIWeather(currentAQIWeather);
        FutureDayAQIParser futureDayAQIParser = new FutureDayAQIParser();
        futureDayAQIParser.setRequestParams(city.getEnvicloudId());
        Map<String, String> futureDatAQI = futureDayAQIParser.getData();
        aqiWeather.setFutureDayAQIs(futureDatAQI);
        LastHourAQIParser lastHourAQIParser = new LastHourAQIParser();
        lastHourAQIParser.setRequestParams(city.getEnvicloudId());
        Map<String, String> lastHourAQI = lastHourAQIParser.getData();
        aqiWeather.setLastHourAQIs(lastHourAQI);
        weather.setAqiWeather(aqiWeather);
        //把数据保存到数据库中
        if (saveDataToContentProvider(weather)) {
            return weather;
        } else {
            //保存失败，返回空
            return null;
        }
    }


    private boolean saveDataToContentProvider(Weather weather) {
        Log.e("WeatherManager","saveDataToContentProvider"+weather.toString());
        ContentResolver contentResolver = mContext.getContentResolver();
        //首先要清空数据库中与该城市有关系的数据,除了城市列表
        contentResolver.delete(Uri.parse(Constant.baseuri + "today_base/" + weather.getCity().getDistrict()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "current_base/" + weather.getCity().getDistrict()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futureday_base/" + weather.getCity().getDistrict()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futurehour_base/" + weather.getCity().getDistrict()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "current_aqi/" + weather.getCity().getEnvicloudId()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futureday_aqi/" + weather.getCity().getDistrict()), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "lasthour_aqi/" + weather.getCity().getEnvicloudId()), null, null);
        //删除完毕，开始添加
        ContentValues contentValues = new ContentValues();
        //today_base表
        contentValues.put("district", weather.getCity().getDistrict());
        contentValues.put("date_y ", weather.getBaseWeather().getTodayBaseWeather().getDate_y());
        contentValues.put("week ", weather.getBaseWeather().getTodayBaseWeather().getWeek());
        contentValues.put("temperature ", weather.getBaseWeather().getTodayBaseWeather().getTemperature());
        contentValues.put("weather ", weather.getBaseWeather().getTodayBaseWeather().getWeather());
        contentValues.put("weather_id ", weather.getBaseWeather().getTodayBaseWeather().getWeather_id()[0] + "," +
                weather.getBaseWeather().getTodayBaseWeather().getWeather_id()[1]);
        contentValues.put("wind ", weather.getBaseWeather().getTodayBaseWeather().getWind());
        contentValues.put("dressing_index ", weather.getBaseWeather().getTodayBaseWeather().getDressing_index());
        contentValues.put("dressing_advice ", weather.getBaseWeather().getTodayBaseWeather().getDressing_advice());
        contentValues.put("uv_index ", weather.getBaseWeather().getTodayBaseWeather().getUv_index());
        contentValues.put("comfort_index ", weather.getBaseWeather().getTodayBaseWeather().getComfort_index());
        contentValues.put("wash_index ", weather.getBaseWeather().getTodayBaseWeather().getWash_index());
        contentValues.put("travel_index ", weather.getBaseWeather().getTodayBaseWeather().getTravel_index());
        contentValues.put("exercise_index ", weather.getBaseWeather().getTodayBaseWeather().getExercise_index());
        contentValues.put("drying_index ", weather.getBaseWeather().getTodayBaseWeather().getDrying_index());
        contentResolver.insert(Uri.parse(Constant.baseuri + "today_base/"), contentValues);
        contentValues.clear();
        //current_base表
        contentValues.put("district", weather.getCity().getDistrict());
        contentValues.put("temp ", weather.getBaseWeather().getCurrentBaseWeather().getTemp());
        contentValues.put("wind_direction ", weather.getBaseWeather().getCurrentBaseWeather().getWind_direction());
        contentValues.put("wind_strength ", weather.getBaseWeather().getCurrentBaseWeather().getWind_strength());
        contentValues.put("humidity ", weather.getBaseWeather().getCurrentBaseWeather().getHumidity());
        contentValues.put("time ", weather.getBaseWeather().getCurrentBaseWeather().getTime());
        contentResolver.insert(Uri.parse(Constant.baseuri + "current_base/"), contentValues);
        contentValues.clear();
        //futureday_base表
        List<FutureDayBaseWeather> futureDayBaseWeathers = weather.getBaseWeather().getFutureDayBaseWeathers();
        for (FutureDayBaseWeather futureDayBaseWeather : futureDayBaseWeathers) {
            contentValues.put("district ", weather.getCity().getDistrict());
            contentValues.put("temperature ", futureDayBaseWeather.getTemperature());
            contentValues.put("weather ", futureDayBaseWeather.getWeather());
            contentValues.put("weather_id ", futureDayBaseWeather.getWeather_id()[0] + "," + futureDayBaseWeather.getWeather_id()[1]);
            contentValues.put("wind ", futureDayBaseWeather.getWind());
            contentValues.put("week ", futureDayBaseWeather.getWeek());
            contentValues.put("date ", futureDayBaseWeather.getDate());
            contentResolver.insert(Uri.parse(Constant.baseuri + "futureday_base/"), contentValues);
            contentValues.clear();
        }
        //futurehour_base表
        List<ThreeHourBaseWeather> threeHourBaseWeathers = weather.getBaseWeather().getThreeHourBaseWeathers();
        for (ThreeHourBaseWeather threeHourBaseWeather : threeHourBaseWeathers) {
            contentValues.put("district ", weather.getCity().getDistrict());
            contentValues.put("weatherid ", threeHourBaseWeather.getWeatherid());
            contentValues.put("weather ", threeHourBaseWeather.getWeather());
            contentValues.put("temp1 ", threeHourBaseWeather.getTemp1());
            contentValues.put("temp2 ", threeHourBaseWeather.getTemp2());
            contentValues.put("sh ", threeHourBaseWeather.getSh());
            contentValues.put("eh ", threeHourBaseWeather.getEh());
            contentValues.put("date ", threeHourBaseWeather.getDate());
            contentValues.put("sfdate ", threeHourBaseWeather.getSfdate());
            contentValues.put("efdate ", threeHourBaseWeather.getEfdate());
            contentResolver.insert(Uri.parse(Constant.baseuri + "futurehour_base/"), contentValues);
            contentValues.clear();
        }
        //current_aqi表
        contentValues.put("envicloudId ", weather.getCity().getEnvicloudId());
        contentValues.put("PM25 ", weather.getAqiWeather().getCurrentAQIWeather().getPM25());
        contentValues.put("time ", weather.getAqiWeather().getCurrentAQIWeather().getTime());
        contentValues.put("rdesc ", weather.getAqiWeather().getCurrentAQIWeather().getRdesc());
        contentValues.put("PM10 ", weather.getAqiWeather().getCurrentAQIWeather().getPM10());
        contentValues.put("SO2 ", weather.getAqiWeather().getCurrentAQIWeather().getSO2());
        contentValues.put("o3 ", weather.getAqiWeather().getCurrentAQIWeather().getO3());
        contentValues.put("NO2 ", weather.getAqiWeather().getCurrentAQIWeather().getNO2());
        contentValues.put("primaryPollute ", weather.getAqiWeather().getCurrentAQIWeather().getPrimary());
        contentValues.put("rcode ", weather.getAqiWeather().getCurrentAQIWeather().getRcode());
        contentValues.put("CO ", weather.getAqiWeather().getCurrentAQIWeather().getCO());
        contentValues.put("AQI ", weather.getAqiWeather().getCurrentAQIWeather().getAQI());
        contentResolver.insert(Uri.parse(Constant.baseuri + "current_aqi/"), contentValues);
        contentValues.clear();
        //futureday_aqi表
        Map<String, String> futureDayAQIs = weather.getAqiWeather().getFutureDayAQIs();
        for (Map.Entry<String, String> stringStringEntry : futureDayAQIs.entrySet()) {
            contentValues.put("district ", weather.getCity().getDistrict());
            contentValues.put("date ", stringStringEntry.getKey());
            contentValues.put("value ", stringStringEntry.getValue());
            contentResolver.insert(Uri.parse(Constant.baseuri + "futureday_aqi/"), contentValues);
            contentValues.clear();
        }
        //lasthour_aqi表
        Map<String, String> lastHourAQIs = weather.getAqiWeather().getLastHourAQIs();
        for (Map.Entry<String, String> stringStringEntry : lastHourAQIs.entrySet()) {
            contentValues.put("envicloudId ", weather.getCity().getEnvicloudId());
            contentValues.put("date ", stringStringEntry.getKey());
            contentValues.put("value ", stringStringEntry.getValue());
            contentResolver.insert(Uri.parse(Constant.baseuri + "lasthour_aqi/"), contentValues);
            contentValues.clear();
        }
        return true;
    }
}
