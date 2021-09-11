package com.dyejeekis.shopdemo.data.model;

public class User {

    public static final int LOGGED_OUT_ID = 0;

    private final int id;
    private final String username;
    private String userToken;

    public User() {
        this.id = LOGGED_OUT_ID;
        this.username = null;
        this.userToken = null;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.userToken = null;
    }

    public User(int id, String username, String userToken) {
        this.id = id;
        this.username = username;
        this.userToken = userToken;
    }

    public boolean isLoggedIn() {
        return id > LOGGED_OUT_ID;
    }

    public int getId() {
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
}
