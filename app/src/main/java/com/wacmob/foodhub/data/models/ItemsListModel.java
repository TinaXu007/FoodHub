package com.wacmob.foodhub.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsListModel {

    @SerializedName("offer_list")
    @Expose
    private List<OfferListModelNew> offerList = null;
    @SerializedName("popular_list")
    @Expose
    private List<PopularListModelNew> popularList = null;

    public List<OfferListModelNew> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferListModelNew> offerList) {
        this.offerList = offerList;
    }

    public List<PopularListModelNew> getPopularList() {
        return popularList;
    }

    public void setPopularList(List<PopularListModelNew> popularList) {
        this.popularList = popularList;
    }
}
