package com.xk.xiaomiweather.provider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xuekai on 2016/11/1.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, "weather.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table citylist(district text primary key," +
                "province text," +
                "city text," +
                "envicloudId text)");
        db.execSQL("create table today_base(district text primary key ," +
                "date_y text," +
                "week text," +
                "temperature text," +
                "weather text," +
                "weather_id text," +
                "wind text," +
                "dressing_index text," +
                "dressing_advice text," +
                "uv_index text," +
                "comfort_index text," +
                "wash_index text," +
                "travel_index text," +
                "exercise_index text," +
                "drying_index text)");
        db.execSQL("create table current_base(district text primary key ," +
                "temp text," +
                "wind_direction text," +
                "wind_strength text," +
                "humidity text," +
                "time text)");
        db.execSQL("create table futureday_base(_id integer primary key autoincrement," +
                "district text," +
                "temperature text," +
                "weather text," +
                "weather_id text," +
                "wind text," +
                "week text," +
                "date text)");
        db.execSQL("create table futurehour_base(_id integer primary key autoincrement," +
                "district text," +
                "weatherid text," +
                "weather text," +
                "temp1 text," +
                "temp2 text," +
                "sh text," +
                "eh text," +
                "date text," +
                "sfdate text," +
                "efdate text)");
        db.execSQL("create table futureday_aqi(_id integer primary key autoincrement," +
                "district text," +
                "date text," +
                "value text)");
        db.execSQL("create table lasthour_aqi(_id integer primary key autoincrement," +
                "envicloudId integer," +
                "date text," +
                "value text)");
        db.execSQL("create table current_aqi(envicloudId integer primary key autoincrement," +
                "PM25 text," +
                "time text," +
                "rdesc text," +
                "PM10 text," +
                "SO2 text," +
                "o3 text," +
                "NO2 text," +
                "primaryPollute text," +
                "rcode text," +
                "CO text," +
                "AQI text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
