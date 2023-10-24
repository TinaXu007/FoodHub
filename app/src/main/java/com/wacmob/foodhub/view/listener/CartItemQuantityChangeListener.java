package com.wacmob.foodhub.view.listener;

import com.wacmob.foodhub.data.models.CartItemModel;

public interface CartItemQuantityChangeListener {

    void increaseQuantity(CartItemModel cartItemModel);

    void decreaseQuantity(CartItemModel cartItemModel);

}
