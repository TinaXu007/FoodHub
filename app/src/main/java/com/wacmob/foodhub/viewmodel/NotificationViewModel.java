package com.wacmob.foodhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wacmob.foodhub.base.BaseViewModel;
import com.wacmob.foodhub.data.local.room.notification.NotificationEntry;
import com.wacmob.foodhub.data.models.NotificationModel;
import com.wacmob.foodhub.data.remote.bean.NotificationBean;
import com.wacmob.foodhub.data.repository.FoodRepository;
import com.wacmob.foodhub.utils.AppLogger;
import com.wacmob.foodhub.utils.InternetUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KP on 1/14/2019.
 */

@Singleton
public class NotificationViewModel extends BaseViewModel {

    private MutableLiveData<NotificationBean> responseLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private FoodRepository notificationRepository;
    @Inject
    InternetUtils internetUtils;

    @Inject
    public NotificationViewModel(FoodRepository homeRepository) {
        this.notificationRepository = homeRepository;
    }

    private void fetchNotifications() {
        setIsLoading(true);
        disposable.add(notificationRepository.getNotifications(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNotificationSuccess, this::onNotificationFail));
    }

    private void onNotificationSuccess(NotificationBean homePageBean) {
        responseLiveData.setValue(homePageBean);
        if (homePageBean.getData() != null && homePageBean.getData().size() > 0) {
            saveNotifications(homePageBean);
            AppLogger.d("Complete API call");
            setIsLoading(false);
        }
    }

    private void onNotificationFail(Throwable t){
        responseLiveData.setValue(NotificationBean.failureResponse());
        setIsLoading(false);
    }

    public void readNotifications() {
        setIsLoading(true);
        disposable.add(notificationRepository.readNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onReadNotificationSuccess, this::onReadNotificationFail));
    }

    private void onReadNotificationSuccess(List<NotificationEntry> notificationEntries) {
        AppLogger.d("Complete DB read success");
        if (notificationEntries != null && notificationEntries.size() > 0) {
            NotificationBean notificationBean = NotificationBean.successResponse();
            ArrayList<NotificationModel> notificationModels = new ArrayList<>();
            for (NotificationEntry model :
                    notificationEntries) {
                NotificationModel notification = new NotificationModel();
                notification.setCategoryId(model.getId());
                notification.setCategoryName(model.getTitle());
                notification.setImageUrl(model.getImage());
                notificationModels.add(notification);
            }
            notificationBean.setData(notificationModels);
            responseLiveData.setValue(notificationBean);
        }
        setIsLoading(false);
    }

    private void onReadNotificationFail(Throwable e) {
        AppLogger.d("Complete DB read with error");
        setIsLoading(false);
    }


    public void saveNotifications(final NotificationBean notificationBean) {
        final List<NotificationEntry> entryList = new ArrayList<>();
        for (NotificationModel model :
                notificationBean.getData()) {
            NotificationEntry entry = new NotificationEntry();
            entry.setTitle(model.getCategoryName());
            entry.setId(model.getCategoryId());
            entry.setDesc(model.getCategoryName());
            entry.setImage(model.getImageUrl());
            entry.setDate(model.getCategoryName());
            entry.setTime(model.getCategoryName());
            entryList.add(entry);
        }
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                notificationRepository.performInsertNotification(entryList);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        AppLogger.d("Subscribe");
                    }

                    @Override
                    public void onComplete() {
                        AppLogger.d("Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogger.d("Error");
                    }
                });
    }

    public LiveData<NotificationBean> getNotifications() {
        if (internetUtils.isNetworkAvailable())
            fetchNotifications();
        else
            setNoInternetError();
        //readNotifications();
        return this.responseLiveData;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
