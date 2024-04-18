package com.example.bt_gridview;

public class Food {
    String imgFood;
    String contextFood;

    public Food(String imgFood, String contextFood) {
        this.imgFood = imgFood;
        this.contextFood = contextFood;
    }

    public String getImgFood() {
        return imgFood;
    }

    public String getContextFood() {
        return contextFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public void setContextFood(String contextFood) {
        this.contextFood = contextFood;
    }
}
