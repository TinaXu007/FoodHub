package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.data.models.SearchResponseModel;
import com.wacmob.foodhub.data.repository.FoodRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends BaseViewModel {

    FoodRepository searchRepository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<SearchItemListModel>> searchItemsLiveData = new MutableLiveData<>();

    @Inject
    public SearchViewModel(FoodRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    MutableLiveData<SearchResponseModel> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<SearchResponseModel> getMutableLiveData() {
        return mutableLiveData;
    }

    public void apiCall(String s) {

        compositeDisposable.clear();
        compositeDisposable.add(searchRepository.searchItemCall(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchResponseSuccess, this::onSearchResponseFail));
    }

    private void onSearchResponseFail(Throwable throwable) {
    }

    private void onSearchResponseSuccess(SearchResponseModel searchResponseModel) {

        mutableLiveData.setValue(searchResponseModel);
    }

    public LiveData<List<SearchItemListModel>> observeSearchItemLiveData() {
        return searchItemsLiveData;
    }

    public void fetchSearchItemList() {
        compositeDisposable.add(searchRepository.fetchSearchItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchItemsSuccess, this::onSearchItemsFail));
    }

    private void onSearchItemsSuccess(List<SearchItemListModel> searchItemListModels) {
        searchItemsLiveData.setValue(searchItemListModels);
    }

    private void onSearchItemsFail(Throwable throwable) {

    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public void insertProductLocal(SearchItemListModel item) {
        searchRepository.insertSearchItems(item);
    }
}
