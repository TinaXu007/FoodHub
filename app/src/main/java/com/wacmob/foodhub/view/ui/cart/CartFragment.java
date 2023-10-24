package com.wacmob.foodhub.view.ui.cart;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseFragment;
import com.wacmob.foodhub.data.models.AddressListModel;
import com.wacmob.foodhub.data.models.CartItemModel;
import com.wacmob.foodhub.databinding.FragmentCartBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.CartItemAdapterNew;
import com.wacmob.foodhub.view.listener.CartItemQuantityChangeListener;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.address_list.AddressListActivity;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.view.ui.order_placed.OrderPlacedActivity;
import com.wacmob.foodhub.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment<FragmentCartBinding, CartViewModel> implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    List<CartItemModel> cartItemModels = new ArrayList<>();
    CartItemAdapterNew cartItemAdapter;
    CartItemModel itemModel;
    private AlertDialog alertDialog;
    int no = 1;
    float totalAmount = 0;
    float deliveryCharge = 0;
    int listSize;
    int itemsQuantity = 1;
    @Inject
    ViewModelProviderFactory mVviewModelFactory;
    FragmentCartBinding dataBinding;
    CartViewModel cartViewModel;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        cartViewModel = ViewModelProviders.of(this, mVviewModelFactory).get(CartViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        dataBinding = getViewDataBinding();
        dataBinding.setCart(cartViewModel);
        dataBinding.setListener(this);

        populateData();
        //dataBinding.nestedScrollView.scrollTo(0, 0);
       /* dataBinding.recyclerViewCartItems.setNestedScrollingEnabled(false);
        dataBinding.nestedScrollView.fullScroll(View.FOCUS_UP);
        dataBinding.nestedScrollView.fullScroll(View.FOCUS_UP);*/

        //dataBinding.nestedScrollView.smoothScrollTo(0,0);

        dataBinding.nestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int scrollViewHeight = dataBinding.nestedScrollView.getHeight();
                if (scrollViewHeight > 0) {
                    dataBinding.nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

/* //nested scroll view scroll to bottom
                    final View lastView = dataBinding.nestedScrollView.getChildAt(dataBinding.nestedScrollView.getChildCount() - 1);
                    final int lastViewBottom = lastView.getBottom() + dataBinding.nestedScrollView.getPaddingBottom();
                    final int deltaScrollY = lastViewBottom - scrollViewHeight - dataBinding.nestedScrollView.getScrollY();
                    *//* If you want to see the scroll animation, call this. *//*
                    dataBinding.nestedScrollView.smoothScrollBy(0, deltaScrollY);
                    *//* If you don't want, call this. *//*
                    dataBinding.nestedScrollView.scrollBy(0, deltaScrollY);*/

                    //nested scroll view scroll to top
                    final View firstView = dataBinding.nestedScrollView.getChildAt(dataBinding.nestedScrollView.getChildCount() - 1);
                    final int lastViewTop = firstView.getTop() + dataBinding.nestedScrollView.getPaddingTop();
                    final int deltaScrollY = lastViewTop - scrollViewHeight - dataBinding.nestedScrollView.getScrollY();
                    dataBinding.nestedScrollView.smoothScrollBy(0, deltaScrollY);
                    dataBinding.nestedScrollView.scrollBy(0, deltaScrollY);
                }
            }
        });
        dataBinding.textViewRate.setVisibility(View.VISIBLE);
        dataBinding.textViewCheckout.setVisibility(View.VISIBLE);
        dataBinding.lottieAnimationView.setVisibility(View.GONE);
        dataBinding.cardViewOrderDetails.setVisibility(View.VISIBLE);
        dataBinding.cardViewAddress.setVisibility(View.VISIBLE);
        dataBinding.textViewContinueShopping.setVisibility(View.GONE);
        dataBinding.textViewNoItems.setVisibility(View.GONE);
        dataBinding.recyclerViewCartItems.setVisibility(View.VISIBLE);
        cartItemAdapter = new CartItemAdapterNew(cartItemModels, selectedListener, cartItemQuantityChangeListener);
        dataBinding.recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.recyclerViewCartItems.setHasFixedSize(true);
        dataBinding.recyclerViewCartItems.setAdapter(cartItemAdapter);

        if (getActivity() != null && getActivity() instanceof HomeActivity) {

            ((HomeActivity) getActivity()).hideBottomBar();
            ((HomeActivity) getActivity()).loadCartFragment();
        }

        refreshList();


    }

    private void refreshList() {
        totalAmount = 0;

        for (CartItemModel cartItemModel : cartItemModels) {

            totalAmount = totalAmount + (Float.valueOf(cartItemModel.getRate()) * cartItemModel.getNo());
        }
        listSize = cartItemModels.size();

        priceCalculation(listSize, totalAmount);

    }

    private void priceCalculation(int size, float Amount) {


        dataBinding.textViewPriceOfItems.setText(String.valueOf(Amount));
        deliveryCharge = Float.valueOf(dataBinding.textViewDelivery.getText().toString());
        dataBinding.textViewFinalAmount.setText(String.valueOf(deliveryCharge + totalAmount));
        dataBinding.textViewItemPrice.setText("Price (" + size + " items)");
        dataBinding.textViewRate.setText(String.valueOf(deliveryCharge + totalAmount));
    }

    CartItemQuantityChangeListener cartItemQuantityChangeListener = new CartItemQuantityChangeListener() {
        @Override
        public void increaseQuantity(CartItemModel cartItemModel) {
            totalAmount = totalAmount + (Float.valueOf(cartItemModel.getRate()));
            priceCalculation(listSize, totalAmount);


        }

        @Override
        public void decreaseQuantity(CartItemModel cartItemModel) {

            totalAmount = totalAmount - (Float.valueOf(cartItemModel.getRate()));
            priceCalculation(listSize, totalAmount);


        }
    };

    OnAdapterSelectedListener<CartItemModel> selectedListener = new OnAdapterSelectedListener<CartItemModel>() {
        @Override
        public void onAdapterSelected(CartItemModel model) {

        }

        @Override
        public void onAdapterString(String text) {


            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = CartFragment.this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.cart_item_delete_diologue, null);
            TextView cancel = dialogView.findViewById(R.id.textView_cancel);
            TextView delete = dialogView.findViewById(R.id.text_view_delete);
            dialogBuilder.setView(dialogView);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();

                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (CartItemModel cartItemModel : cartItemModels) {
                        if (cartItemModel.getId() == Integer.parseInt(text)) {
                            if (alertDialog != null && alertDialog.isShowing()) {
                                int index = cartItemModels.indexOf(cartItemModel);
                                cartItemModels.remove(cartItemModel);
                                cartItemAdapter.notifyItemRangeChanged(0, cartItemModels.size() - 1);
                                showToast(cartItemModel.getItenName() + " is removed");
                                //cartItemAdapter.notifyItemRemoved(index);
                                cartItemAdapter.notifyDataSetChanged();

                                refreshList();


                                alertDialog.dismiss();

                            }
                            break;
                        }
                    }
                    if (cartItemModels.size() == 0) {
                        // dataBinding.setEmpty(0);
                        dataBinding.textViewRate.setVisibility(View.GONE);
                        dataBinding.textViewCheckout.setVisibility(View.GONE);
                        dataBinding.lottieAnimationView.setVisibility(View.VISIBLE);
                        dataBinding.cardViewOrderDetails.setVisibility(View.GONE);
                        dataBinding.cardViewAddress.setVisibility(View.GONE);
                        dataBinding.textViewContinueShopping.setVisibility(View.VISIBLE);
                        dataBinding.textViewNoItems.setVisibility(View.VISIBLE);
                        dataBinding.recyclerViewCartItems.setVisibility(View.GONE);
                        if (getActivity() != null && getActivity() instanceof HomeActivity) {

                            ((HomeActivity) getActivity()).showBottomBar();
                        }
                    }
                }
            });
            alertDialog = dialogBuilder.create();
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            alertDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

            // lp.copyFrom(alertDialog.getWindow().getAttributes());
            lp.copyFrom(alertDialog.getWindow().getAttributes());

            alertDialog.getWindow().setAttributes(lp);


        }
    };

    private void populateData() {

        cartItemModels.add(new CartItemModel("Chicken Biriyani", "Mintzza Food Mall", "100", 0, 1));
        cartItemModels.add(new CartItemModel("Kuzhi Manthi", "Mintzza Food Mall", "200", 1, 1));
        cartItemModels.add(new CartItemModel("Fried Rice", "Mintzza Food Mall", "150", 2, 1));
        cartItemModels.add(new CartItemModel("Porotta", "Mintzza Food Mall", "400", 3, 1));
        cartItemModels.add(new CartItemModel("Alfaham", "Mintzza Food Mall", "250", 4, 1));



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public int getBindingVariable() {
        return BR.cart;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public CartViewModel getBindingObject() {
        return cartViewModel;
    }

    @Override
    public void onClick(View v) {
        if (v == dataBinding.imageViewPrevArrow) {
            if (getActivity() != null && getActivity() instanceof HomeActivity) {

                ((HomeActivity) getActivity()).onBackPressed();

            }
        } else if (v == dataBinding.textViewContinueShopping) {
            if (getActivity() != null && getActivity() instanceof HomeActivity) {

                ((HomeActivity) getActivity()).onBackPressed();
                getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            }
        } else if (v == dataBinding.textViewCheckout) {
            if (getActivity() != null) {
                startActivity(OrderPlacedActivity.newIntent(getContext()));

                getActivity().overridePendingTransition(0, 0);
            }


        } else if (v == dataBinding.textViewChangeAddress) {

            if (getActivity() != null) {

                dataBinding.textViewChangeAddress.setClickable(false);
                Intent i = AddressListActivity.newIntent(getActivity());
                startActivityForResult(i, 100);
                getActivity().overridePendingTransition(0, 0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.textViewChangeAddress.setClickable(true);
                    }
                }, 1000);
            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        dataBinding.nestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int scrollViewHeight = dataBinding.nestedScrollView.getHeight();
                if (scrollViewHeight > 0) {
                    dataBinding.nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    /*final View lastView = dataBinding.nestedScrollView.getChildAt(dataBinding.nestedScrollView.getChildCount() - 1);
                    final int lastViewBottom = lastView.getBottom() + dataBinding.nestedScrollView.getPaddingBottom();
                    final int deltaScrollY = lastViewBottom - scrollViewHeight - dataBinding.nestedScrollView.getScrollY();
                    *//* If you want to see the scroll animation, call this. *//*
                    dataBinding.nestedScrollView.smoothScrollBy(0, deltaScrollY);
                    *//* If you don't want, call this. *//*
                    dataBinding.nestedScrollView.scrollBy(0, deltaScrollY);*/
                    final View firstView = dataBinding.nestedScrollView.getChildAt(dataBinding.nestedScrollView.getChildCount() - 1);
                    final int lastViewTop = firstView.getTop() + dataBinding.nestedScrollView.getPaddingTop();
                    final int deltaScrollY = lastViewTop - scrollViewHeight - dataBinding.nestedScrollView.getScrollY();
                    dataBinding.nestedScrollView.smoothScrollBy(0, deltaScrollY);
                    dataBinding.nestedScrollView.scrollBy(0, deltaScrollY);
                }
            }
        });
        if (requestCode == 100) {

            if (data != null && data.getExtras() != null) {


                AddressListModel addressListModel = data.getParcelableExtra("AddressSelectModel");
                dataBinding.textViewName.setText(addressListModel.getName());
                dataBinding.textViewAddress.setText(addressListModel.getHouseName() + "," + addressListModel.getLandmark()
                        + "" + addressListModel.getStreet() + "," + addressListModel.getCity() + "," + addressListModel.getPincode());
                dataBinding.textViewPhoneNumber.setText(addressListModel.getPhone());

            }

            }
        }


}
