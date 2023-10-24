package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DishItemModel implements Parcelable {
    public static final Creator<DishItemModel> CREATOR = new Creator<DishItemModel>() {
        @Override
        public DishItemModel createFromParcel(Parcel in) {
            return new DishItemModel(in);
        }

        @Override
        public DishItemModel[] newArray(int size) {
            return new DishItemModel[size];
        }
    };
    String dishNme, hubName, rate;
    int image;

    public DishItemModel(String dishNme, String hubName, String rate, int image) {
        this.dishNme = dishNme;
        this.hubName = hubName;
        this.rate = rate;
        this.image = image;
    }

    protected DishItemModel(Parcel in) {
        dishNme = in.readString();
        hubName = in.readString();
        rate = in.readString();
        image = in.readInt();
    }

    public String getDishNme() {
        return dishNme;
    }

    public void setDishNme(String dishNme) {
        this.dishNme = dishNme;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishNme);
        dest.writeString(hubName);
        dest.writeString(rate);
        dest.writeInt(image);
    }
}
