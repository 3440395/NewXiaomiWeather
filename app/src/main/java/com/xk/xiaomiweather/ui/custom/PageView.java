package com.xk.xiaomiweather.ui.custom;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.SensorManager;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.xk.xiaomiweather.ui.AirQualityActivity;
import com.xk.xiaomiweather.ui.IVUpdateable;
import com.xk.xiaomiweather.ui.MainActivity;
import com.xk.xiaomiweather.ui.WeatherForecastActivity;
import com.xk.xiaomiweather.ui.callback.OnPullStateChangeListener;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;
import com.xk.xiaomiweather.ui.util.SharedPrenfenceUtil;

import static android.media.CamcorderProfile.get;

/**
 * 每一页的view
 * Created by xk on 2016/11/1 22:03.
 */

public class PageView extends ScrollView implements IVUpdateable<Weather> {
    private Context context;
    private OnPullStateChangeListener onPullStateChangeListener;
    //该页面代表的city
    private City city;
    //是否正在刷新中
    private boolean isRefresh = false;
    //手指抬起那一瞬间的scrollY
    private int upScrollY;
    //最大的下拉高度
    private int maxPullHeight = 440;
    //刷新的下拉高度
    private int refreshPullHeight = 195;
    //下拉刷新收回所用的时间
    private int pushTime = 200;
    //第一次绘制完成（用来使他滚动到maxPullHeight）
    private boolean firstDraw = true;
    private Weather weather;
    private MainView mainView;
    //上次刷新距离现在几分钟  可刷新 否则不刷新
    private int lastRefresh = 1;
    private WeatherItemView weatherItemView1;
    private WeatherItemView weatherItemView;
    private WeatherItemView weatherItemView2;
    private TitleItem titleItem2;
    private AdviceItemView travelItemView;
    private AdviceItemView dressItemView;
    private AdviceItemView uvItemView;
    private AdviceItemView exerciseItemView;
    private AdviceItemView washItemView;
    private DetailItem detailItem1;
    private DetailItem detailItem2;
    private ImageView airQuality;

    public PageView(Context context, City city) {
        super(context);
        this.context = context;
        this.city = city;
        init();
        initListener();
    }

