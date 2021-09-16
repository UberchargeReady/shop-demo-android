package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.data.remote.api.CartRequest;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.SignUpRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;

public interface ApiHelper {

    Result<UserResponse> postSignUp(SignUpRequest request);

    Result<UserResponse> postLogin(LoginRequest request);

    Result<UserResponse> getLogout();

    Result<UserResponse> getUser(UserRequest request);

    Result<ProductResponse> getProduct(ProductRequest request);

    Result<ProductResponse> getCart(CartRequest request);

    Result<ProductResponse>  postCart(CartRequest request);

    Result<OrderResponse> getOrder(OrderRequest request);
}
