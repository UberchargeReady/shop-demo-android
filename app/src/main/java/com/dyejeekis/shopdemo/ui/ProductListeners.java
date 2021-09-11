package com.dyejeekis.shopdemo.ui;

import android.view.View;

import com.dyejeekis.shopdemo.data.model.Product;

public interface ProductListeners {

    View.OnClickListener onProductClick(Product product);

    View.OnClickListener onRemoveClick(Product product);

    View.OnClickListener onAddClick(Product product);

    View.OnClickListener onCartClick(Product product);
}
