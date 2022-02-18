package org.fooddelivery.bll;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private int id;
    private String title;
    private double rating;
    private int proteins;
    private int calories;
    private int fats;
    private int sodium;
    private int price;

    public abstract int computePrice();

    public abstract String getTitle();

    public abstract void setId(int i);

    public abstract int getId();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }
}
