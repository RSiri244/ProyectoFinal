/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models;

import java.util.HashMap;

public class User extends BaseModel {
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_FIRSTNAME = "firstName";
    private static final String FIELD_LASTNAME = "lastName";
    private static final String FIELD_PHONENUMBER = "phoneNumber";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PASSWORD = "password";

    public User() { super(); }
    public User(User other) { super(other); }
    public User(HashMap other) { super(other); }

    public String getUsername() { return (String)this.hash.get(FIELD_USERNAME); }
    public void setUsername(String value){ this.hash.put(FIELD_USERNAME, value); }

    public String getFirstName() { return (String)this.hash.get(FIELD_FIRSTNAME); }
    public void setFirstName(String value){ this.hash.put(FIELD_FIRSTNAME, value); }

    public String getLastName() { return (String)this.hash.get(FIELD_LASTNAME); }
    public void setLastName(String value){ this.hash.put(FIELD_LASTNAME, value); }

    public String getPhoneNumber() { return (String)this.hash.get(FIELD_PHONENUMBER); }
    public void setPhoneNumber(String value){ this.hash.put(FIELD_PHONENUMBER, value); }

    public String getEmail() { return (String)this.hash.get(FIELD_EMAIL); }
    public void setEmail(String value){ this.hash.put(FIELD_EMAIL, value); }

    private String getPassword() { return (String)this.hash.get(FIELD_PASSWORD); }
    public void setPassword(String value) { this.hash.put(FIELD_PASSWORD, value); }
    public void copyPassword(User otherUser) { this.hash.put(FIELD_PASSWORD, otherUser.getPassword()); }

    public boolean comparePassword(String password){
        return this.getPassword().equals(password);
    }
}
