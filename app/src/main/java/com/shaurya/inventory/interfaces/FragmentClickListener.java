package com.shaurya.inventory.interfaces;

import android.view.View;

import com.shaurya.inventory.model.Product;
import com.shaurya.inventory.model.Variant;

/**
 * Created by shaurya on 07/04/18.
 */

public interface FragmentClickListener {
    void onCompanySelected(int companyID);
    void onProductSelected(Product product);
    void onVariantSelected(Variant variant);
    void onStockModeSelected(int stockMode);
    void onStockInModeSelected(int stockInModeId);
    void onStockOutModeSelected(int stockOutModeId);
    void onSubmitted(String name, int quantity, String batch, String lot, String date);
}
