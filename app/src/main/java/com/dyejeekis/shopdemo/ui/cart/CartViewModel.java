package com.dyejeekis.shopdemo.ui.cart;

import androidx.lifecycle.MutableLiveData;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.CartHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.ui.BaseViewModel;

public class CartViewModel extends BaseViewModel implements CartHelper {

    private final AppApiHelper appApiHelper = new AppApiHelper();

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
            ProductRequest request = new ProductRequest.Builder(getApiHeader()).userLoggedInCart().build();
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

    public ProductList getCart() {
        return cart;
    }

    private void updateCartMutable() {
        cartMutable.setValue(cart);
    }
}