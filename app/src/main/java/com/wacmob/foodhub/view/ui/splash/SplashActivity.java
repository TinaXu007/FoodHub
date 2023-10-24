package com.wacmob.foodhub.view.ui.splash;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivitySplashBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.ui.onboarding.OnboardActivity;
import com.wacmob.foodhub.viewmodel.SplashViewModel;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    ActivitySplashBinding splashBinding;
    SplashViewModel splashViewModel;

    @Inject
    ViewModelProviderFactory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SplashViewModel.class);
        splashBinding = (ActivitySplashBinding) getViewDataBinding();
        splashViewModel.getConfig().observe( this,
                splashBean -> {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(OnboardActivity.newIntent(getApplicationContext()));
                            finish();
                            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                        }
                    },2000);

                });
    }

    @Override
    public int getBindingVariable() {
        return BR.splash;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getBindingObject() {
        return splashViewModel;
    }
}
