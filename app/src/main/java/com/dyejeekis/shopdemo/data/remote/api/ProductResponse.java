package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.ProductList;

public class ProductResponse {

    private String statusCode, message;
    private ProductList data;

    public ProductResponse(String json) {
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

    public ProductList getProducts() {
        return data;
    }
}
