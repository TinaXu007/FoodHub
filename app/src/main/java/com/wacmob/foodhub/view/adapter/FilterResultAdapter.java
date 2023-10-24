package com.wacmob.foodhub.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.FilterResultModel;
import com.wacmob.foodhub.databinding.RecyclerViewFilterResultSingleItemBinding;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class FilterResultAdapter extends RecyclerView.Adapter<FilterResultAdapter.ViewHolder> {

    List<FilterResultModel> filterResultModels;
    OnAdapterSelectedListener listener;
    int no = 1;

    public FilterResultAdapter(List<FilterResultModel> filterResultModels, OnAdapterSelectedListener listener) {
        this.filterResultModels = filterResultModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilterResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerViewFilterResultSingleItemBinding dataBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.recycler_view_filter_result_single_item, parent, false);

        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterResultAdapter.ViewHolder holder, int position) {
        holder.binding.textQuantity.setText("1");
        holder.binding.setListener(listener);
        holder.binding.setItem(filterResultModels.get(position));


        holder.binding.textAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no++;
                holder.binding.textQuantity.setText("" + no);
            }
        });
        holder.binding.textSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (no > 1) {
                    no--;
                    holder.binding.textQuantity.setText("" + no);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return filterResultModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewFilterResultSingleItemBinding binding;

        public ViewHolder(RecyclerViewFilterResultSingleItemBinding dataBinding) {
            super(dataBinding.getRoot());
            binding = dataBinding;
        }
    }
}
