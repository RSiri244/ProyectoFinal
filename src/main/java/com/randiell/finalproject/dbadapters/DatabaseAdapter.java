/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.dbadapters;

import com.randiell.finalproject.models.BaseModel;
import java.util.Collection;

public abstract class DatabaseAdapter {
    // Cambiar que tipo de base de datos se quiere utilizar aqui
    // public static DatabaseAdapter instance = new MemoryDatabaseAdapter();
    public static DatabaseAdapter instance = new MySqlDatabaselAdapter("jdbc:mysql://198.199.85.218:15701/?user=root&password=welc0me123");
    
    public abstract boolean addItem(String collectionName, BaseModel item);
    
    public abstract <T extends BaseModel> Collection<T> getAllItems(String collectionName, Class<T> cls);

    public abstract <T extends BaseModel> T getItem(String collectionName, String filter, Class<T> cls);
    
    public abstract boolean deleteItem(String collectionName, String itemId);
    public boolean deleteItem(String collectionName, BaseModel item) { return this.deleteItem(collectionName, item.getId()); }
    
    public abstract boolean updateItem(String collectionName, BaseModel item);
}
