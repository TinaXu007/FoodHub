package com.wacmob.foodhub.view.ui.profile;

import androidx.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.BuildConfig;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseFragment;
import com.wacmob.foodhub.data.models.ProfileModel;
import com.wacmob.foodhub.databinding.FragmentProfileBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.ui.about_us.AboutUsActivity;
import com.wacmob.foodhub.view.ui.change_location.ChangeLocationActivity;
import com.wacmob.foodhub.view.ui.edit_profile.EditProfileActivity;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.view.ui.login.LoginActivity;
import com.wacmob.foodhub.view.ui.myorders.MyOrdersActivity;
import com.wacmob.foodhub.viewmodel.ProfileViewModel;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileModel> implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    FragmentProfileBinding dataBinding;
    ProfileModel profileModel;
    private AlertDialog alertDialog;


    @Inject
    ViewModelProviderFactory mVviewModelFactory;
    ProfileViewModel profileViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        profileViewModel = ViewModelProviders.of(this, mVviewModelFactory).get(ProfileViewModel.class);
        profileModel = new ProfileModel("title", "desc");
        super.onViewCreated(view, savedInstanceState);
        dataBinding = (FragmentProfileBinding) getViewDataBinding();
        dataBinding.setProfile(profileModel);
        dataBinding.setListener(this::onClick);
        handleProgressBar();
        profileViewModel.getUserProfile().observe(getViewLifecycleOwner() != null ? getViewLifecycleOwner() : this,
                userProfileBean -> {
                    profileModel.setName(userProfileBean.getMessage());
                    profileModel.setUserType(userProfileBean.getData().getUserType());
                });
        hideKeyboard();
        if (getActivity() != null && getActivity() instanceof HomeActivity) {

            //((HomeActivity) getActivity()).hideBottomBar();
            ((HomeActivity) getActivity()).loadprofileFragment();
        }


    }


    private void handleProgressBar() {

        profileViewModel.getIsLoading().observe(getViewLifecycleOwner() != null ? getViewLifecycleOwner() : this,
                progressModel -> {
                    if (progressModel != null && !progressModel.isHasInternet()) {
                        showToast("No internet connection");
                    }
                    if (progressModel != null && progressModel.getErrorMessage() != null) {
                        showToast(progressModel.getErrorMessage());
                    }
                });
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
        return BR.sample;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public ProfileModel getBindingObject() {
        return profileModel;
    }


    @Override
    public void onClick(View v) {
        if (v == dataBinding.imageViewEdit) {

            if (getActivity() != null) {
                dataBinding.imageViewEdit.setClickable(false);
                Intent i = EditProfileActivity.newIntent(getActivity());
                startActivityForResult(i, 1997);
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.imageViewEdit.setClickable(true);
                    }
                }, 1000);
            }

        } else if (v == dataBinding.imageViewChangeLocation) {

            if (getActivity() != null) {
                dataBinding.imageViewChangeLocation.setClickable(false);
                startActivity(ChangeLocationActivity.newIntent(getActivity()));
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.imageViewChangeLocation.setClickable(true);
                    }
                }, 1000);
            }

        } else if (v == dataBinding.imageViewOrders) {
//            if (getActivity() != null) {
//                dataBinding.imageViewOrders.setClickable(false);
//                startActivity(MyOrdersActivity.newIntent(getActivity()));
//                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dataBinding.imageViewOrders.setClickable(true);
//                    }
//                }, 1000);
//            }

        } else if (v == dataBinding.textViewRateApp) {

            dataBinding.textViewRateApp.setClickable(false);
            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dataBinding.textViewRateApp.setClickable(true);
                }
            }, 1000);

        } else if (v == dataBinding.textViewAbout) {

            startActivity(AboutUsActivity.newIntent(getActivity()));

        } else if (v == dataBinding.textViewShareApp) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }

        } else if (v == dataBinding.textViewLogout) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = ProfileFragment.this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.logout_diologue, null);
            ImageView logout = dialogView.findViewById(R.id.image_view_logout);
            logout.setColorFilter(Color.parseColor("#DB1818"));
            TextView no = dialogView.findViewById(R.id.textView_no);
            TextView yes = dialogView.findViewById(R.id.text_view_yes);
            dialogBuilder.setView(dialogView);

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();

                    }
                }
            });
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null) {
                        startActivity(LoginActivity.newIntent(getActivity()));
                        getActivity().finish();

                        alertDialog.dismiss();
                    }

                }
            });

            alertDialog = dialogBuilder.create();
            if (alertDialog.getWindow() != null) {

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            alertDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(alertDialog.getWindow().getAttributes());
            alertDialog.getWindow().setAttributes(lp);

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1997) {
            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
                Bitmap image = bundle.getParcelable("photo");
                if (image != null) {

                    dataBinding.circleImageView.setImageBitmap(image);
                }
            }
        }
    }
}
