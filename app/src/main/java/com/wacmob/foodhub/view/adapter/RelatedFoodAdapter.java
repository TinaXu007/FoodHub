package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.RelatedFoodModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class RelatedFoodAdapter extends MyBaseAdapter {
    List<RelatedFoodModel> relatedFoodModelList = new ArrayList<>();
    OnAdapterSelectedListener listener;

    public RelatedFoodAdapter(List<RelatedFoodModel> relatedFoodModelList, OnAdapterSelectedListener listener) {
        this.relatedFoodModelList = relatedFoodModelList;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return relatedFoodModelList.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_related_foods_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return relatedFoodModelList.size();
    }
}
