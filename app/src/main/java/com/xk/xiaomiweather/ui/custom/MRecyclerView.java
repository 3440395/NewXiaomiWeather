package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xk.xiaomiweather.model.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xk on 2016/11/9 22:14.
 */

public class MRecyclerView extends RecyclerView {
    private List<City> data=new ArrayList<>();
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

public void setData(List<City> data){
    if (this.data!=null) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }
}

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CityItem cityItem = new CityItem(getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 135);
            cityItem.setLayoutParams(layoutParams);
            return new ViewHolder(cityItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.cityItem.setDeletable(isEdit);
            holder.cityItem.setText(data.get(position).getDistrict());
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
