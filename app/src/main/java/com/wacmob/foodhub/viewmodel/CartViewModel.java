package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.remote.bean.ForgotPasswordBean;
import com.wacmob.foodhub.data.remote.bean.ForgotPasswordRequest;
import com.wacmob.foodhub.data.repository.FoodRepository;
import com.wacmob.foodhub.utils.AppLogger;
import com.wacmob.foodhub.utils.InternetUtils;
import com.wacmob.foodhub.utils.ValidateUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KP on 1/4/2019.
 */

@Singleton
public class CartViewModel extends BaseViewModel {

    private MutableLiveData<ForgotPasswordBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository homeRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public CartViewModel(FoodRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public void performValidation(String email) {
        if (internetUtils.isNetworkAvailable()) {
            if (!ValidateUtils.isEmailValid(email)){
                setErrorMessage("Email not valid");
            }else {
                setIsLoading(true);
                disposable.add(homeRepository.performForgotPassword(new ForgotPasswordRequest(email))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onForgotPasswordSucccess, this::onForgotPasswordFail));
            }
        }else{
            setNoInternetError();
        }
    }

    private void onForgotPasswordSucccess(ForgotPasswordBean forgotPasswordBean) {
        responseLiveData.setValue(forgotPasswordBean);
        AppLogger.d("Complete API call");
        setIsLoading(false);
    }

    private void onForgotPasswordFail(Throwable e) {
        responseLiveData.setValue(ForgotPasswordBean.failureResponse());
        setIsLoading(false);
    }

    public LiveData<ForgotPasswordBean> subscribeForgotPassword() {
        return this.responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
