package com.wacmob.foodhub.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wacmob.foodhub.utils.DialogUtils;
import com.wacmob.foodhub.utils.InternetUtils;
import com.wacmob.foodhub.utils.ToastUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by KP on 1/4/2019.
 */

public abstract class SimpleBaseActivity extends AppCompatActivity implements BaseFragment.Callback{

    @Inject
    ToastUtils toastUtils;
    private ProgressDialog mProgressDialog;

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public boolean isNetworkConnected() {
        return InternetUtils.isNetworkAvailable(getApplicationContext());
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showToast(String message){
        toastUtils.showToast(message);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = DialogUtils.showLoadingDialog(this);
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }
}
