package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.util.Util;

import java.util.List;

public class ProductRequest extends Request {

    private final String path;

    public ProductRequest(Builder builder) {
        super(builder.apiHeader);
        this.path = builder.path;
    }

    public String getPath() {
        return path;
    }

    public static class Builder {

        private final ApiHeader apiHeader;
        private String path;

        public Builder(ApiHeader apiHeader) {
            this.apiHeader = apiHeader;
        }

        public Builder userLoggedInCart() {
            path = ApiEndpoint.CART;
            return this;
        }

        public Builder fromId(String productId) {
            path = ApiEndpoint.PRODUCTS + "/" + productId;
            return this;
        }

        public Builder fromIds(List<String> productIds) {
            path = ApiEndpoint.PRODUCTS + "?productIds=" + Util.listToString(productIds, ",");
            return this;
        }

        public Builder allProducts() {
            path = ApiEndpoint.PRODUCTS;
            return this;
        }

        public ProductRequest build() {
            assert path != null;
            return new ProductRequest(this);
        }
    }
}
