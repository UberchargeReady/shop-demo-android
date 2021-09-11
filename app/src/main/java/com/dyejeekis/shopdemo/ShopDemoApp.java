package com.dyejeekis.shopdemo;

import android.app.Application;

import com.dyejeekis.shopdemo.data.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShopDemoApp extends Application {

    private static ShopDemoApp instance;

    public static ShopDemoApp getInstance() {
        return instance;
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public User getCurrentUser() {
        if (currentUser == null)
            currentUser = new User();
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
