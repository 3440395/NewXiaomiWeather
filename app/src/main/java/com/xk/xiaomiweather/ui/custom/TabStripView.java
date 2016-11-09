package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;

/**
 * Created by xuekai on 2016/11/9.
 */

public class TabStripView extends LinearLayout {
    private String stab1,stab2;
    private TextView tab1;
    private TextView tab2;


    public TabStripView(Context context, String tab1, String tab2) {
        super(context);
        this.stab1=tab1;
        this.stab2=tab2;
        init();
        initListener();
    }

    private void initListener() {
        tab1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
            }
        });
        tab2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(2);
            }
        });
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setBackground(getResources().getDrawable(R.drawable.bg_tabstrip));

        tab1 = new TextView(getContext());
        tab1.setGravity(Gravity.CENTER);
        tab1.setText(stab1);
        tab2 = new TextView(getContext());
        tab2.setText(stab2);
        tab2.setGravity(Gravity.CENTER);

        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight=1;
        layoutParams.setMargins(1,1,1,1);
        tab1.setLayoutParams(layoutParams);
        tab2.setLayoutParams(layoutParams);

        View line = new View(getContext());
        line.setBackgroundColor(0xffdadada);
        ViewGroup.LayoutParams lineParams = new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
        line.setLayoutParams(lineParams);
        addView(tab1);
        addView(line);
        addView(tab2);
        selectTab(1);
    }

    public void selectTab(int index){
        if (index==1) {
            tab1.setBackgroundColor(0xfff3f3f3);
            tab2.setBackgroundColor(0x00000000);
        }else{
            tab2.setBackgroundColor(0xfff3f3f3);
            tab1.setBackgroundColor(0x00000000);
        }

    }
}
