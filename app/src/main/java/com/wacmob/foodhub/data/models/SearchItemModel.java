package com.wacmob.foodhub.data.models;

public class SearchItemModel {
    int id;
    String itemName, hotelName, rate;

    public SearchItemModel(int id, String itemName, String hotelName, String rate) {
        this.id = id;
        this.itemName = itemName;
        this.hotelName = hotelName;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
