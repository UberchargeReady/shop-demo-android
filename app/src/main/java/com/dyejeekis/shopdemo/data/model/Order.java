package com.dyejeekis.shopdemo.data.model;

import java.util.List;

public class Order extends ProductList {

    private final int id;
    private User user;
    private String purchaseDate;

    public Order(int id, User user, List<Product> products, String purchaseDate) {
        super(products);
        this.id = id;
        this.user = user;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public float getTotalCost() {
        float total = 0;
        for (Product product : getProducts()) {
            total += product.getPrice();
        }
        return total;
    }
}
