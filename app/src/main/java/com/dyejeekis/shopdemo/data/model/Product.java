package com.dyejeekis.shopdemo.data.model;

public class Product {

    private final int id;
    private int stock, selectedQuantity;
    private String name;
    private float price;

    public Product(int id, int stock, String name, float price) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.price = price;
        this.selectedQuantity = 1;
    }

    public boolean inStock() {
        return selectedQuantity <= stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        if (selectedQuantity > 1) {
            return selectedQuantity * price;
        }
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
