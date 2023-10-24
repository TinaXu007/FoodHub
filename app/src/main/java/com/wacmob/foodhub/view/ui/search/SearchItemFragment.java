package com.wacmob.foodhub.view.ui.search;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.wacmob.foodhub.BR;
import com.wacmob.foodhub.R;
import com.wacmob.foodhub.base.BaseFragment;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.data.models.SearchResponseModel;
import com.wacmob.foodhub.databinding.FragmentSearchItemBinding;
import com.wacmob.foodhub.factory.ViewModelProviderFactory;
import com.wacmob.foodhub.view.adapter.SearchAdapterNew;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;
import com.wacmob.foodhub.view.ui.filter.FilterActivity;
import com.wacmob.foodhub.view.ui.home_page.HomeActivity;
import com.wacmob.foodhub.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchItemFragment extends BaseFragment<FragmentSearchItemBinding, SearchViewModel> implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentSearchItemBinding dataBinding;
    List<SearchItemListModel> recentItemModelList = new ArrayList<>();
    ArrayList<SearchItemListModel> itemList = new ArrayList<>();
    SearchAdapterNew searchAdapterNew;
    SearchViewModel searchViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    public SearchItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchItemFragment newInstance(String param1, String param2) {
        SearchItemFragment fragment = new SearchItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.search;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_item;
    }

    @Override
    public Object getBindingObject() {
        return searchViewModel;
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

        searchViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SearchViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        dataBinding = getViewDataBinding();
        dataBinding.setSearch(searchViewModel);
        dataBinding.setListener(this::onClick);

        if (getActivity() != null && getActivity() instanceof HomeActivity) {

            ((HomeActivity) getActivity()).loadSearchFragment();
        }
        dataBinding.editTextSearch.requestFocus();
        dataBinding.editTextSearch.performClick();
        // dataBinding.recyclerViewSearchItems.setNestedScrollingEnabled(false);
        itemList.clear();
        searchViewModel.fetchSearchItemList();

        searchAdapterNew = new SearchAdapterNew(itemList, selectedListener);
        dataBinding.recyclerViewSearchItems.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        dataBinding.recyclerViewSearchItems.setHasFixedSize(true);
        dataBinding.recyclerViewSearchItems.setAdapter(searchAdapterNew);


        Bundle arguments = getArguments();
        if (arguments != null) {

            String select = arguments.getString("SET", "");
            if (select.equals("0")) {
                dataBinding.setSelection(0);

            } else if (select.equals("1")) {
                dataBinding.setSelection(1);
            }
        }

        dataBinding.recyclerViewSearchItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hideKeyboard();
            }
        });


        searchViewModel.getMutableLiveData().observe(this, new Observer<SearchResponseModel>() {
            @Override
            public void onChanged(@Nullable SearchResponseModel searchResponseModel) {

                itemList.clear();
                searchAdapterNew.notifyDataSetChanged();
                if (searchResponseModel != null && searchResponseModel.getData().size() > 0) {
                    for (SearchItemListModel model : searchResponseModel.getData()) {
                        model.setItemType(1);
                    }
                    itemList.addAll(searchResponseModel.getData());
                } else {
                    Toast.makeText(getContext(), "no response", Toast.LENGTH_SHORT).show();
                }

                searchAdapterNew.notifyDataSetChanged();
                searchAdapterNew.setFilter(dataBinding.editTextSearch.getText().toString());

            }

        });

        searchViewModel.observeSearchItemLiveData().observe(getViewLifecycleOwner() != null ? getViewLifecycleOwner() : this, searchItemListModels -> {

            if (searchItemListModels != null && !searchItemListModels.isEmpty()) {
                recentItemModelList = searchItemListModels;
                for (SearchItemListModel model : recentItemModelList) {
                    model.setItemType(0);
                }

                itemList.addAll(recentItemModelList);
                searchAdapterNew.notifyDataSetChanged();
                // searchViewModel.apiCall("");
            }
        });

        dataBinding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    dataBinding.textViewRecentSearch.setVisibility(View.VISIBLE);
                    itemList.clear();
                    searchViewModel.fetchSearchItemList();

                } else if (s.length() > 0) {
                    itemList.clear();
                    searchViewModel.apiCall(s.toString());
                    dataBinding.textViewRecentSearch.setVisibility(View.GONE);



                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    OnAdapterSelectedListener<SearchItemListModel> selectedListener = new OnAdapterSelectedListener<SearchItemListModel>() {
        @Override
        public void onAdapterSelected(SearchItemListModel model) {

            searchViewModel.insertProductLocal(model);
            if (getActivity() != null) {

                ((HomeActivity) (getActivity())).openSearchFoodDetail(model);
            }

        }

        @Override
        public void onAdapterString(String text) {

        }
    };

    @Override
    public void onClick(View v) {

        if (v == dataBinding.textViewFoods) {
            dataBinding.setSelection(1);

        } else if (v == dataBinding.textViewHubs) {
            dataBinding.setSelection(0);

        } else if (v == dataBinding.imageViewFilter) {

            if (getActivity() != null) {
                dataBinding.imageViewFilter.setClickable(false);
                Intent i = FilterActivity.newIntent(getActivity());
                startActivityForResult(i, 102);
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.imageViewFilter.setClickable(true);
                    }
                }, 1000);
            }


        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            if (data != null && data.getExtras() != null) {
                Bundle bundle = data.getExtras();
            }
        }
    }
}
