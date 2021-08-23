/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.forms;

import com.randiell.finalproject.forms.details.EditProductForm;
import com.randiell.finalproject.forms.details.EditUserForm;
import com.randiell.finalproject.models.BaseModel;
import com.randiell.finalproject.models.Product;
import com.randiell.finalproject.models.User;
import com.randiell.finalproject.models.tablemodels.ProductsTableModel;
import com.randiell.finalproject.models.tablemodels.UserTableModel;
import com.randiell.finalproject.services.ProductsService;
import com.randiell.finalproject.services.UserService;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonUsers = new javax.swing.JButton();
        buttonLogout = new javax.swing.JButton();
        buttonProducts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonUsers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonUsers.setIcon(com.randiell.finalproject.ResourceManager.getIcon("user.png"));
        buttonUsers.setText(" Usuarios");
        buttonUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonUsers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsersActionPerformed(evt);
            }
        });

        buttonLogout.setText("Cerrar Sesión");
        buttonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogoutActionPerformed(evt);
            }
        });

        buttonProducts.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonProducts.setIcon(com.randiell.finalproject.ResourceManager.getIcon("inventory.png"));
        buttonProducts.setText(" Productos");
        buttonProducts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonProducts.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    private void buttonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogoutActionPerformed
        close();
    }//GEN-LAST:event_buttonLogoutActionPerformed

    private void buttonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsersActionPerformed
        var users = UserService.getAllUsers();
        var model = new UserTableModel(users);
       
        var form = this;
        var managementForm = new ManagementForm(model, EditUserForm.class) {
            @Override
            public void deleteItem(BaseModel item) {
                UserService.deleteUser((User)item);
                model.removeElement(item);
            }

            @Override
            public void updateItem(BaseModel item) {
                UserService.updateUser((User)item);
                model.updateElement(item);
            }

            @Override
            public void addItem(BaseModel item) {
                UserService.createUser((User)item);
                model.addElement(item);
            }
        };
        managementForm.setVisible(true);
        managementForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                form.setVisible(true);
            }
        });
        form.setVisible(false);
    }//GEN-LAST:event_buttonUsersActionPerformed

    private void buttonProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProductsActionPerformed
        var products = ProductsService.getAllProducts();
        var model = new ProductsTableModel(products);
        
        var form = this;
        var managementForm = new ManagementForm(model, EditProductForm.class) {
            @Override
            public void deleteItem(BaseModel item) {
                ProductsService.deleteProduct((Product)item);
                model.removeElement(item);
            }
            
            @Override
            public void updateItem(BaseModel item) {
                ProductsService.updateProduct((Product)item);
                model.updateElement(item);
            }

            @Override
            public void addItem(BaseModel item) {
                ProductsService.createProduct((Product)item);
                model.addElement(item);
            }
        };
        managementForm.setVisible(true);
        managementForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                form.setVisible(true);
            }
        });
    }//GEN-LAST:event_buttonProductsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogout;
    private javax.swing.JButton buttonProducts;
    private javax.swing.JButton buttonUsers;
    // End of variables declaration//GEN-END:variables
}
