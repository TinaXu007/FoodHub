package com.wacmob.foodhub.view.ui.login;

import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.databinding.FragmentBottomsheetLoginBinding;
import com.wacmob.foodhub.view.ui.signup.SignupActivity;


public class BottomFragment extends Fragment {
    Spannable spannable;
    FragmentBottomsheetLoginBinding binding;
    String strtext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottomsheet_login, container, false);
        //View view = inflater.inflate(R.layout.fragment_bottomsheet_login, container,false);
        spannable = new SpannableString("Didn't receive the OTP? RESEND OTP");
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 23, 34, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textViewResendOtp.setText(spannable);
        binding.textViewResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Resending OTP", Toast.LENGTH_SHORT).show();
            }
        });
        binding.setListener((LoginActivity) getActivity());
        binding.textViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.editTextFirstDigit.getText().toString().length()==1&&
                        binding.editTextSecondDigit.getText().toString().length()==1&&
                        binding.editTextThirdDigit.getText().toString().length()==1&&
                        binding.editTextFourthDigit.getText().toString().length()==1){

                    startActivity(SignupActivity.newIntent(getContext()));
                    if (getActivity() != null) {

                        getActivity().finish();
                        getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    }
                }else
                    Toast.makeText(getContext(), "please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
   /*     binding.setCallback(new EditTextInterface() {
            @Override
            public void onTextChanged(View view, Editable editable) {
                // TODO Auto-generated method stub
                String text = editable.toString();
                switch (view.getId()) {

                    case R.id.edit_text_first_digit:

                        if (text.length() >= 1)
                            binding.editTextSecondDigit.requestFocus();
                        break;

                    case R.id.edit_text_second_digit:

                        if (text.length() >= 1) {

                            binding.editTextThirdDigit.requestFocus();
                        } else if (text.length() == 0) {
                            binding.editTextFirstDigit.requestFocus();
                        }
                        break;

                    case R.id.edit_text_third_digit:

                        if (text.length() >= 1)
                            binding.editTextFourthDigit.requestFocus();

                        else if (text.length() == 0)
                            binding.editTextSecondDigit.requestFocus();
                        break;

                    case R.id.edit_text_fourth_digit:

                        if (text.length() == 0)
                            binding.editTextThirdDigit.requestFocus();

                        else if (text.length() == 1) {

                            if (getActivity() != null) {
                                ((LoginActivity) getActivity()).hideKeyboard();
                            }
                            binding.textViewVerify.requestFocus();
                        }
                        break;
                }
            }
        });*/
        binding.editTextFirstDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1)
                    binding.editTextSecondDigit.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editTextSecondDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {

                    binding.editTextThirdDigit.requestFocus();

                } /*else if (s.length() == 0) {
                    binding.editTextFirstDigit.requestFocus();
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editTextThirdDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1)
                    binding.editTextFourthDigit.requestFocus();
/*
                else if (s.length() == 0)
                    binding.editTextSecondDigit.requestFocus();*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editTextFourthDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              /*  if (s.length() == 0)
                    binding.editTextThirdDigit.requestFocus();*/

                if (s.length() >= 1) {

                   /* if (getActivity() != null) {
                        ((LoginActivity) getActivity()).hideKeyboard();
                    }*/
                    binding.textViewVerify.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editTextFirstDigit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    /* do something */
                }
                return false;
            }
        });
        binding.editTextSecondDigit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {

                        if (binding.editTextSecondDigit.length() >= 1) {

                        } else {
                            binding.editTextFirstDigit.requestFocus();
                        }

                    }
                }
                return false;
            }
        });
        binding.editTextThirdDigit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {

                    if (keyCode == KeyEvent.KEYCODE_DEL) {

                        if (binding.editTextThirdDigit.length() >= 1) {

                        } else {
                            binding.editTextSecondDigit.requestFocus();
                        }

                    }
                }
                return false;
            }

        });
        binding.editTextFourthDigit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {


                    if (keyCode == KeyEvent.KEYCODE_DEL) {


                        if (binding.editTextFourthDigit.length() >= 1) {

                        } else {
                            binding.editTextThirdDigit.requestFocus();
                        }
                    }
                }
                return false;
            }
        });

        strtext = getArguments().getString("edttext");
        binding.textViewPhone.setText(strtext);
        return binding.getRoot();
    }


    public interface EditTextInterface {

        void onTextChanged(View view, Editable editable);
    }
}
