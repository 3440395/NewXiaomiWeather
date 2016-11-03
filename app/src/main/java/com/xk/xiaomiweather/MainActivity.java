package com.xk.xiaomiweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;

public class MainActivity extends AppCompatActivity {
    private City chaoyang, taiyuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void addty(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taiyuan = CityManager.getInstance().addCity("太原");
                Log.e("MainActivity", "添加太原成功" + taiyuan.toString());
            }
        }).start();

    }

    public void addcy(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                chaoyang = CityManager.getInstance().addCity("朝阳");
                Log.e("MainActivity", "添加朝阳成功" + chaoyang.toString());
            }
        }).start();
    }

    public void dety(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CityManager.getInstance().deleteCity("太原");
                Log.e("MainActivity", "删除太原成功");
            }
        }).start();
    }

    public void decy(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CityManager.getInstance().deleteCity("朝阳");
                Log.e("MainActivity", "删除朝阳成功");
            }
        }).start();
    }

    public void getty(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Weather weather = WeatherManager.getInstance().getWeather(taiyuan);
                Log.e("MainActivity", "获取太原成功" + weather.toString());
            }
        }).start();
    }

    public void getcy(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Weather weather = WeatherManager.getInstance().getWeather(chaoyang);
                Log.e("MainActivity", "获取朝阳成功" + weather.toString());
            }
        }).start();
    }

    public void rety(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Weather weather = WeatherManager.getInstance().refreshWeather(taiyuan);
                Log.e("MainActivity", "刷新太原成功" + weather.toString());
            }
        }).start();
    }

    public void recy(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Weather weather = WeatherManager.getInstance().refreshWeather(chaoyang);
                Log.e("MainActivity", "刷新朝阳成功" + weather.toString());
            }
        }).start();
    }
}
