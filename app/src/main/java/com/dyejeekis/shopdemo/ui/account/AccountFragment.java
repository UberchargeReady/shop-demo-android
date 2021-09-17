package com.dyejeekis.shopdemo.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dyejeekis.shopdemo.R;
import com.dyejeekis.shopdemo.ShopDemoApp;
import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.AppApiCallback;
import com.dyejeekis.shopdemo.data.remote.Result;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;
import com.dyejeekis.shopdemo.databinding.FragmentAccountBinding;
import com.dyejeekis.shopdemo.ui.ProductsAdapter;
import com.dyejeekis.shopdemo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private AccountViewModel accountViewModel;
    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        binding.includedAccountAuth.buttonLogin.setOnClickListener(this);
        binding.includedAccountAuth.buttonSignup.setOnClickListener(this);
        binding.includedAccountInfo.buttonLogout.setOnClickListener(this);
        binding.nestedScrollView.setNestedScrollingEnabled(false);
        binding.recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.getUserMutable().observe(getViewLifecycleOwner(), this::onUserUpdated);
        accountViewModel.getOrdersMutable().observe(getViewLifecycleOwner(), this::onOrdersUpdated);

        return binding.getRoot();
    }

    private void onUserUpdated(User user) {
        accountViewModel.setCurrentUser(user);
        if (accountViewModel.isValidUser()) {
            binding.includedAccountAuth.layoutAccountAuth.setVisibility(View.GONE);
            binding.includedAccountInfo.layoutAccountInfo.setVisibility(View.VISIBLE);
            binding.textViewOrdersLabel.setVisibility(View.VISIBLE);
            binding.recyclerViewOrders.setVisibility(View.VISIBLE);

            binding.includedAccountInfo.textViewUsername.setText(user.getUsername());

            accountViewModel.loadOrders();
        } else {
            binding.includedAccountAuth.layoutAccountAuth.setVisibility(View.VISIBLE);
            binding.includedAccountInfo.layoutAccountInfo.setVisibility(View.GONE);
            binding.textViewOrdersLabel.setVisibility(View.GONE);
            binding.recyclerViewOrders.setVisibility(View.GONE);
        }
    }

    private void onOrdersUpdated(List<Order> orders) {
        if (orders != null && !orders.isEmpty()) {
            List<Entity> items = new ArrayList<>();
            for (Order order : orders) {
                List<Product> products = order.getProducts();
                items.add(new ProductsAdapter.ProductsTitle("", order.getDateCreated()));
                items.addAll(products);

                ProductsAdapter.ProductsTotal total = new ProductsAdapter.ProductsTotal(
                        order.getId(), order.getProducts().getTotalCost());
                items.add(total);
            }
            items.add(new ProductsAdapter.ProductsTitle("", "\n\n"));
            ProductsAdapter adapter = new ProductsAdapter(items);
            binding.recyclerViewOrders.setAdapter(adapter);
        } else {
            binding.recyclerViewOrders.setAdapter(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                Util.hideKeyboard(getActivity());
                String username = binding.includedAccountAuth.editTextUsername.getText().toString();
                String password = binding.includedAccountAuth.editTextPassword.getText().toString();
                accountViewModel.makeLoginRequest(username, password,
                        new AppApiCallback<>(getContext(), result -> {
                            if (result instanceof Result.Success) {
                                User user = ((Result.Success<UserResponse>) result).data.getUser();
                                Util.displayShortToast(getContext(), "Logged in as: " + user.getUsername());
                            }
                            else Util.displayShortToast(getContext(), "Incorrect credentials");
                        }));
                binding.includedAccountAuth.editTextUsername.setText("");
                binding.includedAccountAuth.editTextPassword.setText("");
                break;
            case R.id.button_logout:
                Util.hideKeyboard(getActivity());
                accountViewModel.makeLogoutRequest(new AppApiCallback<>(getContext(), result -> {
                    ShopDemoApp.getInstance().setCurrentUser(new User());
                    if (result instanceof Result.Success)
                        Util.displayShortToast(getContext(), "Logged out");
                }));
                break;
            case R.id.button_signup:
                Util.hideKeyboard(getActivity());
                username = binding.includedAccountAuth.editTextUsername.getText().toString();
                password = binding.includedAccountAuth.editTextPassword.getText().toString();
                accountViewModel.makeSignUpRequest(username, password,
                        new AppApiCallback(getContext(), result -> {
                            if (result instanceof Result.Success) {
                                Util.displayShortToast(getContext(), "Sign up successful");
                            }
                            else Util.displayShortToast(getContext(), "Sign up failed");
                        }));
                binding.includedAccountAuth.editTextUsername.setText("");
                binding.includedAccountAuth.editTextPassword.setText("");
                break;
        }
    }
}