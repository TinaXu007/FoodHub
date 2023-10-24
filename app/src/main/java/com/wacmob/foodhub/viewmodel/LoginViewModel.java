package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.remote.bean.LoginBean;
import com.wacmob.foodhub.data.remote.bean.LoginRequest;
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
public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<LoginBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository homeRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public LoginViewModel(FoodRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public void performValidation(String email, String password) {
        if (internetUtils.isNetworkAvailable()) {
            if (!ValidateUtils.isEmailValid(email)){
                setErrorMessage("Email not valid");
            }else if (password.isEmpty()) {
                setErrorMessage("Password cannot be empty");
            }else {
                setIsLoading(true);
                disposable.add(homeRepository.performLogin(new LoginRequest(email, password))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onLoginSuccess, this::onLoginFail));
            }
        }else{
            setNoInternetError();
        }
    }

    private void onLoginSuccess(LoginBean homePageBean) {
        responseLiveData.setValue(homePageBean);
        AppLogger.d("Complete API call");
        setIsLoading(false);
    }

    private void onLoginFail(Throwable e) {
        responseLiveData.setValue(LoginBean.failureResponse());
        setIsLoading(false);
    }

    public LiveData<LoginBean> subscribeLogin() {
        return this.responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
