package com.dyejeekis.shopdemo.data.model;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean removeProduct(Product product) {
        if (products.remove(product))
            return true;
        else throw new IllegalArgumentException("Product not in list");
    }

    public boolean increaseQuantity(Product product) {
        int index = products.indexOf(product);
        if (index > -1) {
            products.get(index).increaseQuantity();
            return true;
        } else throw new IllegalArgumentException("Product not in list");
    }

    public boolean decreaseQuantity(Product product) {
        if (product.getSelectedQuantity() == 1) {
            return removeProduct(product);
        } else {
            int index = products.indexOf(product);
            if (index > -1) {
                products.get(index).decreaseQuantity();
                return true;
            } else throw new IllegalArgumentException("Product not in list");
        }
    }

    public float getTotalCost() {
        float total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
