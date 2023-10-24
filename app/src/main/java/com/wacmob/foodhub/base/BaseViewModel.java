package com.wacmob.foodhub.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Serin on 1/4/2019.
 */

public class BaseViewModel extends ViewModel {

    private final MutableLiveData<ProgressModel> mIsLoading = new MutableLiveData<ProgressModel>();

    public BaseViewModel() {
    }

    public MutableLiveData<ProgressModel> getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.setValue(new ProgressModel(isLoading));
    }

    public void setNoInternetError(){
        ProgressModel progressModel = new ProgressModel();
        progressModel.setHasInternet(false);
        mIsLoading.setValue(progressModel);
    }

    public void setErrorMessage(String message){
        ProgressModel progressModel = new ProgressModel();
        progressModel.setHasInternet(true);
        progressModel.setErrorMessage(message);
        mIsLoading.setValue(progressModel);
    }
}
