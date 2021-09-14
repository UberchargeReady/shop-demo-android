package com.dyejeekis.shopdemo.ui.account;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;
import com.dyejeekis.shopdemo.data.remote.AppApiHelper;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.LoginRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;

import java.util.List;

public class AccountViewModel extends ViewModel {

    private final AppApiHelper appApiHelper = new AppApiHelper(
            new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));

    private final MutableLiveData<User> userMutable = new MutableLiveData<>();

    private MutableLiveData<List<Order>> ordersMutable;

    public AppApiHelper getAppApiHelper() {
        return appApiHelper;
    }

    public MutableLiveData<User> getUserMutable() {
        return userMutable;
    }

    public MutableLiveData<List<Order>> getOrdersMutable() {
        if (ordersMutable == null) {
            ordersMutable = new MutableLiveData<>();
            loadOrders();
        }
        return ordersMutable;
    }

    public void makeLoginRequest(@NonNull String username, @NonNull String password) {
        LoginRequest request = new LoginRequest(username, password);
        appApiHelper.doLoginApiCallAsync(request, result -> {
            if (result instanceof Result.Success) {
                User newUser = ((Result.Success<UserResponse>) result).data.getUser();
                ShopDemoApp.getInstance().setCurrentUser(newUser);
                getAppApiHelper().getApiHeader().setUser(newUser);
                getUserMutable().setValue(newUser);
                loadOrders();
            } else {
                // TODO: 9/11/2021 indicate unsuccessful login request
            }
        });
    }

    public void makeLogoutRequest() {
        appApiHelper.doLogoutApiCallAsync(result -> {
            if (result instanceof Result.Error) {
                // TODO: 9/11/2021 indicate unsuccessful logout request
            }
            User loggedOut = new User();
            ShopDemoApp.getInstance().setCurrentUser(loggedOut);
            getAppApiHelper().getApiHeader().setUser(loggedOut);
            getUserMutable().setValue(null);
        });
    }

    public void loadOrders() {
        User user = getUserMutable().getValue();
        if (user != null && user.isLoggedIn()) {
            OrderRequest request = new OrderRequest.Builder().ofUser(user).build();
            appApiHelper.doOrderApiCallAsync(request, result -> {
                List<Order> orders;
                if (result instanceof Result.Success) {
                    orders = ((Result.Success<OrderResponse>) result).data.getOrders();
                } else {
                    orders = null;
                }
                getOrdersMutable().setValue(orders);
            });
        }
    }
}