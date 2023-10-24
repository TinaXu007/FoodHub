package com.wacmob.foodhub.view.ui.change_location;

import android.app.Activity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivityChangeLocationBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.PlaceArrayAdapter;
import com.wacmob.foodhub.viewmodel.ChangeLocationViewModel;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider.REQUEST_CHECK_SETTINGS;

public class ChangeLocationActivity extends BaseActivity<ActivityChangeLocationBinding,
        ChangeLocationViewModel> implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    ChangeLocationViewModel changeLocationViewModel;
    ActivityChangeLocationBinding dataBinding;
    LocationManager locationManager;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private GoogleApiClient mGoogleApiClient;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 6000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private String mCurrentContactPhone = "";


    @Inject
    ViewModelProviderFactory mviewmodelfactory;

    @Override
    public int getBindingVariable() {
        return BR.changeLocation;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_location;
    }

    @Override
    public ChangeLocationViewModel getBindingObject() {
        return changeLocationViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeLocationViewModel = ViewModelProviders.of(this, mviewmodelfactory).get(ChangeLocationViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(onClickListener);
        dataBinding.autocompleteTextView.requestFocus();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                        ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.
                    ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


            mGoogleApiClient = new GoogleApiClient.Builder(ChangeLocationActivity.this)
                    .addApi(Places.GEO_DATA_API)
                    .enableAutoManage(ChangeLocationActivity.this, GOOGLE_API_CLIENT_ID, this)
                    .addConnectionCallbacks(this)
                    .build();

            dataBinding.autocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);

            mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                    /*BOUNDS_MOUNTAIN_VIEW,*/ null);
            dataBinding.autocompleteTextView.setAdapter(mPlaceArrayAdapter);

        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == dataBinding.textViewDetectLoc) {
                if (!dataBinding.textViewCurrentLoc.getText().toString().equals("")) {
                    dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.GONE);
                } else {
                    dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.VISIBLE);
                }

                if (SmartLocation.with(ChangeLocationActivity.this).location().state().isGpsAvailable()) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                            ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                            checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                                    ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(ChangeLocationActivity.this, new String[]{android.Manifest.permission.
                                ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                    }
                    getLocation();
                } else {
                    displayLocationSettingsRequest();
                }

            } else if (v == dataBinding.imageViewPrevArrow) {
                onBackPressed();
                hideKeyboard();
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
        }
    };


    void getLocation() {

        SmartLocation.with(this).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {

                        try {
                            Geocoder geocoder = new Geocoder(ChangeLocationActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            dataBinding.lottieAnimationViewLoadPlace.setVisibility(View.GONE);

                            dataBinding.textViewCurrentLoc.setText(addresses.get(0).getAddressLine(0) /*+ ", " +
                    addresses.get(0).getAddressLine(1)*/ /*+ ", " + addresses.get(0).getAddressLine(2)*/);
                        } catch (Exception e) {

                        }
                    }
                });

        SmartLocation.with(ChangeLocationActivity.this).location().state().locationServicesEnabled();

// Check if any provider (network or gps) is enabled
        SmartLocation.with(ChangeLocationActivity.this).location().state().isAnyProviderAvailable();

// Check if GPS is available
        SmartLocation.with(ChangeLocationActivity.this).location().state().isGpsAvailable();

// Check if Network is available
        SmartLocation.with(ChangeLocationActivity.this).location().state().isNetworkAvailable();

// Check if the passive provider is available
        SmartLocation.with(ChangeLocationActivity.this).location().state().isPassiveAvailable();

// Check if the location is mocked
        SmartLocation.with(ChangeLocationActivity.this).location().state().isMockSettingEnabled();
    }


    private void displayLocationSettingsRequest() {

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(ChangeLocationActivity.this)
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
                            status.startResolutionForResult(ChangeLocationActivity.this, REQUEST_CHECK_SETTINGS);
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
    }


    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

        }
    };


    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e("", "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();


            String placeId = place.getId();
            System.out.println("place.getName()>>>>>>>>" + place.getName());
            System.out.println("place.getAddress()>>>>>>>>" + place.getAddress());
            System.out.println("place.getId()>>>>>>>>" + place.getId());
            System.out.println("place.getPhoneNumber()>>>>>>>>" + place.getPhoneNumber());
            System.out.println("place.getWebsiteUri()>>>>>>>>" + place.getWebsiteUri());
            System.out.println("place.getName()>>>>>>>>" + place.getName());


            /*Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId)
                    .setResultCallback(new ResultCallback<PlaceBuffer>() {
                        @Override
                        public void onResult(PlaceBuffer places) {
                            if (places.getStatus().isSuccess()) {
                                final Place myPlace = places.get(0);
                                LatLng queriedLocation = myPlace.getLatLng();
                                Log.v("Latitude is", "" + queriedLocation.latitude);
                                Log.v("Longitude is", "" + queriedLocation.longitude);


                            }
                            places.release();
                        }
                    });*/
        }
    };

    public static Intent newIntent(Context context) {
        return new Intent(context, ChangeLocationActivity.class);
    }

}








