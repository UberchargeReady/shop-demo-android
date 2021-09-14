package com.dyejeekis.shopdemo.data.remote.api;

import androidx.annotation.NonNull;

import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest extends Request {

    private final String username, password;

    public LoginRequest(@NonNull String username, @NonNull String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getPath() {
        return ApiEndpoint.LOGIN;
    }

    public String getRequestBody() throws JSONException {
        return new JSONObject()
                .put("username", username)
                .put("password", password)
                .toString();
    }
}
