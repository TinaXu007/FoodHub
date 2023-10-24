package com.wacmob.foodhub.view.ui.about_us;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivityAboutUsBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.viewmodel.AboutUsViewModel;

import javax.inject.Inject;

public class AboutUsActivity extends BaseActivity<ActivityAboutUsBinding, AboutUsViewModel> implements View.OnClickListener {
    ActivityAboutUsBinding dataBinding;
    AboutUsViewModel aboutUsViewModel;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.aboutUs;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public AboutUsViewModel getBindingObject() {
        return aboutUsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutUsViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AboutUsViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        if (v == dataBinding.imageViewPrevArrow) {
            onBackPressed();
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AboutUsActivity.class);
    }
}
