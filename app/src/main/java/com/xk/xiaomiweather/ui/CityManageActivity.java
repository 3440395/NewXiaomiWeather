package com.xk.xiaomiweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.manager.CityManager;
import com.xk.xiaomiweather.ui.custom.AddCityView;
import com.xk.xiaomiweather.ui.custom.CityItem;
import com.xk.xiaomiweather.ui.custom.MRecyclerView;
import com.xk.xiaomiweather.ui.custom.MToolBar;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuekai on 2016/11/9.
 */
public class CityManageActivity extends AppCompatActivity {
    private boolean dataChanged = false;//数据是否改变，这个值会回传给上一个activity，用来觉得是否需要刷新城市列表1、执行删除后，置为true 2.执行添加后，置为true
    private MToolBar mToolBar;
    private AddCityView addCityView;
    private MRecyclerView mRecyclerView;
    //这个集合代表现在有的城市，数据还原中有极大的作用
    private List<City> allCity;
    //将要删除的城市
    private List<City> deleteCacheCity = new ArrayList<>();
    //临时的城市列表，用来展示临时界面的数据
    private List<City> tempCityList = new ArrayList<>();
    private Intent intent;
    private boolean searchItemCanClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citymanage);
        StatusColorUtil.setStatusBarTextStyle(this, true);
        intent = getIntent();
        setResult(0, intent);
        initView();
        initData();
    }

    private void initData() {
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                allCity = CityManager.getInstance().getAllCity();
                ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setData(allCity);
                    }
                });
            }
        });
    }

    private void initView() {
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        mToolBar = new MToolBar(this);
        LinearLayout.LayoutParams mToolBarParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        mToolBar.setLayoutParams(mToolBarParams);
        root.addView(mToolBar);


        mRecyclerView = new MRecyclerView(this, new LinearLayoutManager(this));
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        listParams.weight = 1;
        mRecyclerView.setLayoutParams(listParams);
        root.addView(mRecyclerView);

        addCityView = new AddCityView(this);
        LinearLayout.LayoutParams addCityViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        addCityView.setLayoutParams(addCityViewParams);
        root.addView(addCityView);
    }


    public void goSearch() {
        if (mToolBar != null) {
            mToolBar.goSearch();
            searchItemCanClick = false;
        }
    }

    public void showAdd(boolean isShow) {
        if (addCityView != null) {
            if (isShow) {
                addCityView.show();

            } else {
                addCityView.hide();

            }
        }
    }

    public void goEdit() {
        mRecyclerView.setEdit(true);
        searchItemCanClick = false;
    }

    public void goNormal() {
        mRecyclerView.setEdit(false);
        mRecyclerView.setData(allCity);
        searchItemCanClick = false;

    }

    /**
     * 点了删除图标后会把城市添加到cache列表中
     *
     * @param city
     */
    public void prepareDeleteCity(City city) {
        deleteCacheCity.add(city);
        mRecyclerView.deleteItem(city);
    }

    /**
     * 执行删除，将cache中的数据从数据库中清除，然后清空这个集合
     */
    public void excuteDelete() {
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                for (City city : deleteCacheCity) {
                    CityManager.getInstance().deleteCity(city.getDistrict());
                }
                dataChanged = true;
                allCity.removeAll(deleteCacheCity);
                clearDeleteCache();
                intent.putExtra("dataChange", dataChanged);
                setResult(0, intent);
            }
        });
    }

    /**
     * 清空deletecache集合
     */
    public void clearDeleteCache() {
        ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                deleteCacheCity.clear();
                mRecyclerView.setData(allCity);
            }
        });
    }

    public void searchCity(final String district) {
        searchItemCanClick = false;
        tempCityList.clear();
        City city1 = new City();
        city1.setDistrict("正在搜索，请稍后。。。");
        tempCityList.add(city1);
        mRecyclerView.setData(tempCityList);
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                final City city = CityManager.getInstance().searchCity(district);
                ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (city == null) {
                            tempCityList.clear();
                            City city1 = new City();
                            city1.setDistrict("无结果");
                            tempCityList.add(city1);
                            mRecyclerView.setData(tempCityList);
                        } else {
                            searchItemCanClick = true;
                            tempCityList.clear();
                            tempCityList.add(city);
                            mRecyclerView.setData(tempCityList);
                        }

                    }
                });
            }
        });
    }

    public void addCity(final City city) {
        if (searchItemCanClick) {
            ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
                @Override
                public void run() {
                    CityManager.getInstance().addCity(city.getDistrict());
                    ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataChanged = true;
                            finish();
                        }
                    });

                }
            });

        }

    }
}
