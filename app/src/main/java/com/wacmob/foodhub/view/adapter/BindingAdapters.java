package com.wacmob.foodhub.view.adapter;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wacmob.foodhub.R;


public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.place_holder_food).error(R.drawable.place_holder_food))
                .load(url).into(view);
    }

}
