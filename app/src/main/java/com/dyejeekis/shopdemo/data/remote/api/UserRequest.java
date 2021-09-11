package com.dyejeekis.shopdemo.data.remote.api;

public class UserRequest {

    private String queryString;

    public UserRequest() {
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

    public void setQueryVerified() {
        // TODO: 9/11/2021
    }

    public void setQueryUnverified() {
        // TODO: 9/11/2021
    }

    public void setQueryAdmins() {
        // TODO: 9/11/2021
    }
}
