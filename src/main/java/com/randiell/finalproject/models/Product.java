/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models;

import java.util.HashMap;

public class Product extends BaseModel {
    private static final String FIELD_NAME = "name";
    private static final String FIELD_BRAND = "brand";
    private static final String FIELD_CATEGORY = "category";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_STOCK = "stock";

    public Product() { super(); }
    public Product(Product other) { super(other); }
    public Product(HashMap other) { super(other); }
    
    public String getName() { return (String)this.hash.get(FIELD_NAME); }
    public void setName(String value){ this.hash.put(FIELD_NAME, value); }

    public String getBrand() { return (String)this.hash.get(FIELD_BRAND); }
    public void setBrand(String value){ this.hash.put(FIELD_BRAND, value); }

    public String getCategory() { return (String)this.hash.get(FIELD_CATEGORY); }
    public void setCategory(String value){ this.hash.put(FIELD_CATEGORY, value); }

    public double getPrice() { return (double)this.hash.get(FIELD_PRICE); }
    public void setPrice(double value){ this.hash.put(FIELD_PRICE, value); }

    public int getStock() { return (int)this.hash.get(FIELD_STOCK); }
    public void setStock(int value){ this.hash.put(FIELD_STOCK, value); }
}
