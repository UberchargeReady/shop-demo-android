package com.dyejeekis.shopdemo.data.model;

import java.util.List;

public class Order extends ProductList {

    private final String id, dateCreated;
    private final User user;

    public Order(String id, User user, String dateCreated, List<Product> products) {
        super(products);
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }
}
