package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.CityManageActivity;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by xk on 2016/11/9 20:12.
 */

public class AddCityView extends RelativeLayout {
    public AddCityView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundColor(0xffffffff);
        LayoutParams ivLayoutParams = new LayoutParams(ScreenManager.getInstance().adpW(100), ScreenManager.getInstance().adpH(100));
        ivLayoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        ivLayoutParams.addRule(ALIGN_PARENT_TOP , TRUE);
        ivLayoutParams.topMargin=ScreenManager.getInstance().adpH(15);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.mipmap.icon_add);
        imageView.setLayoutParams(ivLayoutParams);
        addView(imageView);
        LayoutParams tvLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLayoutParams.addRule(CENTER_HORIZONTAL, TRUE);
        tvLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        tvLayoutParams.bottomMargin=ScreenManager.getInstance().adpH(15);
        TextView textView = new TextView(getContext());
        textView.setText("添加");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,ScreenManager.getInstance().adpH(45));
        textView.setLayoutParams(tvLayoutParams);
        addView(textView);

        View line= new View(getContext());
        line.setBackgroundColor(0xffe7e7e7);
        LayoutParams lineLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        line.setLayoutParams(lineLayoutParams);
        addView(line);


        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CityManageActivity)getContext()).goSearch();
                hide();
            }
        });
    }

    public void show(){
        this.setVisibility(VISIBLE);
    }
    public void hide(){
        this.setVisibility(GONE);
    }
}
