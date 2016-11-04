package com.xk.xiaomiweather.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.adapter.ViewPagerAdapter;
import com.xk.xiaomiweather.ui.custom.PageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<City> cities = new ArrayList<>();
    private List<Weather> weathers = new ArrayList<>();
    private List<PageView> pages=new ArrayList<>();
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        initData();
        testView();
    }

    /**
     * 用来测试
     */
    private void testView() {
        pages.add(new PageView(this));
        pages.add(new PageView(this));
        viewPagerAdapter.setViews(pages);
        Log.e("MainActivity","testView"+        viewPager.getChildCount());
    }


    private void initView() {
        PageView pageView = new PageView(this);
        pages.add(pageView);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(pages);
        viewPager.setAdapter(viewPagerAdapter);

    }

    /**
     * 进来后先获取所有城市列表
     */
    private void initData() {
        //模拟数据
        City city = new City();
        city.setDistrict("朝阳");
        cities.add(city);
        city = new City();
        city.setDistrict("离石");
        cities.add(city);

        City city1 = cities.get(0);//根据这个city请求weather
        Weather weather=null;
        pages.get(0).update(weather);
    }
}
