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

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/**
 * Provides basic amenities for dialogs
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public abstract class DialogAbstract extends javax.swing.JDialog {
    /**
     * Application configuration
     */
    final protected ServiceConfiguration config = ServiceConfiguration.getInstance();
    
    /**
     * i18n support
     */
    final protected java.util.ResourceBundle lang = java.util.ResourceBundle.getBundle(
                "ro/audiozone/OxcRulesetEditor/i18n_" + config.getInterfaceLanguage()
    );
    
    /**
     * Where various images can be found
     */
    final protected String imagesStorage = "/ro/audiozone/OxcRulesetEditor/Images/";
    
    /**
     * Creates new Dialog
     * 
     * @param parent The parent
     * @param modal If the dialog is modal
     */
    public DialogAbstract(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setWindowIcons();
    }
    
    /**
     * Set dialog icons in various sizes
     * 
     * TODO: is 256 needed too?
     */
    private void setWindowIcons() {
        final List<Image> icons = new ArrayList<>();
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-16.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-32.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-64.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-128.png")));
        setIconImages(icons);
    }
    
    /**
     * Customize a file chooser with the following:
     * - internationalize the file chooser
     * - set a special icon for .rul files
     * - set the file filter
     * - (optional) remove the "New Folder" button - useful for "Open Ruleset scenario"
     * 
     * @param chooser The jFileChooser to customize
     * @param removeNFB If to remove the New Folder button from the interface
     */
    protected void customizeFileChooser(JFileChooser chooser, boolean removeNFB) {
        /* Internationalize the thing */
        
        /* Use the OXC icon for .rul files */
        
        /* Set the file filter to .run & all files */
        
        /* Remove the "New Folder button from the chooser */
        if (removeNFB) {
            removeNewFolderButton(chooser);
        }
    }
    
    /**
     * Remove the "New Folder" button from a container
     * 
     * @param container The container to search in
     */
    private void removeNewFolderButton(Container container) {
        int len = container.getComponentCount();
        // Check each component in turn to determine if it is the required one
        for (int i=0; i< len; i++) {
            Component comp = container.getComponent(i);

            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                Icon icon = button.getIcon();

                if (
                    null != icon
                    && icon == UIManager.getIcon("FileChooser.newFolderIcon")
                ) {
                    button.setVisible(false);
                }
            } else if (comp instanceof Container) {
                removeNewFolderButton((Container) comp);
            }
        }
    }
    
    /**
     * Customize a file chooser with the following:
     * - internationalize the file chooser
     * - set a special icon for .rul files
     * - set the file filter
     * 
     * @param chooser The jFileChooser to customize
     */
    protected void customizeFileChooser(JFileChooser chooser) {
        customizeFileChooser(chooser, false);
    }
}
