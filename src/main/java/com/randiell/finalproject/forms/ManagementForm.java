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
import com.randiell.finalproject.models.tablemodels.BaseTableModel;
import com.randiell.finalproject.models.tablemodels.ProductsTableModel;
import com.randiell.finalproject.models.tablemodels.UserTableModel;

public class ManagementForm extends javax.swing.JFrame {
    BaseTableModel model;
    Class editForm;
    
    /**
     * Creates new form UserManagementForm
     */
    public ManagementForm() {
        initComponents();
    }

    public ManagementForm(BaseTableModel model, Class editForm) {
        initComponents();
        this.model = model;
        table.setModel(model);
    }
    
    public void addItem(BaseModel item) {}
    
    public void updateItem(BaseModel item) {}
    
    public void deleteItem(BaseModel item) {}

    
    private void waitForCreateForm() {
        if(model instanceof UserTableModel) {
            var form = new EditUserForm() {
                @Override
                public void onSaveActionPerformed(User user) {
                    addItem(user);
                }
            };
            form.setVisible(true);
        } else if(model instanceof ProductsTableModel) {
            var form = new EditProductForm() {
                @Override
                public void onSaveActionPerformed(Product user) {
                    addItem(user);
                }
            };
            form.setVisible(true);
        } else {
            throw new Error("Unsupported model provided");
        }
    }

    
    private void waitForEditForm(BaseModel item) {
        if(model instanceof UserTableModel) {
            var form = new EditUserForm((User)item) {
                @Override
                public void onSaveActionPerformed(User user) {
                    updateItem(user);
                }

                @Override
                public void onDeleteActionPerformed(User user) {
                    deleteItem(user);
                }
            };
            form.setVisible(true);
        } else if(model instanceof ProductsTableModel) {
            var form = new EditProductForm((Product)item) {
                @Override
                public void onSaveActionPerformed(Product product) {
                    updateItem(product);
                }
                
                @Override
                public void onDeleteActionPerformed(Product product) {
                    deleteItem(product);
                }
            };
            form.setVisible(true);
        } else {
            throw new Error("Unsupported model provided");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonCreate = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(table);

        buttonCreate.setText("Agregar");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Actualizar");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonDelete.setText("Eliminar");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addComponent(buttonCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        var selected = table.getSelectedRow();
        var item = (BaseModel)this.model.getElement(selected);
        deleteItem(item);
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        var selected = table.getSelectedRow();
        var item = (BaseModel)this.model.getElement(selected);
        waitForEditForm(item);
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        waitForCreateForm();
    }//GEN-LAST:event_buttonCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}