package com.wacmob.foodhub.view.ui.address_list;

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
import com.wacmob.foodhub.data.models.AddressListModel;
import com.wacmob.foodhub.databinding.ActivityAddressListBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.AddressListAdapter;
import com.wacmob.foodhub.view.listener.AddressSelectListener;
import com.wacmob.foodhub.view.ui.change_address.ChangeAddressActivity;
import com.wacmob.foodhub.viewmodel.AddressListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddressListActivity extends BaseActivity<ActivityAddressListBinding, AddressListViewModel> implements View.OnClickListener {

    AddressListAdapter addresslistAdapter;
    List<AddressListModel> addressListModel = new ArrayList<>();

    ActivityAddressListBinding dataBinding;
    AddressListViewModel addressListViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.addressList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    public AddressListViewModel getBindingObject() {
        return addressListViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addressListViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AddressListViewModel.class);
        dataBinding = getViewDataBinding();
        dataBinding.setListener(this::onClick);
        populateData();

        addresslistAdapter = new AddressListAdapter(addressListModel, selectedListener);
        dataBinding.recyclerViewAddressList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.recyclerViewAddressList.setHasFixedSize(true);
        dataBinding.recyclerViewAddressList.setAdapter(addresslistAdapter);


    }

    AddressSelectListener selectedListener = new AddressSelectListener() {

        @Override
        public void AddressSelect(AddressListModel model, String text) {

            if (text.equals("select")) {

                Intent i = new Intent();
                i.putExtra("AddressSelectModel", model);
                setResult(RESULT_OK, i);
                onBackPressed();
                overridePendingTransition(0, 0);

            }

        }

        @Override
        public void AddressEdit(AddressListModel model, String text) {

            if (text.equals("edit")) {

                Intent i = new Intent(AddressListActivity.this, ChangeAddressActivity.class);
                i.putExtra("AddressListModel", model);
                startActivityForResult(i, 104);
                overridePendingTransition(0, 0);

            }

        }

        @Override
        public void AddressSelectModel(AddressListModel model) {

        }
    };

    private void populateData() {

        addressListModel.add(new AddressListModel("steve", "webandcrafts indeevaram",
                "korratty-nalukkett road", "Koratty", "infopark", "680308", "9656757030"));
        addressListModel.add(new AddressListModel("edwin", "webandcrafts indeevaram",
                "korratty-nalukkett road", "Koratty", "infopark", "680308", "9656757030"));
      /*  addressListModel.add(new AddressListModel("Antony Leons", "webandcrafts indeevaram",
                "korratty-nalukkett road", "Koratty", "infopark", "680308", "9656757030"));*/
    }

    @Override
    public void onClick(View v) {

        if (v == dataBinding.imageViewAddNewAddress) {

            dataBinding.imageViewAddNewAddress.setClickable(false);
            Intent i = new Intent(AddressListActivity.this, ChangeAddressActivity.class);
            i.putExtra("ADDNEW", 1);
            startActivityForResult(i, 104);
            overridePendingTransition(0, 0);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dataBinding.imageViewAddNewAddress.setClickable(true);
                }
            }, 1000);

        } else if (v == dataBinding.imageViewPrevArrow) {

          onBackPressed();
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddressListActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 104) {

            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("NAME");
                String houseName = bundle.getString("HOUSE_NAME");
                String streetName = bundle.getString("STREET_NAME");
                String cityName = bundle.getString("CITY_NAME");
                String pinCode = bundle.getString("PIN_CODE");
                String phoneNumber = bundle.getString("PHONE_NUMBER");
                String landMark = bundle.getString("LAND_MARK");
                if (houseName != null && landMark != null && streetName != null && cityName != null && pinCode != null && phoneNumber != null && name != null) {

                    /*addressListModel.add(new AddressListModel(name, houseName, streetName, cityName, landMark, pinCode, phoneNumber));
                    addresslistAdapter.notifyDataSetChanged();*/
                }
            }
        }
    }

    @Override
    public void onBackPressed() {

        AddressListActivity.this.finish();
        overridePendingTransition(0, 0);
    }
}
