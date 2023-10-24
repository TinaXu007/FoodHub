package com.wacmob.foodhub.data.models;

public class DishesFilterModel {
    String dishName;
    int id;

    public DishesFilterModel(String dishName, int id) {
        this.dishName = dishName;
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
