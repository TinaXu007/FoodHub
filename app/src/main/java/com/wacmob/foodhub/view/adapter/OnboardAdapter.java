package com.wacmob.foodhub.view.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wacmob.foodhub.data.models.OnboardModel;

import java.util.List;

public class OnboardAdapter  extends PagerAdapter {
    List<OnboardModel>list;
    Context context;

    public OnboardAdapter(List<OnboardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        OnboardModel model = list.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(model.getLayourResId(), container, false);
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
