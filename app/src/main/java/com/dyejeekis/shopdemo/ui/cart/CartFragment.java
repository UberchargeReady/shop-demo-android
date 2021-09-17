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
import com.dyejeekis.shopdemo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ProductListener {

    private CartViewModel cartViewModel;
    private FragmentCartBinding binding;
    private ProductsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.buttonCheckout.setOnClickListener(v -> {
            List<Product> products = cartViewModel.getCartMutable().getValue();
            if (products != null && products.size() > 0) {
                cartViewModel.makeOrder();
            } else Util.displayShortToast(getContext(), "Cart is empty");
        });

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartMutable().observe(getViewLifecycleOwner(), this::onCartUpdated);
        cartViewModel.loadCart();

        return binding.getRoot();
    }

    private void onCartUpdated(ProductList cart) {
        List<Entity> items = new ArrayList<>(cart);
        items.add(new ProductsAdapter.ProductsTotal("", cart.getTotalCost()));
        adapter = new ProductsAdapter(items, this);
        binding.recyclerViewCart.setAdapter(adapter);
    }

    private int getCartIndex(Product product) {
        return cartViewModel.getCartMutable().getValue().indexOf(product);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View.OnClickListener onProductClick(Product product) {
        return null;
    }

    @Override
    public View.OnClickListener onMinusClick(Product product) {
        return v -> {
            if (product.getSelectedQuantity() > 1) {
                product.decreaseQuantity();
                cartViewModel.decreaseQuantity(product);
            } else {
                cartViewModel.removeFromCart(product);
            }
        };
    }

    @Override
    public View.OnClickListener onPlusClick(Product product) {
        return v -> {
            product.increaseQuantity();
            cartViewModel.increaseQuantity(product);
        };
    }

    @Override
    public View.OnClickListener onCartClick(Product product) {
        return v -> {
            cartViewModel.removeFromCart(product);
        };
    }

    @Override
    public boolean cartVisible() {
        return true;
    }
}