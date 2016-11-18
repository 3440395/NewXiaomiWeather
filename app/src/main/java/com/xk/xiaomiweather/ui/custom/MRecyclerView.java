package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xk.xiaomiweather.model.bean.City;
import com.xk.xiaomiweather.ui.CityManageActivity;
import com.xk.xiaomiweather.ui.util.ScreenManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xk on 2016/11/9 22:14.
 */

public class MRecyclerView extends RecyclerView {
    private List<City> data = new ArrayList<>();
    private boolean isEdit = false;
    private Adapter adapter;

    public MRecyclerView(Context context, LayoutManager layoutManager) {
        super(context);
        init(layoutManager);
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        adapter.notifyDataSetChanged();
    }

    private void init(LayoutManager layoutManager) {
        setLayoutManager(layoutManager);
        adapter = new Adapter();
        setAdapter(adapter);
    }

    public void setData(List<City> data) {
        if (this.data != null) {
            this.data.clear();
            this.data.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    public void deleteItem(City city){
        int i = data.indexOf(city);
        data.remove(city);
        adapter.notifyDataSetChanged();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CityItem cityItem = new CityItem(getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenManager.getInstance().adpH(135));
            cityItem.setLayoutParams(layoutParams);
            return new ViewHolder(cityItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.cityItem.setDeletable(isEdit);
            holder.cityItem.setOnDeleteClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CityManageActivity)getContext()).prepareDeleteCity(data.get(position));
                }
            });
            holder.cityItem.setText(data.get(position).getDistrict());
            if (!isEdit) {
                holder.cityItem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((CityManageActivity)getContext()).addCity(data.get(position));
                    }
                });
            }else{
                holder.cityItem.setOnClickListener(null);
            }


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CityItem cityItem;

            public ViewHolder(View itemView) {
                super(itemView);
                this.cityItem = (CityItem) itemView;
            }
        }
    }

}
