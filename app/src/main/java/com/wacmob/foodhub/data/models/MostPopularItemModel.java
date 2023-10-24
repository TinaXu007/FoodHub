package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MostPopularItemModel implements Parcelable {
    public static final Creator<MostPopularItemModel> CREATOR = new Creator<MostPopularItemModel>() {
        @Override
        public MostPopularItemModel createFromParcel(Parcel in) {
            return new MostPopularItemModel(in);
        }

        @Override
        public MostPopularItemModel[] newArray(int size) {
            return new MostPopularItemModel[size];
        }
    };
    String name, restaurentName, rate;
    int image;

    public MostPopularItemModel(String name, String restaurentName, String rate, int image) {
        this.name = name;
        this.restaurentName = restaurentName;
        this.rate = rate;
        this.image = image;
    }

    protected MostPopularItemModel(Parcel in) {
        name = in.readString();
        restaurentName = in.readString();
        rate = in.readString();
        image = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurentName() {
        return restaurentName;
    }

    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
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
        dest.writeString(name);
        dest.writeString(restaurentName);
        dest.writeString(rate);
        dest.writeInt(image);
    }
}
