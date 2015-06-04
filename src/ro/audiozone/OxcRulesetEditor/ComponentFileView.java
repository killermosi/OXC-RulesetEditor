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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.filechooser.FileView;

/**
 * Custom FileView that sets special icons for rulesets files and directories with rulesets in them
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class ComponentFileView extends FileView{
    /**
     * Where various images can be found
     */
    final private String imagesStorage = "/ro/audiozone/OxcRulesetEditor/Images/";
    
    /**
     * Image to be used for directories that contain rulesets
     */
    final private Icon rulesetDirectoryIcon;
    
    /**
     * Class initialization
     */
    public ComponentFileView() {
        super();
        rulesetDirectoryIcon = generateSpecificRulesetDirectoryIcon();
    }
    
    @Override
    public Icon getIcon(File file) {
        if (file.isDirectory()) {
            return getIconForDirectory(file);
        }

        // Default icon for everything else
        return null;
    }

    /**
     * Set a custom folder icon for folders containing rulesets
     * 
     * @param file The folder to look into
     * @return 
     */
    private Icon getIconForDirectory(File folder) {
        // If for some reason this is not a proper object...
        if (null == folder) {
            return null;
        }
        
        // Filter the files in the folder for .rul files
        File[] files = folder.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File directory, String name) {
                return name.toLowerCase().equals(ServiceConfiguration.RULESET_METAFILE);
            }
        });

        if (null == files || 0 == files.length) {
            return null;
        }
        
        return rulesetDirectoryIcon;
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
    
    /**
     * Generate a specific directory icon for directories containing rulesets by combining
     * the default directory icon with a (slightly) a smaller version of the application icon
     * 
     * @return 
     */
    private Icon generateSpecificRulesetDirectoryIcon() {
        // Load the images
        Icon directoryIcon = UIManager.getIcon("FileView.directoryIcon");
        Icon additionalIcon = new ImageIcon(getClass().getResource(imagesStorage + "icon-ruleset-directory-overlay-16.png"));

        // Generate buffered image from the directory icon
        BufferedImage imgBg = new BufferedImage(directoryIcon.getIconWidth(), directoryIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics gBg = imgBg.createGraphics();
        directoryIcon.paintIcon(null, gBg, 0, 0);
        gBg.dispose();

        // Generate buffered image from the overlay icon
        BufferedImage imgFg = new BufferedImage(additionalIcon.getIconWidth(), additionalIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics gFg = imgFg.createGraphics();
        additionalIcon.paintIcon(null, gFg, 0, 0);
        gFg.dispose();
        
        // Generate the combined icon by first drawing the platform-specific directory icon and then drawing the overlay icon over it
        final BufferedImage combinedIcon = new BufferedImage(imgBg.getHeight(), imgBg.getWidth(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = combinedIcon.createGraphics();
        
        g.drawImage(imgBg, 0, 0, null);
        g.drawImage(imgFg, 0, 0, null);
        g.dispose();
        
        return (Icon) new ImageIcon(combinedIcon);
    }
}
