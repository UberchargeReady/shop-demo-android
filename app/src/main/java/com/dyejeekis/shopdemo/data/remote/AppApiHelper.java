package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.api.CartRequest;
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

    public AppApiHelper() {
        this.executor = ShopDemoApp.getInstance().getExecutorService();
    }

    @Override
    public Result<UserResponse> postSignUp(SignUpRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.post(url, request.getApiHeader(), request.getRequestBody());
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> postLogin(LoginRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.post(url, request.getApiHeader(), request.getRequestBody());
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> getLogout() {
        try {
            String url = ApiEndpoint.BASE_URL + ApiEndpoint.LOGOUT;
            String jsonBody = NetworkUtil.get(url, new ApiHeader(new User()));
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<UserResponse> getUser(UserRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, request.getApiHeader());
            UserResponse response = new UserResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<ProductResponse> getProduct(ProductRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, request.getApiHeader());
            ProductResponse response = new ProductResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<ProductResponse> getCart(CartRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, request.getApiHeader());
            ProductResponse response = new ProductResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    @Override
    public Result<OrderResponse> getOrder(OrderRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.get(url, request.getApiHeader());
            OrderResponse response = new OrderResponse(jsonBody);
            return new Result.Success<>(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error<>(e);
        }
    }

    public void doSignUpApiCallAsync(SignUpRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = postSignUp(request);
            callback.onComplete(result);
        });
    }

    public void doLoginApiCallAsync(LoginRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = postLogin(request);
            callback.onComplete(result);
        });
    }

    public void doLogoutApiCallAsync(ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = getLogout();
            callback.onComplete(result);
        });
    }

    public void doUserApiCallAsync(UserRequest request, ApiCallback<UserResponse> callback) {
        executor.execute(() -> {
            Result<UserResponse> result = getUser(request);
            callback.onComplete(result);
        });
    }

    public void doCartApiCallAsync(CartRequest request, ApiCallback<ProductResponse> callback) {
        executor.execute(() -> {
            Result<ProductResponse> result = getCart(request);
            callback.onComplete(result);
        });
    }

    public void doProductApiCallAsync(ProductRequest request, ApiCallback<ProductResponse> callback) {
        executor.execute(() -> {
            Result<ProductResponse> result = getProduct(request);
            callback.onComplete(result);
        });
    }

    public void doOrderApiCallAsync(OrderRequest request, ApiCallback<OrderResponse> callback) {
        executor.execute(() -> {
            Result<OrderResponse> result = getOrder(request);
            callback.onComplete(result);
        });
    }
}
