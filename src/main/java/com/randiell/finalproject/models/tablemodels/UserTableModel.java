/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models.tablemodels;

import com.randiell.finalproject.models.User;
import java.util.Collection;

public class UserTableModel extends BaseTableModel<User> {

    public UserTableModel(Collection<User> users) {
        super(users, new String[]{
                "Nombre",
                "Apellido",
                "Telefono",
                "Email",
                "Usuario",
            }, (User u) -> {
            return new Object[] {
                u.getFirstName(),
                u.getLastName(),
                u.getPhoneNumber(), 
                u.getEmail(),
                u.getUsername()
            };
        });
    }
    
}
