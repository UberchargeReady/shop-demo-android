package com.dyejeekis.shopdemo.data.remote.api;

public class ProductRequest {

    private String queryString;

    public ProductRequest() {
        queryString = "";
    }

    public String getQueryString() {
        if (queryString.isEmpty()) return queryString;
        return "?" + queryString;
    }

    public void setQueryAll() {
        // TODO: 9/11/2021
    }

    public void setQueryId(int id) {
        // TODO: 9/11/2021
    }

    public void setQueryIds(int[] ids) {
        // TODO: 9/11/2021
    }

    public void setQueryCount(int count) {
        // TODO: 9/11/2021
    }
}
