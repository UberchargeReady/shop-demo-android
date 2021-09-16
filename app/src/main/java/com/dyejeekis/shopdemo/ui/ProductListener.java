package com.dyejeekis.shopdemo.ui;

import android.view.View;

import com.dyejeekis.shopdemo.data.model.Product;

public interface ProductListener {

    View.OnClickListener onProductClick(Product product);

    View.OnClickListener onMinusClick(Product product);

    View.OnClickListener onPlusClick(Product product);

    View.OnClickListener onCartClick(Product product);

    boolean cartVisible();
}
