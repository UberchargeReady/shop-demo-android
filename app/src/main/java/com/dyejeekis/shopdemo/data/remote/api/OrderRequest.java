package com.dyejeekis.shopdemo.data.remote.api;

public class OrderRequest {

    private String queryString;

    public OrderRequest() {
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

    public void setQueryUser(int userId) {
        // TODO: 9/11/2021
    }
}
