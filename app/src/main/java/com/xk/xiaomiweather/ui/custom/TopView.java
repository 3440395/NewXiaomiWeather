package com.xk.xiaomiweather.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.lang.reflect.Field;

/**
 * Created by xk on 2016/11/6 18:21.
 */

public class TopView extends RelativeLayout{
//表示城市和温度的textview
    private TextView textView;

    public TopView(Context context) {
        super(context);
        init();

    }

    private void init() {
        setAlpha(0);
        setBackgroundColor(0xffffffff);

        View line = new View(getContext());
        line.setBackgroundColor(0xffe7e7e7);
        LayoutParams lineLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lineLayoutParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
        line.setLayoutParams(lineLayoutParams);
        addView(line);
        LinearLayout content = new LinearLayout(getContext());
        content.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams contentLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        contentLayoutParams.topMargin=ScreenManager.getInstance().getStatusBarHeight();
        contentLayoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        content.setLayoutParams(contentLayoutParams);
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER_VERTICAL;
        imageView.setImageResource(R.mipmap.icon_sun);
        imageView.setLayoutParams(layoutParams);
        textView = new TextView(getContext());
        textView.setText(" 朝阳 10°C");
        textView.setLayoutParams(layoutParams);



        content.addView(imageView);
        content.addView(textView);

        addView(content);
    }


    public void setText(String city,String temp){
        Log.e("TopView","被设置了"+city);
        if (temp.equals("")) {
            textView.setText(" "+city);
        }else{
            textView.setText(" "+city +" "+temp+"°C");

        }
    }
}
