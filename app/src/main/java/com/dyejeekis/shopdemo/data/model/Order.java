package com.dyejeekis.shopdemo.data.model;

import java.util.List;

public class Order extends ProductList {

    private final String id, dateCreated;
    private final User user;

    public Order(List<Product> products, String id, String dateCreated, User user) {
        super(products);
        this.id = id;
        this.dateCreated = dateCreated;
        this.user = user;
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
