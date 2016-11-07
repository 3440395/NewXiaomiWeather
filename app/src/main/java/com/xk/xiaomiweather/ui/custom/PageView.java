package com.xk.xiaomiweather.ui.custom;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorManager;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.xk.xiaomiweather.ui.IVUpdateable;
import com.xk.xiaomiweather.ui.MainActivity;
import com.xk.xiaomiweather.ui.callback.OnPullStateChangeListener;
import com.xk.xiaomiweather.ui.util.ExecutorUtil;
import com.xk.xiaomiweather.ui.util.ScreenManager;

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

    public PageView(Context context, City city) {
        super(context);
        this.context = context;
        this.city = city;
        init();
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


        MainView mainView = new MainView(context);
        LinearLayout.LayoutParams mainViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1090);
        mainView.setLayoutParams(mainViewLayoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        PullLoadingView view = new PullLoadingView(context, this, maxPullHeight);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, maxPullHeight);
        view.setLayoutParams(layoutParams);
        linearLayout.addView(view);
        linearLayout.addView(mainView);

        TextView textView = new TextView(context);

        textView.setTextSize(30);
        textView.setText("adfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfaadfadfa");
        linearLayout.addView(textView);
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
                    Log.e("PageView", "onTouchEvent已到达最大高度 开始刷新");
                    refreshData();
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
                        //刷新成功了  更新ui
                        update(weather);
                        Toast.makeText(context, "获取天气成功" + weather.getCity().getDistrict() + "" + weather.getBaseWeather().getCurrentBaseWeather().getHumidity(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
