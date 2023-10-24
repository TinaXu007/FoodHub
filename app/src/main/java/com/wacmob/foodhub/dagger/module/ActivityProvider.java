package com.wacmob.foodhub.dagger.module;

import com.wacmob.foodhub.dagger.PerActivity;
import com.wacmob.foodhub.view.ui.about_us.AboutUsActivity;
import com.wacmob.foodhub.view.ui.address_list.AddressListActivity;
import com.wacmob.foodhub.view.ui.change_address.ChangeAddressActivity;
import com.wacmob.foodhub.view.ui.change_location.ChangeLocationActivity;
import com.wacmob.foodhub.view.ui.edit_profile.EditProfileActivity;
import com.wacmob.foodhub.view.ui.filter.FilterActivity;
import com.wacmob.foodhub.view.ui.filter_result.FilterResultActivity;
import com.wacmob.foodhub.view.ui.food_item_detail_page.FoodDetailActivity;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.view.ui.hub_detail_page.DetailHubActivity;
import com.wacmob.foodhub.view.ui.login.LoginActivity;
import com.wacmob.foodhub.view.ui.myorders.MyOrdersActivity;
import com.wacmob.foodhub.view.ui.notification.NotificationActivity;
import com.wacmob.foodhub.view.ui.offer_detail.OfferDetailActivity;
import com.wacmob.foodhub.view.ui.onboarding.OnboardActivity;
import com.wacmob.foodhub.view.ui.order_placed.OrderPlacedActivity;
import com.wacmob.foodhub.view.ui.signup.SignupActivity;
import com.wacmob.foodhub.view.ui.splash.SplashActivity;
import com.wacmob.foodhub.view.ui.track_item.TrackItemActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by KP on 1/11/2019.
 */

@Module
public abstract class ActivityProvider {

    @PerActivity
    @ContributesAndroidInjector
    abstract HomeActivity bindHomeActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract NotificationActivity bindNotificationActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract SignupActivity bindSignupActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract OnboardActivity onboardActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract FoodDetailActivity foodDetailActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract DetailHubActivity detailHubActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract OfferDetailActivity offerDetailActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract OrderPlacedActivity paymentActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract EditProfileActivity editProfileActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract ChangeLocationActivity changeLocationActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract MyOrdersActivity myOrdersActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract AboutUsActivity aboutUsActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract ChangeAddressActivity changeAddressActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract FilterActivity filterActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract FilterResultActivity filterResultActivity();


    @PerActivity
    @ContributesAndroidInjector
    abstract TrackItemActivity trackItemActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract AddressListActivity addressListActivity();


}
