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

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Main application window
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class WindowMain extends javax.swing.JFrame {
    
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
     * Creates new form WidowMain
     */
    public WindowMain() {
        // Init main window
        initComponents();

        // Restore windo size and position
        setLocation(config.getWindowPositionX(), config.getWindowPositionY());
        setSize(config.getWindowWidth(), config.getWindowHeight());
        if (config.isWindowMaximized()) {
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
        
        // Set the window icon(s)
        final List<Image> icons = new ArrayList<>();
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-16.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-32.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-64.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagesStorage + "icon-openxcom-128.png")));
        setIconImages(icons);
        
        // Show the disclaimer
        if (!config.isDisclaimerShown() || !config.isDisclaimerDoNotShowAgain()) {
            new DialogDisclaimer(this, true).setVisible(true);
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

        MainToolbar = new javax.swing.JToolBar();
        MainBtnNew = new javax.swing.JButton();
        MainBtnOpen = new javax.swing.JButton();
        MainBtnSave = new javax.swing.JButton();
        MainBtnSaveAs = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        MainBtnUndo = new javax.swing.JButton();
        MainBtnRedo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        MainBtnConfiguration = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        MainBtnAbout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(lang.getString("Application.Title"));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        MainToolbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        MainToolbar.setFloatable(false);
        MainToolbar.setRollover(true);

        MainBtnNew.setBackground(new java.awt.Color(214, 210, 208));
        MainBtnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-document-new-32.png"))); // NOI18N
        MainBtnNew.setToolTipText(lang.getString("MainToolbar.BtnNew.tooltip"));
        MainBtnNew.setEnabled(false);
        MainBtnNew.setFocusable(false);
        MainBtnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnNew);

        MainBtnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-document-open-folder-32.png"))); // NOI18N
        MainBtnOpen.setToolTipText(lang.getString("MainToolbar.BtnOpen.tooltip"));
        MainBtnOpen.setEnabled(false);
        MainBtnOpen.setFocusable(false);
        MainBtnOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnOpen);

        MainBtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-document-save-32.png"))); // NOI18N
        MainBtnSave.setToolTipText(lang.getString("MainToolbar.BtnSave.tooltip"));
        MainBtnSave.setEnabled(false);
        MainBtnSave.setFocusable(false);
        MainBtnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnSave);

        MainBtnSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-document-save-as-32.png"))); // NOI18N
        MainBtnSaveAs.setToolTipText(lang.getString("MainToolbar.BtnSaveAs.tooltip"));
        MainBtnSaveAs.setEnabled(false);
        MainBtnSaveAs.setFocusable(false);
        MainBtnSaveAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnSaveAs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnSaveAs);
        MainToolbar.add(jSeparator1);

        MainBtnUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-edit-undo-32.png"))); // NOI18N
        MainBtnUndo.setToolTipText(lang.getString("MainToolbar.BtnRedo.tooltip"));
        MainBtnUndo.setEnabled(false);
        MainBtnUndo.setFocusable(false);
        MainBtnUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnUndo);

        MainBtnRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-edit-redo.png"))); // NOI18N
        MainBtnRedo.setToolTipText(lang.getString("MainToolbar.BtnRedo.tooltip"));
        MainBtnRedo.setEnabled(false);
        MainBtnRedo.setFocusable(false);
        MainBtnRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnRedo);
        MainToolbar.add(jSeparator2);

        MainBtnConfiguration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-configure-32.png"))); // NOI18N
        MainBtnConfiguration.setToolTipText(lang.getString("MainToolbar.BtnConfiguration.tooltip"));
        MainBtnConfiguration.setEnabled(false);
        MainBtnConfiguration.setFocusable(false);
        MainBtnConfiguration.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnConfiguration.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolbar.add(MainBtnConfiguration);
        MainToolbar.add(filler1);

        MainBtnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-help-about-32.png"))); // NOI18N
        MainBtnAbout.setToolTipText(lang.getString("MainToolbar.BtnAbout.tooltip"));
        MainBtnAbout.setFocusable(false);
        MainBtnAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainBtnAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainBtnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainBtnAboutActionPerformed(evt);
            }
        });
        MainToolbar.add(MainBtnAbout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 254, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Update the window position and size in the configuration
        boolean isWindowMaximized = (getExtendedState() == JFrame.MAXIMIZED_BOTH);

        config.setWindowMaximized(isWindowMaximized);
        if (!isWindowMaximized) {
            // Update position and size only if the window is not maximized
            config.setWindowSize(getSize().width, getSize().height);
            config.setWindowPosition(getLocation().x, getLocation().y);
        }
        
        // Save the configuration
        if (!config.saveConfiguration()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    lang.getString("Application.ConfigurationSaveError"),
                    lang.getString("Application.ConfigurationSaveErrorTitle"),
                    javax.swing.JOptionPane.ERROR_MESSAGE,
                    new ImageIcon(getClass().getResource(imagesStorage + "icon-oxygen-dialog-warning-64.png"))
            );
        }
    }//GEN-LAST:event_formWindowClosing

    private void MainBtnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainBtnAboutActionPerformed
        DialogAbout dialog = new DialogAbout(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_MainBtnAboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MainBtnAbout;
    private javax.swing.JButton MainBtnConfiguration;
    private javax.swing.JButton MainBtnNew;
    private javax.swing.JButton MainBtnOpen;
    private javax.swing.JButton MainBtnRedo;
    private javax.swing.JButton MainBtnSave;
    private javax.swing.JButton MainBtnSaveAs;
    private javax.swing.JButton MainBtnUndo;
    private javax.swing.JToolBar MainToolbar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
