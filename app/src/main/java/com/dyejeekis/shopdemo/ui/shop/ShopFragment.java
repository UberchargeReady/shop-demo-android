package com.dyejeekis.shopdemo.ui.shop;

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

import com.dyejeekis.shopdemo.databinding.FragmentShopBinding;
import com.dyejeekis.shopdemo.ui.ProductsAdapter;

public class ShopFragment extends Fragment {

    private ShopViewModel shopViewModel;
    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        binding.recyclerViewShop.setLayoutManager(new LinearLayoutManager(getContext()));

        shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getProductsMutable().observe(getViewLifecycleOwner(), productList -> {
            if (productList == null) {
                // TODO: 9/14/2021 error
            } else {
                binding.recyclerViewShop.setAdapter(new ProductsAdapter(productList));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}