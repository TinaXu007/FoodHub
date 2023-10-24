package com.wacmob.foodhub.view.ui.change_address;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseActivity;
import com.wacmob.foodhub.data.models.AddressListModel;
import com.wacmob.foodhub.databinding.ActivityChangeAddressBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.viewmodel.ChangeAddressViewModel;

import javax.inject.Inject;

public class ChangeAddressActivity extends BaseActivity<ActivityChangeAddressBinding,
        ChangeAddressViewModel> implements View.OnClickListener {

    ActivityChangeAddressBinding databinding;
    ChangeAddressViewModel changeAddressViewModel;


    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.changeAddress;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_address;
    }

    @Override
    public ChangeAddressViewModel getBindingObject() {
        return changeAddressViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeAddressViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ChangeAddressViewModel.class);
        databinding = getViewDataBinding();
        databinding.setListener(this::onClick);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                if (intent.getExtras().containsKey("ADDNEW")) {

                    databinding.textViewChangeAddress.setText("Add New Address");
                }
            }

        }
        AddressListModel addressListModel = getIntent().getParcelableExtra("AddressListModel");

        if (addressListModel != null) {

            databinding.textViewChangeAddress.setText("Edit Address");

            databinding.editTextFullName.setText(addressListModel.getName());
            databinding.editTextFullName.setSelection(databinding.editTextFullName.getText().length());

            databinding.editTextHouseName.setText(addressListModel.getHouseName());
            databinding.editTextHouseName.setSelection(databinding.editTextHouseName.getText().length());

            databinding.editTextStreet.setText(addressListModel.getStreet());
            databinding.editTextStreet.setSelection(databinding.editTextStreet.getText().length());

            databinding.editTextCity.setText(addressListModel.getCity());
            databinding.editTextCity.setSelection(databinding.editTextCity.getText().length());

            databinding.editTextLandmark.setText(addressListModel.getLandmark());
            databinding.editTextLandmark.setSelection(databinding.editTextLandmark.getText().length());

            databinding.editTextPinCode.setText(addressListModel.getPincode());
            databinding.editTextPinCode.setSelection(databinding.editTextPinCode.getText().length());

            databinding.editTextPhoneNumber.setText(addressListModel.getPhone());
            databinding.editTextPhoneNumber.setSelection(databinding.editTextPhoneNumber.getText().length());

        }

    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        if (v == databinding.imageViewNextArrow) {
            String name = databinding.editTextFullName.getText().toString();
            String houseName = databinding.editTextHouseName.getText().toString();
            String streetName = databinding.editTextStreet.getText().toString();
            String cityName = databinding.editTextCity.getText().toString();
            String pinCode = databinding.editTextPinCode.getText().toString();
            String phoneNumber = databinding.editTextPhoneNumber.getText().toString();
            String landMark = databinding.editTextLandmark.getText().toString();
            int l = phoneNumber.length();
            if (!houseName.isEmpty() && !streetName.isEmpty() && !cityName.isEmpty() && !pinCode.isEmpty() &&
                    !phoneNumber.isEmpty() && !landMark.isEmpty()) {

                if (l <= 8 || l >= 14) {
                    showToast("Enter a valid phone number");
                } else {

                    Intent i = new Intent();
                    i.putExtra("NAME", name);
                    i.putExtra("HOUSE_NAME", houseName);
                    i.putExtra("STREET_NAME", streetName);
                    i.putExtra("CITY_NAME", cityName);
                    i.putExtra("PIN_CODE", pinCode);
                    i.putExtra("PHONE_NUMBER", phoneNumber);
                    i.putExtra("LAND_MARK", landMark);
                    setResult(RESULT_OK, i);
                    onBackPressed();
                }

            } else {
                showToast("please fill all the fields");
            }


        } else if (v == databinding.imageViewPrevArrow) {
            onBackPressed();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ChangeAddressActivity.class);
    }

    @Override
    public void onBackPressed() {

        ChangeAddressActivity.this.finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
}
