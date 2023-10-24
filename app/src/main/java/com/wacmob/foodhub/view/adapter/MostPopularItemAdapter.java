package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.PopularListModelNew;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class MostPopularItemAdapter extends MyBaseAdapter {
    List<PopularListModelNew> mostPopularItemList;
    OnAdapterSelectedListener listener;

    public MostPopularItemAdapter(List<PopularListModelNew> mostPopularItemList, OnAdapterSelectedListener listener) {
        this.mostPopularItemList = mostPopularItemList;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return mostPopularItemList.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_most_popular_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return mostPopularItemList.size();
    }
}
