package com.dyejeekis.shopdemo.ui.shop;

import androidx.lifecycle.MutableLiveData;

import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.ui.BaseViewModel;

public class ShopViewModel extends BaseViewModel {

    private final AppApiHelper appApiHelper = new AppApiHelper();

    private MutableLiveData<ProductList> productsMutable;

    public MutableLiveData<ProductList> getProductsMutable() {
        if (productsMutable == null) {
            productsMutable = new MutableLiveData<>();
            loadProducts();
        }
        return productsMutable;
    }

    private void loadProducts() {
        ProductRequest request = new ProductRequest.Builder(getApiHeader()).allProducts().build();
        appApiHelper.getExecutor().execute(() -> {
            Result<ProductResponse> result = appApiHelper.getProduct(request);
            ProductList products;
            if (result instanceof Result.Success)
                products = ((Result.Success<ProductResponse>) result).data.getProducts();
            else products = null;
            getProductsMutable().postValue(products);
        });
    }
}