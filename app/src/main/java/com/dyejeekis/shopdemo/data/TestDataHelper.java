package com.dyejeekis.shopdemo.data;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;

import java.util.UUID;

public class TestDataHelper {

    public static ProductList getTestProducts(int count) {
        ProductList productList = new ProductList();
        for (int i=0; i<count; i++) {
            Product p = new Product(getRandomUniqueString(),
                    "Product " + (count + 1),
                    getRandomInt(1000),
                    getRandomFloat(1000));
            productList.addProduct(p);
        }
        return productList;
    }

    public static int getRandomInt(int max) {
        return (int)(Math.random() * max + 1);
    }

    public static float getRandomFloat(float max) {
        return (float)(Math.random() * 49 + 1);
    }

    public static String getRandomUniqueString() {
        return UUID.randomUUID().toString();
    }
}
