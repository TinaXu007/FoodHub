package com.wacmob.foodhub.view.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.MyordersItemModel;
import com.wacmob.foodhub.databinding.RecyclerViewMyOrdersSingleItemBinding;
import com.wacmob.foodhub.view.listener.MyOrdersListItemClickListener;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    List<MyordersItemModel> list;
    MyOrdersListItemClickListener listener;
    Context context;

    public OrderListAdapter(List<MyordersItemModel> list, MyOrdersListItemClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerViewMyOrdersSingleItemBinding dataBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.recycler_view_my_orders_single_item, parent, false);

        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {
        holder.binding.setListener(listener);
        holder.binding.setItem(list.get(position));

        if (list.get(position).getId() == 0) {
            holder.binding.textViewStatus.setText(list.get(position).getStatus());
            holder.binding.textViewStatus.setBackground(context.getResources().getDrawable(R.drawable.back_ground_pending_black));
            holder.binding.constraintLayoutDetail.setVisibility(View.GONE);

        } else if (list.get(position).getId() == 1) {

            holder.binding.textViewStatus.setText(list.get(position).getStatus());
            holder.binding.textViewStatus.setBackground(context.getResources().getDrawable(R.drawable.back_ground_cancelled_red));
            holder.binding.constraintLayoutDetail.setVisibility(View.GONE);

        } else if (list.get(position).getId() == 2) {

            holder.binding.textViewStatus.setText(list.get(position).getStatus());
            holder.binding.textViewStatus.setBackground(context.getResources().getDrawable(R.drawable.back_ground_delivered_green));
            if (list.get(position).isSelected()) {
                holder.binding.constraintLayoutDetail.setVisibility(View.VISIBLE);
            } else {
                holder.binding.constraintLayoutDetail.setVisibility(View.GONE);
            }
            holder.binding.textViewReorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, list.get(position).getItemname() + " added to cart", Toast.LENGTH_SHORT).show();
                }
            });

        }
        holder.binding.executePendingBindings();



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewMyOrdersSingleItemBinding binding;

        public ViewHolder(RecyclerViewMyOrdersSingleItemBinding dataBinding) {
            super(dataBinding.getRoot());
            binding = dataBinding;

        }
    }
}
