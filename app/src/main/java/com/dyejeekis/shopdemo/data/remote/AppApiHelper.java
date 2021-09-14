package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.SignUpRequest;
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
    public Result<UserResponse> doSignUpApiCall(SignUpRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.post(url, apiHeader, request.getRequestBody());
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> doLoginApiCall(LoginRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.post(url, apiHeader, request.getRequestBody());
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> doLogoutApiCall() {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.USER_LOGOUT;
            String jsonBody = NetworkUtil.get(url, apiHeader);
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> doUserApiCall(UserRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<ProductResponse> doProductApiCall(ProductRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            ProductResponse response = new ProductResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<OrderResponse> doOrderApiCall(OrderRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, apiHeader);
            OrderResponse response = new OrderResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    public void doSignUpApiCallAsync(SignUpRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = doSignUpApiCall(request);
            callback.onComplete(result);
        });
    }

    public void doLoginApiCallAsync(LoginRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = doLoginApiCall(request);
            callback.onComplete(result);
        });
    }

    public void doLogoutApiCallAsync(ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = doLogoutApiCall();
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
