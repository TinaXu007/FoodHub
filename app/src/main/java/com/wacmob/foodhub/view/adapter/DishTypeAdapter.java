package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.DishTypeModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class DishTypeAdapter extends MyBaseAdapter {
    List<DishTypeModel> dishTypeModels;
    OnAdapterSelectedListener listener;

    public DishTypeAdapter(List<DishTypeModel> dishTypeModels, OnAdapterSelectedListener listener) {
        this.dishTypeModels = dishTypeModels;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return dishTypeModels.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_dish_type_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return dishTypeModels.size();
    }
}
