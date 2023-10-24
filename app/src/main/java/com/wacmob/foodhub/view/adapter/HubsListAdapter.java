package com.wacmob.foodhub.view.adapter;

import androidx.annotation.NonNull;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.HubsListModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class HubsListAdapter extends MyBaseAdapter {
    List<HubsListModel> hubsListModels = new ArrayList<>();
    OnAdapterSelectedListener listener;



    public HubsListAdapter(List<HubsListModel> hubsListModels,
                           OnAdapterSelectedListener listener) {
        this.hubsListModels = hubsListModels;
        this.listener = listener;

    }


    @Override
    public Object getDataAtPosition(int position) {
        return hubsListModels.get(position);

    }


    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_hubs_list_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return hubsListModels.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        super.onBindViewHolder(holder, position);
    }
}
