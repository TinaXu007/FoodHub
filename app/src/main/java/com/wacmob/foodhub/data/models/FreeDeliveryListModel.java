package com.wacmob.foodhub.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FreeDeliveryListModel {

    @SerializedName("free_delivery_list")
    @Expose
    private List<FreeDeliveryListItemsModel> freeDeliveryList = null;

    public List<FreeDeliveryListItemsModel> getFreeDeliveryList() {
        return freeDeliveryList;
    }

    public void setFreeDeliveryList(List<FreeDeliveryListItemsModel> freeDeliveryList) {
        this.freeDeliveryList = freeDeliveryList;
    }
}
