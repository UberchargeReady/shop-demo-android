package com.dyejeekis.shopdemo.data.remote.api;

import androidx.annotation.NonNull;

public class LoginRequest {

    private final String username, password;

    public LoginRequest(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getRequestBody() {
        // TODO: 9/11/2021
        return null;
    }
}
