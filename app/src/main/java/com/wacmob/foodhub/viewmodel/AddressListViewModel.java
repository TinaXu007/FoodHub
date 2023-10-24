package com.wacmob.foodhub.viewmodel;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.repository.FoodRepository;

import javax.inject.Inject;

public class AddressListViewModel extends BaseViewModel {

    private FoodRepository addressListRepository;

    @Inject
    public AddressListViewModel(FoodRepository addressListRepository) {
        this.addressListRepository = addressListRepository;
    }
}
