package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse extends Response {

    private final List<Order> orders;

    public OrderResponse(String json) throws JSONException {
        orders = new ArrayList<>();
        parseResponse(json);
    }

    @Override
    protected void parseJSONObject(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.getString("_id");
        String date = jsonObject.getString("date");

        User user;
        try {
            UserResponse userResponse = new UserResponse(
                    jsonObject.getJSONObject("user").toString());
            user = userResponse.getUser();
        } catch (JSONException e) {
            // orders belong to user that made the request (current user)
            user = ShopDemoApp.getInstance().getCurrentUser();
        }

        ProductResponse productResponse = new ProductResponse(
                jsonObject.getJSONArray("products").toString());
        ProductList products = productResponse.getProducts();

        Order order = new Order(id, user, date, products);
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrder() {
        return orders.get(0);
    }
}
