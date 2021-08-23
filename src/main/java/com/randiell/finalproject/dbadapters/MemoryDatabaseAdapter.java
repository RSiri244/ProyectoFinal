/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.dbadapters;

import com.randiell.finalproject.models.BaseModel;
import java.util.Collection;
import java.util.HashMap;

public class MemoryDatabaseAdapter extends DatabaseAdapter {
    private static final HashMap<String, HashMap> registries = new HashMap<String, HashMap>();
    
    private HashMap<String, BaseModel> getRegistry(String registryName) {
        var registry = registries.get(registryName);
        if(registry == null) {
            registry = new HashMap();
            registries.put(registryName, registry);
        }
        return registry;
    }
        
    @Override
    public boolean addItem(String registryName, BaseModel item) {
        var registry = this.getRegistry(registryName);
         registry.put(item.getId(), item);
         return true;
    }

    @Override
    public <T extends BaseModel> Collection<T> getAllItems(String registryName, Class<T> cls) {
        var items = this.getRegistry(registryName);
        return (Collection<T>)items.values();
    }
    
    @Override
    public <T extends BaseModel> T getItem(String registryName, String filter, Class<T> cls) {
        var registry = this.getRegistry(registryName);
        return (T)registry.get(filter);
    }

    @Override
    public boolean deleteItem(String registryName, String itemId) {
        var registry = this.getRegistry(registryName);
        registry.remove(itemId);
        return true;
    }

    @Override
    public boolean updateItem(String registryName, BaseModel item) {
        var registry = this.getRegistry(registryName);
        registry.put(item.getId(), item);
        return true;
    }
}
