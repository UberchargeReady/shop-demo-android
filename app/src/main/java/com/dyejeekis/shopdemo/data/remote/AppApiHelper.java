package com.dyejeekis.shopdemo.data.remote;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.api.CartRequest;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductRequest;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.Request;
import com.dyejeekis.shopdemo.data.remote.api.Response;
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

    public Executor getExecutor() {
        return executor;
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
            String jsonBody = NetworkUtil.get(url,
                    new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));
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
    public Result<ProductResponse> postCart(CartRequest request) {
        try {
            String url = ApiEndpoint.BASE_URL + request.getPath();
            String jsonBody = NetworkUtil.post(url, request.getApiHeader(), request.getBody());
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
}
