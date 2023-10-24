package com.wacmob.foodhub.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.fragment.app.Fragment;

import com.wacmob.foodhub.dagger.component.ApplicationComponent;
import com.wacmob.foodhub.dagger.component.DaggerApplicationComponent;
import com.wacmob.foodhub.dagger.module.ApplicationModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by KP on 12/12/2018.
 */

public class  BaseApp extends Application implements HasActivityInjector, HasSupportFragmentInjector{

    protected ApplicationComponent applicationComponent;
    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    public static BaseApp get(Context context) {
        return (BaseApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
