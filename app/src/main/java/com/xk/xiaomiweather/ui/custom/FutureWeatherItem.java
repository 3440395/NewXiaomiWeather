package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.ui.util.IconUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.data;
import static android.R.id.icon1;
import static com.xk.xiaomiweather.R.id.weather;

/**
 * 未来几天天气预报的折线图的item
 * 宽度120
 * Created by xuekai on 2016/11/14.
 */

public class FutureWeatherItem extends LinearLayout {

    private TextView week;
    private TextView date;
    private LinearLayout detailContain;
    private TextView situation1;
    private TextView situation2;
    private TextView windPower;
    private TextView windLevel;
    private DoubleTempGraphView doubleTempGraphView;
    private ImageView icon2;
    private ImageView icon1;

    public FutureWeatherItem(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);


        //添加星期和日期
        LinearLayout timeContain = new LinearLayout(getContext());

        timeContain.setOrientation(VERTICAL);
        LayoutParams timeContainParams = new LayoutParams(240, 165);
        LayoutParams tvLayoutparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLayoutparams.gravity = Gravity.CENTER;
        week = new TextView(getContext());
        week.setText("今天");
        week.setLayoutParams(tvLayoutparams);
        week.setTextColor(0xff4BB1E6);
        week.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
        date = new TextView(getContext());
        date.setText("11月14日");
        date.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        date.setLayoutParams(tvLayoutparams);
        timeContain.setGravity(Gravity.CENTER);
        timeContain.setLayoutParams(timeContainParams);
        timeContain.addView(week);
        timeContain.addView(date);
        addView(timeContain);

        //添加具体的天气
        detailContain = new LinearLayout(getContext());
        detailContain.setOrientation(VERTICAL);
        LayoutParams detailContainParams = new LayoutParams(240, 1000);
        detailContain.setBackground(getResources().getDrawable(R.drawable.item_forecast_graph));
        detailContain.setEnabled(true);
        detailContain.setLayoutParams(detailContainParams);


        //多云、晴 图标1
        LinearLayout weatherSituation1 = new LinearLayout(getContext());
        weatherSituation1.setOrientation(VERTICAL);
        LayoutParams weatherSituationParams1 = new LayoutParams(240, 150);
        weatherSituationParams1.topMargin = 50;
        weatherSituationParams1.bottomMargin = 10;
        LayoutParams layoutParams = new LayoutParams(80, 80);
        LinearLayout.LayoutParams situationParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        situationParams.gravity=Gravity.CENTER_HORIZONTAL;
        layoutParams.gravity = Gravity.CENTER;
        icon1 = new ImageView(getContext());
        icon1.setImageResource(R.mipmap.icon_sun);
        icon1.setLayoutParams(layoutParams);
        situation1 = new TextView(getContext());
        situation1.setText("晴");
        situation1.setLayoutParams(situationParams);
        weatherSituation1.setGravity(Gravity.CENTER);
        weatherSituation1.setLayoutParams(weatherSituationParams1);
        weatherSituation1.addView(icon1);
        weatherSituation1.addView(situation1);
        detailContain.addView(weatherSituation1);


        //温度曲线
        doubleTempGraphView = new DoubleTempGraphView(getContext());
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams1.weight = 1;
        doubleTempGraphView.setLayoutParams(layoutParams1);
        detailContain.addView(doubleTempGraphView);


        //多云、晴 图标2
        LinearLayout weatherSituation2 = new LinearLayout(getContext());
        weatherSituation2.setOrientation(VERTICAL);
        LayoutParams weatherSituationParams2 = new LayoutParams(240, 150);
        weatherSituationParams2.topMargin = 25;
        icon2 = new ImageView(getContext());
        icon2.setImageResource(R.mipmap.icon_sun);
        icon2.setLayoutParams(layoutParams);

        situation2 = new TextView(getContext());
        situation2.setText("阴");
        situation2.setLayoutParams(situationParams);
        weatherSituation2.setGravity(Gravity.CENTER);
        weatherSituation2.setLayoutParams(weatherSituationParams2);
        weatherSituation2.addView(icon2);
        weatherSituation2.addView(situation2);
        detailContain.addView(weatherSituation2);

        //风
        LinearLayout windContain = new LinearLayout(getContext());
        windContain.setOrientation(VERTICAL);
        LayoutParams windParams = new LayoutParams(240, 150);
        windParams.topMargin = 40;
        windParams.bottomMargin = 25;
        windPower = new TextView(getContext());
        windPower.setText("微风");
        windPower.setLayoutParams(situationParams);
//        windLevel = new TextView(getContext());
//        windLevel.setText("<2级");
//        windLevel.setLayoutParams(layoutParams);
        windContain.setGravity(Gravity.CENTER);
        windContain.setLayoutParams(windParams);
        windContain.addView(windPower);
//        windContain.addView(windLevel);
        detailContain.addView(windContain);

