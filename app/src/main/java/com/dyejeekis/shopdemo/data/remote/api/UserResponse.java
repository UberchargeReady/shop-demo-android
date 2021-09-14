package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class UserResponse extends Response {

    private final List<User> users;

    public UserResponse(String json) throws JSONException {
        users = new ArrayList<>();
        parseResponse(json);
    }

    protected void parseJSONObject(JSONObject jsonObj) throws JSONException {
        String id = jsonObj.getString("_id");
        String username = jsonObj.getString("username");
        String token = jsonObj.getString("token");
        JSONObject cart = jsonObj.getJSONObject("cart");
        JSONArray items = cart.getJSONArray("items");
        ProductResponse productResponse = new ProductResponse(items.toString());
        User user = new User(id, username, token, productResponse.getProducts());
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser() {
        return users.get(0);
    }
}
