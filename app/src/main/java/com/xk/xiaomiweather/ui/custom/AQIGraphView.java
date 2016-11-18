package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xk.xiaomiweather.model.bean.FutureDayBaseWeather;
import com.xk.xiaomiweather.model.bean.ThreeHourBaseWeather;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.media.CamcorderProfile.get;
import static com.xk.xiaomiweather.R.id.weather;

/**
 * Created by xk on 2016/11/9 22:14.
 */

public class AQIGraphView extends RecyclerView {
    private Map<String, String> data = new HashMap<>();
    private Adapter adapter;
    private List<String> keyList = new ArrayList<>();
    private boolean isHours;

    public AQIGraphView(Context context,boolean isHours) {
        super(context);
        init();
        this.isHours=isHours;
    }


    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new Adapter();
        setAdapter(adapter);
    }

    public void setData(Map<String, String> data) {
        if (this.data != null) {

            Set<String> strings = data.keySet();
            keyList.clear();
            keyList.addAll(strings);
            Collections.sort(keyList);
            adapter.notifyDataSetChanged();
            this.data = data;
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            AQIItem aqiItem = new AQIItem(getContext());

            LayoutParams layoutParams = new LayoutParams(ScreenManager.getInstance().adpW(200), ViewGroup.LayoutParams.MATCH_PARENT);
            aqiItem.setLayoutParams(layoutParams);
            return new ViewHolder(aqiItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isHours) {
                holder.aqiItem.setTime(keyList.get(position).substring(8)+":00");
                if (position == 0) {
                    holder.aqiItem.setDrawLeftLine(false);
                }  else {
                    holder.aqiItem.setDrawLeftLine(true);
                    holder.aqiItem.setLastTemp(Integer.parseInt(data.get(keyList.get(position - 1))));
                }
                if (position == data.size() - 1) {
                    holder.aqiItem.setDrawRightLine(false);
                } else {
                    holder.aqiItem.setDrawRightLine(true);
                    holder.aqiItem.setNextAQI(Integer.parseInt(data.get(keyList.get(position + 1))));
                }
                holder.aqiItem.setCurrentAQI(Integer.parseInt(data.get(keyList.get(position))));

            }else{


            holder.aqiItem.setTime(dateToZhou(keyList.get(position)));
            if (position == 0) {
                holder.aqiItem.setDrawLeftLine(false);
                holder.aqiItem.setTime("昨天");

            } else if (position == 1) {
                holder.aqiItem.setTime("今天");
                holder.aqiItem.setDrawLeftLine(true);
                holder.aqiItem.setLastTemp(Integer.parseInt(data.get(keyList.get(position - 1))));
            } else {
                holder.aqiItem.setDrawLeftLine(true);
                holder.aqiItem.setLastTemp(Integer.parseInt(data.get(keyList.get(position - 1))));
            }
            if (position == data.size() - 1) {
                holder.aqiItem.setDrawRightLine(false);
            } else {
                holder.aqiItem.setDrawRightLine(true);
                holder.aqiItem.setNextAQI(Integer.parseInt(data.get(keyList.get(position + 1))));
            }
            holder.aqiItem.setCurrentAQI(Integer.parseInt(data.get(keyList.get(position))));

            }
        }

        @Override
        public int getItemCount() {
            return keyList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            AQIItem aqiItem;

            public ViewHolder(View itemView) {
                super(itemView);
                this.aqiItem = (AQIItem) itemView;
            }
        }
    }


    public String dateToZhou(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = format.parse(date);
            SimpleDateFormat format1 = new SimpleDateFormat("E");
            String format2 = format1.format(parse);
            return format2;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
