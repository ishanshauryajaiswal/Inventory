package com.shaurya.inventory.db;

/**
 * Created by ishan.jaiswal on 3/23/2018.
 */

public class DBConstants {


    public static final String TABLE_VARIANTS = "Variants";
    public static final String TABLE_PRODUCTS = "Products";
    public static final String TABLE_CUSTOMERS = "Customers";

    public static final String DB_NAME = "INVENTORY_DB";
    public static final int DB_VERSION = 1;

    //TABLE PRODUCTS
    public static final String PRODUCTS_PRODUCT_ID = "product_id";
    public static final String PRODUCTS_COMPANY_ID  = "company_id";
    public static final String PRODUCTS_COMPANY_NAME  = "company_name";
    public static final String PRODUCTS_PRODUCT_NAME = "product_name";
    public static final String PRODUCTS_PRODUCT_IMAGE = "product_image";


    //TABLE VARIANTS
    public static final String VARIANTS_PRODUCTID = "product_id";
    public static final String VARIANTS_QUANTITY = "quantity";
    public static final String VARIANTS_UNIT = "unit";
    public static final String VARIANTS_PRICE = "price";
    public static final String VARIANTS_MRP = "mrp";
    public static final String VARIANTS_QUANTITY_FIXED = "quantity_fixed";
    public static final String VARIANTS_QUANTITY_VARIABLE = "quantity_variable";

    //TABLE CUSTOMERS
    public static final String CUSTOMERS_NAME = "name";


    //CREATE TABLES
    public static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS "+
            TABLE_PRODUCTS+"("+
            PRODUCTS_PRODUCT_ID+ " INTEGER, "+
            PRODUCTS_COMPANY_ID+ " INTEGER, "+
            PRODUCTS_COMPANY_NAME+ " TEXT, "+
            PRODUCTS_PRODUCT_NAME + " TEXT, "+
            PRODUCTS_PRODUCT_IMAGE + " TEXT,"+
            "PRIMARY KEY ("+ PRODUCTS_PRODUCT_ID+","+PRODUCTS_COMPANY_ID+"))";

    public static final String CREATE_TABLE_VARIANTS = "CREATE TABLE IF NOT EXISTS "+
            TABLE_VARIANTS+"("+
            VARIANTS_PRODUCTID+ " INTEGER, "+
            VARIANTS_QUANTITY+ " INTEGER, "+
            VARIANTS_UNIT+ " TEXT, "+
            VARIANTS_PRICE+ " INTEGER, "+
            VARIANTS_MRP+ " INTEGER, "+
            VARIANTS_QUANTITY_FIXED+ " INTEGER, "+
            VARIANTS_QUANTITY_VARIABLE+ " INTEGER,"+
            "PRIMARY KEY ("+ VARIANTS_PRODUCTID+","+VARIANTS_QUANTITY+"))";

    public static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE IF NOT EXISTS "+
            TABLE_CUSTOMERS+"("+ CUSTOMERS_NAME+ " TEXT PRIMARY KEY)";
}
