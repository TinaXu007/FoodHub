package com.wacmob.foodhub.view.listener;

/**
 * Created by KP on 12/27/2018.
 */

public interface OnAdapterSelectedListener<T> {

    void onAdapterSelected(T model);

    void onAdapterString(String text);
}
