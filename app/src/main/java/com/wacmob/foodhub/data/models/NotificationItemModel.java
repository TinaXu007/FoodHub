package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wacmob.foodhub.BR;

public class NotificationItemModel extends BaseObservable {
    int id;
    String notification;

    boolean isSelected = false;

    public NotificationItemModel(int id, String notification) {
        this.id = id;
        this.notification = notification;
    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
