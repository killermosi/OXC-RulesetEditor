/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.audiozone.OxcRulesetEditor;

import java.io.File;

/**
 * Various utility functions
 * @author sghita
 */
public class Utils {
    /**
     * Get the extension of a file
     * 
     * @param file The file to get the extension from
     * @return The file extension
     */ 
    public static String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
