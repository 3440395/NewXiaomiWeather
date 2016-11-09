package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import static android.R.string.cancel;

/**
 * 200
 * Created by xuekai on 2016/11/9.
 */

public class MToolBar extends RelativeLayout {

    private MButton cancel;
    private MButton confirm;
    private MButton edit;

    public MToolBar(Context context) {
        super(context);
        init();
        initListener();
    }

    private void initListener() {
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init() {
        setBackgroundColor(0xfff2f2f2);
        RelativeLayout contain = new RelativeLayout(getContext());
        LayoutParams containParams = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 140);
        containParams.topMargin = ScreenManager.getInstance().getStatusBarHeight();
        contain.setLayoutParams(containParams);

        LayoutParams buttonParams1 = new LayoutParams(140, 85);
        buttonParams1.addRule(CENTER_VERTICAL, TRUE);
        buttonParams1.addRule(ALIGN_PARENT_LEFT,TRUE);
        buttonParams1.leftMargin=30;
        cancel = new MButton(getContext(), "取消", R.drawable.bg_button_write, 0xff000000);
        cancel.setLayoutParams(buttonParams1);
        contain.addView(cancel);

        LayoutParams buttonParams2 = new LayoutParams(140, 85);
        buttonParams2.addRule(CENTER_VERTICAL, TRUE);
        buttonParams2.addRule(ALIGN_PARENT_RIGHT,TRUE);
        buttonParams2.rightMargin=30;

        confirm = new MButton(getContext(), "确定", R.drawable.bg_button_blue, 0xffffffff);
        confirm.setLayoutParams(buttonParams2);
        contain.addView(confirm);

        edit = new MButton(getContext(), "编辑", R.drawable.bg_button_write, 0xff000000);
        edit.setLayoutParams(buttonParams2);
        contain.addView(edit);

        TextView title = new TextView(getContext());
        title.setText("城市管理");
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_IN_PARENT,TRUE);
        title.setLayoutParams(titleParams);
        contain.addView(title);


        TextView search = new TextView(getContext());
        search.setHint("查询国内城市");

        addView(contain);
    }
}
