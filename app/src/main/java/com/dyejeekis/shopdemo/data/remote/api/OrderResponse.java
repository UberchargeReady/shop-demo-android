package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;

public class OrderResponse extends Response {

    private List<Order> orders;

    public OrderResponse(String json) throws JSONException {
        parseResponse(json);
    }

    @Override
    protected void parseResponse(String json) throws JSONException {
        Object obj = new JSONTokener(json).nextValue();
        if (obj instanceof JSONObject) {

        } else if (obj instanceof JSONArray) {

        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
