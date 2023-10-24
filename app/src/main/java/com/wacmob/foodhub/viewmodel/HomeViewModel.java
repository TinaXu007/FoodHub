package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.models.FreeDeliveryResponseModel;
import com.wacmob.foodhub.data.models.ItemResponseModel;
import com.wacmob.foodhub.data.repository.FoodRepository;
import com.wacmob.foodhub.utils.InternetUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class HomeViewModel extends BaseViewModel {

    private final InternetUtils internetUtils;
    FoodRepository itemListRepository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomeViewModel(FoodRepository itemListRepository, InternetUtils internetUtils) {
        this.internetUtils = internetUtils;
        this.itemListRepository = itemListRepository;
    }

    MutableLiveData<ItemResponseModel> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ItemResponseModel> getMutableLiveData() {

        if (internetUtils.isNetworkAvailable()) {
            apiCall();
        } else {
            setNoInternetError();
        }
        return mutableLiveData;
    }

    private void apiCall() {

        setIsLoading(true);
        compositeDisposable.add(itemListRepository.foodItemsListCall()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchSuccess, this::onFetchFail));

    }

    private void onFetchFail(Throwable throwable) {
        setIsLoading(true);
    }


    private void onFetchSuccess(ItemResponseModel itemResponseModel) {

        setIsLoading(false);
        mutableLiveData.setValue(itemResponseModel);
    }


    MutableLiveData<FreeDeliveryResponseModel> freeDeliveryMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<FreeDeliveryResponseModel> getFreeDeliveryMutableLiveData() {

        if (internetUtils.isNetworkAvailable()) {
            freeDeliveryItemsCall();
        } else {
            setNoInternetError();
        }
        return freeDeliveryMutableLiveData;
    }

    private void freeDeliveryItemsCall() {

        setIsLoading(true);
        compositeDisposable.add(itemListRepository.freeDeliveryCall()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFreeDeliveryFetchSuccess, this::onFreeDeliveryFetchFail));
    }

    private void onFreeDeliveryFetchSuccess(FreeDeliveryResponseModel freeDeliveryResponseModel) {
        setIsLoading(false);
        freeDeliveryMutableLiveData.setValue(freeDeliveryResponseModel);
    }

    private void onFreeDeliveryFetchFail(Throwable throwable) {
        setIsLoading(true);
    }

    @Override
    protected void onCleared() {

        compositeDisposable.clear();
        super.onCleared();
    }
}
