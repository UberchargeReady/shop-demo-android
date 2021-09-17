package com.dyejeekis.shopdemo.data.remote;

public class ApiEndpoint {

    public static final String BASE_URL = "http://calm-island-60441.herokuapp.com";

    // public endpoints

    public static final String BASE_API_ENDPOINT = "/api";

    public static final String WELCOME = BASE_API_ENDPOINT + "/welcome";

    public static final String SIGNUP = BASE_API_ENDPOINT + "/signup";

    public static final String LOGIN = BASE_API_ENDPOINT + "/login";

    public static final String LOGOUT = BASE_API_ENDPOINT + "/logout";

    public static final String ACCOUNT = BASE_API_ENDPOINT + "/account";

    public static final String PRODUCT = BASE_API_ENDPOINT + "/product";

    public static final String PRODUCTS = BASE_API_ENDPOINT + "/products";

    public static final String CART = BASE_API_ENDPOINT + "/cart";

    public static final String CART_REMOVE = CART + "/%s/remove";

    public static final String CART_EMPTY = CART + "/empty";

    public static final String CHECKOUT = BASE_API_ENDPOINT + "/checkout";

    public static final String ORDERS_USER = BASE_API_ENDPOINT + "/orders";

    // admin endpoints

    public static final String ADMIN_ENDPOINT = BASE_API_ENDPOINT + "/admin";

    public static final String USERS_ADMIN = ADMIN_ENDPOINT + "/users";

    public static final String CARTS_ADMIN = ADMIN_ENDPOINT + "/carts";

    public static final String ORDERS_ADMIN = ADMIN_ENDPOINT + "/orders";

    public static final String PRODUCT_CREATE = ADMIN_ENDPOINT + PRODUCTS;

    public static final String PRODUCT_EDIT = ADMIN_ENDPOINT + PRODUCT + "/edit";

    public static final String PRODUCT_DELETE = ADMIN_ENDPOINT + PRODUCT;
}
