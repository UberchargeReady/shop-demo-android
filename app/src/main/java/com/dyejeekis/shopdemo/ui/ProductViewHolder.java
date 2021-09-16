package com.dyejeekis.shopdemo.ui;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.databinding.ProductItemViewBinding;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private final ProductItemViewBinding binding;

    public ProductViewHolder(ProductItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ProductItemViewBinding getBinding() {
        return binding;
    }

    public void bindItem(Product product, ProductListener listener) {
        binding.textViewName.setText(product.getName());
        binding.textViewPrice.setText("$" + String.format("%.02f", product.getPrice()));
        binding.textViewQuantity.setText(String.valueOf(product.getSelectedQuantity()));
        if (listener == null) {
            binding.textViewMinus.setVisibility(View.GONE);
            binding.textViewPlus.setVisibility(View.GONE);
            binding.imageButtonCart.setVisibility(View.GONE);
        } else {
            binding.textViewMinus.setOnClickListener(listener.onMinusClick(product));
            binding.textViewPlus.setOnClickListener(listener.onPlusClick(product));
            binding.imageButtonCart.setOnClickListener(listener.onCartClick(product));
            binding.layoutProduct.setOnClickListener(listener.onProductClick(product));
        }
    }

}
