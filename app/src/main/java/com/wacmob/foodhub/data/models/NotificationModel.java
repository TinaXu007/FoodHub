package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KP on 1/4/2019.
 */

public class NotificationModel extends BaseObservable{
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    private String imageUrl = "https://github.com/googlesamples/android-gcmnetworkmanager/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true";

    @Bindable
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
