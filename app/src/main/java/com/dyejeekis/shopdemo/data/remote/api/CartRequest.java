package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;

public class CartRequest extends Request {

    private final String path;

    public CartRequest(Builder builder) {
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

        public Builder ofUser(User user) {
            path = ApiEndpoint.CARTS_ADMIN + "/" + user.getId();
            return this;
        }

        public Builder ofUserLoggedIn() {
            path = ApiEndpoint.CART;
            return this;
        }

        public Builder addToCart(Product product) {
            path = ApiEndpoint.CART + "/" + product.getId();
            return this;
        }

        public Builder modifyQuantity(Product product) {
            path = ApiEndpoint.CART + "/" + product.getId();
            return this;
        }

        public Builder removeFromCart(Product product) {
            path = ApiEndpoint.CART_REMOVE;
            return this;
        }

        public Builder emptyCart() {
            path = ApiEndpoint.CART_EMPTY;
            return this;
        }

        public CartRequest build() {
            assert path != null;
            return new CartRequest(this);
        }
    }
}
