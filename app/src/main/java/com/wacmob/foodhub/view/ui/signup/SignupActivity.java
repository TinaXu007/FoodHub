package com.wacmob.foodhub.view.ui.signup;

import android.app.Activity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivitySignupBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.utils.AppConstants;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.viewmodel.SignupViewModel;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider.REQUEST_CHECK_SETTINGS;

public class SignupActivity extends BaseActivity<ActivitySignupBinding, SignupViewModel> implements View.OnClickListener {

    ActivitySignupBinding dataBinding;
    SignupViewModel signupViewModel;
    String userName, email, cityName, pinCode, houseName, phoneNumber;
    // private AwesomeValidation awesomeValidation;

    @Inject
    ViewModelProviderFactory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        signupViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SignupViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setSignUp(signupViewModel);
        dataBinding.setListener(this);
        initViews();
        subscribeProgress();
        subscribeSignup();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        dataBinding.imageViewSignUpBackground.startAnimation(animation);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                        ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.
                    ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

    }


    private void initViews() {

        String substringOne = getString(R.string.label_not_here);
        String substringTwo = getString(R.string.label_login);
        String combinedString = substringOne + " " + substringTwo;
        SpannableString spannable = new SpannableString(combinedString);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorGrey)),
                0, substringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorBlack)),
                substringOne.length(), combinedString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1), 0, substringOne.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(1.2f), substringOne.length(), combinedString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //dataBinding.txtHaveAccount.setText(spannable);

    }

    private void subscribeProgress() {

        signupViewModel.getIsLoading().observe(this,
                progressModel -> {
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

    private void subscribeSignup() {

        signupViewModel.subscribeSignup().observe(this,
                signupBean -> {
                    if (signupBean != null && signupBean.getStatus() != null
                            && signupBean.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                        showToast(signupBean.getMessage());
                        startActivity(HomeActivity.newIntent(getApplicationContext()));
                        finishAffinity();
                        overridePendingTransition(0, 0);
                    } else if (signupBean != null && signupBean.getStatus() != null
                            && signupBean.getStatus().equalsIgnoreCase(AppConstants.FAILURE)) {
                        showToast(signupBean.getMessage());
                    } else if (signupBean != null && signupBean.getMessage() != null) {
                        showToast(signupBean.getMessage());
                    } else {
                        showToast("Unexpected error occurred");
                    }
                });
    }

    @Override
    public int getBindingVariable() {
        return BR.signUp;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    public SignupViewModel getBindingObject() {
        return signupViewModel;
    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.imageViewNextArrow) {

            hideKeyboard();

            userName = dataBinding.editTextUserName.getText().toString();
            email = dataBinding.editTextEmail.getText().toString();
            cityName = dataBinding.editTextCity.getText().toString();
            pinCode = dataBinding.editTextPinCode.getText().toString();
            houseName = dataBinding.editTextPinCode.getText().toString();
            phoneNumber = dataBinding.editTextPhone.getText().toString();

            int l = phoneNumber.length();
            if (!email.isEmpty() && !cityName.isEmpty() && !pinCode.isEmpty() &&
                    !phoneNumber.isEmpty() && !userName.isEmpty() && !houseName.isEmpty()) {


                String email = dataBinding.editTextEmail.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern) && !(l <= 8 || l >= 14)) {

                    Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                    i.putExtra("NAME", userName);
                    i.putExtra("HOUSE_NAME", houseName);
                    i.putExtra("CITY_NAME", cityName);
                    i.putExtra("PIN_CODE", pinCode);
                    i.putExtra("PHONE_NUMBER", phoneNumber);
                    i.putExtra("USER_NAME", userName);
                    startActivity(i);
                    showToast("Signup success");
                    SignupActivity.this.finish();
                    overridePendingTransition(0, 0);

                } else {
                    if (email.matches(emailPattern) && (l <= 8 || l >= 14)) {
                        dataBinding.editTextPhone.setError("Enter a valid  contact number");
                    } else if (!email.matches(emailPattern) && (l <= 8 || l >= 14)) {
                        dataBinding.editTextEmail.setError("Enter a valid email id");
                        dataBinding.editTextPhone.setError("Enter a valid  contact number");

                    } else {
                        dataBinding.editTextEmail.setError("Enter a valid email id");
                    }
                }

            } else {
                showToast("please fill all the fields");


            }

        } else if (v == dataBinding.imageViewLocate) {

            if (!dataBinding.editTextLocation.getText().toString().equals("")) {
                dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.GONE);
                dataBinding.editTextLocation.setVisibility(View.VISIBLE);
            } else {
                dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.VISIBLE);
                dataBinding.editTextLocation.setVisibility(View.GONE);
            }

            if (SmartLocation.with(SignupActivity.this).location().state().isGpsAvailable()) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                        ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                        checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(SignupActivity.this, new String[]{android.Manifest.permission.
                            ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                }
                getLocation();
            } else {
                displayLocationSettingsRequest();
            }
        }
    }

    private void displayLocationSettingsRequest() {

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(SignupActivity.this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        Log.i("", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(SignupActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });

    }

    private void getLocation() {
        SmartLocation.with(this).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {

                        try {
                            Geocoder geocoder = new Geocoder(SignupActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.GONE);
                            dataBinding.editTextLocation.setVisibility(View.VISIBLE);

                            dataBinding.editTextLocation.setText(addresses.get(0).getAddressLine(0) /*+ ", " +
                    addresses.get(0).getAddressLine(1)*/ /*+ ", " + addresses.get(0).getAddressLine(2)*/);
                        } catch (Exception e) {

                        }
                    }
                });

        SmartLocation.with(SignupActivity.this).location().state().locationServicesEnabled();

// Check if any provider (network or gps) is enabled
        SmartLocation.with(SignupActivity.this).location().state().isAnyProviderAvailable();

// Check if GPS is available
        SmartLocation.with(SignupActivity.this).location().state().isGpsAvailable();

// Check if Network is available
        SmartLocation.with(SignupActivity.this).location().state().isNetworkAvailable();

// Check if the passive provider is available
        SmartLocation.with(SignupActivity.this).location().state().isPassiveAvailable();

// Check if the location is mocked
        SmartLocation.with(SignupActivity.this).location().state().isMockSettingEnabled();

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                }
                break;
        }
    }
}
