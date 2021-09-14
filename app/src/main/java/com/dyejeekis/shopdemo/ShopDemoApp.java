package com.dyejeekis.shopdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.dyejeekis.shopdemo.data.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShopDemoApp extends Application {

    public static final String USER_FILE_KEY = "key.USER";
    public static final String USER_ID_KEY = "key.USER_ID";
    public static final String USER_NAME_KEY = "key.USER_NAME";
    public static final String USER_TOKEN_KEY = "key.USER_TOKEN";

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
        if (currentUser == null) {
            SharedPreferences sharedPref = getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
            String userId = sharedPref.getString(USER_ID_KEY, null);
            if (userId == null) currentUser = new User();
            else {
                String username = sharedPref.getString(USER_NAME_KEY, null);
                String token = sharedPref.getString(USER_TOKEN_KEY, null);
                currentUser = new User(userId, username, token);
            }
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        SharedPreferences sharedPref = getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_ID_KEY, currentUser.getId());
        editor.putString(USER_NAME_KEY, currentUser.getUsername());
        editor.putString(USER_TOKEN_KEY, currentUser.getToken());
        editor.apply();
    }
}
