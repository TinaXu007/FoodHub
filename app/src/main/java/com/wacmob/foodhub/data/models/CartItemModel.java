package com.wacmob.foodhub.data.models;

public class CartItemModel {
    int id, no;
    String itenName, restName, rate;

    public CartItemModel(String itenName, String restName, String rate, int id,int no) {
        this.itenName = itenName;
        this.restName = restName;
        this.rate = rate;
        this.id = id;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItenName() {
        return itenName;
    }

    public void setItenName(String itenName) {
        this.itenName = itenName;
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
