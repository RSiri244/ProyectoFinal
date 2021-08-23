/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models.tablemodels;

import com.randiell.finalproject.models.Product;
import java.util.Collection;

public class ProductsTableModel extends BaseTableModel<Product> {

    public ProductsTableModel(Collection<Product> products) {
        super(products, new String[]{
                "Nombre",
                "Marca",
                "Categoría",
                "Precio",
                "Cantidad Disponible",
            }, (Product u) -> {
            return new Object[] {
                u.getName(),
                u.getBrand(),
                u.getCategory(), 
                u.getPrice(),
                u.getStock()
            };
        });
    }
    
}
