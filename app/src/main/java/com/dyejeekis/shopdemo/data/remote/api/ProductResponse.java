package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ProductResponse extends Response {

    private ProductList products;

    public ProductResponse(String json) throws JSONException {
        parseResponse(json);
    }

    @Override
    protected void parseResponse(String json) throws JSONException {
        Object obj = new JSONTokener(json).nextValue();
        if (obj instanceof JSONObject) {

        } else if (obj instanceof JSONArray) {

        }
    }

    public ProductList getProducts() {
        return products;
    }

    public Product getProduct() {
        return products.get(0);
    }
}
