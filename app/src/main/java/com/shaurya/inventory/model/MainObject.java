package com.shaurya.inventory.model;

import java.io.Serializable;

/**
 * Created by shaurya on 09/04/18.
 */

public class MainObject implements Serializable {
    private int companyID;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;private Variant variant;
    private int stockMode;
    private int stockDetailMode;
    String distributorName;
    int quantity;
    String batchNo, lotNo, date;

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getStockMode() {
        return stockMode;
    }

    public void setStockMode(int stockMode) {
        this.stockMode = stockMode;
    }

    public int getStockDetailMode() {
        return stockDetailMode;
    }

    public void setStockDetailMode(int stockDetailMode) {
        this.stockDetailMode = stockDetailMode;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
