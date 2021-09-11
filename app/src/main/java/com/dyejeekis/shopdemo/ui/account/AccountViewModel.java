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
import com.dyejeekis.shopdemo.data.remote.api.LoginResponse;
import com.dyejeekis.shopdemo.data.remote.api.OrderRequest;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;

import java.util.List;

public class AccountViewModel extends ViewModel {

    private final AppApiHelper appApiHelper = new AppApiHelper(
            new ApiHeader(ShopDemoApp.getInstance().getCurrentUser()));
    private final MutableLiveData<User> account = new MutableLiveData<>();
    private MutableLiveData<List<Order>> accountOrders;

    public AppApiHelper getAppApiHelper() {
        return appApiHelper;
    }

    public MutableLiveData<User> getAccount() {
        return account;
    }

    public MutableLiveData<List<Order>> getAccountOrders() {
        if (accountOrders == null) {
            accountOrders = new MutableLiveData<>();
            loadOrders();
        }
        return accountOrders;
    }

    public void makeLoginRequest(@NonNull String username, @NonNull String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        appApiHelper.doLoginApiCallAsync(loginRequest, result -> {
            if (result instanceof Result.Success) {
                User newUser = ((Result.Success<LoginResponse>) result).data.getUser();
                ShopDemoApp.getInstance().setCurrentUser(newUser);
                getAppApiHelper().getApiHeader().setUser(newUser);
                getAccount().setValue(newUser);
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
            getAccount().setValue(null);
        });
    }

    public void loadOrders() {
        User user = getAccount().getValue();
        if (user != null && user.isLoggedIn()) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setQueryUser(user.getId());
            appApiHelper.doOrderApiCallAsync(orderRequest, result -> {
                List<Order> orders;
                if (result instanceof Result.Success) {
                    orders = ((Result.Success<OrderResponse>) result).data.getOrders();
                } else {
                    orders = null;
                }
                getAccountOrders().setValue(orders);
            });
        }
    }
}