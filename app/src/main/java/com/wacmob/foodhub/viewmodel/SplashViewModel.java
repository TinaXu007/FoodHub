package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.remote.bean.SplashBean;
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
public class SplashViewModel extends BaseViewModel {

    private MutableLiveData<SplashBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository homeRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public SplashViewModel(FoodRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    private void fetchConfig() {
       setIsLoading(true);
        disposable.add(homeRepository.getConfig(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSplashSuccess, this::onSplashFail));
    }

    private void onSplashSuccess(SplashBean homePageBean){
        responseLiveData.setValue(homePageBean);
        AppLogger.d("Complete API call");
        setIsLoading(false);
    }

    private void onSplashFail(Throwable t){
        responseLiveData.setValue(SplashBean.failureResponse());
        setIsLoading(false);
    }

    public LiveData<SplashBean> getConfig() {
        responseLiveData = new MutableLiveData<>();
        fetchConfig();
        return this.responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
