package com.xk.xiaomiweather.ui.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import static android.R.attr.y;

/**
 * 每一页的view
 * Created by xk on 2016/11/1 22:03.
 */

public class PageView extends ListeningScrollView implements IVUpdateable<Weather> {
    private Context context;

    //是否正在加载中
    private boolean isLoading = false;
    //手指是否处于按下状态
    private boolean isdown = false;
private boolean downTime=false;

    public PageView(Context context) {
        this(context, null);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        initListener();
    }

    private void initListener() {
        setScrollViewListener(new ScrollListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy, int computeVerticalScrollRange) {
                Log.e("PageView", "onScrollChanged" + computeVerticalScrollRange);
            }
        });
    }

    private void init() {

        TextView textView = new TextView(context);
        textView.setLines(20);
        textView.setBackgroundColor(0xff0000ff);
        textView.setTextSize(50);
        textView.setText("阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫阿道夫");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        View view = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000);
        view.setLayoutParams(layoutParams);
        linearLayout.addView(view);
        linearLayout.addView(textView);
        addView(linearLayout);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        Log.e("PageView", "onOverScrolled" + clampedY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t < 1000 && !isdown && downTime) {
//            this.smoothScrollTo(0,1000);
//            this.scrollTo(0, 1000);
            fluencyScrollTo(1000, 200);
            downTime=false;


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("MScrollView", "onTouchEvent" + this.getScrollY());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                isdown = false;
                if (this.getScrollY() < 1000) {
                    fluencyScrollTo(1000, 200);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if(getScrollY()<1000){
                    downTime=true;
                }else{
                    downTime=false;
                }
                isdown = true;
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

}
