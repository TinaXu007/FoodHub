package com.wacmob.foodhub.data.remote.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wacmob.foodhub.data.models.NotificationModel;

import java.util.ArrayList;

/**
 * Created by KP on 1/4/2019.
 */

public class NotificationBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ArrayList<NotificationModel> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status != null ? status : "";
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public ArrayList<NotificationModel> getData() {
        return data != null ? data : new ArrayList<NotificationModel>();
    }

    public void setData(ArrayList<NotificationModel> data) {
        this.data = data;
    }

    public NotificationBean() {
    }

    public NotificationBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static NotificationBean failureResponse(){
        return new NotificationBean("failure", "An unexpected error occurred");
    }

    public static NotificationBean successResponse(){
        return new NotificationBean("success", "Retrieved successfully");
    }
}
