package com.xk.xiaomiweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

import java.util.List;

/**
 * Created by xuekai on 2016/11/9.
 */
public class CityManageActivity extends AppCompatActivity {

    private MToolBar mToolBar;
    private AddCityView addCityView;
    private MRecyclerView mRecyclerView;
    //这个集合代表现在有的城市，数据还原中有极大的作用
    private List<City> allCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citymanage);
        StatusColorUtil.setStatusBarTextStyle(this, true);
        initView();
        initData();
    }

    private void initData() {
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                allCity = CityManager.getInstance().getAllCity();
                mRecyclerView.setData(allCity);
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
    }

    public void goNormal() {
        mRecyclerView.setEdit(false);

    }
}
