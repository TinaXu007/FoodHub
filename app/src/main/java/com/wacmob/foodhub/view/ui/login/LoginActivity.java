package com.wacmob.foodhub.view.ui.login;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivityLoginBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.utils.AppConstants;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements View.OnClickListener {

    ActivityLoginBinding dataBinding;
    LoginViewModel loginViewModel;
    Spannable spannable;
    int height;
    String s;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BottomFragment bottomFragment;
    String mob;
    int l;

    @Inject
    ViewModelProviderFactory mViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LoginViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setLogin(loginViewModel);
        dataBinding.setListener(this);
        // initViews();
        // subscribeProgress();
        //subscribeLogin();
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        ObjectAnimator animation = ObjectAnimator.ofFloat(dataBinding.imageViewNextArrow,
                "translationX", width - convertDpIntoPx(80));
        animation.setDuration(2000);
        animation.start();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
                dataBinding.imageViewLogin.startAnimation(animation);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

       // dataBinding.ccp.registerPhoneNumberTextView(dataBinding.editTextLogin);

    }

    private void initViews() {

        String substringOne = getString(R.string.label_not_here);
        String substringTwo = getString(R.string.label_signup);
        String combinedString = substringOne + " " + substringTwo;
        SpannableString spannable = new SpannableString(combinedString);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorGrey)),
                0, substringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorBlack)),
                substringOne.length(), combinedString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1), 0, substringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1.2f), substringOne.length(),
                combinedString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // dataBinding.txtHaveAccount.setText(spannable);
    }

    private void subscribeProgress() {

        loginViewModel.getIsLoading().observe(this, progressModel -> {
            if (progressModel != null) {
                if (progressModel.isLoading()) showLoading();
                else hideLoading();
            }
            if (progressModel != null && !progressModel.isHasInternet()) {
                showToast("No internet connection");
            }
            if (progressModel != null && progressModel.getErrorMessage() != null) {
                showToast(progressModel.getErrorMessage());
            }
        });
    }

    private void subscribeLogin() {

        loginViewModel.subscribeLogin().observe(this, loginBean -> {
            if (loginBean != null && loginBean.getStatus() != null
                    && loginBean.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                showToast(loginBean.getMessage());
                startActivity(HomeActivity.newIntent(getApplicationContext()));
                finish();
                overridePendingTransition(R.anim.grow_from_middle, R.anim.shrink_to_middle);
            } else if (loginBean != null && loginBean.getStatus() != null
                    && loginBean.getStatus().equalsIgnoreCase(AppConstants.FAILURE)) {
                showToast(loginBean.getMessage());
            } else if (loginBean != null && loginBean.getMessage() != null) {
                showToast(loginBean.getMessage());
            } else {
                showToast("Unexpected error occurred");
            }
        });

    }

    @Override
    public int getBindingVariable() {
        return BR.login;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getBindingObject() {
        return loginViewModel;
    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.imageViewNextArrow) {
            if (dataBinding.editTextLogin.getText().toString().isEmpty()) {
                showToast("please enter your phone number ");

            } else {
                mob = dataBinding.editTextLogin.getText().toString();
                l = mob.length();
                if (l <= 8)
                    showToast("please enter a valid phone number ");
                else if (l >14)
                    showToast("please enter a valid phone number");
                else {
                    hideKeyboard();
                    s = dataBinding.editTextLogin.getText().toString();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    bottomFragment = new BottomFragment();
                    dataBinding.container.setVisibility(View.VISIBLE);
                    dataBinding.container.bringToFront();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_up, 0);
                    fragmentTransaction.replace(R.id.container, bottomFragment, "bottomSheetFragment");
                    fragmentTransaction.addToBackStack("FRAG");
                    fragmentTransaction.commit();
                    dataBinding.cardView.setVisibility(View.GONE);

                    Bundle bundle = new Bundle();
                    bundle.putString("edttext", s);
                    bottomFragment.setArguments(bundle);
                }

            }


        } else if (v.getId() == R.id.image_view_edit_number) {


            onBackPressed();
            dataBinding.cardView.setVisibility(View.VISIBLE);

        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }


    public int convertDpIntoPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dataBinding.cardView.setVisibility(View.VISIBLE);
    }
}
