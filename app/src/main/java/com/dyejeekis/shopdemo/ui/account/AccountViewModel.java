package com.dyejeekis.shopdemo.ui.account;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.User;
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

    public AppApiHelper getAppApiHelper() {
        return appApiHelper;
    }

    public MutableLiveData<User> getUserMutable() {
        if (userMutable == null) {
            userMutable = new MutableLiveData<>();
            updateUser();
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

    public void makeLoginRequest(@NonNull String username, @NonNull String password) {
        LoginRequest request = new LoginRequest(username, password);
        appApiHelper.doLoginApiCallAsync(request, result -> {
            if (result instanceof Result.Success) {
                getUserMutable().postValue(((Result.Success<UserResponse>) result).data.getUser());
            } else {
                // TODO: 9/15/2021
            }
        });
    }

    public void makeLogoutRequest() {
        appApiHelper.doLogoutApiCallAsync(result -> {
            if (result instanceof Result.Error) {
                // TODO: 9/15/2021
            }
            getUserMutable().postValue(new User());
        });
    }

    public void makeSignUpRequest(@NonNull String username, @NonNull String password) {
        SignUpRequest request = new SignUpRequest(username, password);
        appApiHelper.doSignUpApiCallAsync(request, result -> {
            if (result instanceof Result.Success) {
                getUserMutable().postValue(((Result.Success<UserResponse>) result).data.getUser());
            } else {
                // TODO: 9/15/2021
            }
        });
    }

    public void updateUser() {
        getUserMutable().postValue(getCurrentUser());
    }

    public void updateOrders() {
        if (isValidUser()) {
            OrderRequest request = new OrderRequest.Builder(getApiHeader()).ofUserLoggedIn().build();
            appApiHelper.doOrderApiCallAsync(request, result -> {
                List<Order> orders;
                if (result instanceof Result.Success) {
                    orders = ((Result.Success<OrderResponse>) result).data.getOrders();
                } else {
                    orders = null;
                }
                getOrdersMutable().postValue(orders);
            });
        }
    }
}