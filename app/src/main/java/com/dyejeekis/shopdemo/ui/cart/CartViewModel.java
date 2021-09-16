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
        if (isValidUser()) {
            CartRequest request = new CartRequest.Builder(getApiHeader()).ofUserLoggedIn().build();
            appApiHelper.getExecutor().execute(() -> {
                Result<ProductResponse> result = appApiHelper.getCart(request);
                if (result instanceof Result.Success)
                    cart = ((Result.Success<ProductResponse>) result).data.getProducts();
                updateCartMutable();
            });
        }
    }

    @Override
    public void addToCart(Product product) {
        if (isValidUser()) {
            String body = ""; // TODO: 9/16/2021
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .addToCart(product)
                    .body(body)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void removeFromCart(Product product) {
        if (isValidUser()) {
            String body = ""; // TODO: 9/16/2021
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .removeFromCart(product)
                    .body(body)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void increaseQuantity(Product product) {
        if (isValidUser()) {
            String body = ""; // TODO: 9/16/2021
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .modifyQuantity(product)
                    .body(body)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void decreaseQuantity(Product product) {
        if (isValidUser()) {
            String body = ""; // TODO: 9/16/2021
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .modifyQuantity(product)
                    .body(body)
                    .build();
            postCartAsync(request);
        }
    }

    @Override
    public void emptyCart() {
        if (isValidUser()) {
            String body = ""; // TODO: 9/16/2021
            CartRequest request = new CartRequest.Builder(getApiHeader())
                    .emptyCart()
                    .body(body)
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
                cart = new ProductList();
                updateCartMutable();
            }
        });
    }

    private void postCartAsync(CartRequest request) {
        appApiHelper.getExecutor().execute(() -> {
            Result<ProductResponse> result = appApiHelper.postCart(request);
            if (result instanceof Result.Success) {
                cart = ((Result.Success<ProductResponse>) result).data.getProducts();
                updateCartMutable();
            }
        });
    }

    public ProductList getCart() {
        return cart;
    }

    private void updateCartMutable() {
        cartMutable.postValue(cart);
    }
}