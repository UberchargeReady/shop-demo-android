package com.dyejeekis.shopdemo.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.databinding.FragmentShopBinding;
import com.dyejeekis.shopdemo.ui.ProductListener;
import com.dyejeekis.shopdemo.ui.ProductsAdapter;
import com.dyejeekis.shopdemo.ui.cart.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements ProductListener {

    private CartViewModel cartViewModel;
    private ShopViewModel shopViewModel;
    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        binding.recyclerViewShop.setLayoutManager(new LinearLayoutManager(getContext()));

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartMutable().observe(getViewLifecycleOwner(), this::onCartUpdated);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getProductsMutable().observe(getViewLifecycleOwner(), this::onShopUpdated);

        return binding.getRoot();
    }

    private void onCartUpdated(ProductList products) {
        // TODO: 9/16/2021
    }

    private void onShopUpdated(ProductList products) {
        if (products == null) {
            // TODO: 9/14/2021 error
        } else {
            List<Entity> items = new ArrayList<>(products);
            binding.recyclerViewShop.setAdapter(new ProductsAdapter(items, this));
            items.add(new ProductsAdapter.ProductsTitle("", "\n\n"));
        }
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
        return null;
    }

    @Override
    public View.OnClickListener onPlusClick(Product product) {
        return null;
    }

    @Override
    public View.OnClickListener onCartClick(Product product) {
        return null;
    }

    @Override
    public boolean cartVisible() {
        return false;
    }
}