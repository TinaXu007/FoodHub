package com.wacmob.foodhub.dagger.component;

import android.app.Activity;
import android.app.Application;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.fragment.app.Fragment;

import com.wacmob.foodhub.application.BaseApp;
import com.wacmob.foodhub.dagger.ApplicationContext;
import com.wacmob.foodhub.dagger.module.ActivityProvider;
import com.wacmob.foodhub.dagger.module.ApplicationModule;
import com.wacmob.foodhub.dagger.module.FragmentProvider;
import com.wacmob.foodhub.dagger.module.ViewModelModule;
import com.wacmob.foodhub.data.local.preference.PreferencesHandler;
import com.wacmob.foodhub.data.remote.WebService;
import com.wacmob.foodhub.utils.InternetUtils;

import java.util.Map;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by KP on 1/4/2019.
 */

@Singleton
@Component(modules = {ApplicationModule.class,
        ViewModelModule.class,
        AndroidSupportInjectionModule.class,
        FragmentProvider.class,
        ActivityProvider.class})
public interface ApplicationComponent {

    void inject(BaseApp app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    WebService getWebService();

    PreferencesHandler getPreferencesHandler();

    //MusicDbHelper getMusicDbHelper();

    InternetUtils getInternetUtils();

    Map<Class<? extends ViewModel>, Provider<ViewModel>> creators();

    Map<Class<? extends Fragment>, Provider<AndroidInjector.Factory<? extends Fragment>>> provideFragment();

    Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> provideActivity();
}
