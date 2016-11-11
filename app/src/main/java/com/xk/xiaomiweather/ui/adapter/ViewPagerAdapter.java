package com.xk.xiaomiweather.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xk.xiaomiweather.ui.custom.PageView;

import java.util.List;

/**
 * Created by xuekai on 2016/11/4.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<PageView> views;

    public ViewPagerAdapter( List<PageView> views) {
        this.views = views;
    }

    public List<PageView> getViews() {
        return views;
    }

    public void setViews(List<PageView> views) {
        this.views = views;
        Log.e("ViewPagerAdapter","setViews"+views.size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return views.size();
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
}
