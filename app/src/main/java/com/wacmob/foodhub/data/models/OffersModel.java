package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class OffersModel implements Parcelable {
    public static final Creator<OffersModel> CREATOR = new Creator<OffersModel>() {
        @Override
        public OffersModel createFromParcel(Parcel in) {
            return new OffersModel(in);
        }

        @Override
        public OffersModel[] newArray(int size) {
            return new OffersModel[size];
        }
    };
    String offer, validity, offerCode;
    int image;

    public OffersModel(String offer, String validity, int image, String offerCode) {
        this.offer = offer;
        this.validity = validity;
        this.image = image;
        this.offerCode = offerCode;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    protected OffersModel(Parcel in) {
        offer = in.readString();
        validity = in.readString();
        image = in.readInt();
        offerCode = in.readString();
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
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
        dest.writeString(validity);
        dest.writeInt(image);
        dest.writeString(offerCode);
    }
}
