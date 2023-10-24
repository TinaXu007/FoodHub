package com.wacmob.foodhub.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

/**
 * Created by KP on 12/27/2018.
 */

public abstract class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.item, obj);
            binding.setVariable(BR.isLast, false);
            binding.setVariable(BR.listener, getAdapterClickListener());
            binding.executePendingBindings();
        }

        public void bindLast(Object obj) {
            binding.setVariable(BR.item, obj);
            binding.setVariable(BR.isLast, true);
            binding.setVariable(BR.listener, getAdapterClickListener());
            binding.executePendingBindings();
        }

     /*   public void bindFirst(Object obj) {
            binding.setVariable(BR.item, obj);
            binding.setVariable(BR.isFirst, true);
            binding.setVariable(BR.listener, getAdapterClickListener());
            binding.executePendingBindings();
        }*/
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position == getItemCount() - 1) {
            holder.bindLast(getDataAtPosition(position));
        } /*else if (position == 0) {
            holder.bindFirst(getDataAtPosition(position));
        }*/ else {
            holder.bind(getDataAtPosition(position));
        }
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);

    public abstract OnAdapterSelectedListener getAdapterClickListener();
}
