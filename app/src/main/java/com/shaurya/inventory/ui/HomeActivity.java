package com.shaurya.inventory.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shaurya.inventory.interfaces.FragmentClickListener;
import com.shaurya.inventory.model.Inventory;
import com.shaurya.inventory.model.MainObject;
import com.shaurya.inventory.model.Product;
import com.shaurya.inventory.model.Variant;
import com.shaurya.inventory.R;
import com.shaurya.inventory.db.DBHelper;
import com.shaurya.inventory.util.DefaultValues;
import com.shaurya.inventory.util.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaurya on 07/04/18.
 */

public class HomeActivity extends AppCompatActivity implements FragmentClickListener{

    FragmentManager fragmentManager;
    private MainObject mainObject;
    private ProgressBar progressBar;
    private int localDbVersion,serverDbVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_home);
        setUpLocalDatabase();
        mainObject = new MainObject();
        CompanyFragment companyFragment = CompanyFragment.getInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,companyFragment,"tag_fragment_company")
                .commit();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void setUpLocalDatabase() {
        final SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.db_name),MODE_PRIVATE);
        localDbVersion = sharedPreferences.getInt(getString(R.string.db_version),0);
        serverDbVersion = 0;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DatabaseVersion");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                progressBar.setVisibility(View.GONE);
                if (e == null) {
                    ParseObject parseObject = objects.get(0);
                    serverDbVersion = parseObject.getInt("db_version");
                    if (localDbVersion != serverDbVersion) {
                        getDataFromServer();
                        sharedPreferences.edit().putInt(getString(R.string.db_version),serverDbVersion).commit();
                    }
                }
            }
        });
    }

    @Override
    public void onCompanySelected(int companyId) {
        mainObject.setCompanyID(companyId);
        ProductFragment productFragment = ProductFragment.getInstance(this,companyId);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,productFragment,"tag_fragment_product")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onProductSelected(Product product) {
        mainObject.setProduct(product);
        ProductFragment productFragment = (ProductFragment)fragmentManager.findFragmentByTag("tag_fragment_product");
        productFragment.showDialog(product.getProductId());
    }

    @Override
    public void onVariantSelected(Variant variant) {
        mainObject.setVariant(variant);
        StockInOutFragment stockInOutFragment = StockInOutFragment.getInstance();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,stockInOutFragment,"tag_fragment_stockInOut")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStockModeSelected(int stockMode) {
        mainObject.setStockMode(stockMode);
        if (stockMode == 1){
            StockInDetailFragment stockInDetailFragment = StockInDetailFragment.getInstance();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer,stockInDetailFragment,"tag_fragment_stockInDetail")
                    .addToBackStack(null)
                    .commit();
        }
        else if (stockMode == 2){
            StockOutDetailFragment stockOutDetailFragment = StockOutDetailFragment.getInstance();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer,stockOutDetailFragment,"tag_fragment_stockOutDetail")
                    .addToBackStack(null)
                    .commit();
        }
        else {
            Toast.makeText(HomeActivity.this,"Under Development",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStockInModeSelected(int modeId) {
        mainObject.setStockDetailMode(modeId);
        MainFragment mainFragment = MainFragment.getInstance(this, mainObject);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,mainFragment,"tag_fragment_main")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStockOutModeSelected(int modeId) {
        mainObject.setStockDetailMode(modeId);
        MainFragment mainFragment = MainFragment.getInstance(this, mainObject);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,mainFragment,"tag_fragment_main")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSubmitted(String name, int quantity, String batch, String lot, String date) {
        mainObject.setDistributorName(name);
        mainObject.setQuantity(quantity);
        mainObject.setBatchNo(batch);
        mainObject.setLotNo(lot);
        mainObject.setDate(date);
        Product product = mainObject.getProduct();
        Variant variant = mainObject.getVariant();
        Inventory inventory = new Inventory();
        inventory.setStockMode(Maps.stockModeMap.get(mainObject.getStockMode()));
        inventory.setCompanyName(product.getCompanyName());
        inventory.setProduct(product.getProductName());
        inventory.setVariant(variant.getQuantity() + " "+ variant.getUnit());
        inventory.setQuantity(quantity);
        inventory.setPrice(variant.getPrice()*quantity);
        inventory.setMrp(variant.getMrp()*quantity);
        inventory.setName(name);
        inventory.setSource(Maps.sourceMap.get(mainObject.getStockMode()).get(mainObject.getStockDetailMode()));
        inventory.setBatch(batch);
        inventory.setLot(lot);

        DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
        dbHelper.insertCustomer(name);

        ParseObject parseObject = new ParseObject("Inventory");
        parseObject.put("StockMode",inventory.getStockMode());
        parseObject.put("CompanyName",inventory.getCompanyName());
        parseObject.put("Product",inventory.getProduct());
        parseObject.put("Variant",inventory.getVariant());
        parseObject.put("Quantity",inventory.getQuantity());
        parseObject.put("Price",inventory.getPrice());
        parseObject.put("MRP",inventory.getMrp());
        parseObject.put("Name",inventory.getName());
        parseObject.put("Source",inventory.getSource());
        parseObject.put("Batch",inventory.getBatch());
        parseObject.put("Lot",inventory.getLot());
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null) {
                    Toast.makeText(getApplicationContext(), "Stock Updated", Toast.LENGTH_SHORT).show();
                    while (fragmentManager.getBackStackEntryCount()!=0) {
                        fragmentManager.popBackStackImmediate();
                        Log.d("pop_fragment",fragmentManager.getFragments().toString());
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    Log.e("submit",e.getCode()+"  "+e.getMessage());
                }
            }
        });

    }

    public void getDataFromServer() {
        final DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
        progressBar.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> queryForProduct = ParseQuery.getQuery("Products");
        queryForProduct.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> productList, ParseException e) {
                progressBar.setVisibility(View.GONE);
                if (e == null) {
                    Log.d("products", "Retrieved " + productList.size() + " products");
                    List<Product> mProductList = new ArrayList<>();
                    for (ParseObject p : productList) {
                        Product product = new Product();
                        product.setCompanyId(p.getInt("CompanyID"));
                        product.setProductId(p.getInt("ProductID"));
                        product.setProductName(p.getString("ProductName"));
                        product.setCompanyName(p.getString("Company"));
                        String productImage = p.getString("ProductImage");
                        if (productImage==null)
                            productImage = DefaultValues.stringEncodedLogoImage();
                        product.setProductImage(productImage);
                        mProductList.add(product);
                    }
                    if (mProductList.size()>0)
                        dbHelper.insertProducts(mProductList);
                } else {
                    Log.d("products", "Error: " + e.getMessage());
                }
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> queryForVariant = ParseQuery.getQuery("Variants");
        queryForVariant.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> variantList, ParseException e) {
                progressBar.setVisibility(View.GONE);
                if (e == null) {
                    Log.d("variants", "Retrieved " + variantList.size() + " variants");
                    List<Variant> mVariantList = new ArrayList<>();
                    for (ParseObject p : variantList) {
                        Variant variant = new Variant();
                        variant.setProductID(p.getInt("productID"));
                        variant.setQuantity(p.getInt("quantity"));
                        variant.setUnit(p.getString("unit"));
                        variant.setPrice(p.getInt("price"));
                        variant.setMrp(p.getInt("mrp"));
                        variant.setQuantity_fixed(p.getInt("quantity_fixed"));
                        variant.setQuantity_variable(p.getInt("quantity_variable"));
                        mVariantList.add(variant);
                    }
                    if (mVariantList.size()>0)
                        dbHelper.insertVariants(mVariantList);
                } else {
                    Log.d("variants", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                ParseUser.logOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
                return true;
            case R.id.menu_dashboard:
                startActivity(new Intent(HomeActivity.this,DashboardActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
