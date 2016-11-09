package com.xk.xiaomiweather.ui;

import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.ui.adapter.ViewPagerAdapter;
import com.xk.xiaomiweather.ui.callback.OnPageViewScrollChange;
import com.xk.xiaomiweather.ui.callback.OnRefreshFinish;
import com.xk.xiaomiweather.ui.custom.PageView;
import com.xk.xiaomiweather.ui.custom.TopView;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnPageViewScrollChange, OnRefreshFinish {
    private List<PageView> pages = new ArrayList<>();
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private FrameLayout root;
    //顶部显示天气的view
    private TopView topView;
    private PageView currentPager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusColorUtil.setStatusBarTextStyle(this,true);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPager = pages.get(position);
                pages.get(position).onSelected();
                Weather weather = pages.get(position).getWeather();
                setTopView(weather);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void initView() {

        root = (FrameLayout) findViewById(R.id.root);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(pages);
        viewPager.setAdapter(viewPagerAdapter);


        topView = new TopView(this);
        FrameLayout.LayoutParams topViewLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 165);
        topView.setLayoutParams(topViewLayoutParams);

        root.addView(topView);
        progressBar = new ProgressBar(getApplicationContext());
        progressBar.setVisibility(View.GONE);
        root.addView(progressBar);
//        City city1 = new City();
//        city1.setDistrict("北京");
//        city1.setEnvicloudId("101010100");
//        PageView pageView1 = new PageView(this, city1);
//        City city2 = new City();
//        city2.setDistrict("吕梁");
//        city2.setEnvicloudId("101101100");
//        PageView pageView2 = new PageView(this, city2);
//        City city3 = new City();
//        city3.setDistrict("太原");
//        city3.setEnvicloudId("101100101");
//        PageView pageView3 = new PageView(this, city3);


    }

    /**
     * 进来后先获取所有城市列表
     */
    private void initData() {

        List<City> allCity = CityManager.getInstance().getAllCity();
        if (allCity.size() == 0) {
            progressBar.setVisibility(View.VISIBLE);
            ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
                @Override
                public void run() {
                    CityManager.getInstance().addCity("朝阳");
                    CityManager.getInstance().addCity("太原");
                    CityManager.getInstance().addCity("离石");
                    List<City> allCity1 = CityManager.getInstance().getAllCity();
                    for (City city : allCity1) {
                        pages.add(new PageView(MainActivity.this, city));
                    }
                    currentPager = pages.get(0);
                    ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPagerAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });

        } else {
            for (City city : allCity) {
                pages.add(new PageView(this, city));
            }
            currentPager = pages.get(0);
            viewPagerAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 当完全显示topview时候的scrolly值
     */
    private int completeShowTopView = 840;

    @Override
    public void onScrollChange(int progress) {
        if (progress <= 440) {
            topView.setAlpha(0);
        } else if (progress > completeShowTopView) {
            topView.setAlpha(1);

        } else {
            topView.setAlpha((400 - completeShowTopView + progress) / 400f);
                 }
//        440--->0
//        840--->1
    }

    @Override
    public void onRefresh(Weather weather, View fromWhere) {
        if (fromWhere == currentPager) {
            if (weather != null&&weather.getBaseWeather().getTodayBaseWeather()!=null) {
                Log.e("MainActivity", "onRefresh" + weather.getBaseWeather().getCurrentBaseWeather());
                topView.setText(weather.getCity().getDistrict(), weather.getBaseWeather().getCurrentBaseWeather().getTemp());
            } else {
                topView.setText("请刷新数据", "");
            }
        }
    }

    private void setTopView(Weather weather){
        if (weather != null&&weather.getBaseWeather().getTodayBaseWeather()!=null) {
            topView.setText(weather.getCity().getDistrict(), weather.getBaseWeather().getCurrentBaseWeather().getTemp());
        } else {
            topView.setText("请刷新数据", "");
        }
    }
}
