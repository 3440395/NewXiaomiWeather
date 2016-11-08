package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by xuekai on 2016/11/8.
 */

public class DetailItem  extends RelativeLayout {

    private TextView textView;
    private String content;

    public DetailItem(Context context,String content) {
        super(context);
        this.content=content;
        init();
    }

    private void init() {
        setBackgroundColor(0xffffffff);
        View line = new View(getContext());
        ViewGroup.LayoutParams lineLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        line.setLayoutParams(lineLayoutParams);
        line.setBackgroundColor(0xffe7e7e7);
        addView(line);
        textView = new TextView(getContext());
        textView.setText(content);
        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(CENTER_IN_PARENT,TRUE);
        textView.setLayoutParams(textLayoutParams);
        addView(textView);
    }
}
