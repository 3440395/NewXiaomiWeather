package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import static com.xk.xiaomiweather.R.id.aqi;
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

        String pm25 = data.getPM25();
        String aqiDes;
        if (pm25 != null) {
            int ipm25 = Integer.parseInt(pm25);
            if (ipm25 >= 0 && ipm25 <= 50) {
                aqiDes = "优";
            } else if (ipm25 > 50 && ipm25 <= 100) {
                aqiDes = "良";

            } else if (ipm25 > 100 && ipm25 <= 150) {
                aqiDes = "中度污染";
            } else {
                aqiDes = "重度污染";
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
