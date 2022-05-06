package com.example.laba4;

import android.widget.ImageView;
import android.widget.TextView;

public class MenuItem {
    String  FoodImage;
   String FoodTitle;
   int Price;
   int Quantity;
   String FoodDescription;

    public MenuItem(String  foodImage, String foodTitle, String foodDescription) {
        FoodImage = foodImage;
        FoodTitle = foodTitle;
        FoodDescription = foodDescription;
    }

    public MenuItem() {

    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public String getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(String foodImage) {
        FoodImage = foodImage;
    }

    public String getFoodTitle() {
        return FoodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        FoodTitle = foodTitle;
    }

}
