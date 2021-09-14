package com.dyejeekis.shopdemo.data.model;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends ArrayList<Product> {

    public ProductList() {
        super();
    }

    public ProductList(List<Product> products) {
        super(products);
    }

    public boolean addProduct(Product product) {
        return add(product);
    }

    public boolean removeProduct(Product product) {
        if (remove(product))
            return true;
        else throw new IllegalArgumentException("Product not in list");
    }

    public boolean increaseQuantity(Product product) {
        int index = indexOf(product);
        if (index > -1) {
            get(index).increaseQuantity();
            return true;
        } else throw new IllegalArgumentException("Product not in list");
    }

    public boolean decreaseQuantity(Product product) {
        if (product.getSelectedQuantity() == 1) {
            return removeProduct(product);
        } else {
            int index = indexOf(product);
            if (index > -1) {
                get(index).decreaseQuantity();
                return true;
            } else throw new IllegalArgumentException("Product not in list");
        }
    }

    public float getTotalCost() {
        float total = 0;
        for (Product product : this) {
            total += product.getPrice();
        }
        return total;
    }
}
