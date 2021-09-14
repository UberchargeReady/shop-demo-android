package com.dyejeekis.shopdemo.data.remote.api;

import androidx.annotation.NonNull;

import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;

public class SignUpRequest {

    private final String username, password;

    public SignUpRequest(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getPath() {
        return ApiEndpoint.SIGNUP;
    }

    public String getRequestBody() {
        // TODO: 9/11/2021
        return null;
    }
}
