package com.wacmob.foodhub.view.ui.nearme;


import androidx.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
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
import com.wacmob.foodhub.base.BaseFragment;
import com.wacmob.foodhub.data.models.CenterZoomLayoutManager;
import com.wacmob.foodhub.data.models.HubsListModel;
import com.wacmob.foodhub.data.models.StartSnapHelper;
import com.wacmob.foodhub.databinding.FragmentHubsListBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.HublistAdapterNew;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.viewmodel.HubListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HubsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HubsListFragment extends BaseFragment<FragmentHubsListBinding,
        HubListViewModel> implements OnMapReadyCallback, OnAdapterSelectedListener<HubsListModel> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<HubsListModel> hubsListModels = new ArrayList<>();
    // HubsListAdapter hubsListAdapter;
    HublistAdapterNew hubsListAdapter;
    FragmentHubsListBinding dataBinding;
    HubsListModel hubModel;
    CenterZoomLayoutManager centerZoomLayoutManager;
    double lattitude, longitude, placeLattitude, placeLongitude;
    SupportMapFragment mapFragment;
    LatLng latLng;
    int middle;
    @Inject
    ViewModelProviderFactory mVviewModelFactory;
    HubListViewModel hubListViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Marker> markers = new ArrayList<>();
    private GoogleMap mGoogleMap;

    public HubsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HubsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HubsListFragment newInstance(String param1, String param2) {
        HubsListFragment fragment = new HubsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.hubs;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hubs_list;
    }

    @Override
    public HubListViewModel getBindingObject() {
        return hubListViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hubListViewModel = ViewModelProviders.of(this, mVviewModelFactory).get(HubListViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        dataBinding = getViewDataBinding();
        handleProgressBar();
        populateData();
        SnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(dataBinding.recyclerViewHubsList);
        centerZoomLayoutManager = new CenterZoomLayoutManager(getContext(),
                CenterZoomLayoutManager.HORIZONTAL, false);
        dataBinding.recyclerViewHubsList.setLayoutManager(centerZoomLayoutManager);
        dataBinding.recyclerViewHubsList.setHasFixedSize(true);
        hubsListAdapter = new HublistAdapterNew(hubsListModels, this);
        dataBinding.recyclerViewHubsList.setAdapter(hubsListAdapter);
        dataBinding.recyclerViewHubsList.smoothScrollBy(5, 0);
        dataBinding.recyclerViewHubsList.smoothScrollBy(-5, 0);

        hubsListModels.get(1).setSelected(true);


        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {

            mapFragment.getMapAsync(this);
        }
        if (getActivity() != null && getActivity() instanceof HomeActivity) {

            ((HomeActivity) getActivity()).showBottomBar();
            ((HomeActivity) getActivity()).loadHublistFragment();
        }


    }

    private void handleProgressBar() {

        hubListViewModel.getIsLoading().observe(getViewLifecycleOwner() != null ? getViewLifecycleOwner() : this,
                progressModel -> {
                    if (progressModel != null && !progressModel.isHasInternet()) {
                        showToast("No internet connection");
                    }
                });
    }

    private void populateData() {


        hubsListModels.add(new HubsListModel("", "",
                R.drawable.dp, 0, 0));
        hubsListModels.add(new HubsListModel("Drunken Panda", "0.2km,4 stars",
                R.drawable.dp, 10.272800, 76.357540));
        hubsListModels.add(new HubsListModel("Malabar Thattukkada", "0.2km,4 stars",
                R.drawable.dp, 10.264230, 76.352564));
        hubsListModels.add(new HubsListModel("Spicy Kitchen", "0.2km,4 stars",
                R.drawable.dp, 10.269600, 76.345001));
        hubsListModels.add(new HubsListModel("Paragon", "0.2km,4 stars",
                R.drawable.dp, 10.276900, 76.368640));
        hubsListModels.add(new HubsListModel("Food Court", "0.2km,4 stars",
                R.drawable.dp, 10.278430, 76.36374));
        hubsListModels.add(new HubsListModel("Food Factory", "0.2km,4 stars",
                R.drawable.dp, 10.283520, 76.36984));
        hubsListModels.add(new HubsListModel("Rahmania", "0.2km,4 stars",
                R.drawable.dp, 10.289720, 76.35634));
        hubsListModels.add(new HubsListModel("Bait Al ", "0.2km,4 stars",
                R.drawable.dp, 10.280800, 76.34040));
        hubsListModels.add(new HubsListModel("", "",
                R.drawable.dp, 0, 0));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        dataBinding.recyclerViewHubsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstPos = centerZoomLayoutManager.findFirstVisibleItemPosition();
                int lastPos = centerZoomLayoutManager.findLastVisibleItemPosition();
                middle = Math.abs(lastPos - firstPos) / 2 + firstPos;


                for (int i = 0; i < hubsListAdapter.getItemCount(); i++) {

                    if (i == middle) {
                        hubsListModels.get(i).setSelected(true);
                        latLng = new LatLng(hubsListModels.get(i).getLat(), hubsListModels.get(i).getLon());
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                        for (Marker marker : markers) {

                            if (marker.getTitle().equals(hubsListModels.get(i).getRestaurentName())) {
                                marker.showInfoWindow();
                                break;
                            }
                        }
                    } else {
                        hubsListModels.get(i).setSelected(false);
                    }
                }

                hubsListAdapter.notifyDataSetChanged();


            }
        });

        placeLattitude = 10.263600;
        placeLongitude = 76.345001;
        int length = hubsListModels.size();
        int i;

        LatLng latLng = new LatLng(placeLattitude, placeLongitude);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        for (i = 0; i < length; i++) {
            if (mGoogleMap != null) {

                LatLng latLngChild = new LatLng(hubsListModels.get(i).getLat(), hubsListModels.get(i).getLon());
                markers.add(mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLngChild)
                        .title(hubsListModels.get(i).getRestaurentName())
                        .snippet("Restaurent")
                        .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_restaurant))));

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


    @Override
    public void onAdapterSelected(HubsListModel model) {

        if (getActivity() != null) {
            ((HomeActivity) getActivity()).openHubDetails(model);
        }
        lattitude = model.getLat();
        longitude = model.getLon();
        hubModel = model;


        centerZoomLayoutManager.smoothScrollToPosition(dataBinding.recyclerViewHubsList, null, hubsListModels.indexOf(model));
        for (Marker marker : markers) {

            if (marker.getTitle().equals(model.getRestaurentName())) {
                marker.showInfoWindow();
                break;
            }
        }

        for (HubsListModel hubsListModel : hubsListModels) {

            if (hubsListModel.equals(model)) {
                hubsListModel.setSelected(true);


            } else {
                hubsListModel.setSelected(false);
            }
        }


    }

    @Override
    public void onAdapterString(String text) {

    }
}
