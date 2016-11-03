package com.xk.xiaomiweather.ui.custom;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

/**
 * Created by xk on 2016/11/3 22:20.
 */

public class CircleIndicateView extends View implements IVUpdateable<CurrentAQIWeather> {
    public CircleIndicateView(Context context) {
        this(context,null);
    }

    public CircleIndicateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleIndicateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private int maxValue=100;
    private int minValue=0;
    private int value=50;
    private String valueType="AQI";
    private String valueDesc="空气质量指数";
    private void init() {
    }

    public void setValue(int value){
        this.value=value;
    }
    public void setMaxValue(int maxValue){
        this.maxValue=maxValue;
    }
    public void setMinValue(int minValue){
        this.minValue=minValue;
    }
    public void setValueType(String valueType){
        this.valueType=valueType;
    }
    public void setValueDesc(String valueDesc){
        this.valueDesc=valueDesc;
    }
    @Override
    public void update(CurrentAQIWeather data) {

    }
}
