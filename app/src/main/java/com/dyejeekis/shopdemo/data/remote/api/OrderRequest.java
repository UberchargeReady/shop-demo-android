package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest extends Request {

    private final String path;

    public OrderRequest(Builder builder) {
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

        public Builder fromId(String orderId) {
            path = ApiEndpoint.ORDERS_ADMIN + "?orderId=" + orderId;
            return this;
        }

        public Builder fromIds(List<String> orderIds) {
            path = ApiEndpoint.ORDERS_ADMIN + "?orderIds=" + Util.listToString(orderIds, ",");
            return this;
        }

        public Builder ofUserLoggedIn() {
            path = ApiEndpoint.ORDERS_USER;
            return this;
        }

        public Builder ofUser(User user) {
            path = ApiEndpoint.ORDERS_ADMIN + "?userId=" + user.getId();
            return this;
        }

        public Builder ofUsers(List<User> users) {
            final List<String> userIds = new ArrayList<>();
            for (User user : users) {
                userIds.add(user.getId());
            }
            path = ApiEndpoint.ORDERS_ADMIN + "?userIds=" + Util.listToString(userIds, ",");
            return this;
        }

        public Builder allOrders() {
            path = ApiEndpoint.ORDERS_ADMIN;
            return this;
        }

        public OrderRequest build() {
            assert path != null;
            return new OrderRequest(this);
        }
    }
}
