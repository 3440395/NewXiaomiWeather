package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import org.w3c.dom.Text;

/**
 * Created by xk on 2016/11/8 20:15.
 */

public class AdviceItemView extends FrameLayout implements IVUpdateable<TodayBaseWeather> {

    private int iconId;
    private TextView intro;
    private TextView des;

    public AdviceItemView(Context context, @DrawableRes int icon) {
        super(context);
        this.iconId = icon;
        init(icon);
    }

    private void init(int iconId) {
        View item = View.inflate(getContext(), R.layout.layout_advice_item, null);
        ImageView icon = (ImageView) item.findViewById(R.id.icon);
        intro = (TextView) item.findViewById(R.id.intro);
        des = (TextView) item.findViewById(R.id.des);
        icon.setImageResource(iconId);
        addView(item);
    }

    @Override
    public void update(TodayBaseWeather data) {
        switch (iconId) {
            case R.mipmap.icon_dress:
                intro.setText(data.getDressing_index());
                des.setText(data.getDressing_advice());
                break;
            case R.mipmap.icon_wash:
                intro.setText(data.getWash_index());
//                des.setText(data.getWash_index());
                break;
            case R.mipmap.icon_exercise:
                intro.setText(data.getExercise_index());
//                des.setText(data.getExercise_index());
                break;
            case R.mipmap.icon_san:
                intro.setText(data.getTravel_index());
//                des.setText(data.getDressing_advice());
                break;
            case R.mipmap.icon_uv:
                intro.setText(data.getUv_index());
//                des.setText(data.getDressing_advice());
                break;
        }

    }
}
