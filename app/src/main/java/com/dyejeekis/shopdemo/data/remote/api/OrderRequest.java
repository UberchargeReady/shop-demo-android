package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;

import java.util.List;

public class OrderRequest {

    private final String path;

    public OrderRequest(Builder builder) {
        this.path = builder.path;
    }

    public String getPath() {
        return path;
    }

    public static class Builder {

        private String path;

        public Builder() {}

        public Builder fromId(String orderId) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder fromIds(List<String> orderIds) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder ofUser(User user) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder ofUsers(List<User> users) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder allOrders() {
            // TODO: 9/13/2021
            return this;
        }

        public OrderRequest build() {
            assert path != null;
            return new OrderRequest(this);
        }
    }
}
