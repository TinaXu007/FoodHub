package com.wacmob.foodhub.view.ui.track_item;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.databinding.ActivityTrackItemBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.viewmodel.TrackItemViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TrackItemActivity extends BaseActivity<ActivityTrackItemBinding, TrackItemViewModel> implements OnMapReadyCallback {

    ActivityTrackItemBinding dataBinding;
    TrackItemViewModel trackItemViewModel;
    GoogleMap mGoogleMap;
    double placeLatitude, placeLongitude;
    SupportMapFragment mapFragment;
    List<Marker> markers = new ArrayList<>();
    LatLng latLngHotel = new LatLng(10.263600, 76.345001);
    LatLng latLngHome = new LatLng(10.308027, 76.333679);

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.trackItem;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_track_item;
    }

    @Override
    public TrackItemViewModel getBindingObject() {
        return trackItemViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackItemViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(TrackItemViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(onClick);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {

            mapFragment.getMapAsync(this);
        }

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == dataBinding.imageViewCall) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9037324058"));
                startActivity(callIntent);
            } else if (v == dataBinding.imageViewRoute) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + latLngHotel.latitude + ","
                                + latLngHotel.longitude + "&daddr=" + latLngHome.latitude + "," + latLngHome.longitude));
                startActivity(intent);
            }
        }
    };


    public static Intent newIntent(Context context) {
        return new Intent(context, TrackItemActivity.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        placeLatitude = 10.263600;
        placeLongitude = 76.345001;

        LatLng latLng = new LatLng(placeLatitude, placeLongitude);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));


        if (mGoogleMap != null) {

          /*  LatLng latLngHotel = new LatLng(10.272800, 76.357540);
            LatLng latLngHome = new LatLng(10.280800, 76.34040);
*/

            markers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLngHotel)
                    .title("DRUNKEN PANDA")
                    .snippet("Restaurent")
                    .icon(bitmapDescriptorFromVector(TrackItemActivity.this, R.drawable.ic_restaurant))));



            markers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLngHome)
                    .title("HOME")
                    .icon(bitmapDescriptorFromVector(TrackItemActivity.this, R.drawable.ic_home_red))));


            for (Marker marker : markers) {

                marker.showInfoWindow();

            }

        }
    }

    private BitmapDescriptor bitmapDescriptorFromVector(FragmentActivity activity, int ic_stop) {


        Drawable vectorDrawable = ContextCompat.getDrawable(activity, ic_stop);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}


