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
import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.User;
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
        binding.recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.getUserMutable().observe(getViewLifecycleOwner(), this::onUserChanged);
        accountViewModel.getOrdersMutable().observe(getViewLifecycleOwner(), this::onOrdersChanged);

        return binding.getRoot();
    }

    private void onUserChanged(User user) {
        accountViewModel.setCurrentUser(user);
        if (accountViewModel.isValidUser()) {
            binding.includedAccountAuth.layoutAccountAuth.setVisibility(View.GONE);
            binding.includedAccountInfo.layoutAccountInfo.setVisibility(View.VISIBLE);
            binding.textViewOrdersLabel.setVisibility(View.VISIBLE);
            binding.recyclerViewOrders.setVisibility(View.VISIBLE);

            binding.includedAccountInfo.textViewUsername.setText(user.getUsername());

            accountViewModel.updateOrders();
            //Util.displayShortToast(getContext(), "Logged in as: " + user.getUsername());
        } else {
            binding.includedAccountAuth.layoutAccountAuth.setVisibility(View.VISIBLE);
            binding.includedAccountInfo.layoutAccountInfo.setVisibility(View.GONE);
            binding.textViewOrdersLabel.setVisibility(View.GONE);
            binding.recyclerViewOrders.setVisibility(View.GONE);
            //Util.displayShortToast(getContext(), "Logged out");
        }
    }

    private void onOrdersChanged(List<Order> orders) {
        if (orders != null && !orders.isEmpty()) {
            List<Entity> items = new ArrayList<>();
            for (Order order : orders) {
                List<Product> products = order.getProducts();
                items.addAll(products);

                ProductsAdapter.ProductsTotal total = new ProductsAdapter.ProductsTotal(
                        order.getId(), order.getProducts().getTotalCost());
                items.add(total);
            }
            ProductsAdapter adapter = new ProductsAdapter(items);
            binding.recyclerViewOrders.setAdapter(adapter);
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
                accountViewModel.makeLoginRequest(username, password);
                binding.includedAccountAuth.editTextUsername.setText("");
                binding.includedAccountAuth.editTextPassword.setText("");
                break;
            case R.id.button_logout:
                Util.hideKeyboard(getActivity());
                accountViewModel.makeLogoutRequest();
                break;
            case R.id.button_signup:
                Util.hideKeyboard(getActivity());
                username = binding.includedAccountAuth.editTextUsername.getText().toString();
                password = binding.includedAccountAuth.editTextPassword.getText().toString();
                accountViewModel.makeSignUpRequest(username, password);
                binding.includedAccountAuth.editTextUsername.setText("");
                binding.includedAccountAuth.editTextPassword.setText("");
                break;
        }
    }
}