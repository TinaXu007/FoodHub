package com.wacmob.foodhub.view.ui.home_page;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.HubsListModel;
import com.wacmob.foodhub.data.models.MostPopularItemModel;
import com.wacmob.foodhub.data.models.OfferListModelNew;
import com.wacmob.foodhub.data.models.OffersModel;
import com.wacmob.foodhub.data.models.PopularListModelNew;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.databinding.ActivityHomeBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.utils.InternetUtils;
import com.wacmob.foodhub.view.ui.cart.CartFragment;
import com.wacmob.foodhub.view.ui.food_item_detail_page.FoodDetailActivity;
import com.wacmob.foodhub.view.ui.hub_detail_page.DetailHubActivity;
import com.wacmob.foodhub.view.ui.nearme.FoodItemListFragment;
import com.wacmob.foodhub.view.ui.nearme.HubsListFragment;
import com.wacmob.foodhub.view.ui.notification.NotificationFragment;
import com.wacmob.foodhub.view.ui.offer_detail.OfferDetailActivity;
import com.wacmob.foodhub.view.ui.offers.OffersFragment;
import com.wacmob.foodhub.view.ui.profile.ProfileFragment;
import com.wacmob.foodhub.view.ui.search.SearchItemFragment;
import com.wacmob.foodhub.viewmodel.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity implements HasSupportFragmentInjector, View.OnClickListener {

    @Inject
    ViewModelProviderFactory mViewModelFactory;
    HomeViewModel homeViewModel;
    ActivityHomeBinding dataBinding;


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel.class);
        dataBinding = (ActivityHomeBinding) getViewDataBinding();
        dataBinding.setListener(this);
        dataBinding.setSelection(1);


        if (InternetUtils.isNetworkAvailable(HomeActivity.this)) {

            dataBinding.noNetworkLayout.setVisibility(View.GONE);
            getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(0, 0)
                    .replace(R.id.fragment_container, new FoodItemListFragment())
                    .commit();
        } else {
            dataBinding.noNetworkLayout.setVisibility(View.VISIBLE);
        }


        dataBinding.editTextSearch.setFocusable(false);


    }

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingInjector;
    }

    @Override
    public int getBindingVariable() {
        return BR.home;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Object getBindingObject() {
        return homeViewModel;

    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.textViewHubs) {
            dataBinding.setSelection(0);
            if (getVisibleFragment() instanceof HubsListFragment) {

            } else {

                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new HubsListFragment())
                        .commit();
            }


        } else if (v == dataBinding.textViewFoods) {

            dataBinding.setSelection(1);
            if (getVisibleFragment() instanceof FoodItemListFragment) {

            } else {

                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new FoodItemListFragment())
                        .commit();
            }

        } else if (v == dataBinding.linearLayoutNearMe) {

            if (getVisibleFragment() instanceof FoodItemListFragment || getVisibleFragment() instanceof HubsListFragment) {

            } else {

                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new FoodItemListFragment())
                        .commit();
            }


        } else if (v == dataBinding.linearLayoutOffers) {

            if (getVisibleFragment() instanceof OffersFragment) {

            } else {
                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new OffersFragment())
                        .commit();
            }
        } else if (v == dataBinding.linearLayoutCart) {

            if (getVisibleFragment() instanceof CartFragment) {

            } else {
                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new CartFragment())
                        .commit();
            }

        } else if (v == dataBinding.linearLayoutNotification) {

            if (getVisibleFragment() instanceof NotificationFragment) {

            } else {
                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new NotificationFragment())
                        .commit();
            }

        } else if (v == dataBinding.linearLayoutProfile) {

            if (getVisibleFragment() instanceof ProfileFragment) {

            } else {
                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .commit();
            }

        } else if (v == dataBinding.editTextSearch) {

            if (getVisibleFragment() instanceof SearchItemFragment) {

            } else {


                SearchItemFragment fragment = new SearchItemFragment();
                Bundle arguments = new Bundle();
                if (dataBinding.getSelection() == 0) {
                    arguments.putString("SET", "0");
                    fragment.setArguments(arguments);

                } else {
                    arguments.putString("SET", "1");
                    fragment.setArguments(arguments);
                }

                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, fragment)
                        .commit();

            }
        } else if (v == dataBinding.textViewRetry) {

            if (InternetUtils.isNetworkAvailable(HomeActivity.this)) {

                getSupportFragmentManager()
                        .beginTransaction().setCustomAnimations(0, 0)
                        .replace(R.id.fragment_container, new FoodItemListFragment())
                        .commit();
            } else {
                internet();
            }

        }

    }


    private Fragment getVisibleFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    @Override
    public void onBackPressed() {

        if (getVisibleFragment() instanceof FoodItemListFragment || getVisibleFragment() instanceof HubsListFragment) {

            super.onBackPressed();

        } else {

            getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(0, 0)
                    .replace(R.id.fragment_container, new FoodItemListFragment())
                    .commit();

        }

    }

    public void hideBottomBar() {

        dataBinding.constraintLayoutBottomBar.setVisibility(View.GONE);
    }

    public void showBottomBar() {
        dataBinding.constraintLayoutBottomBar.setVisibility(View.VISIBLE);
    }

    public void loadHublistFragment() {

        dataBinding.setBottomSelection(0);
        dataBinding.setSelection(0);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.VISIBLE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#DB1818"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));

    }

    public void loadFoodListFragment() {

        dataBinding.setBottomSelection(0);
        dataBinding.setSelection(1);
        dataBinding.cardViewSearchTop.setVisibility(View.VISIBLE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#DB1818"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));

    }

    public void loadOffersFragment() {

        dataBinding.setBottomSelection(1);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.GONE);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#DB1818"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));
    }

    public void loadCartFragment() {

        dataBinding.setBottomSelection(2);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.GONE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#DB1818"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));
    }

    public void loadNotificationFragment() {

        dataBinding.setBottomSelection(3);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.GONE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#DB1818"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));
    }

    public void loadprofileFragment() {

        dataBinding.setBottomSelection(4);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.GONE);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#DB1818"));
    }

    public void loadSearchFragment() {

        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        dataBinding.cardViewSearchTop.setVisibility(View.GONE);
        dataBinding.setBottomSelection(5);
        dataBinding.imageViewNearMe.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewOffers.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewCart.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewNotification.setColorFilter(Color.parseColor("#C7C7C7"));
        dataBinding.imageViewProfile.setColorFilter(Color.parseColor("#C7C7C7"));

    }

    public void openHubDetails(HubsListModel model) {
        Intent i = new Intent(HomeActivity.this, DetailHubActivity.class);
        i.putExtra("HUBMODEL", model);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openFoodDetails(PopularListModelNew model) {
        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("FOODMODEL", model);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openRelatedFoodDetails(MostPopularItemModel model) {
        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("FOODMODEL", model);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openOfferDetail(OffersModel model) {

        Intent i = new Intent(HomeActivity.this, OfferDetailActivity.class);
        i.putExtra("OFFERMODEL", model);
        startActivityForResult(i, 8880);

    }


    public void openSearchFoodDetail(SearchItemListModel model) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("SEARCHITEMMODEL", model);
        startActivityForResult(i, 4311);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

    }

    public void openOfferFood(OfferListModelNew model) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("OFFERMODEL", model);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }


    public void openCardViewFirst(String itemName, String itemRate) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("ITEM_NAME", itemName);
        i.putExtra("ITEM_RATE", itemRate);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openCardViewSecond(String itemName, String itemRate) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("ITEM_NAME", itemName);
        i.putExtra("ITEM_RATE", itemRate);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openCardViewThird(String itemName, String itemRate) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("ITEM_NAME", itemName);
        i.putExtra("ITEM_RATE", itemRate);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);

    }

    public void openCardViewFourth(String itemName, String itemRate) {

        Intent i = new Intent(HomeActivity.this, FoodDetailActivity.class);
        i.putExtra("ITEM_NAME", itemName);
        i.putExtra("ITEM_RATE", itemRate);
        startActivityForResult(i, 4311);
        overridePendingTransition(0, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4311) {
            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
                if (bundle.containsKey("GOTOCART")) {
                    int go = bundle.getInt("GOTOCART", 0);
                    if (go == 1) {
                        getSupportFragmentManager()
                                .beginTransaction().setCustomAnimations(0, 0)
                                .replace(R.id.fragment_container, new CartFragment())
                                .commit();
                    }
                }
            }

        } else if (requestCode == 8880) {

            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
                if (bundle.containsKey("GOTOFOODLIST")) {
                    int goToFoodList = bundle.getInt("GOTOFOODLIST", 0);
                    if (goToFoodList == 1) {
                        getSupportFragmentManager()
                                .beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                                .replace(R.id.fragment_container, new FoodItemListFragment())
                                .commit();
                    }
                }
            }

        }
    }


    public void noInternet() {

        dataBinding.noNetworkLayout.setVisibility(View.VISIBLE);
    }

    public void hasInternet() {

        dataBinding.noNetworkLayout.setVisibility(View.GONE);
    }

    public void internet() {

        dataBinding.noNetworkLayout.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataBinding.noNetworkLayout.setVisibility(View.VISIBLE);
            }
        }, 80);
    }


}
