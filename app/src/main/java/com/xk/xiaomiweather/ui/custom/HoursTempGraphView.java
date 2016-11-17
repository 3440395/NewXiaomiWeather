package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xk.xiaomiweather.model.bean.BaseWeather;
import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xk on 2016/11/9 22:14.
 */

public class HoursTempGraphView extends RecyclerView {
    private List<ThreeHourBaseWeather> data = new ArrayList<>();
    private Adapter adapter;

    public HoursTempGraphView(Context context) {
        super(context);
        init();
    }


    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new Adapter();
        setAdapter(adapter);
    }

    public void setData(List<ThreeHourBaseWeather> data) {
        if (this.data != null) {
            this.data.clear();
            this.data.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HourWeatherItem hourWeatherItem = new HourWeatherItem(getContext());

            LayoutParams layoutParams = new LayoutParams(ScreenManager.getInstance().adpW(200), ViewGroup.LayoutParams.MATCH_PARENT);
            hourWeatherItem.setLayoutParams(layoutParams);
            return new ViewHolder(hourWeatherItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ThreeHourBaseWeather threeHourBaseWeather = data.get(position);
            holder.hourWeatherItem.setTime(data.get(position).getEh() + ":00");
            if (position == 0) {
                holder.hourWeatherItem.setDrawLeftLine(false);
                holder.hourWeatherItem.setTime("现在");

            } else {
                holder.hourWeatherItem.setDrawLeftLine(true);
                int temp = (Integer.parseInt(data.get(position - 1).getTemp1()) + Integer.parseInt(data.get(position - 1).getTemp2())) / 2;
                holder.hourWeatherItem.setLastTemp(temp);
            }
            if (position == data.size() - 1) {
                holder.hourWeatherItem.setDrawRightLine(false);
            } else {
                holder.hourWeatherItem.setDrawRightLine(true);
                int temp = (Integer.parseInt(data.get(position + 1).getTemp1()) + Integer.parseInt(data.get(position + 1).getTemp2())) / 2;
                holder.hourWeatherItem.setNextTemp(temp);
            }
            int temp = (Integer.parseInt(data.get(position).getTemp1()) + Integer.parseInt(data.get(position).getTemp2())) / 2;
            holder.hourWeatherItem.setCurrentTemp(temp);

            if(data.get(position).getEh().contains("00")||data.get(position).getEh().contains("01")||data.get(position).getEh().contains("02")||position==0||position==data.size()-1){
                holder.hourWeatherItem.setDrawDotte(true);
            }else{
                holder.hourWeatherItem.setDrawDotte(false);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            HourWeatherItem hourWeatherItem;

            public ViewHolder(View itemView) {
                super(itemView);
                this.hourWeatherItem = (HourWeatherItem) itemView;
            }
        }
    }

}
