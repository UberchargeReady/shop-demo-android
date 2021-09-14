package com.dyejeekis.shopdemo.data.remote.api;

import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;

public abstract class Request {

    public abstract String getPath();

    private final ApiHeader apiHeader;

    public Request() {
        this.apiHeader = new ApiHeader(new User());
    }

    public Request(ApiHeader apiHeader) {
        this.apiHeader = apiHeader;
    }

    public ApiHeader getApiHeader() {
        return apiHeader;
    }
}
