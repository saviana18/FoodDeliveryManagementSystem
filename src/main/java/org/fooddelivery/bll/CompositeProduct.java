package org.fooddelivery.bll;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private final ArrayList<BaseProduct> products;
    private String compositeName;
    private int id;

    public CompositeProduct(String name, int id, ArrayList<BaseProduct> products) {
        this.products = products;
    }

    public void setId(int id) {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return compositeName;
    }

    public ArrayList<BaseProduct> getProducts() {
        return products;
    }

    public int computePrice() {
        int totalPrice = 0;
        for (BaseProduct baseProduct : products) {
            totalPrice += baseProduct.computePrice();
        }
        return totalPrice;
    }
}
