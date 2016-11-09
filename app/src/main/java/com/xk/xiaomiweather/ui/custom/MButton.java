package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by xuekai on 2016/11/9.
 */

public class MButton extends TextView {
    public MButton(Context context, String text, int background,int textColor) {
        super(context);
        init(text,background,textColor);
    }

    private void init(String text, int background,int textColor) {
        setText(text);
        setGravity(Gravity.CENTER);
        setTextColor(textColor);
        setBackgroundDrawable(getResources().getDrawable(background));
    }

}
