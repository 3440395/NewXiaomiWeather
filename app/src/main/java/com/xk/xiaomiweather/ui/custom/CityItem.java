package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.ui.CityManageActivity;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import static android.R.attr.data;

/**
 * Created by xk on 2016/11/9 21:49.
 */

public class CityItem extends RelativeLayout {

    private ImageView imageView;
    private TextView textView;

    public CityItem(Context context) {
        super(context);
        init();
    }

    private void init() {
        View line = new View(getContext());
        line.setBackgroundColor(0xffe7e7e7);
        LayoutParams lineLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lineLayoutParams.leftMargin = ScreenManager.getInstance().adpW(70);
        lineLayoutParams.rightMargin =  ScreenManager.getInstance().adpW(70);
        lineLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        line.setLayoutParams(lineLayoutParams);
        addView(line);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_VERTICAL, TRUE);
        layoutParams.leftMargin =  ScreenManager.getInstance().adpW(70);
        linearLayout.setLayoutParams(layoutParams);


        imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.icon_delete);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams ivLayoutParams = new LinearLayout.LayoutParams( ScreenManager.getInstance().adpW(58),  ScreenManager.getInstance().adpH(58));
        ivLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        ivLayoutParams.rightMargin =  ScreenManager.getInstance().adpW(20);
        imageView.setLayoutParams(ivLayoutParams);
        linearLayout.addView(imageView);

        textView = new TextView(getContext());
        textView.setText("美国");

        textView.setTextSize(14);
        LinearLayout.LayoutParams tvLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLayoutParams.leftMargin =  ScreenManager.getInstance().adpW(10);
        tvLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(tvLayoutParams);
        linearLayout.addView(textView);


        addView(linearLayout);

    }

    public void setDeletable(boolean canDelete) {
        imageView.setVisibility(canDelete ? VISIBLE : GONE);
    }

    public void setOnDeleteClickListener(OnClickListener onClickListener) {
        imageView.setOnClickListener(onClickListener);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
