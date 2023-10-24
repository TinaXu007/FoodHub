package com.wacmob.foodhub.dagger.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wacmob.foodhub.dagger.ViewModelKey;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.viewmodel.AboutUsViewModel;
import com.wacmob.foodhub.viewmodel.AddressListViewModel;
import com.wacmob.foodhub.viewmodel.CartViewModel;
import com.wacmob.foodhub.viewmodel.ChangeAddressViewModel;
import com.wacmob.foodhub.viewmodel.ChangeLocationViewModel;
import com.wacmob.foodhub.viewmodel.DetailViewModel;
import com.wacmob.foodhub.viewmodel.EditProfileViewModel;
import com.wacmob.foodhub.viewmodel.FilterResultViewModel;
import com.wacmob.foodhub.viewmodel.FilterViewModel;
import com.wacmob.foodhub.viewmodel.FoodDetailViewModel;
import com.wacmob.foodhub.viewmodel.FoodItemListViewModel;
import com.wacmob.foodhub.viewmodel.HomeViewModel;
import com.wacmob.foodhub.viewmodel.HubListViewModel;
import com.wacmob.foodhub.viewmodel.LoginViewModel;
import com.wacmob.foodhub.viewmodel.MyOrdersViewModel;
import com.wacmob.foodhub.viewmodel.NotificationFragmentViewModel;
import com.wacmob.foodhub.viewmodel.NotificationViewModel;
import com.wacmob.foodhub.viewmodel.OfferDetailViewModel;
import com.wacmob.foodhub.viewmodel.OffersViewModel;
import com.wacmob.foodhub.viewmodel.OnboardViewModel;
import com.wacmob.foodhub.viewmodel.PaymentViewModel;
import com.wacmob.foodhub.viewmodel.ProfileViewModel;
import com.wacmob.foodhub.viewmodel.SearchViewModel;
import com.wacmob.foodhub.viewmodel.SignupViewModel;
import com.wacmob.foodhub.viewmodel.SplashViewModel;
import com.wacmob.foodhub.viewmodel.TrackItemViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by KP on 1/9/2019.
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel.class)
    abstract ViewModel bindSignupViewModel(SignupViewModel signupViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindForgotPasswordViewModel(CartViewModel cartViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OnboardViewModel.class)
    abstract ViewModel onBoardViewModel(OnboardViewModel onboardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FoodItemListViewModel.class)
    abstract ViewModel FoodItemListViewModel(FoodItemListViewModel foodItemListViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(HubListViewModel.class)
    abstract ViewModel HubListViewModel(HubListViewModel hubListViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(OffersViewModel.class)
    abstract ViewModel OffersViewModel(OffersViewModel offersViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(NotificationFragmentViewModel.class)
    abstract ViewModel NotificationFragmentViewModel(NotificationFragmentViewModel notificationFragmentViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(FoodDetailViewModel.class)
    abstract ViewModel FoodDetailViewModel(FoodDetailViewModel foodDetailViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel DetailViewModel(DetailViewModel detailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OfferDetailViewModel.class)
    abstract ViewModel OfferDetailViewModel(OfferDetailViewModel offerDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel.class)
    abstract ViewModel PaymentViewModel(PaymentViewModel paymentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel.class)
    abstract ViewModel EditProfileViewModel(EditProfileViewModel editProfileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangeLocationViewModel.class)
    abstract ViewModel ChangeLocationViewModel(ChangeLocationViewModel changeLocationViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MyOrdersViewModel.class)
    abstract ViewModel MyOrdersViewModel(MyOrdersViewModel myOrdersViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel.class)
    abstract ViewModel AboutUsViewModel(AboutUsViewModel aboutUsViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ChangeAddressViewModel.class)
    abstract ViewModel ChangeAddressViewModel(ChangeAddressViewModel changeAddressViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel.class)
    abstract ViewModel FilterViewModel(FilterViewModel changeAddressViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(FilterResultViewModel.class)
    abstract ViewModel FilterResultViewModel(FilterResultViewModel filterResultViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel SearchViewModel(SearchViewModel searchViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(TrackItemViewModel.class)
    abstract ViewModel TrackItemViewModel(TrackItemViewModel trackItemViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AddressListViewModel.class)
    abstract ViewModel AddressListViewModel(AddressListViewModel addressListViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}