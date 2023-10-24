package com.wacmob.foodhub.data.remote.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wacmob.foodhub.data.models.HomepageModel;

import java.util.ArrayList;

/**
 * Created by KP on 1/4/2019.
 */

public class HomePageBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ArrayList<HomepageModel> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status != null ? status : "";
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public ArrayList<HomepageModel> getData() {
        return data != null ? data : new ArrayList<HomepageModel>();
    }

    public HomePageBean() {
    }

    public HomePageBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static HomePageBean failureResponse(){
        return new HomePageBean("failure", "An unexpected error occurred");
    }
}
