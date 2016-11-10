package com.xk.xiaomiweather.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.ui.CityManageActivity;
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
    private ImageView back;
    private SearchView searchView;
    private TextView title;
    private boolean isNormal=true;

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
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNormal) {
                    ((Activity)getContext()).finish();
                }else{
                    goNormal();
                }
            }
        });

        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goEdit();
                ((CityManageActivity)getContext()).goEdit();

            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goNormal();
                ((CityManageActivity)getContext()).goNormal();
            }
        });

        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goNormal();
                ((CityManageActivity)getContext()).goNormal();
            }
        });

        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
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

        title = new TextView(getContext());
        title.setText("城市管理");
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_IN_PARENT,TRUE);
        title.setLayoutParams(titleParams);
        contain.addView(title);


        searchView = new SearchView(getContext());
        RelativeLayout.LayoutParams searchViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,85 );
        searchViewParams.addRule(CENTER_VERTICAL,TRUE);
        searchViewParams.addRule(ALIGN_PARENT_RIGHT,TRUE);
        searchViewParams.addRule(ALIGN_PARENT_LEFT,TRUE);
        searchViewParams.leftMargin=120;
        searchViewParams.rightMargin=40;
        searchView.setLayoutParams(searchViewParams);
        contain.addView(searchView);


        LayoutParams buttonParams3 = new LayoutParams(85, 85);
        buttonParams3.addRule(CENTER_VERTICAL, TRUE);
        buttonParams3.addRule(ALIGN_PARENT_LEFT,TRUE);
        buttonParams3.leftMargin=15;
        back = new ImageView(getContext());
        back.setScaleType(ImageView.ScaleType.CENTER_CROP);
        back.setImageResource(R.mipmap.icon_back1);
        back.setLayoutParams(buttonParams3);
        contain.addView(back);

        addView(contain);


        goNormal();
    }


    public void goSearch(){
        isNormal=false;
        cancel.setVisibility(GONE);
        edit.setVisibility(GONE);
        confirm.setVisibility(GONE);
        title.setVisibility(GONE);
        back.setVisibility(VISIBLE);
        searchView.setVisibility(VISIBLE);
    }

    public void goEdit(){
        ((CityManageActivity)getContext()).showAdd(false);
        isNormal=false;
        cancel.setVisibility(VISIBLE);
        edit.setVisibility(GONE);
        confirm.setVisibility(VISIBLE);
        title.setVisibility(VISIBLE);
        back.setVisibility(GONE);
        searchView.setVisibility(GONE);
    }

    public void goNormal(){
        ((CityManageActivity)getContext()).showAdd(true);
        isNormal=true;
        cancel.setVisibility(GONE);
        edit.setVisibility(VISIBLE);
        confirm.setVisibility(GONE);
        title.setVisibility(VISIBLE);
        back.setVisibility(VISIBLE);
        searchView.setVisibility(GONE);
    }
}
