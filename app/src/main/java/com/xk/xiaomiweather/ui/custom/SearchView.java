package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;

/**
 * Created by xuekai on 2016/11/9.
 */

public class SearchView extends LinearLayout {
    public SearchView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackground(getResources().getDrawable(R.drawable.bg_search));
        ImageView icon = new ImageView(getContext());
        LayoutParams iconParams = new LayoutParams(48, 48);
        iconParams.gravity= Gravity.CENTER_VERTICAL;
        icon.setLayoutParams(iconParams);
        icon.setImageResource(R.mipmap.icon_search);
        addView(icon);

        TextView textView = new TextView(getContext());
        LayoutParams textViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textViewParams.gravity= Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(textViewParams);
        textView.setHint("请输入国家");
        addView(textView);

    }
}
