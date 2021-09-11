package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.data.model.User;

public class ApiHeader {

    public static final String USER_ID = "user_id";
    public static final String USER_TOKEN = "user_token";

    private int userId;
    private String userToken;

    public ApiHeader() {}

    public ApiHeader(User user) {
        this.userId = user.getId();
        this.userToken = user.getUserToken();
    }

    public ApiHeader(int userId) {
        this.userId = userId;
        this.userToken = null;
    }

    public ApiHeader(int userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.userToken = user.getUserToken();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
