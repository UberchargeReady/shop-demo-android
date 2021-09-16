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
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.databinding.FragmentShopBinding;
import com.dyejeekis.shopdemo.ui.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        binding.recyclerViewShop.setLayoutManager(new LinearLayoutManager(getContext()));

        shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getProductsMutable().observe(getViewLifecycleOwner(), this::onShopUpdated);

        return binding.getRoot();
    }

    private void onShopUpdated(ProductList productList) {
        if (productList == null) {
            // TODO: 9/14/2021 error
        } else {
            List<Entity> items = new ArrayList<>(productList);
            binding.recyclerViewShop.setAdapter(new ProductsAdapter(items));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}