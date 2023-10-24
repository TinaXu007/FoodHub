package com.wacmob.foodhub.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferListModelNew implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("discount_percent")
    @Expose
    private String discountPercent;
    @SerializedName("image")
    @Expose
    private String image;

    protected OfferListModelNew(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        productTitle = in.readString();
        discountPercent = in.readString();
        image = in.readString();
    }

    public static final Creator<OfferListModelNew> CREATOR = new Creator<OfferListModelNew>() {
        @Override
        public OfferListModelNew createFromParcel(Parcel in) {
            return new OfferListModelNew(in);
        }

        @Override
        public OfferListModelNew[] newArray(int size) {
            return new OfferListModelNew[size];
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

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
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
        dest.writeString(discountPercent);
        dest.writeString(image);
    }
}
