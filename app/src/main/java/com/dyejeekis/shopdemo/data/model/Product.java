package com.dyejeekis.shopdemo.data.model;

public class Product extends Entity {

    private final String name;
    private final int stock;
    private final float price;

    private int selectedQuantity;

    public Product(String id, String name, int stock,  float price) {
        super(id);
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.selectedQuantity = 1;
    }

    public Product(String id, int selectedQuantity) {
        super(id);
        this.name = "";
        this.stock = 0;
        this.price = 0;
        this.selectedQuantity = selectedQuantity;
    }

    public boolean inStock() {
        return selectedQuantity <= stock;
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
        if (selectedQuantity > 1) selectedQuantity--;
    }
}
