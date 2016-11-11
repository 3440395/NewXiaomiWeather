package com.xk.xiaomiweather.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.custom.AirQualityView;
import com.xk.xiaomiweather.ui.util.StatusColorUtil;

import java.io.Serializable;

/**
 * Created by xuekai on 2016/11/9.
 */

public class AirQualityActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Weather  data = (Weather) getIntent().getSerializableExtra("data");
        StatusColorUtil.setStatusBarTextStyle(this,true);
        AirQualityView airQualityView = new AirQualityView(this);
        addContentView(airQualityView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        airQualityView.update(data);
    }
}
