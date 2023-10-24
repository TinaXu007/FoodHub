package com.wacmob.foodhub.view.listener;

import com.wacmob.foodhub.data.models.AddressListModel;

public interface AddressSelectListener {

    void AddressSelect(AddressListModel model, String text);

    void AddressEdit(AddressListModel model, String text);

    void AddressSelectModel(AddressListModel model);
}
