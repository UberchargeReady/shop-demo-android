package com.dyejeekis.shopdemo.data.model;

import java.util.List;

public class User {

    public static final String LOGGED_OUT_ID = "LOGGED_OUT";

    private final String id, username;
    private final ProductList cart;
    private final List<Order> orders;

    private String userToken;

    public User() {
        this.id = LOGGED_OUT_ID;
        this.username = null;
        this.userToken = null;
        this.cart = null;
        this.orders = null;
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.userToken = null;
        this.cart = null;
        this.orders = null;
    }

    public User(String id, String username, ProductList cart, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.cart = cart;
        this.orders = orders;
    }

    public boolean isLoggedIn() {
        return !id.equals(LOGGED_OUT_ID);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public ProductList getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