    private void initListener() {
        detailItem1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeatherForecastActivity.class);
                intent.putExtra("data", weather);
                context.startActivity(intent);
            }
        });
        airQuality.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AirQualityActivity.class);
                intent.putExtra("data", weather);
                context.startActivity(intent);
            }
        });
        detailItem2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AirQualityActivity.class);
                intent.putExtra("data", weather);


                context.startActivity(intent);
            }
        });
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (firstDraw) {
            scrollTo(0, maxPullHeight);
            firstDraw = false;
            //只有刚创建下这个pager才会从本地请求数据
            requestWeatherFromDisk();
        }

    }

    /**
     * 通过目标y得到需要的初速度 （指头向上滑，需要的初速度）
     *
     * @param endY
     * @return
     */
    private int getVelocityY(int endY) {
        int signum = -1;//如果指头上滑，他肯定是-1；
        double dis = (endY - upScrollY) * signum;
        double g = Math.log(dis / ViewConfiguration.getScrollFriction() / (SensorManager.GRAVITY_EARTH // g (m/s^2)
                * 39.37f // inch/meter
                * (context.getResources().getDisplayMetrics().density * 160.0f)
                * 0.84f)) * Math.log(0.9) * ((float) (Math.log(0.78) / Math.log(0.9)) - 1.0) / (Math.log(0.78));
        return (int) (Math.exp(g) / 0.35f * (ViewConfiguration.getScrollFriction() * SensorManager.GRAVITY_EARTH
                * 39.37f // inch/meter
                * (context.getResources().getDisplayMetrics().density * 160.0f)
                * 0.84f));
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ((MainActivity) context).onScrollChange(t);
        if (t == maxPullHeight) {
            isRefresh = false;
        }
        if (t < maxPullHeight && t >= 0) {
            if (onPullStateChangeListener != null) {
                onPullStateChangeListener.onChange(maxPullHeight - t, isRefresh);
            }
        }
    }

    public void setOnPullStateChangeListener(OnPullStateChangeListener onPullStateChangeListener) {
        this.onPullStateChangeListener = onPullStateChangeListener;
    }

    @Override
    public void fling(int velocityY) {
        if (velocityY < 0) {
            if ((upScrollY + getDistance(velocityY) * Math.signum(velocityY) < maxPullHeight)) {
                int velocityY1 = -getVelocityY(maxPullHeight);
                super.fling(velocityY1);
                return;
            }
        }
        super.fling(velocityY);
    }

    /**
     * 通过初速度 获取滑动的距离
     *
     * @param velocityY
     * @return
     */
    private double getDistance(int velocityY) {

        final double l = Math.log(0.35f * Math.abs(velocityY) / (ViewConfiguration.getScrollFriction() * SensorManager.GRAVITY_EARTH
                * 39.37f // inch/meter
                * (context.getResources().getDisplayMetrics().density * 160.0f)
                * 0.84f));
        final double decelMinusOne = (float) (Math.log(0.78) / Math.log(0.9)) - 1.0;
        return ViewConfiguration.getScrollFriction() * (SensorManager.GRAVITY_EARTH // g (m/s^2)
                * 39.37f // inch/meter
                * (context.getResources().getDisplayMetrics().density * 160.0f)
                * 0.84f) * Math.exp((float) (Math.log(0.78) / Math.log(0.9)) / decelMinusOne * l);
    }

    /**
     * 当这个pager被选中的时候会调用它，由viewpager来调用
     */
    public void onSelected() {
        ((MainActivity) context).onScrollChange(getScrollY());
    }

    private void requestWeatherFromDisk() {
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                weather = WeatherManager.getInstance().getWeather(city);
                ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("PageView", "run" + weather);
                        if (weather == null) {
                            refreshData();
                        } else {
                            ((MainActivity) context).onRefresh(weather, PageView.this);
                            //请求成功了，更新ui
                            update(weather);
                        }
                    }
                });
            }
        });
    }

    private void init() {
        //隐藏scrollbar
        setVerticalScrollBarEnabled(false);


        mainView = new MainView(context);
        LinearLayout.LayoutParams mainViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1090);
        mainView.setLayoutParams(mainViewLayoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(0xffEFEFEF);
        PullLoadingView pullLoadingView = new PullLoadingView(context, this, maxPullHeight);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, maxPullHeight);
        pullLoadingView.setLayoutParams(layoutParams);
        linearLayout.addView(pullLoadingView);
        linearLayout.addView(mainView);

        //添加天气item
        ViewGroup.LayoutParams weatherItemViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 229);
        weatherItemView = new WeatherItemView(context, 0);
        weatherItemView.setLayoutParams(weatherItemViewLayoutParams);
        weatherItemView1 = new WeatherItemView(context, 1);
        weatherItemView1.setLayoutParams(weatherItemViewLayoutParams);
        weatherItemView2 = new WeatherItemView(context, 2);
        weatherItemView2.setLayoutParams(weatherItemViewLayoutParams);

        linearLayout.addView(weatherItemView);
        linearLayout.addView(weatherItemView1);
        linearLayout.addView(weatherItemView2);
        //添加几天趋势预报
        detailItem1 = new DetailItem(context, "7天趋势预报");
        ViewGroup.LayoutParams detailItemLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        detailItem1.setLayoutParams(detailItemLayoutParams);
        linearLayout.addView(detailItem1);

        //添加24小时预报title
        TitleItem titleItem1 = new TitleItem(context, "24小时预报");
        LinearLayout.LayoutParams titleItemLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        titleItemLayoutParams.topMargin = 30;
        titleItem1.setLayoutParams(titleItemLayoutParams);
        linearLayout.addView(titleItem1);

        //添加24小时预报图表（假的）
        ImageView diagram24Hours = new ImageView(context);
        diagram24Hours.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams diagram24HoursLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 546);
        diagram24Hours.setImageResource(R.mipmap.test1);
        diagram24Hours.setLayoutParams(diagram24HoursLayoutParams);
        linearLayout.addView(diagram24Hours);

        //添加 空气质量 title
        titleItem2 = new TitleItem(context, "空气质量");
        titleItem2.setLayoutParams(titleItemLayoutParams);
        linearLayout.addView(titleItem2);

        //添加空气质量图表（假的）
        airQuality = new ImageView(context);
        airQuality.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams airQualityLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 442);
        airQuality.setImageResource(R.mipmap.test2);
        airQuality.setLayoutParams(airQualityLayoutParams);
        linearLayout.addView(airQuality);

        //添加空气质量预报详情
        detailItem2 = new DetailItem(context, "空气质量详情");
        detailItem2.setLayoutParams(detailItemLayoutParams);
        linearLayout.addView(detailItem2);

        //添加出行建议 title
        TitleItem titleItem3 = new TitleItem(context, "出行建议");
        titleItem3.setLayoutParams(titleItemLayoutParams);
        linearLayout.addView(titleItem3);

        //添加打伞建议
        travelItemView = new AdviceItemView(context, R.mipmap.icon_san);
        LinearLayout.LayoutParams travelLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 255);
        travelItemView.setLayoutParams(travelLayoutParams);
        linearLayout.addView(travelItemView);
        //添加穿衣建议
        dressItemView = new AdviceItemView(context, R.mipmap.icon_dress);
        LinearLayout.LayoutParams dressLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 255);
        dressItemView.setLayoutParams(dressLayoutParams);
        linearLayout.addView(dressItemView);
        //添加打紫外线建议
        uvItemView = new AdviceItemView(context, R.mipmap.icon_uv);
        LinearLayout.LayoutParams uvLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 255);
        uvItemView.setLayoutParams(uvLayoutParams);
        linearLayout.addView(uvItemView);
        //添加运动建议 title
        TitleItem titleItem4 = new TitleItem(context, "运动建议");
        titleItem4.setLayoutParams(titleItemLayoutParams);
        linearLayout.addView(titleItem4);
        //添加运动建议
        exerciseItemView = new AdviceItemView(context, R.mipmap.icon_exercise);
        LinearLayout.LayoutParams exerciseLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 255);
        exerciseItemView.setLayoutParams(exerciseLayoutParams);
        linearLayout.addView(exerciseItemView); //添加出行建议 title
        //添加汽车建议 title
        TitleItem titleItem5 = new TitleItem(context, "驾车建议");
        titleItem5.setLayoutParams(titleItemLayoutParams);
        linearLayout.addView(titleItem5);
        //添加洗车建议
        washItemView = new AdviceItemView(context, R.mipmap.icon_wash);
        LinearLayout.LayoutParams washLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 255);
        washItemView.setLayoutParams(washLayoutParams);
        linearLayout.addView(washItemView); //添加出行建议 title


        TextView adTextView = new TextView(context);
        adTextView.setTextSize(12);
        adTextView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams adTextViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        adTextView.setLayoutParams(adTextViewLayoutParams);
        adTextView.setText("环境云     聚合天气");
        linearLayout.addView(adTextView);
        addView(linearLayout);


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefresh) {//如果当前是刷新状态，那么一切触摸事件都直接消费掉
            requestDisallowInterceptTouchEvent(true);
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                upScrollY = getScrollY();
                if (upScrollY < maxPullHeight && upScrollY > 0) {
                    fluencyScrollTo(maxPullHeight, pushTime);
                } else if (this.getScrollY() == 0) {
                    String time = SharedPrenfenceUtil.getString(context, city.getDistrict());
                    if (time != null && !time.equals("")) {
                        long interval = System.currentTimeMillis() - Long.parseLong(time);
                        if (weather != null && weather.getAqiWeather().getCurrentAQIWeather() != null && interval > lastRefresh * 60 * 1000) {
                            refreshData();
                        } else {
                            fluencyScrollTo(maxPullHeight, pushTime);
                        }
                    } else {
                        refreshData();
                    }


                }
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void update(Weather data) {
        weather = data;
        if (data != null && data.getBaseWeather().getFutureDayBaseWeathers().size() > 0) {
            Log.e("PageView", "update" + data.getBaseWeather().getTodayBaseWeather());
            mainView.update(data);
            weatherItemView.update(data);
            weatherItemView1.update(data);
            weatherItemView2.update(data);
            travelItemView.update(data.getBaseWeather().getTodayBaseWeather());
            dressItemView.update(data.getBaseWeather().getTodayBaseWeather());
            uvItemView.update(data.getBaseWeather().getTodayBaseWeather());
            exerciseItemView.update(data.getBaseWeather().getTodayBaseWeather());
            washItemView.update(data.getBaseWeather().getTodayBaseWeather());
        }
        if (data != null && data.getAqiWeather().getCurrentAQIWeather() != null) {
            titleItem2.update(data.getAqiWeather().getCurrentAQIWeather());
        }
    }

    /**
     * 在一段时间内滑动到某个位置
     *
     * @param toY
     * @param duration
     */
    public void fluencyScrollTo(int toY, int duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(getScrollY(), toY);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer scrollY = (Integer) animation.getAnimatedValue();
                scrollTo(0, scrollY);
            }
        });

        valueAnimator.start();
    }

    /**
     * 调用它可以刷新数据
     */
    @UiThread
    public void refreshData() {
        fluencyScrollTo(refreshPullHeight, pushTime);
        isRefresh = true;
        //开一个线程请求数据，请求到之后，把isRefresh设置成false，然后滚动到maxPullHeight
        ExecutorUtil.getInstance().runOnSingleThread(new Runnable() {
            @Override
            public void run() {
                weather = WeatherManager.getInstance().refreshWeather(city);
                isRefresh = false;
                ExecutorUtil.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fluencyScrollTo(maxPullHeight, 200);
                        ((MainActivity) context).onRefresh(weather, PageView.this);
                        SharedPrenfenceUtil.putString(context, city.getDistrict(), System.currentTimeMillis() + "");
                        //刷新成功了  更新ui
                        update(weather);

                        Toast.makeText(context, "获取天气成功" + weather.getCity().getDistrict() + "" + weather.getBaseWeather().getCurrentBaseWeather().getHumidity(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
