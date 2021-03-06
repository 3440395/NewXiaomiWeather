package com.xk.xiaomiweather.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.CityManageActivity;
import com.xk.xiaomiweather.ui.IVUpdateable;
import com.xk.xiaomiweather.ui.util.IconUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import org.w3c.dom.Text;

/**
 * 最上面那个一大块的view
 * Created by xuekai on 2016/11/7.
 */

public class MainView extends RelativeLayout implements IVUpdateable<Weather> {

    private TextView aqi;
    private TextView aqi_index;
    private TextView wind_direction;
    private TextView wind_strength;
    private TextView humidity;
    private TextView tempTextView;
    private TextView cityAndState;
    private ImageView more;
    private ImageView background;
    private ScaleAnimation scaleAnimation;

    public MainView(Context context) {
        super(context);
        init();
        initListener();
    }

    private void initListener() {
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).startActivityForResult(new Intent(getContext(),CityManageActivity.class),0);
            }
        });
    }

    private void init() {
        setBackgroundColor(0xff2FB184);

        background = new ImageView(getContext());
        background.setScaleType(ImageView.ScaleType.FIT_XY);
        background.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(background);

        more = new ImageView(getContext());
        more.setImageResource(R.mipmap.icon_more);
        LayoutParams moreLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        moreLayoutParams.topMargin =  ScreenManager.getInstance().adpH(115);;
        moreLayoutParams.height =  ScreenManager.getInstance().adpH(50);;
        moreLayoutParams.rightMargin =  ScreenManager.getInstance().adpW(35);;
        moreLayoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        more.setLayoutParams(moreLayoutParams);
        addView(more);

        tempTextView = new TextView(getContext());
        tempTextView.setText("--");
        tempTextView.setTextColor(0xffffffff);
        tempTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,ScreenManager.getInstance().adpH(165));
        LayoutParams tempTextViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tempTextViewLayoutParams.topMargin =  ScreenManager.getInstance().adpH(80);;
        tempTextViewLayoutParams.leftMargin =  ScreenManager.getInstance().adpW(70);;
        tempTextView.setLayoutParams(tempTextViewLayoutParams);
        addView(tempTextView);

        cityAndState = new TextView(getContext());
        cityAndState.setText("");
        cityAndState.setTextColor(0xaaffffff);
        cityAndState.setTextSize( TypedValue.COMPLEX_UNIT_PX,ScreenManager.getInstance().adpH(45));
        LayoutParams cityAndStateLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cityAndStateLayoutParams.leftMargin =  ScreenManager.getInstance().adpW(80);
        cityAndStateLayoutParams.topMargin =  ScreenManager.getInstance().adpH(320);
        cityAndState.setLayoutParams(cityAndStateLayoutParams);
        addView(cityAndState);


        LayoutParams bottomContainLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ScreenManager.getInstance().adpH(235));
        bottomContainLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        View bottomContain = View.inflate(getContext(), R.layout.layout_main_bottom, null);
        bottomContain.setLayoutParams(bottomContainLayoutParams);

        addView(bottomContain);

        aqi = (TextView) bottomContain.findViewById(R.id.aqi);
        aqi_index = (TextView) bottomContain.findViewById(R.id.aqi_index);
        wind_direction = (TextView) bottomContain.findViewById(R.id.wind_direction);
        wind_strength = (TextView) bottomContain.findViewById(R.id.wind_strength);
        humidity = (TextView) bottomContain.findViewById(R.id.humidity);
    }

    @Override
    public void update(Weather data) {
        IconUtil.setMainViewBg(background,data.getBaseWeather().getTodayBaseWeather().getWeather_id()[0]);
        scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(10000);
        scaleAnimation.setRepeatCount(-1);
        background.startAnimation(scaleAnimation);
        cityAndState.setText(data.getCity().getDistrict());
        if (data.getBaseWeather().getCurrentBaseWeather() != null) {

            tempTextView.setText(data.getBaseWeather().getCurrentBaseWeather().getTemp() + "°");

            wind_direction.setText(data.getBaseWeather().getCurrentBaseWeather().getWind_direction());
            wind_strength.setText(data.getBaseWeather().getCurrentBaseWeather().getWind_strength());
            humidity.setText(data.getBaseWeather().getCurrentBaseWeather().getHumidity());
        }
        if (data.getAqiWeather().getCurrentAQIWeather() != null) {
            String pm25 = data.getAqiWeather().getCurrentAQIWeather().getPM25();

            String  aqiDes = "PM2.5";
                int ipm25 = Integer.parseInt(pm25);

                aqi.setText(aqiDes);
                aqi_index.setText(ipm25+"");

            String[] split = null;
            if (data.getBaseWeather().getTodayBaseWeather().getWeather().contains("-")) {
                split = data.getBaseWeather().getTodayBaseWeather().getWeather().split("-");
                cityAndState.setText(data.getCity().getDistrict() + " | " + split[0]);
            } else {
                cityAndState.setText(data.getCity().getDistrict() + " | " + data.getBaseWeather().getTodayBaseWeather().getWeather());
            }
        }
    }
}
