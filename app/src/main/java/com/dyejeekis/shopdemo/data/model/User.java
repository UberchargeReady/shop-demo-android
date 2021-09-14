package com.dyejeekis.shopdemo.data.model;

public class User {

    public static final String LOGGED_OUT_ID = "LOGGED_OUT";

    private final String id, username;

    private String token;
    private ProductList cart;

    public User() {
        this.id = LOGGED_OUT_ID;
        this.username = null;
        this.token = null;
        this.cart = new ProductList();
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.token = null;
        this.cart = new ProductList();
    }

    public User(String id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.cart = new ProductList();
    }

    public User(String id, String username, String token, ProductList cart) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.cart = cart;
    }

    public boolean isLoggedIn() {
        return !id.equals(LOGGED_OUT_ID) && username != null && token != null && !token.isEmpty();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ProductList getCart() {
        return cart;
    }

    public void setCart(ProductList cart) {
        this.cart = cart;
    }
}
