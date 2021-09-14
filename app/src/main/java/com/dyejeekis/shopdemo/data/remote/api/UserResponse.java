package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;

public class UserResponse extends Response {

    private List<User> users;

    public UserResponse(String json) throws JSONException {
        parseResponse(json);
    }

    @Override
    protected void parseResponse(String json) throws JSONException {
        Object obj = new JSONTokener(json).nextValue();
        if (obj instanceof JSONObject) {

        } else if (obj instanceof JSONArray) {

        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser() {
        return users.get(0);
    }
}
