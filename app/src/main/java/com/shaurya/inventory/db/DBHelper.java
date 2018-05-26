package com.shaurya.inventory.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.shaurya.inventory.model.Product;
import com.shaurya.inventory.model.Variant;
import com.shaurya.inventory.util.DefaultValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaurya on 12/04/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    private static DBConstants D;
    private static DBHelper mInstance;

    private DBHelper(Context context) {
        super(context, D.DB_NAME, null, D.DB_VERSION);

    }

    public static DBHelper getInstance(Context context){
        if (mInstance==null)
            mInstance = new DBHelper(context);
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(D.CREATE_TABLE_PRODUCTS);
            db.execSQL(D.CREATE_TABLE_VARIANTS);
            db.execSQL(D.CREATE_TABLE_CUSTOMERS);
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + D.CREATE_TABLE_PRODUCTS);
            db.execSQL("DROP TABLE IF EXISTS " + D.CREATE_TABLE_VARIANTS);
            db.execSQL("DROP TABLE IF EXISTS " + D.CREATE_TABLE_CUSTOMERS);
            onCreate(db);
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    public void insertProducts(List<Product> productList){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        try {
            db.beginTransaction();
            String sql = "insert or replace into "+ D.TABLE_PRODUCTS + " VALUES (?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            for (Product product : productList) {
                statement.clearBindings();
                statement.bindLong(1, product.getProductId());
                statement.bindLong(2, product.getCompanyId());
                statement.bindString(3, product.getCompanyName());
                statement.bindString(4, product.getProductName());
                if (product.getProductImage() == null)
                    product.setProductImage(DefaultValues.stringEncodedLogoImage());
                statement.bindString(5, product.getProductImage());
                statement.executeInsert();
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }

    public void insertVariants(List<Variant> variantList){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        try {
            db.beginTransaction();
            String sql = "insert or replace into "+ D.TABLE_VARIANTS + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            for (Variant variant : variantList) {
                statement.clearBindings();
                statement.bindLong(1, variant.getProductID());
                statement.bindLong(2, variant.getQuantity());
                statement.bindString(3, variant.getUnit());
                statement.bindLong(4, variant.getPrice());
                statement.bindLong(5, variant.getMrp());
                statement.bindLong(6, variant.getQuantity_fixed());
                statement.bindLong(7, variant.getQuantity_variable());
                statement.executeInsert();
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }

    public void insertCustomer(String name){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        try {
            db.beginTransaction();
            String sql = "insert or replace into " + D.TABLE_CUSTOMERS + " VALUES (?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, name);
            statement.executeInsert();
            db.setTransactionSuccessful();
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }

    public List<Product> getProducts(){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db.beginTransaction();
            String query = "SELECT * FROM " + D.TABLE_PRODUCTS;
            cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Product product = new Product();
                    product.setProductId(cursor.getInt(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_ID)));
                    product.setCompanyId(cursor.getInt(cursor.getColumnIndex(D.PRODUCTS_COMPANY_ID)));
                    product.setCompanyName(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_COMPANY_NAME)));
                    product.setProductName(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_NAME)));
                    product.setProductImage(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_IMAGE)));
                    productList.add(product);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
            return productList;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.endTransaction();
        }
        return null;
    }

    public List<Product> getProductsBasedOnCompanyID(int companyID){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db.beginTransaction();
            String query = "SELECT * FROM " + D.TABLE_PRODUCTS + " WHERE " + D.PRODUCTS_COMPANY_ID + "=" +companyID;
            cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Product product = new Product();
                    product.setProductId(cursor.getInt(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_ID)));
                    product.setCompanyId(cursor.getInt(cursor.getColumnIndex(D.PRODUCTS_COMPANY_ID)));
                    product.setCompanyName(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_COMPANY_NAME)));
                    product.setProductName(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_NAME)));
                    product.setProductImage(cursor.getString(cursor.getColumnIndex(D.PRODUCTS_PRODUCT_IMAGE)));
                    productList.add(product);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
            return productList;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.endTransaction();
        }
        return null;
    }

    public List<Variant> getVariants(){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        List<Variant> variantList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db.beginTransaction();
            String query = "SELECT * FROM " + D.TABLE_VARIANTS;
            cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Variant variant = new Variant();
                    variant.setProductID(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_PRODUCTID)));
                    variant.setQuantity(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY)));
                    variant.setUnit(cursor.getString(cursor.getColumnIndex(D.VARIANTS_UNIT)));
                    variant.setPrice(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_PRICE)));
                    variant.setMrp(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_MRP)));
                    variant.setQuantity_fixed(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY_FIXED)));
                    variant.setQuantity_variable(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY_VARIABLE)));
                    variantList.add(variant);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
            return variantList;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.endTransaction();
        }
        return null;
    }

    public List<Variant> getVariantsBasedOnProductID(int productID){
        SQLiteDatabase db = mInstance.getWritableDatabase();
        List<Variant> variantList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db.beginTransaction();
            String query = "SELECT * FROM " + D.TABLE_VARIANTS + " WHERE " + D.VARIANTS_PRODUCTID + "=" + productID;
            cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Variant variant = new Variant();
                    variant.setProductID(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_PRODUCTID)));
                    variant.setQuantity(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY)));
                    variant.setUnit(cursor.getString(cursor.getColumnIndex(D.VARIANTS_UNIT)));
                    variant.setPrice(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_PRICE)));
                    variant.setMrp(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_MRP)));
                    variant.setQuantity_fixed(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY_FIXED)));
                    variant.setQuantity_variable(cursor.getInt(cursor.getColumnIndex(D.VARIANTS_QUANTITY_VARIABLE)));
                    variantList.add(variant);
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
            return variantList;
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        finally {
            if (cursor!=null)
                cursor.close();
            db.endTransaction();
        }
        return null;
    }

    public List<String > getCustomers() {
        SQLiteDatabase db = mInstance.getWritableDatabase();
        List<String> customerList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db.beginTransaction();
            String query = "SELECT * FROM " + D.TABLE_CUSTOMERS;
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    customerList.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        } finally {
            if (cursor!=null)
                if (cursor!=null)
                cursor.close();
            db.endTransaction();
        }
        return null;
    }
}
