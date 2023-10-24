package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularListModelNew implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("hotel_name")
    @Expose
    private String hotelName;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("item_rate")
    @Expose
    private String itemRate;
    @SerializedName("image")
    @Expose
    private String image;

    protected PopularListModelNew(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        productTitle = in.readString();
        hotelName = in.readString();
        rating = in.readString();
        itemRate = in.readString();
        image = in.readString();
    }

    public static final Creator<PopularListModelNew> CREATOR = new Creator<PopularListModelNew>() {
        @Override
        public PopularListModelNew createFromParcel(Parcel in) {
            return new PopularListModelNew(in);
        }

        @Override
        public PopularListModelNew[] newArray(int size) {
            return new PopularListModelNew[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(productTitle);
        dest.writeString(hotelName);
        dest.writeString(rating);
        dest.writeString(itemRate);
        dest.writeString(image);
    }
}
