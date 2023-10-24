package com.wacmob.foodhub.data.models;

public class FilterResultModel {

    int id;
    String itemName, restName, rate;

    public FilterResultModel(int id, String itemName, String restName, String rate) {
        this.id = id;
        this.itemName = itemName;
        this.restName = restName;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
