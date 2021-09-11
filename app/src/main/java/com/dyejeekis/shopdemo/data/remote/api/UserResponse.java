package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;

import java.util.List;

public class UserResponse {

    private String statusCode, message;
    private List<User> data;

    public UserResponse(String json) {
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

    public List<User> getUsers() {
        return data;
    }
}
