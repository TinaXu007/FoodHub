package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.OrderdItemsModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class OrderedItemsAdapter extends MyBaseAdapter {
    List<OrderdItemsModel> orderdItemsModels;
    OnAdapterSelectedListener listener;

    public OrderedItemsAdapter(List<OrderdItemsModel> orderdItemsModels, OnAdapterSelectedListener listener) {
        this.orderdItemsModels = orderdItemsModels;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {

        return orderdItemsModels.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_ordered__single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {

        return orderdItemsModels.size();
    }
}
