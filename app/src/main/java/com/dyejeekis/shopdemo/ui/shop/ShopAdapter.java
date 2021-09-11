package com.dyejeekis.shopdemo.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.databinding.ProductItemViewBinding;
import com.dyejeekis.shopdemo.ui.ProductListeners;
import com.dyejeekis.shopdemo.ui.ProductViewHolder;

public class ShopAdapter extends ListAdapter<Product, ProductViewHolder> implements ProductListeners {

    // TODO: 9/11/2021

    protected ShopAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemViewBinding binding = ProductItemViewBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // TODO: 9/11/2021
        //holder.bindItem(product, this);
    }

    @Override
    public View.OnClickListener onProductClick(Product product) {
        return v -> {
            // TODO: 9/11/2021
        };
    }

    @Override
    public View.OnClickListener onRemoveClick(Product product) {
        return v -> {
            // TODO: 9/11/2021
        };
    }

    @Override
    public View.OnClickListener onAddClick(Product product) {
        return v -> {
            // TODO: 9/11/2021
        };
    }

    @Override
    public View.OnClickListener onCartClick(Product product) {
        return v -> {
            // TODO: 9/11/2021
        };
    }
}
