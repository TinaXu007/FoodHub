package com.wacmob.foodhub.view.ui.hub_detail_page;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.DishItemModel;
import com.wacmob.foodhub.data.models.DishTypeModel;
import com.wacmob.foodhub.data.models.HubsListModel;
import com.wacmob.foodhub.databinding.ActivityDetailHubBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.DIshItemAdapter;
import com.wacmob.foodhub.view.adapter.DishTypeAdapter;
import com.wacmob.foodhub.view.customview.HeaderView;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.food_item_detail_page.FoodDetailActivity;
import com.wacmob.foodhub.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailHubActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {
    protected HeaderView toolbarHeaderView;
    protected AppBarLayout appBarLayout;
    protected Toolbar toolbar;
    List<DishTypeModel> dishTypeModels = new ArrayList<>();
    List<DishItemModel> dishItemModels = new ArrayList<>();
    List<DishItemModel> indianItemsList = new ArrayList<>();
    List<DishItemModel> sweetItemsList = new ArrayList<>();
    List<DishItemModel> snacksList = new ArrayList<>();
    List<DishItemModel> cooldrinksList = new ArrayList<>();
    List<DishItemModel> chineseItemsList = new ArrayList<>();
    DIshItemAdapter dishItemAdapter;
    DishTypeAdapter dishTypeAdapter;
    @Inject
    ViewModelProviderFactory mViewModelFactory;
    ActivityDetailHubBinding dataBinding;
    DetailViewModel detailViewModel;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == dataBinding.toolbarHeaderView.imageViewPrevious) {
                onBackPressed();

            } else if (v == dataBinding.toolbarHeaderView.imageViewCart) {

                openCart();
            }

        }
    };
    OnAdapterSelectedListener<DishTypeModel> onAdapterSelectedListener = new OnAdapterSelectedListener<DishTypeModel>() {
        @Override
        public void onAdapterSelected(DishTypeModel model) {
            for (DishTypeModel dishTypeModel : dishTypeModels) {
                if (dishTypeModel.equals(model)) {
                    dishTypeModel.setSelected(true);
                    if (dishTypeModel.getId() == 0) {

                        dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.GONE);
                        dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.VISIBLE);
                        dataBinding.mainContentLayout.shimmerLayout.startShimmerAnimation();
                        setRecyclerView(indianItemsList);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.VISIBLE);
                                dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.GONE);
                            }
                        }, 500);

                    }
                    if (dishTypeModel.getId() == 1) {

                        dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.GONE);
                        dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.VISIBLE);
                        dataBinding.mainContentLayout.shimmerLayout.startShimmerAnimation();
                        setRecyclerView(chineseItemsList);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.VISIBLE);
                                dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.GONE);
                            }
                        }, 500);

                    }
                    if (dishTypeModel.getId() == 2) {
                        dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.GONE);
                        dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.VISIBLE);
                        dataBinding.mainContentLayout.shimmerLayout.startShimmerAnimation();
                        setRecyclerView(sweetItemsList);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.VISIBLE);
                                dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.GONE);
                            }
                        }, 500);
                    }
                    if (dishTypeModel.getId() == 3) {

                        dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.GONE);
                        dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.VISIBLE);
                        dataBinding.mainContentLayout.shimmerLayout.startShimmerAnimation();
                        setRecyclerView(snacksList);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.VISIBLE);
                                dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.GONE);
                            }
                        }, 500);
                    }
                    if (dishTypeModel.getId() == 4) {
                        dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.GONE);
                        dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.VISIBLE);
                        dataBinding.mainContentLayout.shimmerLayout.startShimmerAnimation();
                        setRecyclerView(cooldrinksList);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dataBinding.mainContentLayout.recyclerViewDishes.setVisibility(View.VISIBLE);
                                dataBinding.mainContentLayout.shimmerLayout.setVisibility(View.GONE);
                            }
                        }, 500);

                    }

                } else {
                    dishTypeModel.setSelected(false);
                }
            }


        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    private void setRecyclerView(List<DishItemModel> dishItemModelList) {

        dishItemModels.clear();
        dishItemModels.addAll(dishItemModelList);

        dataBinding.mainContentLayout.recyclerViewDishes.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.mainContentLayout.recyclerViewDishes.setHasFixedSize(true);
        dataBinding.mainContentLayout.recyclerViewDishes.setAdapter(dishItemAdapter);
        dishItemAdapter.notifyDataSetChanged();
    }

    OnAdapterSelectedListener<DishItemModel> selectedListener = new OnAdapterSelectedListener<DishItemModel>() {
        @Override
        public void onAdapterSelected(DishItemModel model) {

            Intent i = new Intent(DetailHubActivity.this, FoodDetailActivity.class);
            i.putExtra("DishItemModel", model);
            startActivityForResult(i, 4311);
            overridePendingTransition(0, 0);

        }

        @Override
        public void onAdapterString(String text) {

        }
    };
    private boolean isHideToolbarView = false;

    @Override
    public int getBindingVariable() {
        return BR.hubDetail;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_hub;
    }

    @Override
    public Object getBindingObject() {
        return detailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailViewModel.class);
        dataBinding = (ActivityDetailHubBinding) getViewDataBinding();
        dataBinding.setListener(listener);
        dataBinding.toolbarHeaderView.setListener(listener);
        HubsListModel hubsListModel = getIntent().getParcelableExtra("HUBMODEL");
        dataBinding.toolbarHeaderView.setItem(hubsListModel);

        toolbarHeaderView = findViewById(R.id.toolbar_header_view);
        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        dataBinding.recyclerViewDishTypes.setNestedScrollingEnabled(false);

        setSupportActionBar(toolbar);
        initUi();
        populateData();
        dishItemAdapter = new DIshItemAdapter(dishItemModels, selectedListener);
        setRecyclerView(indianItemsList);

        dishTypeAdapter = new DishTypeAdapter(dishTypeModels, onAdapterSelectedListener);
        dataBinding.recyclerViewDishTypes.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        dataBinding.recyclerViewDishTypes.setHasFixedSize(true);
        dataBinding.recyclerViewDishTypes.setAdapter(dishTypeAdapter);
        dishTypeModels.get(0).setSelected(true);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4311) {
            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
                if (bundle.containsKey("GOTOCART")) {
                    int go = bundle.getInt("GOTOCART", 0);
                    if (go == 1) {
                        openCart();
                    }
                }
            }

        }
    }

    private void populateData() {

        dishTypeModels.add(new DishTypeModel("Indian", 0));
        dishTypeModels.add(new DishTypeModel("Chinese", 1));
        dishTypeModels.add(new DishTypeModel("Sweets", 2));
        dishTypeModels.add(new DishTypeModel("Snacks", 3));
        dishTypeModels.add(new DishTypeModel("Cocktails", 4));


        indianItemsList.add(new DishItemModel("Chicken Burger", "Drunken Panda", "60", R.drawable.login_image));
        indianItemsList.add(new DishItemModel("Chicken Burger", "Drunken Panda", "70", R.drawable.login_image));
        indianItemsList.add(new DishItemModel("Chicken Burger", "Drunken Panda", "80", R.drawable.login_image));
        indianItemsList.add(new DishItemModel("Chicken Burger", "Drunken Panda", "70", R.drawable.login_image));
        indianItemsList.add(new DishItemModel("Chicken Burger", "Drunken Panda", "70", R.drawable.login_image));

        chineseItemsList.add(new DishItemModel("Kung Pao Chicken", "Drunken Panda", "100", R.drawable.dp));
        chineseItemsList.add(new DishItemModel("Wontons", "Drunken Panda", "120", R.drawable.login_image));
        chineseItemsList.add(new DishItemModel("Dumplings", "Drunken Panda", "70", R.drawable.dp));
        chineseItemsList.add(new DishItemModel("Chow Mein", "Drunken Panda", "70", R.drawable.mintzza));
        chineseItemsList.add(new DishItemModel("Spring Rolls", "Drunken Panda", "70", R.drawable.login_image));


        sweetItemsList.add(new DishItemModel("PATISA", "Drunken Panda", "40", R.drawable.dp));
        sweetItemsList.add(new DishItemModel("MATHURA PEDA", "Drunken Panda", "90", R.drawable.login_image));
        sweetItemsList.add(new DishItemModel("CHAINA ANGOOR", "Drunken Panda", "70", R.drawable.dp));
        sweetItemsList.add(new DishItemModel("MEWA BITE", "Drunken Panda", "70", R.drawable.mintzza));
        sweetItemsList.add(new DishItemModel("RAJBHOG", "Drunken Panda", "70", R.drawable.login_image));


        snacksList.add(new DishItemModel("Bitterballen", "Drunken Panda", "80", R.drawable.dp));
        snacksList.add(new DishItemModel("Bonda", "Drunken Panda", "100", R.drawable.login_image));
        snacksList.add(new DishItemModel("Croquette", "Drunken Panda", "70", R.drawable.dp));
        snacksList.add(new DishItemModel("Doughnutn", "Drunken Panda", "70", R.drawable.mintzza));
        snacksList.add(new DishItemModel("Pancakes", "Drunken Panda", "70", R.drawable.login_image));


        cooldrinksList.add(new DishItemModel("Apple Margarita", "Drunken Panda", "50", R.drawable.dp));
        cooldrinksList.add(new DishItemModel("Aperol Spritz", "Drunken Panda", "70", R.drawable.login_image));
        cooldrinksList.add(new DishItemModel("Alabama Slammer ", "Drunken Panda", "80", R.drawable.dp));
        cooldrinksList.add(new DishItemModel("Adult Hot Chocolate", "Drunken Panda", "90", R.drawable.mintzza));
        cooldrinksList.add(new DishItemModel("Anejo Highball", "Drunken Panda", "70", R.drawable.login_image));

    }

    private void initUi() {
        dataBinding.appbar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {

            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

            dataBinding.imageViewLocation.setVisibility(View.GONE);

            dataBinding.imageViewStarWhite.setVisibility(View.GONE);
            dataBinding.wacTextViewNoOfStars.setVisibility(View.GONE);
            dataBinding.wacTextViewStars.setVisibility(View.GONE);

            dataBinding.imageViewClock.setVisibility(View.GONE);
            dataBinding.wacTextViewTime.setVisibility(View.GONE);
            dataBinding.wacTextViewDeliveryTime.setVisibility(View.GONE);

            dataBinding.imageViewLocToLoc.setVisibility(View.GONE);
            dataBinding.wacTextViewDistance.setVisibility(View.GONE);
            dataBinding.wacTextViewKm.setVisibility(View.GONE);

        } else if (percentage < 1f && !isHideToolbarView) {

            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

            dataBinding.imageViewLocation.setVisibility(View.VISIBLE);

            dataBinding.imageViewStarWhite.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewNoOfStars.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewStars.setVisibility(View.VISIBLE);

            dataBinding.imageViewClock.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewTime.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewDeliveryTime.setVisibility(View.VISIBLE);

            dataBinding.imageViewLocToLoc.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewDistance.setVisibility(View.VISIBLE);
            dataBinding.wacTextViewKm.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        DetailHubActivity.this.finish();
        overridePendingTransition(0, 0);

    }

    public void openCart() {

        Intent i = new Intent();
        i.putExtra("GOTOCART", 1);
        setResult(RESULT_OK, i);
        onBackPressed();

    }

}
