package com.wacmob.foodhub.view.ui.onboarding;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.OnboardModel;
import com.wacmob.foodhub.databinding.ActivityOnboardBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.OnboardAdapter;
import com.wacmob.foodhub.view.ui.login.LoginActivity;
import com.wacmob.foodhub.viewmodel.OnboardViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OnboardActivity extends BaseActivity<ActivityOnboardBinding, OnboardViewModel> {

    List<OnboardModel> screenList = new ArrayList<>();
    ActivityOnboardBinding onboardBinding;
    OnboardViewModel onboardViewModel;
    OnboardAdapter onboardAdapter;
    int pos;
    @Inject
    ViewModelProviderFactory mViewModelFactory;

    @Override
    public int getBindingVariable() {
        return BR.onboard;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_onboard;
    }

    @Override
    public OnboardViewModel getBindingObject() {
        return onboardViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboardViewModel = ViewModelProviders.of(this, mViewModelFactory).get(OnboardViewModel.class);
        onboardBinding = (ActivityOnboardBinding) getViewDataBinding();

        screenList.add(new OnboardModel(R.layout.onboard_screen_first));
        screenList.add(new OnboardModel(R.layout.onboard_screen_second));
        screenList.add(new OnboardModel(R.layout.onboard_screen_third));
        onboardAdapter = new OnboardAdapter(screenList, this);
        onboardBinding.onBoardViewpager.setAdapter(onboardAdapter);
        onboardBinding.onBoardViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos=position;

                if(position==0){
                    onboardBinding.imageViewPrevArrow.setVisibility(View.GONE);
                    onboardBinding.imageViewFinish.setVisibility(View.GONE);
                    onboardBinding.imageViewNextArrow.setVisibility(View.VISIBLE);


                }
                else if(position==1){
                    onboardBinding.imageViewPrevArrow.setVisibility(View.VISIBLE);
                    onboardBinding.imageViewFinish.setVisibility(View.GONE);
                    onboardBinding.imageViewNextArrow.setVisibility(View.VISIBLE);
                }
                if(position==2){
                    onboardBinding.imageViewNextArrow.setVisibility(View.GONE);
                    onboardBinding.imageViewFinish.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        onboardBinding.imageViewNextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos==0){
                    onboardBinding.onBoardViewpager.setCurrentItem(pos+1);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }
               else if(pos==1){
                    onboardBinding.onBoardViewpager.setCurrentItem(pos+1);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }

            }
        });
        onboardBinding.imageViewPrevArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos==1){
                    onboardBinding.onBoardViewpager.setCurrentItem(pos-1);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }
               else if(pos==2){
                    onboardBinding.onBoardViewpager.setCurrentItem(pos-1);
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                }
            }
        });

        onboardBinding.imageViewFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(LoginActivity.newIntent(getApplicationContext()));
                finish();
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }
        });

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, OnboardActivity.class);
    }


}
