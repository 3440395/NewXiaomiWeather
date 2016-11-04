package com.xk.xiaomiweather.ui.custom;

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

/**
 * 每一页的view
 * Created by xk on 2016/11/1 22:03.
 */

public class PageView extends ScrollView implements IVUpdateable<Weather>{
    private Context context;

    //是否正在加载中
    private boolean isLoading=false;
    //手指是否处于按下状态
    private boolean isdown = false;


    public PageView(Context context) {
        this(context, null);
    }

    public PageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        view.setLayoutParams(layoutParams);
        linearLayout.addView(view);
        linearLayout.addView(textView);
        addView(linearLayout);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t < 500 && !isdown) {
            this.scrollTo(0, 500);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("MScrollView", "onTouchEvent" + this.getScrollY());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                isdown = false;
                if (this.getScrollY() < 500) {
                    this.scrollTo(0, 500);
                }
                break;
            case MotionEvent.ACTION_DOWN:
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

//    float downx,downy;
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downx=ev.getX();
//                downy=ev.getY();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(ev.getX()-downx)>Math.abs(ev.getY()-downy)) {
//                    break;
//                }else{
//                    if(getScrollY()==0&&(ev.getY()-downy)>0){
//                        if((ev.getY()-downy)>200) return true;
//                        Log.e("MScrollView","onTouchEvent"+(-(int) (ev.getY()-downy)));
//                        setPadding(0,(int) (ev.getY()-downy),0,0);
//                        return true;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                setPadding(0,0,0,0);
//
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
}