        addView(detailContain);


    }

    public void updata(List<FutureDayBaseWeather> futureDayBaseWeathers, int postion) {
        String sDate = futureDayBaseWeathers.get(postion).getDate().substring(4, 6) + "月" + futureDayBaseWeathers.get(postion).getDate().substring(6, 8) + "日";
        date.setText(sDate);
        if (postion == 0) {
            week.setText("今天");
            week.setTextColor(0xff4BB1E6);
            detailContain.setEnabled(true);
        } else {
            week.setTextColor(0xff000000);
            detailContain.setEnabled(false);
            week.setText(futureDayBaseWeathers.get(postion).getWeek());
        }

        String[] weaters = futureDayBaseWeathers.get(postion).getWeather().split("转");
        situation1.setText(weaters[0]);
        if (weaters.length == 2) {
            situation2.setText(weaters[1]);
        } else {
            situation2.setText(weaters[0]);
        }

        IconUtil.setIcon(icon1,futureDayBaseWeathers.get(postion).getWeather_id()[0]);
        IconUtil.setIcon(icon2,futureDayBaseWeathers.get(postion).getWeather_id()[1]);

        windPower.setText(futureDayBaseWeathers.get(postion).getWind().substring(0, 2));

//        doubleTempGraphView.setData();
        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        for (FutureDayBaseWeather futureDayBaseWeather : futureDayBaseWeathers) {
//            -2℃~13℃
            String replace = futureDayBaseWeather.getTemperature().replace("℃", "");
            String[] split = replace.split("~");
            temp1.add(Integer.parseInt(split[0]));
            temp2.add(Integer.parseInt(split[1]));
        }


        Collections.sort(temp1);
        Collections.sort(temp2);
        if (postion == 0) {
            doubleTempGraphView.setDrawLeft(false);
            doubleTempGraphView.setDrawRight(true);
            doubleTempGraphView.setData1(temp1.get(temp1.size() - 1), temp1.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[0]),
                    0, Integer.parseInt(futureDayBaseWeathers.get(postion + 1).getTemperature().replace("℃", "").split("~")[0]));
            doubleTempGraphView.setData2(temp2.get(temp2.size() - 1), temp2.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[1]),
                    0, Integer.parseInt(futureDayBaseWeathers.get(postion + 1).getTemperature().replace("℃", "").split("~")[1]));
        } else if (postion == futureDayBaseWeathers.size() - 1) {
            doubleTempGraphView.setDrawLeft(true);
            doubleTempGraphView.setDrawRight(false);
            doubleTempGraphView.setData1(temp1.get(temp1.size() - 1), temp1.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[0]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion - 1).getTemperature().replace("℃", "").split("~")[0]),
                    0);
            doubleTempGraphView.setData2(temp2.get(temp2.size() - 1), temp2.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[1]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion - 1).getTemperature().replace("℃", "").split("~")[1]),
                    0);
        } else {
            doubleTempGraphView.setDrawLeft(true);
            doubleTempGraphView.setDrawRight(true);
            doubleTempGraphView.setData1(temp1.get(temp1.size() - 1), temp1.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[0]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion - 1).getTemperature().replace("℃", "").split("~")[0]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion + 1).getTemperature().replace("℃", "").split("~")[0]));
            doubleTempGraphView.setData2(temp2.get(temp2.size() - 1), temp2.get(0), Integer.parseInt(futureDayBaseWeathers.get(postion).getTemperature().replace("℃", "").split("~")[1]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion - 1).getTemperature().replace("℃", "").split("~")[1]),
                    Integer.parseInt(futureDayBaseWeathers.get(postion + 1).getTemperature().replace("℃", "").split("~")[1]));
        }


//        [FutureDayBaseWeather{temperature='-2℃~13℃', weather='晴', weather_id=[00, 00], wind='北风4-5 级', week='星期一', date='20161114'}
// FutureDayBaseWeather{temperature='-1℃~10℃', weather='晴转霾', weather_id=[00, 53], wind='微风', week='星期二', date='20161115'}
// FutureDayBaseWeather{temperature='1℃~11℃', weather='霾', weather_id=[53, 53], wind='微风', week='星期三', date='20161116'}
// FutureDayBaseWeather{temperature='5℃~10℃', weather='霾', weather_id=[53, 53], wind='微风', week='星期四', date='20161117'},
// FutureDayBaseWeather{temperature='6℃~9℃', weather='霾', weather_id=[53, 53], wind='微风', week='星期五', date='20161118'}
// , FutureDayBaseWeather{temperature='-1℃~10℃', weather='晴转霾', weather_id=[00, 53], wind='微风', week='星期六', date='20161119'},
// FutureDayBaseWeather{temperature='1℃~11℃', weather='霾', weather_id=[53, 53], wind='微风', week='星期日', date='20161120'}]
    }

}
