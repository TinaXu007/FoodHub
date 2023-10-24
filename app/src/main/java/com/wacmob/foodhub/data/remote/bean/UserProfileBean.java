package com.wacmob.foodhub.data.remote.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wacmob.foodhub.data.models.ProfileModel;

/**
 * Created by KP on 1/4/2019.
 */

public class UserProfileBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private ProfileModel data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status != null ? status : "";
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public ProfileModel getData() {
        return data != null ? data : new ProfileModel();
    }

    public UserProfileBean() {
    }

    public UserProfileBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static UserProfileBean failureResponse(){
        return new UserProfileBean("failure", "An unexpected error occurred");
    }
}
