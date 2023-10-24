package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.remote.bean.UserProfileBean;
import com.wacmob.foodhub.data.repository.FoodRepository;
import com.wacmob.foodhub.utils.AppLogger;
import com.wacmob.foodhub.utils.InternetUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KP on 1/14/2019.
 */

@Singleton
public class ProfileViewModel extends BaseViewModel {

    private MutableLiveData<UserProfileBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository homeRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public ProfileViewModel(FoodRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    private void fetchUserProfile() {
        setIsLoading(true);
        disposable.add(homeRepository.getUserProfile(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onProfileSuccess, this::onProfileFail));
    }

    public LiveData<UserProfileBean> getUserProfile() {
        if (internetUtils.isNetworkAvailable())
            fetchUserProfile();
        else
            setNoInternetError();
        return this.responseLiveData;
    }

    private void onProfileSuccess(UserProfileBean homePageBean) {
        responseLiveData.setValue(homePageBean);
        AppLogger.d("Complete API call");
        setIsLoading(false);
    }

    private void onProfileFail(Throwable e) {
        responseLiveData.setValue(UserProfileBean.failureResponse());
        setIsLoading(false);
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
