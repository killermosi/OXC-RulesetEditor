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
     * Creates new form WidowMain
     */
    public WindowMain() {
        // Init main window
        initComponents();
        
        // Configure the main window
        if (config.isWindowMaximized()) {
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        } else {
            setLocation(config.getWindowPositionX(), config.getWindowPositionY());
            setSize(config.getWindowWidth(), config.getWindowHeight());
        }
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(lang.getString("Application.Title"));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            javax.swing.JOptionPane.showMessageDialog(null, lang.getString("Application.ConfigurationSaveError"));
        }
    }//GEN-LAST:event_formWindowClosing

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
    // End of variables declaration//GEN-END:variables
}
