package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;

import java.util.List;

public class ProductRequest {

    private final String path;

    public ProductRequest(Builder builder) {
        this.path = builder.path;
    }

    public String getPath() {
        return path;
    }

    public static class Builder {

        private String path;

        public Builder() {}

        public Builder fromUserCart(User user) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder fromId(String productId) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder fromIds(List<String> productIds) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder allProducts() {
            // TODO: 9/13/2021
            return this;
        }

        public ProductRequest build() {
            assert path != null;
            return new ProductRequest(this);
        }
    }
}
