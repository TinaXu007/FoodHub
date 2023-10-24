package com.wacmob.foodhub.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.CartItemModel;
import com.wacmob.foodhub.databinding.RecyclerViewCartSingleItemBinding;
import com.wacmob.foodhub.view.listener.CartItemQuantityChangeListener;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.List;

public class CartItemAdapterNew extends RecyclerView.Adapter<CartItemAdapterNew.ViewHolder> {
    List<CartItemModel> cartItemModels;
    OnAdapterSelectedListener listener;
    CartItemQuantityChangeListener quantityChangeListener;
    int no=1;

    public CartItemAdapterNew(List<CartItemModel> cartItemModels, OnAdapterSelectedListener listener,
                              CartItemQuantityChangeListener quantityChangeListener) {
        this.cartItemModels = cartItemModels;
        this.listener = listener;
        this.quantityChangeListener = quantityChangeListener;
    }

    @NonNull
    @Override
    public CartItemAdapterNew.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerViewCartSingleItemBinding dataBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.recycler_view_cart_single_item, parent, false);

        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapterNew.ViewHolder holder, int position) {

        //holder.binding.textQuantity.setText(String.valueOf(no));
        holder.binding.setListener(listener);
        holder.binding.setItem(cartItemModels.get(position));


        holder.binding.textAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no = cartItemModels.get(position).getNo();
                no++;
                cartItemModels.get(position).setNo(no);

                holder.binding.textQuantity.setText("" + no);
                quantityChangeListener.increaseQuantity(cartItemModels.get(position));
            }

        });
        holder.binding.textSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no = cartItemModels.get(position).getNo();
                if (no > 1) {
                    no--;
                    cartItemModels.get(position).setNo(no);
                    holder.binding.textQuantity.setText("" + String.valueOf(no));
                    quantityChangeListener.decreaseQuantity(cartItemModels.get(position));
                }
            }
        });
//        holder.binding.textQuantity.setText(cartItemModels.get(position).getNo());
    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewCartSingleItemBinding binding;

        public ViewHolder(RecyclerViewCartSingleItemBinding dataBinding) {
            super(dataBinding.getRoot());
            binding = dataBinding;
        }
    }
}
