package com.dyejeekis.shopdemo.ui;

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

    public void bindItem(Product product, ProductListeners listener) {
        binding.textViewName.setText(product.getName());
        binding.textViewPrice.setText("$" + String.format("%.02f", product.getPrice()));
        binding.textViewQuantity.setText(product.getSelectedQuantity());
        binding.textViewRemove.setOnClickListener(listener.onRemoveClick(product));
        binding.textViewAdd.setOnClickListener(listener.onAddClick(product));
        binding.imageButtonCart.setOnClickListener(listener.onCartClick(product));
        binding.layoutProduct.setOnClickListener(listener.onProductClick(product));
    }
}
