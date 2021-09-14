package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductResponse extends Response {

    private final ProductList products;

    public ProductResponse(String json) throws JSONException {
        products = new ProductList();
        parseResponse(json);
    }

    @Override
    protected void parseJSONObject(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.getString("_id");
        String name = jsonObject.getString("name");
        int stock = jsonObject.getInt("stock");
        float price = (float) jsonObject.getDouble("price");
        Product product = new Product(id, name, stock, price);
        products.addProduct(product);
    }

    public ProductList getProducts() {
        return products;
    }

    public Product getProduct() {
        return products.get(0);
    }
}
