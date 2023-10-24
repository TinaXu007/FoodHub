package com.wacmob.foodhub.view.ui.filter;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.View;

import com.appyvet.materialrangebar.RangeBar;
import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.DishesFilterModel;
import com.wacmob.foodhub.databinding.ActivityFilterBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.utils.AppLogger;
import com.wacmob.foodhub.view.adapter.DishFilterAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.viewmodel.FilterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FilterActivity extends BaseActivity<ActivityFilterBinding, FilterViewModel> implements View.OnClickListener {

    ActivityFilterBinding dataBinding;
    FilterViewModel filterViewModel;
    List<DishesFilterModel> dishItemsList = new ArrayList<>();
    DishFilterAdapter dishFilterAdapter;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.filter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public FilterViewModel getBindingObject() {
        return filterViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FilterViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this);

        populateData();
        dataBinding.setRatingSelection(1);
        dishFilterAdapter = new DishFilterAdapter(dishItemsList, selectedListener);
        dataBinding.recyclerViewDishFilterItems.setLayoutManager(new GridLayoutManager(this, 3));
        dataBinding.recyclerViewDishFilterItems.setHasFixedSize(true);
        dataBinding.recyclerViewDishFilterItems.setAdapter(dishFilterAdapter);

        dataBinding.rangeBarPrice.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                dataBinding.textViewMin.setText(String.valueOf(leftPinValue));
                dataBinding.textViewMax.setText(String.valueOf(rightPinValue));

            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });


    }


    OnAdapterSelectedListener<DishesFilterModel> selectedListener = new OnAdapterSelectedListener<DishesFilterModel>() {
        @Override
        public void onAdapterSelected(DishesFilterModel model) {

        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    private void populateData() {

        dishItemsList.add(new DishesFilterModel("Indian", 0));
        dishItemsList.add(new DishesFilterModel("Chinese", 1));
        dishItemsList.add(new DishesFilterModel("Italian", 2));
        dishItemsList.add(new DishesFilterModel("Sweets", 3));
        dishItemsList.add(new DishesFilterModel("Deserts", 4));
    }

    @Override
    public void onClick(View v) {
        AppLogger.d("onclick");
        if (v == dataBinding.imageViewPrevArrow) {
            onBackPressed();

        } else if (v == dataBinding.textViewRateOne) {
            dataBinding.setRatingSelection(1);

        } else if (v == dataBinding.textViewRateTwo) {
            dataBinding.setRatingSelection(2);

        } else if (v == dataBinding.textViewRateThree) {
            dataBinding.setRatingSelection(3);

        } else if (v == dataBinding.textViewRateFour) {
            dataBinding.setRatingSelection(4);

        } else if (v == dataBinding.textViewRateFive) {
            dataBinding.setRatingSelection(5);

        } else if (v == dataBinding.textViewApply) {

            Intent i = new Intent();
            setResult(RESULT_OK, i);
            onBackPressed();

        } else if (v == dataBinding.textViewResetAll) {

            dataBinding.checkBoxBreakFast.setChecked(false);
            dataBinding.checkBoxLunch.setChecked(false);
            dataBinding.checkBoxDinner.setChecked(false);
            dataBinding.setRatingSelection(1);
            dataBinding.recyclerViewDishFilterItems.setAdapter(dishFilterAdapter);
            dataBinding.rangeBarPrice.setRangePinsByValue(0, 500);


        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FilterActivity.class);
    }

    @Override
    public void onBackPressed() {
        FilterActivity.this.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
}

