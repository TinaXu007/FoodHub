package com.wacmob.foodhub.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wacmob.foodhub.dagger.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by KP on 12/12/2018.
 */

@Singleton
public class InternetUtils {

    private Context context;

    @Inject
    public InternetUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
