package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class OfferListModel implements Parcelable {
    public static final Creator<OfferListModel> CREATOR = new Creator<OfferListModel>() {
        @Override
        public OfferListModel createFromParcel(Parcel in) {
            return new OfferListModel(in);
        }

        @Override
        public OfferListModel[] newArray(int size) {
            return new OfferListModel[size];
        }
    };
    String offer, name;
    int image;

    public OfferListModel(String offer, String name, int image) {
        this.offer = offer;
        this.name = name;
        this.image = image;
    }

    protected OfferListModel(Parcel in) {
        offer = in.readString();
        name = in.readString();
        image = in.readInt();
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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
        dest.writeString(offer);
        dest.writeString(name);
        dest.writeInt(image);
    }
}
