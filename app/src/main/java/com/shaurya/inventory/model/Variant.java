package com.shaurya.inventory.model;

import java.io.Serializable;

/**
 * Created by shaurya on 08/04/18.
 */

public class Variant implements Serializable{
    private int productID;
    private int quantity;
    private String unit;
    private int price;
    private int mrp;
    private int quantity_fixed;
    private int quantity_variable;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getQuantity_fixed() {
        return quantity_fixed;
    }

    public void setQuantity_fixed(int quantity_fixed) {
        this.quantity_fixed = quantity_fixed;
    }

    public int getQuantity_variable() {
        return quantity_variable;
    }

    public void setQuantity_variable(int quantity_variable) {
        this.quantity_variable = quantity_variable;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String toString(){
        return this.getQuantity()+" "+this.getUnit();
    }
}
