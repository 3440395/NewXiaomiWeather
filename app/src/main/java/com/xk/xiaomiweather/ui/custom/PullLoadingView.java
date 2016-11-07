package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.xk.xiaomiweather.model.manager.WeatherManager;
import com.xk.xiaomiweather.ui.callback.OnPullStateChangeListener;
import com.xk.xiaomiweather.ui.util.SharedPrenfenceUtil;

import java.text.SimpleDateFormat;

import static android.R.attr.max;

/**
 * 下拉刷新时，加载的那个view
 * Created by xk on 2016/11/5 13:26.
 */

public class PullLoadingView extends RelativeLayout {
    //可以看见的高度（根据他来确定他的两个孩子的位置）
    private int enableHeight;

    //他的父容器
    private PageView parent;

    //包含刷新时间的textview和图标的容器
    private RelativeLayout container;
    //icon
    private ImageView icon;

    //
    private TextView refreshText;
    private LayoutParams containerLayoutParams;
    private LayoutParams refreshTextLayoutParams;
    private LayoutParams iconLayoutParams;
    private int textHeight;
    private int maxPullHeight;
    //text最大的下边距（拉倒最下面时的下边距）与可见高度的比率
    private float maxTextBottomMarginRate=5f;  //icon最大的上边距（拉倒最下面时的上边距）与可见高度的比率
    private float maxIconTopMarginRate=3.5f;
    private int iconHeight;
    private boolean firstMeasure=true;

    public PullLoadingView(Context context, PageView parent,int maxPullHeight) {
        super(context);
        this.parent = parent;
        this.maxPullHeight=maxPullHeight;
        init();
        initListener();

    }
//             gaodu   dibianju
//text的底边句   0     -（textHeight）/2
//              max   max/3
    private void initListener() {
        parent.setOnPullStateChangeListener(new OnPullStateChangeListener() {

            private RotateAnimation iconRotateAnimation;

            @Override
            public void onChange(int height, boolean isLoading) {
                refreshTextLayoutParams.bottomMargin= (int) (maxPullHeight/maxTextBottomMarginRate*height/maxPullHeight+height*1f*textHeight/2/maxPullHeight-textHeight/2f);
                iconLayoutParams.topMargin= (int) (height/maxIconTopMarginRate+maxPullHeight-height);
                if (!isLoading) {
                    iconLayoutParams.width= (int) (iconHeight*1f/maxPullHeight*height);
                    iconLayoutParams.height= (int) (iconHeight*1f/maxPullHeight*height);
                    icon.setAnimation(null);
                    if (iconRotateAnimation != null) {
                        iconRotateAnimation=null;
                    }
                    String refreshTime = SharedPrenfenceUtil.getString(getContext(), parent.getWeather().getCity().getDistrict());
                    if (refreshTime!=null&&!refreshTime.equals("")) {
                        long interval = System.currentTimeMillis() - Long.parseLong(refreshTime);
                        if(interval<(60*1000)){//小于一分钟
                            refreshText.setText("刚刚更新");// TODO: by xk 2016/11/6 21:01 以后这个值要从sp中根据城市名称获取
                        }else if(interval<(60*1000*5)){//小于5分钟
                            refreshText.setText(interval/60+"分钟前更新");// TODO: by xk 2016/11/6 21:01 以后这个值要从sp中根据城市名称获取
                        }else{
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
                            String time = simpleDateFormat.format(Long.parseLong(refreshTime));
                            refreshText.setText("上次更新："+time);// TODO: by xk 2016/11/6 21:01 以后这个值要从sp中根据城市名称获取

                        }
                    }else{
                        refreshText.setText("请更新数据");// TODO: by xk 2016/11/6 21:01 以后这个值要从sp中根据城市名称获取
                    }
                }else{
                    iconRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    iconRotateAnimation.setRepeatCount(-1);
                    iconRotateAnimation.setInterpolator(new LinearInterpolator());
                    iconRotateAnimation.setDuration(3000);
                    icon.startAnimation(iconRotateAnimation);
                    refreshText.setText("正在刷新...");
                }

                icon.setLayoutParams(iconLayoutParams);
                refreshText.setLayoutParams(refreshTextLayoutParams);
            }
                   });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (firstMeasure) {
            textHeight = refreshText.getMeasuredHeight();
            iconHeight = icon.getMeasuredHeight();
            Log.e("PullLoadingView","onMeasure");
            firstMeasure=false;
        }


    }

    private void init() {


        icon = new ImageView(getContext());
        icon.setImageResource(R.mipmap.icon_sun);
        iconLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        iconLayoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        iconLayoutParams.addRule(ALIGN_PARENT_TOP,TRUE);

        refreshText=new TextView(getContext());
        refreshText.setText("刚刚更新");
        refreshText.setTextSize(12);
        refreshTextLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        refreshTextLayoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        refreshTextLayoutParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
        addView(icon);
        addView(refreshText);
    }
}
