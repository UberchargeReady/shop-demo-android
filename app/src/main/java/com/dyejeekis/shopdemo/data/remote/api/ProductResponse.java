package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.util.Util;

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
        try {
            JSONObject productObj = Util.safeJsonObject(jsonObject, "product");
            if (productObj == null) productObj = jsonObject.getJSONObject("productId");
            int quantity = jsonObject.getInt("quantity");
            parseProduct(productObj, quantity);
        } catch (JSONException e) {
            parseProduct(jsonObject, 1);
        }
    }

    private void parseProduct(JSONObject jsonObject, int quantity) throws JSONException {
        try {
            String id = jsonObject.getString("_id");
            String name = jsonObject.getString("name");
            int stock = jsonObject.getInt("stock");
            float price = (float) jsonObject.getDouble("price");
            Product product = new Product(id, name, stock, price);
            product.setSelectedQuantity(quantity);
            products.addProduct(product);
        } catch (JSONException e) {
            parseCartItem(jsonObject);
        }
    }

    private void parseCartItem(JSONObject jsonObject) throws JSONException{
        try {
            String id = jsonObject.getString("_id");
            int quantity = jsonObject.getInt("quantity");
            Product product = new Product(id, quantity);
            products.addProduct(product);
        } catch (JSONException e) {
            UserResponse response = new UserResponse(jsonObject.toString());
            products.addAll(response.getUser().getCart());
        }
    }

    public ProductList getProducts() {
        return products;
    }

    public Product getProduct() {
        return products.get(0);
    }
}
