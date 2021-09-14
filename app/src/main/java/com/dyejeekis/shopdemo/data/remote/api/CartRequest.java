package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.User;

// TODO: 9/13/2021 DELETE THIS

public class CartRequest {

    private final String path;

    public CartRequest(Builder builder) {
        this.path = builder.path;
    }

    public String getPath() {
        return path;
    }

    public static class Builder {

        private String path;

        public Builder() {}

        public Builder ofUser(User user) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder addToCart(Product product) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder removeFromCart(Product product) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder modifyQuantity(Product product) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder emptyCart() {
            // TODO: 9/13/2021
            return this;
        }

        public CartRequest build() {
            assert path != null;
            return new CartRequest(this);
        }
    }
}
