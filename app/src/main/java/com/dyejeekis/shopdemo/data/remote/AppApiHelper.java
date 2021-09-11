package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.LoginResponse;
import com.dyejeekis.shopdemo.data.remote.api.LogoutResponse;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.UserRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;
import com.dyejeekis.shopdemo.util.NetworkUtil;

import java.util.concurrent.Executor;

public class AppApiHelper implements ApiHelper {

    private final Executor executor;
    private final ApiHeader apiHeader;

    public AppApiHelper(ApiHeader apiHeader) {
        this.apiHeader = apiHeader;
        this.executor = ShopDemoApp.getInstance().getExecutorService();
    }

    @Override
    public ApiHeader getApiHeader() {
        return apiHeader;
    }

    @Override
    public Result<LoginResponse> doLoginApiCall(LoginRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.ENDPOINT_LOGIN;
            String jsonBody = NetworkUtil.post(url, apiHeader, request.getRequestBody());
            LoginResponse loginResponse = new LoginResponse(jsonBody);
            return new Result.Success<>(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<LogoutResponse> doLogoutApiCall() {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.ENDPOINT_LOGOUT;
            String jsonBody = NetworkUtil.get(url, apiHeader);
            LogoutResponse logoutResponse = new LogoutResponse(jsonBody);
            return new Result.Success<>(logoutResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> doUserApiCall(UserRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.ENDPOINT_USER + request.getQueryString();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            UserResponse userResponse = new UserResponse(jsonBody);
            return new Result.Success<>(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<ProductResponse> doProductApiCall(ProductRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.ENDPOINT_PRODUCT + request.getQueryString();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            ProductResponse productResponse = new ProductResponse(jsonBody);
            return new Result.Success<>(productResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<OrderResponse> doOrderApiCall(OrderRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.ENDPOINT_ORDER + request.getQueryString();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            OrderResponse orderResponse = new OrderResponse(jsonBody);
            return new Result.Success<>(orderResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    public void doLoginApiCallAsync(LoginRequest request, ApiCallback<LoginResponse> callback) {
        executor.execute(() -> {
            Result<LoginResponse> result = doLoginApiCall(request);
            callback.onComplete(result);
        });
    }

    public void doLogoutApiCallAsync(ApiCallback<LogoutResponse> callback) {
        executor.execute(() -> {
            Result<LogoutResponse> result = doLogoutApiCall();
            callback.onComplete(result);
        });
    }

    public void doUserApiCallAsync(UserRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = doUserApiCall(request);
            callback.onComplete(result);
        });
    }

    public void doProductApiCallAsync(ProductRequest request, ApiCallback<ProductResponse> callback) {
        executor.execute(() -> {
            Result<ProductResponse> result = doProductApiCall(request);
            callback.onComplete(result);
        });
    }

    public void doOrderApiCallAsync(OrderRequest request, ApiCallback<OrderResponse> callback) {
        executor.execute(() -> {
            Result<OrderResponse> result = doOrderApiCall(request);
            callback.onComplete(result);
        });
    }
}
