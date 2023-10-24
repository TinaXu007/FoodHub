package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wacmob.foodhub.BR;


public class MyordersItemModel extends BaseObservable {
    String itemname, restName, rate, status;
    int id;

    boolean selected = false;

    public MyordersItemModel(String itemname, String restName, String rate, String status, int id) {
        this.itemname = itemname;
        this.restName = restName;
        this.rate = rate;
        this.status = status;
        this.id = id;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
