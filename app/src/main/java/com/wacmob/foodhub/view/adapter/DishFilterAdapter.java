package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.DishesFilterModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class DishFilterAdapter extends MyBaseAdapter {
    List<DishesFilterModel> dishesFilterModels;
    OnAdapterSelectedListener listener;

    public DishFilterAdapter(List<DishesFilterModel> dishesFilterModels, OnAdapterSelectedListener listener) {
        this.dishesFilterModels = dishesFilterModels;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return dishesFilterModels.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_dish_filter_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return dishesFilterModels.size();
    }
}
