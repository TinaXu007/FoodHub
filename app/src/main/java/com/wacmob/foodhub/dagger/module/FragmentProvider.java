package com.wacmob.foodhub.dagger.module;

import com.wacmob.foodhub.dagger.PerActivity;
import com.wacmob.foodhub.view.ui.cart.CartFragment;
import com.wacmob.foodhub.view.ui.login.BottomFragment;
import com.wacmob.foodhub.view.ui.nearme.FoodItemListFragment;
import com.wacmob.foodhub.view.ui.nearme.HubsListFragment;
import com.wacmob.foodhub.view.ui.notification.NotificationFragment;
import com.wacmob.foodhub.view.ui.offers.OffersFragment;
import com.wacmob.foodhub.view.ui.profile.ProfileFragment;
import com.wacmob.foodhub.view.ui.search.SearchItemFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by KP on 1/11/2019.
 */

@Module
public abstract class FragmentProvider {

    @PerActivity
    @ContributesAndroidInjector
    abstract FoodItemListFragment foodItemListFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract ProfileFragment bindProfileFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract CartFragment bindForgotPasswordFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract BottomFragment bottomSheetFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract HubsListFragment hubsListFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract OffersFragment offersFragment();

    @PerActivity
    @ContributesAndroidInjector
    abstract NotificationFragment notificationFragment();


    @PerActivity
    @ContributesAndroidInjector
    abstract SearchItemFragment searchItemFragment();

}
