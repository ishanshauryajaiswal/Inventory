package com.shaurya.inventory.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shaurya.inventory.R;
import com.shaurya.inventory.adapters.InventoryListAdapter;
import com.shaurya.inventory.model.Inventory;
import com.shaurya.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan.jaiswal on 5/23/2018.
 */

public class DashboardActivity extends AppCompatActivity {

    RecyclerView rvInventory;
    InventoryListAdapter mAdapter;
    List<Inventory> mInventoryList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
        getInventory();
    }

    private void initView() {
        rvInventory = (RecyclerView)findViewById(R.id.rvInventory);
        mAdapter = new InventoryListAdapter(this,mInventoryList);
        rvInventory.setLayoutManager(new LinearLayoutManager(this));
        rvInventory.setAdapter(mAdapter);
    }

    private void getInventory() {
        //progressBar.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> queryForInventory = ParseQuery.getQuery("Inventory");
        queryForInventory.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> inventoryList, ParseException e) {
                //progressBar.setVisibility(View.GONE);
                if (e == null) {
                    Log.d("inventory", "Retrieved " + inventoryList.size() + " products");
                    mInventoryList = new ArrayList<>();
                    for (ParseObject p : inventoryList) {
                        Inventory inventory = new Inventory();
                        inventory.setDate(p.getCreatedAt());
                        inventory.setCompanyName(p.getString("CompanyName"));
                        inventory.setProduct(p.getString("Product"));
                        inventory.setVariant(p.getString("Variant"));
                        inventory.setQuantity(p.getInt("Quantity"));
                        inventory.setStockMode(p.getString("StockMode"));
                        inventory.setSource(p.getString("Source"));
                        mInventoryList.add(inventory);
                    }
                    if (mInventoryList.size()>0) {
                        mAdapter.setList(mInventoryList);
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("products", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
