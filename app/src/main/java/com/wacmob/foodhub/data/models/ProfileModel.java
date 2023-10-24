package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

//import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by KP on 12/14/2018.
 */

public class ProfileModel extends BaseObservable{
    private String name;
    private String usertype;
    private String imageUrl;

    public ProfileModel(String name, String usertype) {
        this.name = name;
        this.usertype = usertype;
    }

    public ProfileModel(String name, String usertype, String imageUrl) {
        this.name = name;
        this.usertype = usertype;
        this.imageUrl = imageUrl;
    }

    public ProfileModel(String name) {
        this.name = name;
    }

    public ProfileModel() {
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getUserType() {
        return usertype;
    }

    public void setUserType(String usertype) {
        this.usertype = usertype;
        notifyPropertyChanged(BR.userType);
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
