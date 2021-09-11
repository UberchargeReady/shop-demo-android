package com.dyejeekis.shopdemo.ui.cart;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;

public class CartViewModel extends ViewModel {

    private MutableLiveData<ProductList> cart;
    private final ProductList productList = new ProductList();

    public MutableLiveData<ProductList> getCart() {
        if (cart == null) {
            cart = new MutableLiveData<>();
            loadCart();
        }
        return cart;
    }

    private void loadCart() {
        // TODO: 9/11/2021
    }

    public void increaseQuantity(Product product) {
        if (productList.increaseQuantity(product))
            updateCart();
    }

    public void decreaseQuantity(Product product) {
        if (productList.decreaseQuantity(product))
            updateCart();
    }

    public void addToCart(Product product) {
        if (productList.addProduct(product))
            updateCart();
    }

    public void removeFromCart(Product product) {
        if (productList.removeProduct(product))
            updateCart();
    }

    private void updateCart() {
        cart.setValue(productList);
    }
}