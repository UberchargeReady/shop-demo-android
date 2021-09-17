package com.dyejeekis.shopdemo.ui.account;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiCallback;
import com.dyejeekis.shopdemo.data.remote.AppApiCallback;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.SignUpRequest;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;
import com.dyejeekis.shopdemo.ui.BaseViewModel;

import java.util.List;

public class AccountViewModel extends BaseViewModel {

    private final AppApiHelper appApiHelper = new AppApiHelper();

    private MutableLiveData<User> userMutable;

    private MutableLiveData<List<Order>> ordersMutable;

    public MutableLiveData<User> getUserMutable() {
        if (userMutable == null) {
            userMutable = new MutableLiveData<>();
            updateUserMutable(getCurrentUser());
        }
        return userMutable;
    }

    public MutableLiveData<List<Order>> getOrdersMutable() {
        if (ordersMutable == null) {
            ordersMutable = new MutableLiveData<>();
            //updateOrders();
        }
        return ordersMutable;
    }

    public void makeLoginRequest(@NonNull String username, @NonNull String password,
                                 AppApiCallback<UserResponse> callback) {
        LoginRequest request = new LoginRequest(username, password);
        appApiHelper.getExecutor().execute(() -> {
            Result<UserResponse> result = appApiHelper.postLogin(request);
            if (result instanceof Result.Success)
                updateUserMutable(((Result.Success<UserResponse>) result).data.getUser());
            callback.onComplete(result);
        });
    }

    public void makeLogoutRequest(AppApiCallback<UserResponse> callback) {
        appApiHelper.getExecutor().execute(() -> {
            userMutable.postValue(new User());
            Result<UserResponse> result = appApiHelper.getLogout();
            callback.onComplete(result);
        });
    }

    public void makeSignUpRequest(@NonNull String username, @NonNull String password,
                                  AppApiCallback<UserResponse> callback) {
        SignUpRequest request = new SignUpRequest(username, password);
        appApiHelper.getExecutor().execute(() -> {
            Result<UserResponse> result = appApiHelper.postSignUp(request);
            if (result instanceof Result.Success) {
                updateUserMutable(((Result.Success<UserResponse>) result).data.getUser());
            }
            callback.onComplete(result);
        });
    }

    private void updateUserMutable(User user) {
        getUserMutable().postValue(user);
    }

    public void updateOrders() {
        if (isValidUser()) {
            OrderRequest request = new OrderRequest.Builder(getApiHeader()).ofUserLoggedIn().build();
            appApiHelper.getExecutor().execute(() -> {
                Result<OrderResponse> result = appApiHelper.getOrder(request);
                List<Order> orders = null;
                if (result instanceof Result.Success)
                    orders = ((Result.Success<OrderResponse>) result).data.getOrders();
                getOrdersMutable().postValue(orders);
            });
        }
    }
}