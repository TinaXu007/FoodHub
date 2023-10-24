package com.wacmob.foodhub.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KP on 1/4/2019.
 */

public class SplashModel {
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
