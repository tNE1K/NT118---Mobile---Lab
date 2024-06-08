package com.example.lab2;

public class Dish {
    public String name;
    public int img;
    public boolean promotion;

    public Dish() {
    }

    public Dish(String name, int img, boolean promotion) {
        this.name = name;
        this.img = img;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
}
