package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.remote.bean.SignupBean;
import com.wacmob.foodhub.data.remote.bean.SignupRequest;
import com.wacmob.foodhub.data.repository.FoodRepository;
import com.wacmob.foodhub.utils.AppLogger;
import com.wacmob.foodhub.utils.InternetUtils;
import com.wacmob.foodhub.utils.ValidateUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class SignupViewModel extends BaseViewModel {

    private MutableLiveData<SignupBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository homeRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public SignupViewModel(FoodRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public void performValidation(String fullName, String email, String password, String phoneNumber) {
        if (internetUtils.isNetworkAvailable()) {
            if (fullName.isEmpty()) {
                setErrorMessage("Name cannot be empty");
            }else if (!ValidateUtils.isEmailValid(email)){
                setErrorMessage("Email not valid");
            }else if (password.isEmpty()) {
                setErrorMessage("Password cannot be empty");
            }else if (!ValidateUtils.isPhoneNumberValid(phoneNumber)) {
                setErrorMessage("Phone number not valid");
            }else {
                setIsLoading(true);
                disposable.add(homeRepository.performSignup(new SignupRequest(email, password))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSignupSuccess, this::onSignupFail));
            }
        }else{
            setNoInternetError();
        }
    }

    private void onSignupSuccess(SignupBean homePageBean) {
        responseLiveData.setValue(homePageBean);
        AppLogger.d("Complete API call");
        setIsLoading(false);
    }

    private void onSignupFail(Throwable e) {
        responseLiveData.setValue(SignupBean.failureResponse());
        setIsLoading(false);
    }

    public LiveData<SignupBean> subscribeSignup() {
        return this.responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
