package com.dyejeekis.shopdemo.data.remote.api;

import androidx.annotation.NonNull;

import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;

public class LoginRequest {

    private final String username, password;

    public LoginRequest(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getPath() {
        return ApiEndpoint.LOGIN;
    }

    public String getRequestBody() {
        // TODO: 9/11/2021
        return null;
    }
}
