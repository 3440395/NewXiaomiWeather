package com.xk.xiaomiweather.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xk.xiaomiweather.provider.db.DBOpenHelper;

/**
 * Created by xuekai on 2016/11/1.
 */

public class WeatherProvider extends ContentProvider {
    private Context context;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper dbHelper;
    private SQLiteDatabase readableDatabase;
    private SQLiteDatabase writableDatabase;


    public WeatherProvider() {
    }

    // 匹配码
    private static final int code_citylist = 0;//省 行政区 市（主键）  环境云id
    private static final int code_today_base = 1;//市  date_y week   temperature weather  weather_id wind   dressing_index    dressing_advice    uv_index   comfort_index wash_index travel_index  exercise_index drying_index
    private static final int code_current_base = 2;// 市   temp wind_direction  wind_strength      humidity  time
    private static final int code_futureday_base = 3;//市 temperature weather weather_id(两个值用逗号隔开) wind week date
    private static final int code_futurehour_base = 4;//市   weatherid  weather temp1   temp2 sh    eh   date    sfdate  efdate
    private static final int code_futureday_aqi = 5;//市   date  value
    private static final int code_lasthour_aqi = 6;//环境云id   date value
    private static final int code_current_aqi = 7;//环境云id   //    PM25         time  rdesc           PM10 SO2           o3  NO2      primary   rcode    CO AQI
    private static final int code_citylist_item = 8;


    static {
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/citylist", code_citylist);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/citylist/*", code_citylist_item);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/today_base/*", code_today_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/today_base/", code_today_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/current_base/*", code_current_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/current_base/", code_current_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futureday_base/*", code_futureday_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futureday_base/", code_futureday_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futurehour_base/*", code_futurehour_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futurehour_base/", code_futurehour_base);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futureday_aqi/*", code_futureday_aqi);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/futureday_aqi/", code_futureday_aqi);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/lasthour_aqi/*", code_lasthour_aqi);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/lasthour_aqi/", code_lasthour_aqi);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/current_aqi/*", code_current_aqi);
        uriMatcher.addURI("com.xk.xiaomiweather", "weather/current_aqi/", code_current_aqi);
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case code_citylist:
                return "vnd.android.cursor.dir/citylist";
            case code_citylist_item:
                return "vnd.android.cursor.item/citylist";
            case code_today_base:
                return "vnd.android.cursor.item/today_base";
            case code_current_base:
                return "vnd.android.cursor.item/current_base";
            case code_futureday_base:
                return "vnd.android.cursor.dir/futureday_base";
            case code_futurehour_base:
                return "vnd.android.cursor.dir/futurehour_base";
            case code_futureday_aqi:
                return "vnd.android.cursor.dir/futureday_aqi";
            case code_lasthour_aqi:
                return "vnd.android.cursor.dir/lasthour_aqi";
            case code_current_aqi:
                return "vnd.android.cursor.item/current_aqi";

            default:
                throw new IllegalArgumentException("this is unkown uri:" + uri);
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case code_citylist_item:
                //根据城市名称 返回city数据
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("citylist", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_citylist:
                //返回所有的城市数据
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("citylist", null, null, null, null, null, null);
            case code_today_base:
                //从数据库中查询今天的天气，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("today_base", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_current_base:
                //从数据库中查询当前的天气，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("current_base", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_futureday_base:
                //从数据库中查询未来几天的天气，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("futureday_base", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_futurehour_base:
                //从数据库中查询未来3小时的天气，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("futurehour_base", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_current_aqi:
                //从数据库中查询当前的空气质量，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("current_aqi", null, "  envicloudId=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_futureday_aqi:
                //从数据库中查询未来几天的空气质量，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("futureday_aqi", null, "   district=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            case code_lasthour_aqi:
                //从数据库中查询过去24小时的空气质量，解析uri可以知道城市id
                readableDatabase = dbHelper.getReadableDatabase();
                return readableDatabase.query("lasthour_aqi", null, "  envicloudId=?", new String[]{uri.getLastPathSegment()}, null, null, null);
            default:
                throw new IllegalArgumentException("uri有问题，查询失败"+uri.toString());
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        ContentValues contentValues;
        switch (uriMatcher.match(uri)) {
            case code_citylist:
            case code_citylist_item:
                //通过函数传来的参数，插入数据库中数据
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("citylist", null, values);
                break;
            case code_today_base:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("today_base", null, values);
                break;
            case code_current_base:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("current_base", null, values);//只有在刚添加这个城市的时候调用
                break;
            case code_futureday_base:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("futureday_base", null, values);//每次请求到数据都会调用
                break;
            case code_futurehour_base:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("futurehour_base", null, values);//每次请求到数据都会调用
                break;
            case code_current_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("current_aqi", null, values);//只有在刚添加这个城市的时候调用
                break;
            case code_futureday_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("futureday_aqi", null, values);//每次请求到数据都会调用
                break;
            case code_lasthour_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.insert("lasthour_aqi", null, values);//每次请求到数据都会调用
                break;
            default:
                throw new IllegalArgumentException("uri有问题，插入失败"+uri.toString());
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case code_citylist:
                throw new IllegalArgumentException("城市列表数据不支持删除");
            case code_citylist_item:
                //根据城市id，删除数据
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("citylist", " district=?",new String[]{uri.getLastPathSegment()});//删除城市的时候调用
            case code_today_base:
                //根据城市id，删除数据
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("today_base", " district=?",new String[]{uri.getLastPathSegment()});//删除城市的时候调用
            case code_current_base:
                //根据城市id，删除数据
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("current_base", " district=?", new String[]{uri.getLastPathSegment()});//删除城市的时候调用
            case code_futureday_base:
                //根据城市id，删除所有数据
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("futureday_base", " district=?", new String[]{uri.getLastPathSegment()});//每次更新未来几天天气都要删除
            case code_futurehour_base:
                //根据城市id，删除所有数据
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("futurehour_base", " district=?", new String[]{uri.getLastPathSegment()});//每次更新未来三小时天气都要删除
            case code_current_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("current_aqi", "envicloudId=?", new String[]{uri.getLastPathSegment()});////删除城市的时候调用
            case code_futureday_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("futureday_aqi", " district=?",new String[]{uri.getLastPathSegment()});//每次更新未来几天aqi都要删除
            case code_lasthour_aqi:
                writableDatabase = dbHelper.getWritableDatabase();
                return writableDatabase.delete("lasthour_aqi", "envicloudId=?", new String[]{uri.getLastPathSegment()});//每次更新过去24小时aqi都要删除
            default:
                throw new IllegalArgumentException("uri有问题，删除失败");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

                throw new IllegalArgumentException("数据不支持更新，请先删除，再添加");
    }
}

//    /**
//     * 外部应用向本应用插入数据
//     */
//    @Override
//    public Uri insert(Uri uri, ContentValues values)
//    {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        switch (MATCHER.match(uri))
//        {
//            case CODE_NOPARAM:
//                // 若主键值是自增长的id值则返回值为主键值，否则为行号，但行号并不是RecNo列
//                long id = db.insert("person", "name", values);
//                Uri insertUri = ContentUris.withAppendedId(uri, id);
//                return insertUri;
//            default:
//                throw new IllegalArgumentException("this is unkown uri:" + uri);
//        }
//    }
//
//


