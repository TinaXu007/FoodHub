package com.wacmob.foodhub.view.ui.order_placed;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.OrderdItemsModel;
import com.wacmob.foodhub.databinding.ActivityOrderPlacedBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.OrderedItemsAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.track_item.TrackItemActivity;
import com.wacmob.foodhub.viewmodel.PaymentViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrderPlacedActivity extends BaseActivity<ActivityOrderPlacedBinding, PaymentViewModel> implements View.OnClickListener {

    ActivityOrderPlacedBinding dataBinding;
    PaymentViewModel paymentViewModel;
    List<OrderdItemsModel> orderdItemsModels = new ArrayList<>();
    OrderedItemsAdapter orderedItemsAdapter;
    @Inject
    ViewModelProviderFactory mViewModelFactory;

    @Override
    public int getBindingVariable() {
        return BR.payment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_placed;
    }

    @Override
    public PaymentViewModel getBindingObject() {
        return paymentViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PaymentViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this::onClick);
        dataBinding.lottieAnimationView.setVisibility(View.VISIBLE);
        dataBinding.textViewPaymentProcessing.setVisibility(View.VISIBLE);
        dataBinding.textViewSuccess.setVisibility(View.GONE);
        dataBinding.constraintLayoutOrderPlaced.setVisibility(View.GONE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataBinding.lottieAnimationView.setVisibility(View.GONE);
                dataBinding.textViewPaymentProcessing.setVisibility(View.GONE);
                dataBinding.textViewSuccess.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.textViewSuccess.setVisibility(View.GONE);
                        showOrderPlaced();

                    }
                }, 1500);

            }
        }, 4000);

    }

    private void showOrderPlaced() {

        dataBinding.constraintLayoutOrderPlaced.setVisibility(View.VISIBLE);
        populateData();
        orderedItemsAdapter = new OrderedItemsAdapter(orderdItemsModels, onAdapterSelectedListener);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setAdapter(orderedItemsAdapter);
    }

    OnAdapterSelectedListener<OrderdItemsModel> onAdapterSelectedListener = new OnAdapterSelectedListener<OrderdItemsModel>() {
        @Override
        public void onAdapterSelected(OrderdItemsModel model) {

        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    private void populateData() {

        orderdItemsModels.add(new OrderdItemsModel(0, R.drawable.login_image));
        orderdItemsModels.add(new OrderdItemsModel(0, R.drawable.login_image));

    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.imageViewPrevArrow) {
            onBackPressed();

        } else if (v == dataBinding.textViewTrackItem) {

            Intent i = TrackItemActivity.newIntent(getApplicationContext());
            startActivity(i);
            overridePendingTransition(0, 0);
        }

    }

    public static Intent newIntent(Context context) {

        return new Intent(context, OrderPlacedActivity.class);
    }

}
