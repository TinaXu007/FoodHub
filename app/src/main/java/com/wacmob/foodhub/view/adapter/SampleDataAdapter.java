package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.SampleModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.ArrayList;

/**
 * Created by KP on 12/27/2018.
 */

public class SampleDataAdapter extends MyBaseAdapter {

    private ArrayList<SampleModel> data;
    private OnAdapterSelectedListener listener;

    public SampleDataAdapter(ArrayList<SampleModel> data, OnAdapterSelectedListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.sample_row_item;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }
}
