package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.data.model.User;

public class ApiHeader {

    public static final String USER_ID = "user_id";
    public static final String USER_TOKEN = "user_token";

    private String userId, userToken;

    public ApiHeader(User user) {
        this.userId = user.getId();
        this.userToken = user.getUserToken();
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.userToken = user.getUserToken();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
