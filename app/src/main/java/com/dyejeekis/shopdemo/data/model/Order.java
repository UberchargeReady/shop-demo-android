package com.dyejeekis.shopdemo.data.model;

public class Order extends Entity {

    private final String dateCreated;
    private final User user;
    private final ProductList products;

    public Order(String id, User user, String dateCreated, ProductList products) {
        super(id);
        this.dateCreated = dateCreated;
        this.user = user;
        this.products = products;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public ProductList getProducts() {
        return products;
    }
}
