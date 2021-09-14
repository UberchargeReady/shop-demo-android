package com.dyejeekis.shopdemo.ui;

import androidx.lifecycle.ViewModel;

import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.ApiHeader;

public abstract class BaseViewModel extends ViewModel {

    public boolean checkUser() {
        User user = getCurrentUser();
        return user != null && user.isLoggedIn();
    }

    public void setCurrentUser(User user) {
        ShopDemoApp.getInstance().setCurrentUser(user);
    }

    public User getCurrentUser() {
        return ShopDemoApp.getInstance().getCurrentUser();
    }

    public ApiHeader getApiHeader() {
        return new ApiHeader(getCurrentUser());
    }
}
