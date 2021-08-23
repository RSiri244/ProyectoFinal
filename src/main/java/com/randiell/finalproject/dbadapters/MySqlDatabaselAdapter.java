/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.dbadapters;

import com.randiell.finalproject.models.BaseModel;
import com.randiell.finalproject.services.ProductsService;
import com.randiell.finalproject.services.UserService;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDatabaselAdapter extends DatabaseAdapter {
    Connection db;
    MySqlDatabaselAdapter(String connectionString) {
        try {
            db = (Connection)DriverManager.getConnection(connectionString);
            
            // Conectar al server
            var st = db.createStatement();
            st.executeQuery("SELECT 1;");
            st.execute("CREATE DATABASE IF NOT EXISTS proyectofinal;");
            
            // Reabrir conexión con base de datos
            db = (Connection)DriverManager.getConnection(connectionString.replace("/?", "/proyectofinal?"));
            st = db.createStatement();
            st.executeQuery("SELECT 1;");
            
            // Crear tablas
            // [TABLA: Users]
            st.execute("CREATE TABLE IF NOT EXISTS "+UserService.COLLECTION_NAME+" ("
                    + "  id varchar(64) not null primary key unique,"
                    + "  username varchar(140) not null,"
                    + "  firstName varchar(140) not null,"
                    + "  lastName varchar(140) not null,"
                    + "  phoneNumber varchar(140) not null,"
                    + "  email varchar(140) not null,"
                    + "  password varchar(140) not null"
                    + ")");
            
            // [TABLA: Products]
            st.execute("CREATE TABLE IF NOT EXISTS "+ProductsService.COLLECTION_NAME+" ("
                    + "  id varchar(64) not null primary key unique,"
                    + "  name varchar(140) not null,"
                    + "  brand varchar(140) not null,"
                    + "  category varchar(140) not null,"
                    + "  price double not null,"
                    + "  stock int not null"
                    + ")");

            // Crear tablas
            // [TABLA: Users]
            st.execute("CREATE TABLE IF NOT EXISTS migration ("
                    + "  id varchar(64) not null primary key unique"
                    + ")");
            
            System.out.println("Database connection successfull");
        } catch(SQLException ex) {
            System.out.println("Connection error:"+ex.getMessage());
        }
    }
    
    @Override
    public boolean addItem(String collectionName, BaseModel item) {
        try {
            var hash = item.toHashMap();
            var keys = hash.keySet();
            var columns = "";
            var values = "";
            
            for(var key : keys) {
                columns += key + ",";
                values += "?,";
            }
            columns = columns.replaceFirst(",$", "");
            values = values.replaceFirst(",$", "");
            
            var st = db.prepareStatement("INSERT INTO "+collectionName+" ("+columns+") VALUES ("+values+") ");
            var idx = 1;
            for(var key : keys) {
                st.setObject(idx++, hash.get(key));
            }
            st.execute();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL State: "+ex.getSQLState());
            System.out.println("Message: "+ex.getMessage());
        }
        return false;
    }

    @Override
    public <T extends BaseModel> Collection<T> getAllItems(String collectionName, Class<T> cls) {
        
        try {   
            var items = new ArrayList<T>();
            var st = db.prepareStatement("SELECT * FROM "+collectionName);
            var rs = st.executeQuery();
            
            while(rs.next()) {
                var meta = rs.getMetaData();
                var columns = meta.getColumnCount();
                var hash = new HashMap<String, Object>();
                System.out.println("Columns Count: "+ columns);
                for (int i = 1; i <= columns; i++) {
                    System.out.println("Column "+i+": "+ meta.getColumnName(i));
                    hash.put(meta.getColumnName(i), rs.getObject(i));
                }
                
                T item = cls.getConstructor(HashMap.class).newInstance(hash);
                items.add(item);
            }

            return items;
        } catch (Exception ex) {
            Logger.getLogger(MySqlDatabaselAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public <T extends BaseModel> T getItem(String collectionName, String itemId, Class<T> cls) {
        try {            
            var st = db.prepareStatement("SELECT * FROM "+collectionName+" WHERE id=?");
            st.setObject(1, itemId);
            var rs = st.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            var meta = rs.getMetaData();
            var columns = meta.getColumnCount();
            var hash = new HashMap<String, Object>();
            for (int i = 1; i <= columns; i++) {
                hash.put(meta.getColumnName(i), rs.getObject(i));
            }

            var item = cls.getConstructor(HashMap.class).newInstance(hash);
            return item;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException ex) {
            Logger.getLogger(MySqlDatabaselAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public boolean deleteItem(String collectionName, String itemId) {
        try {
            var st = db.prepareStatement("DELETE FROM "+collectionName+" WHERE id=?");
            st.setObject(1, itemId);
            st.execute();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL State: "+ex.getSQLState());
            System.out.println("Message: "+ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateItem(String collectionName, BaseModel item) {
        try {
            var hash = item.toHashMap();
            var keys = hash.keySet();
            var columns = "";
            
            for(var key : keys) {
                if(key.equals("id")) {
                    continue;
                }
                columns += key + "=?,";
            }
            columns = columns.replaceFirst(",$", "");
            
            var st = db.prepareStatement("UPDATE "+collectionName+" SET "+columns+" WHERE id=?");
            var idx = 1;
            for(var key : keys) {
                if(key.equals("id")) {
                    continue;
                }
                st.setObject(idx++, hash.get(key));
            }
            st.setObject(keys.size(), hash.get("id"));
            st.execute();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL State: "+ex.getSQLState());
            System.out.println("Message: "+ex.getMessage());
        }
        return false;
    }
    
}
