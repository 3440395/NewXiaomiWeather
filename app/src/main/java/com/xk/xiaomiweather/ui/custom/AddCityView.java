package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.CityManageActivity;

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
        LayoutParams ivLayoutParams = new LayoutParams(105, 105);
        ivLayoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        ivLayoutParams.addRule(ALIGN_PARENT_TOP , TRUE);
        ivLayoutParams.topMargin=15;
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.icon_add);
        imageView.setLayoutParams(ivLayoutParams);
        addView(imageView);
        LayoutParams tvLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLayoutParams.addRule(CENTER_HORIZONTAL, TRUE);
        tvLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        tvLayoutParams.bottomMargin=15;
        TextView textView = new TextView(getContext());
        textView.setText("添加");
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
