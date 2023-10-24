package com.wacmob.foodhub.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.OfferListModelNew;
import com.wacmob.foodhub.databinding.RecyclerViewOfferSingleItemBinding;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.ViewHolder> {
    private List<OfferListModelNew> offerListModels;
    private OnAdapterSelectedListener listener;

    public OfferListAdapter(List<OfferListModelNew> offerListModels, OnAdapterSelectedListener listener) {
        this.offerListModels = offerListModels;
        this.listener = listener;
    }


    @NonNull
    @Override
    public OfferListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerViewOfferSingleItemBinding dataBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.recycler_view_offer_single_item, parent, false);

        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferListAdapter.ViewHolder holder, int position) {

        if (position == offerListModels.size() - 1) {
            holder.binding.viewEnd.setVisibility(View.VISIBLE);
        } else {
            holder.binding.viewEnd.setVisibility(View.GONE);

        }
        holder.binding.setItem(offerListModels.get(position));
        holder.binding.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return offerListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerViewOfferSingleItemBinding binding;

        public ViewHolder(RecyclerViewOfferSingleItemBinding dataBinding) {
            super(dataBinding.getRoot());
            binding = dataBinding;
        }
    }
}
