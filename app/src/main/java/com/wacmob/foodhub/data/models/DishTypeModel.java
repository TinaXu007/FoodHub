package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wacmob.foodhub.BR;

public class DishTypeModel extends BaseObservable {
    int id;
    String dishType;

    private boolean isSelected = false;


    public DishTypeModel(String dishType, int id) {
        this.dishType = dishType;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyPropertyChanged(BR.selected);
    }

}
