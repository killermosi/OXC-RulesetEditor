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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.io.File;
import java.io.FilenameFilter;

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
     * Static marker for the localization of the file chooser
     */
    private static boolean fileChooserLocalized = false;
    
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
        localizeFileChoosers();
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
     * - set a special icon for .rul files
     * - set the file filter
     * - (optional) remove the "New Folder" button - useful for "Open Ruleset scenario"
     * 
     * @param chooser The jFileChooser to customize
     * @param removeNFB If to remove the New Folder button from the interface
     */
    protected void customizeFileChooser(JFileChooser chooser, boolean removeNFB) {
        /* Use the OXC icon for .rul files */
        chooser.setFileView(new FileView() {
            @Override
            public Icon getIcon(File file) {
                if (file.isDirectory()) {
                    return getIconForDirectory(file);
                }
                
                String extension = Utils.getExtension(file);
                
                if (extension.equals("")) {
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
                for (File fileEntry : folder.listFiles()) {
                    // Ignore directories
                    if (fileEntry.isDirectory()) {
                        continue;
                    }
                    
                    // Ignore hidden files
                    if (fileEntry.getName().startsWith(".")) {
                        continue;
                    }

                    System.out.println(fileEntry.getAbsolutePath());
                    
                    String extension = Utils.getExtension(folder);
                    //System.out.println(extension);
                    //if (Utils.getExtension(folder).equals(ServiceConfiguration.DEFAULT_RULESET_EXTENSION)) {
                        //return new ImageIcon(getClass().getResource(imagesStorage + "icon-oxygen-openxcom-inode-directory-16.png"));
                    //}
                }
                // "Default" icon
                return new ImageIcon(getClass().getResource(imagesStorage + "icon-oxygen-inode-directory-16.png"));
            }
        });
        
        /* Set the file filter to .run & all files */
        String rulesetName = lang.getString("FileChooser.RulesetFileName");
        FileFilter filter = new FileNameExtensionFilter(
                String.format(rulesetName, "." + ServiceConfiguration.DEFAULT_RULESET_EXTENSION),
                ServiceConfiguration.DEFAULT_RULESET_EXTENSION
        );
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        
        /* Remove the "New Folder button from the chooser */
        if (removeNFB) {
            removeNewFolderButton(chooser);
        }
    }
    
    /**
     * Customize a file chooser with the following:
     * - set a special icon for .rul files
     * - set the file filter
     * 
     * @param chooser The jFileChooser to customize
     */
    protected void customizeFileChooser(JFileChooser chooser) {
        customizeFileChooser(chooser, false);
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
     * Localize the file choosers (sadly, all of them)
     */
    private void localizeFileChoosers() {
        if (fileChooserLocalized) {
            return;
        }

        String[] labels = {
            "FileChooser.acceptAllFileFilterText",
            "FileChooser.detailsViewButtonAccessibleName",
            "FileChooser.detailsViewButtonToolTipText",
            "FileChooser.directoryDescriptionText",
            "FileChooser.fileDescriptionText",
            "FileChooser.fileNameLabelText",
            "FileChooser.filesOfTypeLabelText",
            "FileChooser.helpButtonText",
            "FileChooser.helpButtonToolTipText",
            "FileChooser.homeFolderAccessibleName",
            "FileChooser.homeFolderToolTipText",
            "FileChooser.listViewButtonAccessibleName",
            "FileChooser.listViewButtonToolTipText",
            "FileChooser.lookInLabelText",
            "FileChooser.newFolderAccessibleName",
            "FileChooser.newFolderErrorSeparator",
            "FileChooser.newFolderErrorText",
            "FileChooser.newFolderToolTipText",
            "FileChooser.other.newFolder",
            "FileChooser.other.newFolder.subsequent",
            "FileChooser.upFolderAccessibleName",
            "FileChooser.upFolderToolTipText",
            "FileChooser.updateButtonText",
            "FileChooser.updateButtonToolTipText",
            "FileChooser.win32.newFolder",
            "FileChooser.win32.newFolder.subsequent"
        };
        
        for (String label:labels) {
            try {
                UIManager.put(label, lang.getString(label));
            } catch(Exception exc) {
                System.out.println("Minor: label '" + label + "' not found in the current i18n bundle");
            }
        }
        
        fileChooserLocalized = true;
    }
}
