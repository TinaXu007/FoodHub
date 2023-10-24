package com.wacmob.foodhub.data.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "SearchItemListModel")
public class SearchItemListModel implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("product_rate")
    @Expose
    private String productRate;
    @SerializedName("hotel_name")
    @Expose
    private String hotelName;

    private int itemType;

    protected SearchItemListModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        productTitle = in.readString();
        productRate = in.readString();
        hotelName = in.readString();
        itemType = in.readInt();
    }

    public static final Creator<SearchItemListModel> CREATOR = new Creator<SearchItemListModel>() {
        @Override
        public SearchItemListModel createFromParcel(Parcel in) {
            return new SearchItemListModel(in);
        }

        @Override
        public SearchItemListModel[] newArray(int size) {
            return new SearchItemListModel[size];
        }
    };

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public SearchItemListModel() {

    }

    @Ignore
    public SearchItemListModel(Integer id, String productTitle, String productRate, String hotelName) {
        this.id = id;
        this.productTitle = productTitle;
        this.productRate = productRate;
        this.hotelName = hotelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        dest.writeString(productTitle);
        dest.writeString(productRate);
        dest.writeString(hotelName);
        dest.writeInt(itemType);
    }

}

/*
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("product_rate")
    @Expose
    private String productRate;
    @SerializedName("hotel_name")
    @Expose
    private String hotelName;

    private int itemType;

    protected SearchItemListModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        productTitle = in.readString();
        productRate = in.readString();
        hotelName = in.readString();
        itemType = in.readInt();
    }

    public static final Creator<SearchItemListModel> CREATOR = new Creator<SearchItemListModel>() {
        @Override
        public SearchItemListModel createFromParcel(Parcel in) {
            return new SearchItemListModel(in);
        }

        @Override
        public SearchItemListModel[] newArray(int size) {
            return new SearchItemListModel[size];
        }
    };

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public SearchItemListModel() {

    }

    @Ignore
    public SearchItemListModel(Integer id, String productTitle, String productRate, String hotelName) {
        this.id = id;
        this.productTitle = productTitle;
        this.productRate = productRate;
        this.hotelName = hotelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        dest.writeString(productTitle);
        dest.writeString(productRate);
        dest.writeString(hotelName);
        dest.writeInt(itemType);
    }*/
