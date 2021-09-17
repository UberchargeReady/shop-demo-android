package com.dyejeekis.shopdemo.ui.cart;

import androidx.lifecycle.MutableLiveData;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.CartHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.CartRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.ui.BaseViewModel;

public class CartViewModel extends BaseViewModel implements CartHelper {

    private final AppApiHelper appApiHelper = new AppApiHelper();

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
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader()).ofUserLoggedIn().build();
            appApiHelper.getExecutor().execute(() -> {
                Result<ProductResponse> result = appApiHelper.getCart(request);
                ProductList cart = null;
                if (result instanceof Result.Success)
                    cart = ((Result.Success<ProductResponse>) result).data.getProducts();
                cartMutable.postValue(cart);
            });
        }
    }

    @Override
    public void addToCart(Product product) {
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .addToCart(product)
                    .body(product)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void removeFromCart(Product product) {
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .removeFromCart(product)
                    .body(product)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void increaseQuantity(Product product) {
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .modifyQuantity(product)
                    .body(product)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void decreaseQuantity(Product product) {
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .modifyQuantity(product)
                    .body(product)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void emptyCart() {
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .emptyCart()
                    .body("")
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void makeOrder() {
        OrderRequest request = new OrderRequest.Builder(getApiHeader()).checkout().build();
        appApiHelper.getExecutor().execute(() -> {
            Result<OrderResponse> result = appApiHelper.getOrder(request);
            if (result instanceof Result.Success) {
                ProductList cart = new ProductList();
                cartMutable.postValue(cart);
            }
        });
    }

    private void postCartAsync(CartRequest request) {
        appApiHelper.getExecutor().execute(() -> {
            Result<ProductResponse> result = appApiHelper.postCart(request);
            if (result instanceof Result.Success) {
                //ProductList cart = ((Result.Success<ProductResponse>) result).data.getProducts();
                Result<ProductResponse> cartDetails = appApiHelper.getCart(
                        new CartRequest.Builder(getApiHeader()).ofUserLoggedIn().build());
                if (cartDetails instanceof Result.Success) {
                    cartMutable.postValue(((Result.Success<ProductResponse>) cartDetails).data.getProducts());
                } else {
                    // TODO: 9/17/2021 error
                }
            }
        });
    }
}