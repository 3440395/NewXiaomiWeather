package com.xk.xiaomiweather.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.WorkerThread;

import com.xk.xiaomiweather.Constant;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.parser.CityParser;
import com.xk.xiaomiweather.provider.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xk on 2016/11/2 20:25.
 */

public class CityManager {
    private static CityManager instance;
    private String uri;
    private static Context mContext;


    public static void init(Context context) {
        mContext = context;
        instance = new CityManager();
    }

    public static CityManager getInstance() {
        if (mContext == null) {
            throw new IllegalStateException("请在application中初始化");
        }
        if (instance == null) {
            instance = new CityManager();
        }
        return instance;
    }

    private CityManager() {
    }


    /**
     * 通过城市名添加一个城市(从网络获取到城市环境云id，然后把它插入到数据库中)
     *
     * @param district
     */
    @WorkerThread
    public City addCity(String district) {
        CityParser cityParser = new CityParser();
        cityParser.setRequestParams(district);
        City city = cityParser.getData();
        uri = Constant.baseuri + "citylist";
        ContentValues contentValues = new ContentValues();
        contentValues.put("district", city.getDistrict());
        contentValues.put("envicloudId", city.getEnvicloudId());
        ContentResolver contentResolver = mContext.getContentResolver();
        contentResolver.insert(Uri.parse(uri), contentValues);
        return city;
    }

    /**
     * 通过城市名删除一个城市(同时还有把其他表中的数据删除)
     *
     * @param district
     */
    @WorkerThread
    public void deleteCity(String district) {
        ContentResolver contentResolver = mContext.getContentResolver();

        //获取到环境云id
        Cursor query = contentResolver.query(Uri.parse(uri), null, null, null, null);
        query.moveToNext();
        String envicloudId = query.getString(query.getColumnIndex("envicloudId"));
        contentResolver.delete(Uri.parse(Constant.baseuri + "citylist/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "today_base/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "current_base/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futureday_base/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futurehour_base/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "current_aqi/" + envicloudId), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "futureday_aqi/" + district), null, null);
        contentResolver.delete(Uri.parse(Constant.baseuri + "lasthour_aqi/" + envicloudId), null, null);
        query.close();
    }

    @WorkerThread
    public List<City> getAllCity(){
        ArrayList<City> cities = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();

        Cursor query = contentResolver.query(Uri.parse(Constant.baseuri+"citylist"), null, null, null, null);
        while (query.moveToNext()){
            City city = new City();
            city.setDistrict(query.getString(query.getColumnIndex("district")));
            city.setEnvicloudId(query.getString(query.getColumnIndex("envicloudId")));
            cities.add(city);
        }
        query.close();
        return cities;
    }


}
