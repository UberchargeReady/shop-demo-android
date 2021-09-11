package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Order;

import java.util.List;

public class OrderResponse {

    private String statusCode, message;
    private List<Order> data;

    public OrderResponse(String json) {
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

    public List<Order> getOrders() {
        return data;
    }
}
