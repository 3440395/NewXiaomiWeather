package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.IVUpdateable;

/**
 * 最上面那个一大块的view
 * Created by xuekai on 2016/11/7.
 */

public class MainView extends RelativeLayout implements IVUpdateable<Weather> {
    public MainView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundColor(0xff2FB184);

        ImageView more = new ImageView(getContext());
        more.setImageResource(R.mipmap.icon_more);
        LayoutParams moreLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        moreLayoutParams.topMargin = 115;
        moreLayoutParams.height = 50;
        moreLayoutParams.rightMargin = 35;
        moreLayoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        more.setLayoutParams(moreLayoutParams);
        addView(more);

        TextView tempTextView = new TextView(getContext());
        tempTextView.setText("11°");
        tempTextView.setTextColor(0xffffffff);
        tempTextView.setTextSize(70);
        LayoutParams tempTextViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tempTextViewLayoutParams.topMargin = 80;
        tempTextViewLayoutParams.leftMargin = 70;
        tempTextView.setLayoutParams(tempTextViewLayoutParams);
        addView(tempTextView);

        TextView cityAndState = new TextView(getContext());
        cityAndState.setText("北京 | 晴");
        cityAndState.setTextColor(0xaaffffff);
        cityAndState.setTextSize(14);
        LayoutParams cityAndStateLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cityAndStateLayoutParams.leftMargin = 110;
        cityAndStateLayoutParams.topMargin = 320;
        cityAndState.setLayoutParams(cityAndStateLayoutParams);
        addView(cityAndState);


        LinearLayout bottomContent = new LinearLayout(getContext());
//        235
        LayoutParams bottomContentLayoutParams = new LayoutParams(235, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void update(Weather data) {

    }
}
