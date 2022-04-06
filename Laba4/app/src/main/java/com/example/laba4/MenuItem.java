package com.example.laba4;

import android.widget.ImageView;
import android.widget.TextView;

public class MenuItem {
    int FoodImage;
   String FoodTitle;
   int Index;
   String FoodDescription;

    public MenuItem(int foodImage, String foodTitle, String foodDescription) {
        FoodImage = foodImage;
        FoodTitle = foodTitle;
        FoodDescription = foodDescription;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public int getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(int foodImage) {
        FoodImage = foodImage;
    }

    public String getFoodTitle() {
        return FoodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        FoodTitle = foodTitle;
    }

}
