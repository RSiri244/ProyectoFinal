/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.services;
import com.randiell.finalproject.dbadapters.DatabaseAdapter;
import com.randiell.finalproject.models.Migration;

public class MigrationService {
    public static final String COLLECTION_NAME = "migration";

    public static boolean pendingMiration(){
        return DatabaseAdapter.instance.getItem(COLLECTION_NAME, "migration", Migration.class) == null;
    }
    
    public static void startMigration(){
        if(pendingMiration()) {
            DatabaseAdapter.instance.addItem(COLLECTION_NAME, new Migration());
            
            UserService.seed();
            ProductsService.seed();
        }
    }
    
 }
