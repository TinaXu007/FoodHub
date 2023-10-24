package com.wacmob.foodhub.utils;

import android.content.Context;
import android.widget.Toast;

import com.wacmob.foodhub.dagger.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by KP on 1/18/2019.
 */

@Singleton
public class ToastUtils {

    Context context;

    @Inject
    public ToastUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
