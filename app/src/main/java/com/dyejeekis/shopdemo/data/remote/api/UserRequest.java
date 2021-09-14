package com.dyejeekis.shopdemo.data.remote.api;

import java.util.List;

public class UserRequest {

    private final String path;

    public UserRequest(Builder builder) {
        this.path = builder.path;
    }

    public String getPath() {
        return path;
    }

    public static class Builder {

        private String path;

        public Builder() {}

        public Builder fromId(String userId) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder fromIds(List<String> userIds) {
            // TODO: 9/13/2021
            return this;
        }

        public Builder allUsers() {
            // TODO: 9/13/2021
            return this;
        }

        public UserRequest build() {
            assert path != null;
            return new UserRequest(this);
        }
    }
}
