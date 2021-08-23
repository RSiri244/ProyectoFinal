/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject.models.tablemodels;

import com.randiell.finalproject.models.BaseModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BaseTableModel<T extends BaseModel> implements TableModel {
    ArrayList<T> items;
    String columns[];
    Function<T, Object[]> mapper;
    ArrayList<TableModelListener> listeners = new ArrayList<>();
    
    public BaseTableModel(Collection<T> items, String[] columns, Function<T, Object[]> mapper) {
        this.items = new ArrayList(items);
        this.columns = columns;
        this.mapper = mapper;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mapper.apply(items.get(rowIndex))[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
    
    public T getElement(int rowIndex) {
        return items.get(rowIndex);
    }
    
    public void addElement(T item) {
        items.add(item);
        refresh();
    }
    
    public void updateElement(T item) {
        for (var idx = 0; idx < items.size(); idx++) {
            T existingItem = items.get(idx);
            if(existingItem.getId().equals(item.getId())) {
                items.set(idx, item);
                refresh();
                return;
            }
        }
    }
    
    public void removeElement(T item) {
        items.remove(item);
        refresh();
    }
    
    public void removeElement(int rowIndex) {
        var item = getElement(rowIndex);
        removeElement(item);
    }
    
    public void refresh() {
        for (var listener : listeners) {
            listener.tableChanged(new TableModelEvent(this));
        }
    }
}
