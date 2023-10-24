package com.wacmob.foodhub.view.ui.offers;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseFragment;
import com.wacmob.foodhub.data.models.OffersModel;
import com.wacmob.foodhub.databinding.FragmentOffersBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.OffersAdapter;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.viewmodel.OffersViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends BaseFragment<FragmentOffersBinding, OffersViewModel> implements OnAdapterSelectedListener<OffersModel> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentOffersBinding datainding;
    List<OffersModel> offersModels = new ArrayList<>();
    OffersAdapter offersAdapter;
    @Inject
    ViewModelProviderFactory mVviewModelFactory;
    OffersViewModel offersViewModel;


    public OffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OffersFragment newInstance(String param1, String param2) {
        OffersFragment fragment = new OffersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.offers;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_offers;
    }

    @Override
    public OffersViewModel getBindingObject() {
        return offersViewModel;
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

        offersViewModel = ViewModelProviders.of(this, mVviewModelFactory).get(OffersViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        datainding = getViewDataBinding();
        datainding.setOffers(offersViewModel);
        if (getActivity() != null && getActivity() instanceof HomeActivity) {

            ((HomeActivity) getActivity()).loadOffersFragment();
        }
        populatData();


    }

    private void populatData() {

        offersModels.add(new OffersModel("FIRST50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FIRST50"));
        offersModels.add(new OffersModel("FOODHUBPROMO offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FOODHUBPROMO"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FOODHUBPROMO offer ", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FOODHUBPROMO"));
        offersModels.add(new OffersModel("FIRST50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FIRST50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));
        offersModels.add(new OffersModel("FLAT50 offer", "20 June 2019 - 30 December 2019",
                R.drawable.special_offer, "FLAT50"));

    }


    @Override
    public void onAdapterSelected(OffersModel model) {

        if (model != null) {

            ((HomeActivity) Objects.requireNonNull(getActivity())).openOfferDetail(model);
        }


    }

    @Override
    public void onAdapterString(String text) {

    }
}
