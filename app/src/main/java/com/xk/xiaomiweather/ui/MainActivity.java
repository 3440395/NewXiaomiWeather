package com.xk.xiaomiweather.ui;

import android.content.Intent;
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
import android.widget.Toast;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.ui.adapter.ViewPagerAdapter;
import com.xk.xiaomiweather.ui.callback.OnPageViewScrollChange;
import com.xk.xiaomiweather.ui.callback.OnRefreshFinish;
import com.xk.xiaomiweather.ui.custom.HourWeatherItem;
import com.xk.xiaomiweather.ui.custom.HoursTempGraphView;
import com.xk.xiaomiweather.ui.custom.PageView;
import com.xk.xiaomiweather.ui.custom.TopView;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

import java.util.ArrayList;
import java.util.List;

import static com.xk.xiaomiweather.R.id.weather;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusColorUtil.setStatusBarTextStyle(this, true);
        initView();
        initData();
        initListener();
        Toast.makeText(this,ScreenManager.getInstance().getScreenWidth()+"="+ScreenManager.getInstance().getScreenHeigth()+"="+ScreenManager.getInstance().getDensity(),Toast.LENGTH_LONG).show();
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
                if (weather==null||weather.getBaseWeather()==null||weather.getBaseWeather().getCurrentBaseWeather()==null) {
                    currentPager.refreshData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void initView() {
View
        root = (FrameLayout) findViewById(R.id.root);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(pages);
        viewPager.setAdapter(viewPagerAdapter);


        topView = new TopView(this);
        FrameLayout.LayoutParams topViewLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenManager.getInstance().adpH(200));
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

//        HoursTempGraphView hoursTempGraphView = new HoursTempGraphView(this);
//
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 546);
//        layoutParams.leftMargin=100;
//        layoutParams.topMargin=200;
//        hourWeatherItem.setLayoutParams(layoutParams);
//        root.addView(hourWeatherItem);

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
                    CityManager.getInstance().addCity("北京");
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
                            if (currentPager.getWeather()==null||currentPager.getWeather().getBaseWeather()==null||currentPager.getWeather().getBaseWeather().getCurrentBaseWeather()==null) {
                                currentPager.refreshData();

                            }
                        }
                    });
                }
            });

        } else {
            pages.clear();
            for (City city : allCity) {
                pages.add(new PageView(this, city));
            }
            currentPager = pages.get(0);
            viewPager.setAdapter(new ViewPagerAdapter(pages));
//            this.viewPagerAdapter.setViews(pages);
//            viewPagerAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 当完全显示topview时候的scrolly值
     */
    private int completeShowTopView = ScreenManager.getInstance().adpH(840);

    @Override
    public void onScrollChange(int progress) {
        if (progress <= ScreenManager.getInstance().adpH(440)) {
            topView.setAlpha(0);
        } else if (progress > completeShowTopView) {
            topView.setAlpha(1);

        } else {
            topView.setAlpha((ScreenManager.getInstance().adpH(400) - completeShowTopView + progress) / 1f/ScreenManager.getInstance().adpH(400));
        }
//        440--->0
//        840--->1
    }

    @Override
    public void onRefresh(Weather weather, View fromWhere) {
        if (fromWhere == currentPager) {
            if (weather != null && weather.getBaseWeather().getTodayBaseWeather() != null) {
                topView.setText(weather.getCity().getDistrict(), weather.getBaseWeather().getCurrentBaseWeather().getTemp(),weather.getBaseWeather().getTodayBaseWeather().getWeather_id()[0]);
            } else {
                topView.setText("请刷新数据", "","");
            }
        }
    }

    private void setTopView(Weather weather) {
        if (weather != null && weather.getBaseWeather().getTodayBaseWeather() != null) {
            topView.setText(weather.getCity().getDistrict(), weather.getBaseWeather().getCurrentBaseWeather().getTemp(),weather.getBaseWeather().getTodayBaseWeather().getWeather_id()[0]);
        } else {
            topView.setText("请刷新数据", "","");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity","onActivityResult"+resultCode+"=="+data);
        switch (resultCode) {
            case 0:
                //从城市列表中返回
                boolean dataChange = data.getBooleanExtra("dataChange", false);
                if (dataChange) {
                    initData();
                }
                break;
        }

    }
}
