package com.wacmob.foodhub.data.remote;

import com.wacmob.foodhub.data.models.FreeDeliveryResponseModel;
import com.wacmob.foodhub.data.models.ItemResponseModel;
import com.wacmob.foodhub.data.models.SearchResponseModel;
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

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KP on 1/4/2019.
 */

public interface ApiService {

    @GET(ServiceNames.GET_HOME)
    Observable<HomePageBean> loadHomePage(@Query("language") String language);

    @GET(ServiceNames.GET_CONFIG)
    Observable<SplashBean> loadConfig(@Query("language") String language);

    @GET(ServiceNames.GET_CONFIG)
    Observable<UserProfileBean> loadUserProfile(@Query("language") String language);

    @GET(ServiceNames.GET_CONFIG)
    Observable<NotificationBean> loadNotifications(@Query("language") String language);

    @POST(ServiceNames.GET_CONFIG)
    Observable<LoginBean> login(@Body LoginRequest loginRequest);

    @POST(ServiceNames.GET_CONFIG)
    Observable<SignupBean> signup(@Body SignupRequest signupRequest);

    @POST(ServiceNames.GET_CONFIG)
    Observable<ForgotPasswordBean> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @GET(ServiceNames.SEARCH_END_POINT + "/{key}")
    Observable<SearchResponseModel> getItemList(@Path("key") String key);

    @GET(ServiceNames.FOOD_LIST_END_POINT)
    Observable<ItemResponseModel> getFoodItemsList();

    @GET(ServiceNames.FREE_DELIVERY_LIST_END_POINT)
    Observable<FreeDeliveryResponseModel> getFreeDeliveryList();

}
