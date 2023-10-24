package com.wacmob.foodhub.data.repository;

import com.wacmob.foodhub.data.local.preference.PreferencesHandler;
import com.wacmob.foodhub.data.local.room.FoodDao;
import com.wacmob.foodhub.data.local.room.MusicDbHelper;
import com.wacmob.foodhub.data.local.room.notification.NotificationEntry;
import com.wacmob.foodhub.data.models.FreeDeliveryResponseModel;
import com.wacmob.foodhub.data.models.ItemResponseModel;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.data.models.SearchResponseModel;
import com.wacmob.foodhub.data.remote.WebService;
import com.wacmob.foodhub.data.remote.bean.ForgotPasswordBean;
import com.wacmob.foodhub.data.remote.bean.ForgotPasswordRequest;
import com.wacmob.foodhub.data.remote.bean.HomePageBean;
import com.wacmob.foodhub.data.remote.bean.LoginBean;
import com.wacmob.foodhub.data.remote.bean.LoginRequest;
import com.wacmob.foodhub.data.remote.bean.NotificationBean;
import com.wacmob.foodhub.data.remote.bean.SignupBean;
import com.wacmob.foodhub.data.remote.bean.SignupRequest;
import com.wacmob.foodhub.data.remote.bean.SplashBean;
import com.wacmob.foodhub.data.remote.bean.UserProfileBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by KP on 1/4/2019.
 */

@Singleton
public class FoodRepository {

    private PreferencesHandler preferencesHandler;
    private WebService webService;
    private MusicDbHelper musicDbHelper;
    private FoodDao musicDao;

    @Inject
    public FoodRepository(PreferencesHandler preferencesHandler, WebService webService, MusicDbHelper musicDbHelper) {
        this.preferencesHandler = preferencesHandler;
        this.webService = webService;
        this.musicDbHelper = musicDbHelper;
        this.musicDao = musicDbHelper.provideMusicDao(musicDbHelper.provideMusicDatabase());
    }

    public Observable<HomePageBean> getHomePage(int param) {
        return webService.getSearchAPI().loadHomePage("english");
    }

    public Observable<SplashBean> getConfig(int param) {
        return webService.getSearchAPI().loadConfig("english");
    }

    public Observable<UserProfileBean> getUserProfile(int param) {
        return webService.getSearchAPI().loadUserProfile("english");
    }

    public Observable<NotificationBean> getNotifications(int param) {
        return webService.getSearchAPI().loadNotifications("english");
    }

    public Observable<LoginBean> performLogin(LoginRequest param) {
        return webService.getSearchAPI().login(param);
    }

    public Observable<SignupBean> performSignup(SignupRequest param) {
        return webService.getSearchAPI().signup(param);
    }

    public Observable<ForgotPasswordBean> performForgotPassword(ForgotPasswordRequest param) {
        return webService.getSearchAPI().forgotPassword(param);
    }

    public void performInsertNotification(List<NotificationEntry> param) {
        musicDao.insertMultiple(param);
    }

    public Single<List<NotificationEntry>> readNotification() {
        return musicDao.getAllNotification();
    }

    public Single<List<SearchItemListModel>> fetchSearchItems() {
        return musicDao.fetchItems();
    }

    public Observable<SearchResponseModel> searchItemCall(String key) {
        return webService.getSearchAPI().getItemList(key);
    }

    public Observable<ItemResponseModel> foodItemsListCall() {
        return webService.getSearchAPI().getFoodItemsList();
    }

    public Observable<FreeDeliveryResponseModel> freeDeliveryCall() {
        return webService.getSearchAPI().getFreeDeliveryList();
    }

    public void insertSearchItems(SearchItemListModel item) {
        musicDao.insertProduct(item);
    }
}
