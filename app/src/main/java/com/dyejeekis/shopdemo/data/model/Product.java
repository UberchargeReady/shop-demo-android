package com.dyejeekis.shopdemo.data.model;

public class Product {

    private final String id, name;
    private final int stock;
    private final float price;

    private int selectedQuantity;

    public Product(String id, String name, int stock,  float price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.selectedQuantity = 1;
    }

    public boolean inStock() {
        return selectedQuantity <= stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        if (selectedQuantity > 1) {
            return selectedQuantity * price;
        }
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public void increaseQuantity() {
        selectedQuantity++;
    }

    public void decreaseQuantity() {
        selectedQuantity--;
    }
}
