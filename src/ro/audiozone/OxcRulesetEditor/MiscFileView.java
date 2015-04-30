/*
 * Copyright (C) 2015 Silviu Ghita <killermosi@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ro.audiozone.OxcRulesetEditor;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
 * Custom FileView that sets special icons for rulesets files and directories with rulesets in them
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class MiscFileView extends FileView{
    /**
     * Where various images can be found
     */
    final private String imagesStorage = "/ro/audiozone/OxcRulesetEditor/Images/";
    
    /**
     * Set a limit for the files scanned to determine if a certain directory contains
     * rulesets or not (should speed up directory scanning when there are a lot of files in them)
     */
    final private int fileScanLimit = 15;
    
    @Override
    public Icon getIcon(File file) {
        if (file.isDirectory()) {
            return getIconForDirectory(file);
        }

        String extension = getExtension(file);

        if (null == extension || extension.equals("")) {
            return null;
        }

        if (!extension.equals(ServiceConfiguration.DEFAULT_RULESET_EXTENSION)) {
            return super.getIcon(file);
        }

        return new ImageIcon(getClass().getResource(imagesStorage + "icon-openxcom-16.png"));
    }

    /**
     * Set a custom folder icon for folders containing rulesets
     * 
     * @param file The folder to look into
     * @return 
     */
    private Icon getIconForDirectory(File folder) {
        // If for some reason this is not a folder of sorts...
        if (null == folder) {
            return null;
        }
        
        File[] files = folder.listFiles();

        if (null == files || 0 == files.length) {
            return getDefaultFolderIcon();
        }
        
        // Scan files in the folder to determine if it contains ruleset files
        int scannedFiles = 0;
        for (File fileEntry : files) {
            // Just in case we get a null file pointer (who knows...)
            if (null == fileEntry) {
                continue;
            }
            
            // Ignore directories
            if (!fileEntry.isFile()) {
                continue;
            }

            // Ignore hidden files
            if (fileEntry.getName().startsWith(".")) {
                continue;
            }
            
            // Increment the file scan counter
            scannedFiles++;
            
            // Stop if the scan limit was reached
            if (scannedFiles > fileScanLimit) {
                break;
            }
            
            String fileExtension = getExtension(fileEntry);
            
            // No extension? Move to the next file
            if (null == fileExtension) {
                continue;
            }
            
            if (fileExtension.equals(ServiceConfiguration.DEFAULT_RULESET_EXTENSION)) {
                return new ImageIcon(getClass().getResource(imagesStorage + "icon-oxygen-openxcom-inode-directory-16.png"));
            }
        }

        // Default icon
        return getDefaultFolderIcon();
    }
    
    private Icon getDefaultFolderIcon()
    {
        return new ImageIcon(getClass().getResource(imagesStorage + "icon-oxygen-inode-directory-16.png"));
    }
    
    /**
     * Get the extension of a file
     * 
     * @param file The file to get the extension from
     * @return The file extension
     */ 
    private String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
