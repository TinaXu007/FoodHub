package com.wacmob.foodhub.dagger.module;

import android.app.Application;
import android.content.Context;

import com.wacmob.foodhub.dagger.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KP on 1/4/2019.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

}
