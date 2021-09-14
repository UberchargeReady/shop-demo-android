package com.dyejeekis.shopdemo.data.remote.api;

import org.json.JSONException;

public abstract class Response {

    protected abstract void parseResponse(String json) throws JSONException;

    protected String statusCode, message;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
