package com.dyejeekis.shopdemo.data.remote.api;

import androidx.annotation.NonNull;

import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpRequest extends Request {

    private final String username, password;

    public SignUpRequest(@NonNull String username, @NonNull String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getPath() {
        return ApiEndpoint.SIGNUP;
    }

    public String getRequestBody() throws JSONException {
        return new JSONObject()
                .put("username", username)
                .put("password", password)
                .toString();
    }
}
