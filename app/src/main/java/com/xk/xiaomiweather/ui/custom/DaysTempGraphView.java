package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 未来几天的天气预报曲线图
 * Created by xk on 2016/11/9 22:14.
 */

public class DaysTempGraphView extends RecyclerView {
    private List<FutureDayBaseWeather> data = new ArrayList<>();
    private Adapter adapter;

    public DaysTempGraphView(Context context) {
        super(context);
        init();
    }


    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new Adapter();
        setAdapter(adapter);
    }

    public void setData(List<FutureDayBaseWeather> data) {
        if (this.data != null) {
            this.data.clear();
            this.data.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FutureWeatherItem futureWeatherItem = new FutureWeatherItem(getContext());
            LayoutParams layoutParams = new LayoutParams(ScreenManager.getInstance().adpW(240), ViewGroup.LayoutParams.MATCH_PARENT);
            futureWeatherItem.setLayoutParams(layoutParams);
            return new ViewHolder(futureWeatherItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.futureWeatherItem.updata(data,position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            FutureWeatherItem futureWeatherItem;

            public ViewHolder(View itemView) {
                super(itemView);
                this.futureWeatherItem = (FutureWeatherItem) itemView;
            }
        }
    }

}
