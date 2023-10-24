package com.wacmob.foodhub.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeDeliveryListItemsModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("image")
    @Expose
    private String image;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
