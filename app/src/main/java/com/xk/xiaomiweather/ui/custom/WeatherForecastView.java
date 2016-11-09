package com.xk.xiaomiweather.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;

/**
 * Created by xuekai on 2016/11/9.
 */

public class WeatherForecastView extends RelativeLayout {

    private ImageView back;

    public WeatherForecastView(Context context) {
        super(context);
        init();
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }

    private void init() {
        setBackgroundColor(0xffffffff);
        //添加 名称
        TextView cityName = new TextView(getContext());
        cityName.setText("15天趋势预报");
        cityName.setTextSize(17);
        cityName.setId(R.id.aqi_des);//这里我也不清楚为啥 反正随便设置一个id 下面就可以用了
        cityName.setTextColor(0xff000000);
        LayoutParams cityNameParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        cityNameParams.addRule(CENTER_HORIZONTAL, TRUE);
        cityNameParams.topMargin = 165;
        cityName.setLayoutParams(cityNameParams);
        addView(cityName);
        //添加 时间
        TextView publish = new TextView(getContext());
        publish.setText("11月9日-11月20日");
        publish.setTextSize(12);
        publish.setTextColor(0xff8e8e8e);
        LayoutParams publishParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        publishParams.addRule(CENTER_HORIZONTAL, TRUE);
        publishParams.addRule(BELOW, R.id.aqi_des);//照应上面的cityName.setId(R.id.aqi_des);
        publishParams.topMargin = 25;
        publish.setLayoutParams(publishParams);
        addView(publish);

        //添加 图表
        ImageView test = new ImageView(getContext());
        test.setScaleType(ImageView.ScaleType.CENTER_CROP);
        test.setImageResource(R.mipmap.test5);
        LayoutParams testParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1155);
        testParams.topMargin = 380;
        test.setLayoutParams(testParams);
        addView(test);


        //添加返回按钮
        back = new ImageView(getContext());
        back.setImageResource(R.mipmap.icon_back);
        back.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams backParams = new LayoutParams(145, 145);
        backParams.addRule(CENTER_HORIZONTAL,TRUE);
        backParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
        backParams.bottomMargin=135;
        back.setLayoutParams(backParams);
        addView(back);

    }
}
//[FutureDayBaseWeather{temperature='2℃~10℃', weather='霾', weather_id=[53, 53], wind='微风', week='星期三', date='20161109'},
//        FutureDayBaseWeather{temperature='-1℃~10℃', weather='阴转多云', weather_id=[02, 01], wind='微风', week='星期四', date='20161110'},
//        FutureDayBaseWeather{temperature='1℃~10℃', weather='多云转晴', weather_id=[01, 00], wind='微风', week='星期五', date='20161111'},
//        FutureDayBaseWeather{temperature='1℃~12℃', weather='晴转霾', weather_id=[00, 53], wind='微风', week='星期六', date='20161112'},
//        FutureDayBaseWeather{temperature='3℃~13℃', weather='霾转晴', weather_id=[53, 00], wind='微风', week='星期日', date='20161113'},
//        FutureDayBaseWeather{temperature='-1℃~10℃', weather='阴转多云', weather_id=[02, 01], wind='微风', week='星期一', date='20161114'},
//        FutureDayBaseWeather{temperature='1℃~10℃', weather='多云转晴', weather_id=[01, 00], wind='微风', week='星期二', date='20161115'}]
