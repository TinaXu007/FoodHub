package com.wacmob.foodhub.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeDeliveryResponseModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private FreeDeliveryListModel data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FreeDeliveryListModel getData() {
        return data;
    }

    public void setData(FreeDeliveryListModel data) {
        this.data = data;
    }
}
