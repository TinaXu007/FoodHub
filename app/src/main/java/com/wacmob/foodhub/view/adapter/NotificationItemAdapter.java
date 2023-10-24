package com.wacmob.foodhub.view.adapter;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.NotificationItemModel;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class NotificationItemAdapter extends MyBaseAdapter {
    List<NotificationItemModel> notificationItemModelList;
    OnAdapterSelectedListener listener;

    public NotificationItemAdapter(List<NotificationItemModel> notificationItemModelList, OnAdapterSelectedListener listener) {
        this.notificationItemModelList = notificationItemModelList;
        this.listener = listener;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return notificationItemModelList.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.recycler_view_notification_single_item;
    }

    @Override
    public OnAdapterSelectedListener getAdapterClickListener() {
        return listener;
    }

    @Override
    public int getItemCount() {
        return notificationItemModelList.size();
    }
}
