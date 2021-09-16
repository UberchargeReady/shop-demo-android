package com.dyejeekis.shopdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dyejeekis.shopdemo.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    public static final String APP_STARTED_KEY = "key.APP_STARTED";

    private ActivityMainBinding binding;
    private NavController navController;
    private boolean appStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_shop, R.id.navigation_cart, R.id.navigation_account)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        binding.navView.setOnItemSelectedListener(this);

        if (savedInstanceState != null) {
            appStarted = savedInstanceState.getBoolean(APP_STARTED_KEY, false);
        }
        onAppStart();
    }

    private void onAppStart() {
        if (!appStarted) {
            if (!ShopDemoApp.getInstance().getCurrentUser().isLoggedIn()) {
                View view = binding.navView.findViewById(R.id.navigation_account);
                view.performClick();
            }
        }
        appStarted = true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(APP_STARTED_KEY, appStarted);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                navController.navigate(R.id.action_global_shop);
                break;
            case R.id.navigation_cart:
                navController.navigate(R.id.action_global_cart);
                break;
            case R.id.navigation_account:
                navController.navigate(R.id.action_global_account);
                break;
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        //return super.onSupportNavigateUp();
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}