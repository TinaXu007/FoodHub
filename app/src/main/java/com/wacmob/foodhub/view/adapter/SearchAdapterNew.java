package com.wacmob.foodhub.view.adapter;

import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacmob.foodhub.R;
import com.wacmob.foodhub.data.models.SearchItemListModel;
import com.wacmob.foodhub.databinding.RecyclerViewRecentItemBinding;
import com.wacmob.foodhub.databinding.RecyclerViewSearchSingleItemNewBinding;
import com.wacmob.foodhub.view.listener.OnAdapterSelectedListener;

import java.util.ArrayList;

public class SearchAdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<SearchItemListModel> itemList;
    OnAdapterSelectedListener listener;
    private String newText;

    public SearchAdapterNew(ArrayList<SearchItemListModel> itemList, OnAdapterSelectedListener listener) {

        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {

        return itemList.get(position).getItemType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            RecyclerViewRecentItemBinding recyclerViewRecentItemBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.recycler_view_recent_item, viewGroup, false);
            return new ViewHolderRecentItems(recyclerViewRecentItemBinding);

        } else if (viewType == 1) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            RecyclerViewSearchSingleItemNewBinding searchSingleItemBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.recycler_view_search_single_item_new, viewGroup, false);
            return new ViewHolderSearchItems(searchSingleItemBinding);

        }
        return null;

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {

        if (viewHolder instanceof ViewHolderRecentItems) {

            if (pos == 0) {
                ((ViewHolderRecentItems) viewHolder).binding.viewNew.setVisibility(View.VISIBLE);
            } else {
                ((ViewHolderRecentItems) viewHolder).binding.viewNew.setVisibility(View.GONE);
            }
            SearchItemListModel recentItemModel = itemList.get(pos);
            ((ViewHolderRecentItems) viewHolder).binding.setItem(recentItemModel);
            ((ViewHolderRecentItems) viewHolder).binding.setListener(listener);

        } else if (viewHolder instanceof ViewHolderSearchItems) {

            SearchItemListModel searchItemListModel = itemList.get(pos);
            ((ViewHolderSearchItems) viewHolder).binding.setItem(searchItemListModel);

            ((ViewHolderSearchItems) viewHolder).binding.setListener(listener);

            if (newText.length() > 0) {
                //color your text here

                String title = searchItemListModel.getProductTitle().toLowerCase();

                int index = title.indexOf(newText.toLowerCase());
                SpannableStringBuilder sb = new SpannableStringBuilder(searchItemListModel.getProductTitle());
//                while (index > 0) {
                if (index > -1) {
                    ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(0, 156, 220)); //specify color here
                    int startIndex = index;
                    int endIndex = index + newText.length();
                    sb.setSpan(fcs, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//                index = searchItemListModel.getProductTitle().indexOf(newText, index + 1);
                    System.out.println(index);
                }

//                }
                // ((ViewHolderSearchItems) viewHolder).binding.textViewSearchItemName.setText(sb);

            }
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setFilter(String newText) {

        this.newText = newText;
        notifyDataSetChanged();
    }

    public class ViewHolderRecentItems extends RecyclerView.ViewHolder {

        RecyclerViewRecentItemBinding binding;

        public ViewHolderRecentItems(@NonNull RecyclerViewRecentItemBinding recyclerViewRecentItemBinding) {
            super(recyclerViewRecentItemBinding.getRoot());
            binding = recyclerViewRecentItemBinding;
        }
    }

    public class ViewHolderSearchItems extends RecyclerView.ViewHolder {
        RecyclerViewSearchSingleItemNewBinding binding;

        public ViewHolderSearchItems(@NonNull RecyclerViewSearchSingleItemNewBinding searchSingleItemBinding) {
            super(searchSingleItemBinding.getRoot());
            binding = searchSingleItemBinding;
        }
    }

}
