package com.dyejeekis.shopdemo.ui.shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;

public class ShopViewModel extends ViewModel {

    private final AppApiHelper appApiHelper = new AppApiHelper(
            new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));;

    private MutableLiveData<ProductList> productsMutable;

    public AppApiHelper getAppApiHelper() {
        return appApiHelper;
    }

    public MutableLiveData<ProductList> getProductsMutable() {
        if (productsMutable == null) {
            productsMutable = new MutableLiveData<>();
            loadProducts();
        }
        return productsMutable;
    }

    private void loadProducts() {
        ProductRequest request = new ProductRequest.Builder().allProducts().build();
        appApiHelper.doProductApiCallAsync(request, result -> {
            ProductList products;
            if (result instanceof Result.Success) {
                products = ((Result.Success<ProductResponse>) result).data.getProducts();
            } else {
                products = null;
            }
            getProductsMutable().setValue(products);
        });
    }
}