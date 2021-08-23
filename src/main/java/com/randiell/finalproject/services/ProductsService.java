/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.services;
import com.randiell.finalproject.dbadapters.DatabaseAdapter;
import com.randiell.finalproject.models.Product;
import java.util.Collection;

public class ProductsService {
    public static final String COLLECTION_NAME = "products";

    public static void createProduct(Product product){
        DatabaseAdapter.instance.addItem(COLLECTION_NAME, product);
    }

    public static Collection<Product> getAllProducts() {
        return DatabaseAdapter.instance.<Product>getAllItems(COLLECTION_NAME, Product.class);
    }
    
    public static void deleteProduct(Product product) {
        DatabaseAdapter.instance.deleteItem(COLLECTION_NAME, product);
    }

    public static void updateProduct(Product product) {
        DatabaseAdapter.instance.updateItem(COLLECTION_NAME, product);
    }
    public static void seed() {
        Object[][] products = new Object[][] {
            new Object[] { "Handcrafted Rubber Pants", "Dare, Schmeler and Lueilwitz", "Granite", 90.8, 20 },
            new Object[] { "Licensed Fresh Table", "Graham - Steuber", "Soft", 55.59, 29 },
            new Object[] { "Practical Soft Car", "Glover - Sipes", "Cotton", 58.78, 21 },
            new Object[] { "Rustic Fresh Shoes", "Brown, Brown and Cartwright", "Soft", 48.51, 15 },
            new Object[] { "Small Concrete Chicken", "Graham Group", "Steel", 89.68, 22 },
            new Object[] { "Gorgeous Frozen Bike", "Kreiger, Greenfelder and Christiansen", "Granite", 66.61, 8 },
            new Object[] { "Handcrafted Soft Fish", "Veum and Sons", "Plastic", 63.79, 30 },
            new Object[] { "Gorgeous Metal Shoes", "Koelpin and Sons", "Wooden", 13.86, 12 },
            new Object[] { "Practical Rubber Chips", "Bauch, Gaylord and Wilkinson", "Concrete", 34.39, 25 },
            new Object[] { "Rustic Granite Mouse", "Windler, Simonis and Wisoky", "Frozen", 48.13, 27 }
        };

        for (Object[] object : products) {
            var product = new Product();
            product.setName((String) object[0]);
            product.setBrand((String) object[1]);
            product.setCategory((String) object[2]);
            product.setPrice((double) object[3]);
            product.setStock((int) object[4]);
            DatabaseAdapter.instance.addItem(COLLECTION_NAME, product);
        }
    }
 }
