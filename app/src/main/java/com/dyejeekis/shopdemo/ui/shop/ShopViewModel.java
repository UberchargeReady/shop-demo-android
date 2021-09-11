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

import java.util.List;

public class ShopViewModel extends ViewModel {

    private AppApiHelper appApiHelper;
    private MutableLiveData<ProductList> shop;

    public AppApiHelper getAppApiHelper() {
        if (appApiHelper == null)
            appApiHelper = new AppApiHelper(new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));
        return appApiHelper;
    }

    public MutableLiveData<ProductList> getShop() {
        if (shop == null) {
            shop = new MutableLiveData<>();
            loadProducts();
        }
        return shop;
    }

    private void loadProducts() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setQueryAll();
        appApiHelper.doProductApiCallAsync(productRequest, result -> {
            ProductList products;
            if (result instanceof Result.Success) {
                products = ((Result.Success<ProductResponse>) result).data.getProducts();
            } else {
                products = null;
            }
            getShop().setValue(products);
        });
    }
}