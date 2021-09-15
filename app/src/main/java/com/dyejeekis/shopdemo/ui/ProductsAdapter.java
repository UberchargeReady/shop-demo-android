package com.dyejeekis.shopdemo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyejeekis.shopdemo.data.model.Entity;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.databinding.ProductItemViewBinding;
import com.dyejeekis.shopdemo.databinding.ProductsTotalItemViewBinding;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ProductListeners {

    public static final int VIEW_TYPE_PRODUCT = 0;
    public static final int VIEW_TYPE_TOTAL = 1;

    private List<Entity> items;

    public ProductsAdapter(List<Entity> items) {
        assert items != null;
        this.items = items;
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
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object o = items.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_PRODUCT:
                ((ProductViewHolder) holder).bindItem((Product) o, this);
                break;
            case VIEW_TYPE_TOTAL:
                ((ProductsTotalViewHolder) holder).bindItem((ProductsTotal) o);
                break;
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
        }
        return super.getItemViewType(position);
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

    public static class ProductsTotalViewHolder extends RecyclerView.ViewHolder {

        private final ProductsTotalItemViewBinding binding;

        public ProductsTotalViewHolder(ProductsTotalItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ProductsTotalItemViewBinding getBinding() {
            return binding;
        }

        public void bindItem(ProductsTotal totalCost) {
            binding.textViewTotalCost.setText("$" + String.format("%.02f", totalCost));
        }
    }
}
