package com.dyejeekis.shopdemo.ui.cart;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.CartHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;

public class CartViewModel extends ViewModel implements CartHelper {

    private final AppApiHelper appApiHelper = new AppApiHelper(
            new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));

    private ProductList cart = new ProductList();
    private MutableLiveData<ProductList> cartMutable;

    public MutableLiveData<ProductList> getCartMutable() {
        if (cartMutable == null) {
            cartMutable = new MutableLiveData<>();
            loadCart();
        }
        return cartMutable;
    }

    @Override
    public void loadCart() {
        if (checkUser()) {
            ProductRequest request = new ProductRequest.Builder().fromUserCart(getUser()).build();
            appApiHelper.doProductApiCallAsync(request, result -> {
                if (result instanceof Result.Success) {
                    cart = ((Result.Success<ProductResponse>) result).data.getProducts();
                } else cart = new ProductList();
                updateCartMutable();
            });
        }
    }

    @Override
    public void addToCart(Product product) {

    }

    @Override
    public void removeFromCart(Product product) {

    }

    @Override
    public void increaseQuantity(Product product) {

    }

    @Override
    public void decreaseQuantity(Product product) {

    }

    @Override
    public void emptyCart() {

    }

    @Override
    public void makeOrder() {

    }

    private boolean checkUser() {
        User user = ShopDemoApp.getInstance().getCurrentUser();
        return user != null && user.isLoggedIn();
    }

    private User getUser() {
        return ShopDemoApp.getInstance().getCurrentUser();
    }

    public ProductList getCart() {
        return cart;
    }

    private void updateCartMutable() {
        cartMutable.setValue(cart);
    }
}