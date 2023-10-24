package com.wacmob.foodhub.data.remote.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wacmob.foodhub.data.models.ConfigModel;



public class SignupBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ConfigModel data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status != null ? status : "";
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public ConfigModel getData() {
        return data != null ? data : new ConfigModel();
    }

    public SignupBean() {
    }

    public SignupBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static SignupBean failureResponse(){
        return new SignupBean("failure", "An unexpected error occurred");
    }
}
