package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;

public class LogoutResponse {

    private String statusCode, message;
    private User user;

    public LogoutResponse(String json) {
        parseResponse(json);
    }

    private void parseResponse(String json) {
        // TODO: 9/11/2021
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
