package com.dyejeekis.shopdemo.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyejeekis.shopdemo.R;
import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.databinding.ProductItemViewBinding;
import com.dyejeekis.shopdemo.databinding.ProductsTitleItemViewBinding;
import com.dyejeekis.shopdemo.databinding.ProductsTotalItemViewBinding;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_PRODUCT = 0;
    public static final int VIEW_TYPE_TOTAL = 1;
    public static final int VIEW_TYPE_TITLE = 2;

    private List<Entity> items;
    private final ProductListener listener;

    public ProductsAdapter(List<Entity> items) {
        assert items != null;
        this.items = items;
        this.listener = null;
    }

    public ProductsAdapter(List<Entity> items, ProductListener listener) {
        assert items != null;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PRODUCT:
                ProductItemViewBinding productBinding = ProductItemViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new ProductViewHolder(productBinding);
            case VIEW_TYPE_TOTAL:
                ProductsTotalItemViewBinding totalBinding = ProductsTotalItemViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new ProductsTotalViewHolder(totalBinding);
            case VIEW_TYPE_TITLE:
                ProductsTitleItemViewBinding titleBinding = ProductsTitleItemViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                return new ProductsTitleViewHolder(titleBinding);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Entity item = items.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_PRODUCT:
                ProductViewHolder productViewHolder = (ProductViewHolder) holder;
                productViewHolder.bindItem((Product) item, listener);
                if (listener != null && listener.cartVisible())
                    productViewHolder.getBinding().imageButtonCart
                            .setImageResource(R.drawable.baseline_remove_shopping_cart_black_24dp);
                break;
            case VIEW_TYPE_TOTAL:
                ((ProductsTotalViewHolder) holder).bindItem((ProductsTotal) item);
                break;
            case VIEW_TYPE_TITLE:
                ((ProductsTitleViewHolder) holder).bindItem((ProductsTitle) item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Product) {
            return VIEW_TYPE_PRODUCT;
        } else if (items.get(position) instanceof ProductsTotal) {
            return VIEW_TYPE_TOTAL;
        } else if (items.get(position) instanceof ProductsTitle) {
            return VIEW_TYPE_TITLE;
        }
        return super.getItemViewType(position);
    }

    public List<Entity> getItems() {
        return items;
    }

    public void setItems(List<Entity> items) {
        this.items = items;
    }

    public static class ProductsTotal extends Entity {
        float totalCost;

        public ProductsTotal(String id, float totalCost) {
            super(id);
            this.totalCost = totalCost;
        }
    }

    public static class ProductsTitle extends Entity {
        String title;

        public ProductsTitle(String id, String title) {
            super(id);
            this.title = title;
        }
    }

    public static class ProductsTotalViewHolder extends RecyclerView.ViewHolder {

        private final ProductsTotalItemViewBinding binding;

        public ProductsTotalViewHolder(ProductsTotalItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(ProductsTotal productsTotal) {
            binding.textViewTotalCost.setText("$" + String.format("%.02f", productsTotal.totalCost));
        }
    }

    public static class ProductsTitleViewHolder extends RecyclerView.ViewHolder {

        private final ProductsTitleItemViewBinding binding;

        public ProductsTitleViewHolder(ProductsTitleItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(ProductsTitle productsTitle) {
            binding.textViewProductsTitle.setText(productsTitle.title);
        }
    }
}
