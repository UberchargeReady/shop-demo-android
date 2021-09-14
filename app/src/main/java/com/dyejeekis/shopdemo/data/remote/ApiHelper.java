package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.SignUpRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Result<UserResponse> doSignUpApiCall(SignUpRequest request);

    Result<UserResponse> doLoginApiCall(LoginRequest request);

    Result<UserResponse> doLogoutApiCall();

    Result<UserResponse> doUserApiCall(UserRequest request);

    Result<ProductResponse> doProductApiCall(ProductRequest request);

    Result<OrderResponse> doOrderApiCall(OrderRequest request);
}
