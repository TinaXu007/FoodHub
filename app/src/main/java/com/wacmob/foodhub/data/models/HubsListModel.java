package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wacmob.foodhub.BR;

public class HubsListModel extends BaseObservable implements Parcelable {
    public static final Creator<HubsListModel> CREATOR = new Creator<HubsListModel>() {
        @Override
        public HubsListModel createFromParcel(Parcel in) {
            return new HubsListModel(in);
        }

        @Override
        public HubsListModel[] newArray(int size) {
            return new HubsListModel[size];
        }
    };
    String restaurentName, rateAndDistance;
    int image;
    double lat, lon;
    private boolean isSelected = false;
    private boolean isHide = false;

    public HubsListModel(String restaurentName, String rateAndDistance, int image, double lat, double lon) {

        this.restaurentName = restaurentName;
        this.rateAndDistance = rateAndDistance;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
        notifyPropertyChanged(BR.hide);
    }

    protected HubsListModel(Parcel in) {
        restaurentName = in.readString();
        rateAndDistance = in.readString();
        image = in.readInt();
        lat = in.readDouble();
        lon = in.readDouble();
        isSelected = in.readByte() != 0;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public String getRestaurentName() {
        return restaurentName;
    }

    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
    }

    public String getRateAndDistance() {
        return rateAndDistance;
    }

    public void setRateAndDistance(String rateAndDistance) {
        this.rateAndDistance = rateAndDistance;
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
        dest.writeString(restaurentName);
        dest.writeString(rateAndDistance);
        dest.writeInt(image);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
