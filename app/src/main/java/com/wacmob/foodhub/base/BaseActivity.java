package com.wacmob.foodhub.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
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

public abstract class BaseActivity<T extends ViewDataBinding, V> extends AppCompatActivity implements BaseFragment.Callback{

    private LifecycleRegistry mLifecycleRegistry;

    private T mViewDataBinding;
    private V mViewModel;

    @Inject
    ToastUtils toastUtils;
    private ProgressDialog mProgressDialog;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getBindingObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        performDataBinding();
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

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getBindingObject() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }
}
