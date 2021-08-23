/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randiell.finalproject;

import java.nio.file.Path;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class ResourceManager {
    private static String getPath(String path) {
        if(!path.startsWith("/")) {
            path = "/" + path;
        }
        
        return Path.of("resources"+path).toAbsolutePath().toString();
    }

    public static ImageIcon getIcon(String path) {
        return new javax.swing.ImageIcon(ResourceManager.getPath("icons/"+path));
    }
}
