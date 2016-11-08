package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import static com.xk.xiaomiweather.R.id.aqi_index;
import static com.xk.xiaomiweather.R.id.wind_direction;
import static com.xk.xiaomiweather.R.id.wind_strength;

/**
 * Created by xuekai on 2016/11/8.
 */

public class TitleItem extends FrameLayout implements IVUpdateable<CurrentAQIWeather>{

    private TextView aqi_des;
    private TextView time;
    private View line;

    public TitleItem(Context context,String title) {
        super(context);
        init(title);
    }

    private void init(String stitle) {
        View item = View.inflate(getContext(), R.layout.layout_title_item, null);
        TextView title = (TextView) item.findViewById(R.id.title);
        line = item.findViewById(R.id.line);
        aqi_des = (TextView) item.findViewById(R.id.aqi_des);
        time = (TextView) item.findViewById(R.id.time);

        title.setText(stitle);

        addView(item);
    }

    @Override
    public void update(CurrentAQIWeather data) {
        String stime = data.getTime().substring(8);
        time.setVisibility(VISIBLE);
        time.setText(stime+":00发布");

        String aqi = data.getAQI();
        String aqiDes;
        if (aqi != null) {
            int iaqi = Integer.parseInt(aqi);
            if (iaqi >= 0 && iaqi <= 50) {
                aqiDes = "优";
            } else if (iaqi > 50 && iaqi <= 100) {
                aqiDes = "良";

            } else if (iaqi > 100 && iaqi <= 150) {
                aqiDes = "中";
            } else {
                aqiDes = "差";
            }
            line.setVisibility(VISIBLE);
            aqi_des.setVisibility(VISIBLE);
            aqi_des.setText(aqiDes);
        }else{
            aqi_des.setVisibility(INVISIBLE);
            line.setVisibility(INVISIBLE);
        }

    }
}
