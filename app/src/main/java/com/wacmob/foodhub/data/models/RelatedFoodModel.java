package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RelatedFoodModel implements Parcelable {
    String rate, name;
    int image;

    public RelatedFoodModel(String rate, String name, int image) {
        this.rate = rate;
        this.name = name;
        this.image = image;
    }

    protected RelatedFoodModel(Parcel in) {
        rate = in.readString();
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<RelatedFoodModel> CREATOR = new Creator<RelatedFoodModel>() {
        @Override
        public RelatedFoodModel createFromParcel(Parcel in) {
            return new RelatedFoodModel(in);
        }

        @Override
        public RelatedFoodModel[] newArray(int size) {
            return new RelatedFoodModel[size];
        }
    };

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(rate);
        dest.writeString(name);
        dest.writeInt(image);
    }
}
