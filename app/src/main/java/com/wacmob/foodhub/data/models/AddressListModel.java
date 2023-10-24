package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressListModel implements Parcelable {
    private String name;
    private String houseName;
    private String street;
    private String city;
    private String landmark;
    private String pincode;
    private String phone;

    public AddressListModel(String name, String houseName, String street, String city, String landmark, String pincode, String phone) {

        this.name = name;
        this.houseName = houseName;
        this.street = street;
        this.city = city;
        this.landmark = landmark;
        this.pincode = pincode;
        this.phone = phone;
    }

    protected AddressListModel(Parcel in) {
        name = in.readString();
        houseName = in.readString();
        street = in.readString();
        city = in.readString();
        landmark = in.readString();
        pincode = in.readString();
        phone = in.readString();
    }

    public static final Creator<AddressListModel> CREATOR = new Creator<AddressListModel>() {
        @Override
        public AddressListModel createFromParcel(Parcel in) {
            return new AddressListModel(in);
        }

        @Override
        public AddressListModel[] newArray(int size) {
            return new AddressListModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(houseName);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(landmark);
        dest.writeString(pincode);
        dest.writeString(phone);
    }
}
