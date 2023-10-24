package com.wacmob.foodhub.view.ui.notification;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.NotificationModel;
import com.wacmob.foodhub.databinding.ActivityNotificationBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.NotificationAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.viewmodel.NotificationViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class NotificationActivity extends BaseActivity<ActivityNotificationBinding, NotificationViewModel> {

    ActivityNotificationBinding activityNotificationBinding;
    NotificationViewModel notificationViewModel;
    @Inject
    ViewModelProviderFactory mViewModelFactory;

    private NotificationAdapter notificationAdapter;
    private ArrayList<NotificationModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NotificationViewModel.class);
        activityNotificationBinding = (ActivityNotificationBinding) getViewDataBinding();
        activityNotificationBinding.rvNotification.setHasFixedSize(true);
        activityNotificationBinding.rvNotification.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        notificationAdapter = new NotificationAdapter(arrayList, notificationAdapterListener);
        activityNotificationBinding.rvNotification.setAdapter(notificationAdapter);
        handleProgressBar();
        notificationViewModel.getNotifications().observe(this, notificationBean -> {
            if (notificationBean.getData() != null) arrayList.addAll(notificationBean.getData());
            notificationAdapter.notifyDataSetChanged();
        });
    }

    private void handleProgressBar() {
        notificationViewModel.getIsLoading().observe(this, progressModel -> {
            if (progressModel != null && !progressModel.isHasInternet()){
                showToast("No internet connection");
            }
            if (progressModel != null && progressModel.getErrorMessage() != null){
                showToast(progressModel.getErrorMessage());
            }
        });
    }

    @Override
    public int getBindingVariable() {
        return BR.notification;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    public NotificationViewModel getBindingObject() {
        return notificationViewModel;
    }

  /*  @BindingAdapter("bind:src")
    public static void setImageUrl(ImageView view, String url) {

//        Glide.with(view.getContext()).
//                load(url).
//                placeholder(R.drawable.battery).
//                into(view);
    }*/

    OnAdapterSelectedListener<NotificationModel> notificationAdapterListener = new OnAdapterSelectedListener<NotificationModel>() {
        @Override
        public void onAdapterSelected(NotificationModel model) {

        }

        @Override
        public void onAdapterString(String text) {
            Toast.makeText(NotificationActivity.this, "Clicked " + text, Toast.LENGTH_SHORT).show();
        }
    };

    public static Intent newIntent(Context context){
        return new Intent(context, NotificationActivity.class);
    }
}
