package com.wacmob.foodhub.view.ui.filter_result;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.FilterResultModel;
import com.wacmob.foodhub.databinding.ActivityFilterResultBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.FilterResultAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.viewmodel.FilterResultViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FilterResultActivity extends BaseActivity<ActivityFilterResultBinding, FilterResultViewModel> implements View.OnClickListener {

    ActivityFilterResultBinding dataBinding;
    FilterResultAdapter filterResultAdapter;
    List<FilterResultModel> filterResultModelList = new ArrayList<>();

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    FilterResultViewModel filterResultViewModel;

    @Override
    public int getBindingVariable() {
        return BR.filterResult;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter_result;
    }

    @Override
    public FilterResultViewModel getBindingObject() {
        return filterResultViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterResultViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FilterResultViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this::onClick);
        populateData();

        filterResultAdapter = new FilterResultAdapter(filterResultModelList, selectedListener);
        dataBinding.recyclerViewFilterResult.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.recyclerViewFilterResult.setHasFixedSize(true);
        dataBinding.recyclerViewFilterResult.setAdapter(filterResultAdapter);

    }

    private void populateData() {

        filterResultModelList.add(new FilterResultModel(0, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(1, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(2, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(3, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(4, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(5, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(6, "Chicken Biriyani", "Mintza", "170"));
        filterResultModelList.add(new FilterResultModel(7, "Chicken Biriyani", "Mintza", "170"));

    }

    @Override
    public void onClick(View v) {

    }

    OnAdapterSelectedListener<FilterResultModel> selectedListener = new OnAdapterSelectedListener<FilterResultModel>() {
        @Override
        public void onAdapterSelected(FilterResultModel model) {

        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    public static Intent newIntent(Context context) {
        return new Intent(context, FilterResultActivity.class);
    }
}
