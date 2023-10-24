package com.wacmob.foodhub.view.ui.food_item_detail_page;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.DishItemModel;
import com.wacmob.foodhub.data.models.OfferListModelNew;
import com.wacmob.foodhub.data.models.PopularListModelNew;
import com.wacmob.foodhub.data.models.RelatedFoodModel;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.databinding.ActivityFoodDetailBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.RelatedFoodAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.viewmodel.FoodDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FoodDetailActivity extends BaseActivity implements View.OnClickListener {
    boolean selected = false;
    int no = 1;
    String name, rate;
    RelatedFoodAdapter relatedFoodAdapter;
    List<RelatedFoodModel> relatedFoodModels = new ArrayList<>();
    @Inject
    FoodDetailViewModel foodDetailViewModel;
    ActivityFoodDetailBinding dataBinding;
    ViewModelProviderFactory mViewModelFactory;


    @Override
    public int getBindingVariable() {
        return BR.foodDetail;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_food_detail;
    }

    @Override
    public Object getBindingObject() {
        return foodDetailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(FoodDetailViewModel.class);
        dataBinding = (ActivityFoodDetailBinding) getViewDataBinding();
        dataBinding.setListener(this);
        dataBinding.textViewActualPrice.setPaintFlags(dataBinding.textViewActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        populateData();

        dataBinding.constraintLayoutFoodDetail.setVisibility(View.VISIBLE);
        dataBinding.shimmerLayout.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                if (intent.getExtras().containsKey("ITEM_NAME") && intent.getExtras().containsKey("ITEM_RATE")) {
                    name = intent.getExtras().getString("ITEM_NAME");
                    rate = intent.getExtras().getString("ITEM_RATE");
                    dataBinding.textViewFoodName.setText(name);
                    dataBinding.textViewActualPrice.setText(rate);
                    findOfferPrice(Float.valueOf(rate));
                }
            }


        }
        PopularListModelNew mostPopularItemModel = getIntent().getParcelableExtra("FOODMODEL");
        OfferListModelNew offerListModel = getIntent().getParcelableExtra("OFFERMODEL");
        SearchItemListModel searchItemListModel = getIntent().getParcelableExtra("SEARCHITEMMODEL");
        DishItemModel dishItemModel = getIntent().getParcelableExtra("DishItemModel");
        RelatedFoodModel relatedFoodModel = getIntent().getParcelableExtra("RelatedFoodModel");


        if (mostPopularItemModel != null) {


            findOfferPrice(Float.valueOf(mostPopularItemModel.getItemRate()));
            dataBinding.textViewFoodName.setText(mostPopularItemModel.getProductTitle());
            dataBinding.textViewHotelName.setText(mostPopularItemModel.getHotelName());
            dataBinding.textViewActualPrice.setText(mostPopularItemModel.getItemRate());

        }

        if (offerListModel != null) {

            dataBinding.textViewFoodName.setText(offerListModel.getProductTitle());
            dataBinding.textViewActualPrice.setText("60");
            findOfferPrice(60);

        }
        if (searchItemListModel != null) {
            dataBinding.textViewFoodName.setText(searchItemListModel.getProductTitle());
            dataBinding.textViewHotelName.setText(searchItemListModel.getHotelName());
            findOfferPrice(Float.valueOf(searchItemListModel.getProductRate()));
            dataBinding.textViewActualPrice.setText(searchItemListModel.getProductRate());

        }

        if (dishItemModel != null) {

            dataBinding.textViewFoodName.setText(dishItemModel.getDishNme());
            dataBinding.textViewHotelName.setText(dishItemModel.getHubName());
            findOfferPrice(Float.valueOf(dishItemModel.getRate()));
            dataBinding.textViewActualPrice.setText(dishItemModel.getRate());
        }

        if (relatedFoodModel != null) {

        }


        relatedFoodAdapter = new RelatedFoodAdapter(relatedFoodModels, relatedFoodSelectedListener);
        dataBinding.recyclerViewRelatedFoods.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        dataBinding.recyclerViewRelatedFoods.setHasFixedSize(true);
        dataBinding.recyclerViewRelatedFoods.setAdapter(relatedFoodAdapter);

        dataBinding.textQuantity.setText("1");
    }

    private void findOfferPrice(float price) {


        float offer = ((price * 10) / 100);
        float discountRate = price - offer;
        dataBinding.textViewDiscountPrice.setText(String.valueOf(discountRate));

    }

    OnAdapterSelectedListener<RelatedFoodModel> relatedFoodSelectedListener = new OnAdapterSelectedListener<RelatedFoodModel>() {
        @Override
        public void onAdapterSelected(RelatedFoodModel model) {

            dataBinding.constraintLayoutFoodDetail.setVisibility(View.GONE);
            dataBinding.shimmerLayout.setVisibility(View.VISIBLE);
            dataBinding.shimmerLayout.startShimmerAnimation();
            dataBinding.textViewFoodName.setText(model.getName());
            dataBinding.textViewActualPrice.setText(model.getRate());
            findOfferPrice(Float.valueOf(model.getRate()));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dataBinding.shimmerLayout.stopShimmerAnimation();
                    dataBinding.shimmerLayout.setVisibility(View.GONE);
                    dataBinding.constraintLayoutFoodDetail.setVisibility(View.VISIBLE);
                }
            }, 1000);






        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    private void populateData() {

        relatedFoodModels.add(new RelatedFoodModel("200", "Kuzhi Manthi", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("130", "Fried Rice", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("150", "Chicken Kabab", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("210", "Alfaham", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("220", "Alfaham Manthi", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("180", "Beef Biriyani", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("50", "Porotta", R.drawable.login_image));
        relatedFoodModels.add(new RelatedFoodModel("70", "Meals", R.drawable.login_image));

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FoodDetailActivity.class);
    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.textViewAddToCart) {
            if (dataBinding.textQuantity.getText().toString().equals("0")) {
                showToast("Add at least one Item");
            } else {
                showToast(dataBinding.textViewFoodName.getText().toString() + " added to cart");
            }
        } else if (v == dataBinding.textViewOrderNow) {

            openCart();

        } else if (v == dataBinding.textAdd) {
            no++;
            dataBinding.textQuantity.setText("" + no);

        } else if (v == dataBinding.textSubtract) {
            if (no > 1) {
                no--;
                dataBinding.textQuantity.setText("" + no);
            }


        } else if (v == dataBinding.imageViewPrevArrow) {
            onBackPressed();

        } else if (v == dataBinding.imageViewCartDetail) {
            openCart();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        FoodDetailActivity.this.finish();
        overridePendingTransition(0, 0);
    }


    private void openCart() {

        Intent i = new Intent();
        i.putExtra("GOTOCART", 1);
        setResult(RESULT_OK, i);
        onBackPressed();
    }
}

