package com.dyejeekis.shopdemo.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.databinding.ProductsTotalItemViewBinding;

public class ProductsTotalViewHolder extends RecyclerView.ViewHolder {

    private final ProductsTotalItemViewBinding binding;

    public ProductsTotalViewHolder(ProductsTotalItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ProductsTotalItemViewBinding getBinding() {
        return binding;
    }

    public void bindItem(ProductList productList) {
        binding.textViewTotalCost.setText("$" + String.format("%.02f", productList.getTotalCost()));
    }
}
