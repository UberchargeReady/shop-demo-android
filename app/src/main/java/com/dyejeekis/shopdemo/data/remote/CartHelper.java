package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.data.model.Product;

public interface CartHelper {

    void updateCart();

    void addToCart(Product product);

    void removeFromCart(Product product);

    void increaseQuantity(Product product);

    void decreaseQuantity(Product product);

    void emptyCart();

    void makeOrder();
}
