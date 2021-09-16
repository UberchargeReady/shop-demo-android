package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.remote.ApiEndpoint;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.util.Util;

import java.util.List;

public class UserRequest extends Request {

    private final String path;

    public UserRequest(Builder builder) {
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
            this.path = "";
        }

        public Builder ofUserLoggedIn() {
            path = ApiEndpoint.ACCOUNT;
            return this;
        }

        public Builder fromId(String userId) {
            path = ApiEndpoint.USERS_ADMIN + "/" + userId;
            return this;
        }

        public Builder fromIds(List<String> userIds) {
            path = ApiEndpoint.USERS_ADMIN + "?userIds=" + Util.listToString(userIds, ",");
            return this;
        }

        public Builder allUsers() {
            path = ApiEndpoint.USERS_ADMIN;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}
