package com.xk.xiaomiweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.custom.MToolBar;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

/**
 * Created by xuekai on 2016/11/9.
 */
public class CityManageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citymanage);
        StatusColorUtil.setStatusBarTextStyle(this,true);
        initView();
    }

    private void initView() {
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        MToolBar mToolBar = new MToolBar(this);
        LinearLayout.LayoutParams mToolBarParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        mToolBar.setLayoutParams(mToolBarParams);
        root.addView(mToolBar);
    }
}
