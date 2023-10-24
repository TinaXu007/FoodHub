package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.MyordersItemModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class MyordersAdapter extends MyBaseAdapter {
    List<MyordersItemModel> myordersItemModelList;
    OnAdapterSelectedListener listener;

    public MyordersAdapter(List<MyordersItemModel> myordersItemModelList, OnAdapterSelectedListener listener) {
        this.myordersItemModelList = myordersItemModelList;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return myordersItemModelList.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_my_orders_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return myordersItemModelList.size();
    }
}
