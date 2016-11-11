package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.CityManageActivity;

/**
 * Created by xuekai on 2016/11/9.
 */

public class SearchView extends LinearLayout {
    private String district;
    public SearchView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackground(getResources().getDrawable(R.drawable.bg_search));
        ImageView icon = new ImageView(getContext());
        LayoutParams iconParams = new LayoutParams(48, 48);
        iconParams.gravity = Gravity.CENTER_VERTICAL;
        iconParams.leftMargin = 10;
        icon.setLayoutParams(iconParams);
        icon.setImageResource(R.mipmap.icon_search);
        addView(icon);

        EditText editText = new EditText(getContext());
        editText.setGravity(Gravity.CENTER_VERTICAL);
        editText.setSingleLine();
        LayoutParams textViewParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        textViewParams.weight=1;
        textViewParams.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(textViewParams);
        editText.setHint("请输入城市");
        editText.setTextSize(13);
        editText.setPadding(5, 0 - 5, 0, 0 - 5);
        editText.setBackground(null);
        addView(editText);

        final FrameLayout frameLayout = new FrameLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);
        View line = new View(getContext());
        line.setBackgroundColor(0xff7e7e7e);
        ViewGroup.LayoutParams lineLayoutParams = new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
        line.setLayoutParams(lineLayoutParams);
        frameLayout.addView(line);
        TextView textView = new TextView(getContext());
        FrameLayout.LayoutParams textLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(textLayoutParams);
        textView.setGravity(Gravity.CENTER);
        textLayoutParams.leftMargin=20;
        textLayoutParams.rightMargin=20;
        textView.setText("搜索");
        frameLayout.addView(textView);
        addView(frameLayout);
        frameLayout.setVisibility(GONE);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    frameLayout.setVisibility(VISIBLE);
                    district=s.toString();

                }else{
                    frameLayout.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        frameLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CityManageActivity)getContext()).searchCity(district);
            }
        });
    }
}
