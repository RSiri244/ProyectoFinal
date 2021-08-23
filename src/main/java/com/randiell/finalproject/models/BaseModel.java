/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models;

import java.util.HashMap;
import java.util.UUID;

public class BaseModel {
    private static final String FIELD_ID = "id";
    protected HashMap<String, Object> hash = new HashMap<String, Object>();
    
    public BaseModel(){
        hash.put(FIELD_ID, String.valueOf(UUID.randomUUID()));
    }
    
    public BaseModel(String id){
        hash.put(FIELD_ID, id);
    }
   
    public BaseModel(HashMap<String, Object> hash){
        this.hash = hash;
    }
    
    public BaseModel(BaseModel original){
        this.hash = original.toHashMap();
    }
    
    public String getId() {
        return (String)hash.get(FIELD_ID);
    }
    
    @Override
    public String toString(){
        return String.format("%s {%s}", this.getClass().getSimpleName(), this.getId());
    }
    
    public HashMap<String, Object> toHashMap() {
        var newHash = new HashMap<String, Object>();
        newHash.putAll(hash);
        return newHash;
    }
}
