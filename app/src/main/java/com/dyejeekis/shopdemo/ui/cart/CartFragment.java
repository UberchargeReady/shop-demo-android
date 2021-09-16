package com.dyejeekis.shopdemo.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.databinding.FragmentCartBinding;
import com.dyejeekis.shopdemo.ui.ProductListener;
import com.dyejeekis.shopdemo.ui.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ProductListener {

    private CartViewModel cartViewModel;
    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));

        cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCartMutable().observe(getViewLifecycleOwner(), this::onCartUpdated);

        return binding.getRoot();
    }

    private void onCartUpdated(ProductList cart) {
        List<Entity> items = new ArrayList<>(cart);
        items.add(new ProductsAdapter.ProductsTotal("", cart.getTotalCost()));
        binding.recyclerViewCart.setAdapter(new ProductsAdapter(items));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View.OnClickListener onProductClick(Product product) {
        return v -> {
            // TODO: 9/16/2021
        };
    }

    @Override
    public View.OnClickListener onMinusClick(Product product) {
        return v -> {
            cartViewModel.decreaseQuantity(product);
        };
    }

    @Override
    public View.OnClickListener onPlusClick(Product product) {
        return v -> {
            cartViewModel.increaseQuantity(product);
        };
    }

    @Override
    public View.OnClickListener onCartClick(Product product) {
        return v -> {
            cartViewModel.removeFromCart(product);
        };
    }
}