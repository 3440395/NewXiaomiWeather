package com.xk.xiaomiweather.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.AQIWeather;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import static com.xk.xiaomiweather.R.id.aqi_des;
import static com.xk.xiaomiweather.R.id.line;
import static com.xk.xiaomiweather.R.mipmap.test3;

/**
 * Created by xuekai on 2016/11/9.
 */

public class AirQualityView extends RelativeLayout implements IVUpdateable<Weather> {

    private ImageView back;
    private TextView cityName;
    private TextView publish;
    private PolluteDetialView polluteDetialView;
    private DialView dialView;

    public AirQualityView(Context context) {
        super(context);
        init();
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    private void init() {
        setBackgroundColor(0xffffffff);
        //添加 城市名
        cityName = new TextView(getContext());
        cityName.setText("朝阳");
        cityName.setTextSize(17);
        cityName.setId(aqi_des);//这里我也不清楚为啥 反正随便设置一个id 下面就可以用了
        cityName.setTextColor(0xff000000);
        RelativeLayout.LayoutParams cityNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        cityNameParams.addRule(CENTER_HORIZONTAL, TRUE);
        cityNameParams.topMargin = 165;
        cityName.setLayoutParams(cityNameParams);
        addView(cityName);
        //添加 发布时间
        publish = new TextView(getContext());
        publish.setText("环境云 08:00发布");
        publish.setTextSize(12);
        publish.setTextColor(0xff8e8e8e);
        RelativeLayout.LayoutParams publishParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        publishParams.addRule(CENTER_HORIZONTAL, TRUE);
        publishParams.addRule(BELOW, aqi_des);//照应上面的cityName.setId(R.id.aqi_des);
        publishParams.topMargin = 25;
        publish.setLayoutParams(publishParams);
        addView(publish);
//    "PM25":"39",,"time":"2016110909","PM10":"74","SO2":"49.25","o3":"1.25","NO2":"35.00","primary":"颗粒物(PM10)","rcode":200,"CO":"1.28","AQI":"63"}


        //添加污染详情
        polluteDetialView = new PolluteDetialView(getContext());
        RelativeLayout.LayoutParams polluteDetialParams = new RelativeLayout.LayoutParams(450, 330);
        polluteDetialParams.topMargin = 435;
        polluteDetialParams.leftMargin = 100;
        polluteDetialView.setLayoutParams(polluteDetialParams);
        addView(polluteDetialView);

        //添加aqi 图表
        dialView = new DialView(getContext());
        LayoutParams dialViewParams = new LayoutParams(350, 350);
        dialViewParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        dialViewParams.topMargin = 430;
        dialViewParams.rightMargin = 100;
        dialView.setLayoutParams(dialViewParams);
        addView(dialView);

        //添加 tab
        TabStripView tabStripView = new TabStripView(getContext(), "未来5天预报", "过去24小时");
        LayoutParams tabStripParams = new LayoutParams(880, 80);
        tabStripParams.addRule(CENTER_HORIZONTAL, TRUE);
        tabStripParams.topMargin = 890;
        tabStripView.setLayoutParams(tabStripParams);
        addView(tabStripView);

        //添加 来源
        TextView resourceFrom = new TextView(getContext());
        resourceFrom.setText("数据来源：环境云");
        resourceFrom.setTextSize(10);
        resourceFrom.setTextColor(0xff8e8e8e);
        LayoutParams resourceFromParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        resourceFromParams.topMargin = 1020;
        resourceFromParams.leftMargin = 100;
        resourceFrom.setLayoutParams(resourceFromParams);
        addView(resourceFrom);

        //添加曲线图
        ImageView test4 = new ImageView(getContext());
        test4.setImageResource(R.mipmap.test4);
        test4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams test4Params = new LayoutParams(905, 400);
        test4Params.addRule(CENTER_HORIZONTAL, TRUE);
        test4Params.topMargin = 1110;
        test4.setLayoutParams(test4Params);
        addView(test4);

        //添加返回按钮
        back = new ImageView(getContext());
        back.setImageResource(R.mipmap.icon_back);
        back.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams backParams = new LayoutParams(145, 145);
        backParams.addRule(CENTER_HORIZONTAL, TRUE);
        backParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        backParams.bottomMargin = 135;
        back.setLayoutParams(backParams);
        addView(back);

    }

    @Override
    public void update(Weather data) {
        dialView.updata(data.getAqiWeather().getCurrentAQIWeather().getAQI(),"AQI","空气质量指数",0,400);
        cityName.setText(data.getCity().getDistrict());
        publish.setText("环境云 " + data.getAqiWeather().getCurrentAQIWeather().getTime().substring(8) + ":00发布");
        polluteDetialView.update(data.getAqiWeather().getCurrentAQIWeather());
    }
}
//11-09 14:54:27.753 4386-4386/com.xk.xiaomiweather E/AirQualityActivity: onCreateAQIWeather{futureDayAQIs={20161031=34, 20161028=38, 20161029=35, 20161030=34, 20161027=38}, lastHourAQIs={2016110909=82, 2016110908=63, 2016110815=37, 2016110907=59, 2016110814=33, 2016110906=55, 2016110905=55, 2016110904=61, 2016110818=40, 2016110902=64, 2016110911=124, 2016110819=42, 2016110903=63, 2016110912=122, 2016110816=30, 2016110823=71, 2016110900=75, 2016110913=85, 2016110817=37, 2016110901=69, 2016110821=49, 2016110820=46, 2016110910=102}, currentAQIWeather=CurrentAQIWeather{PM25='52', time='2016110913', rdesc='Success', PM10='118', SO2='139.25', o3='23.75', NO2='31.50', primary='颗粒物(PM10)', rcode='null', CO='1.23', AQI='85'}}
