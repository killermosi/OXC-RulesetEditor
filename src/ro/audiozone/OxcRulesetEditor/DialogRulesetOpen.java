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
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Handles opening rulesets (single or split)
 * 
 * @author Silviu Ghita <killermosi@yahoo.com>
 */
public class DialogRulesetOpen extends DialogAbstract {
    
    /**
     * Creates new form DialogOpen
     * @param parent The parent
     * @param modal If the dialog is modal or not
     */
    public DialogRulesetOpen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        customizeFileChooser(FileChooserOpen, true);
        
        // Set the current directory from session (if available) or from the config
        String currentDirectorySession = (String) config.getSessionData("DialogRulesetOpen.LastOpenDirectory");
        File currentDirectory;
        
        if (null != currentDirectorySession) {
            currentDirectory = new File(currentDirectorySession);
        } else {
            currentDirectory = new File(config.getFilesLastOpenDirectory());
        }
        
        FileChooserOpen.setCurrentDirectory(currentDirectory);
        
        // Set the rule type selector to the proper state from the session (if available) or from the config
        if (null != config.getSessionData("DialogRulesetOpen.LastOpenSplitRule")) {
            SplitRulesetFileToggleButton.setSelected((boolean) config.getSessionData("DialogRulesetOpen.LastOpenSplitRule"));
        } else {
            SplitRulesetFileToggleButton.setSelected(config.isFilesLastOpenSplitRule());
        }
    }
    
    @Override
    public void setLocationRelativeTo(Component c){
        // Restore position and size if found in the session
        int[] session = (int[]) config.getSessionData("DialogRulesetOpen.PositionAndSize");
        
        // Nothing in the seesion? Run the parent routine
        if (null == session) {
            super.setLocationRelativeTo(c);
            return;
        }
        
        // I know, I know, magic numbers...
        setLocation(session[0], session[1]);
        setSize(session[2], session[3]);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RulesetTypeGroup = new javax.swing.ButtonGroup();
        FileChooserOpen = new javax.swing.JFileChooser();
        BtnCancel = new javax.swing.JButton();
        BtnOpen = new javax.swing.JButton();
        Separator = new javax.swing.JSeparator();
        SingleRulesetFileToggleButton = new javax.swing.JToggleButton();
        SplitRulesetFileToggleButton = new javax.swing.JToggleButton();
        LabelRulesetType = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(lang.getString("DialogOpenRuleset.Title"));
        setMinimumSize(new java.awt.Dimension(650, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        FileChooserOpen.setControlButtonsAreShown(false);

        BtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-dialog-cancel-32.png"))); // NOI18N
        BtnCancel.setText(lang.getString("DialogOpenRuleset.BtnCancel.Text"));
        BtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelActionPerformed(evt);
            }
        });

        BtnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-dialog-ok-32.png"))); // NOI18N
        BtnOpen.setText(lang.getString("DialogOpenRuleset.BtnOpen.Text"));

        RulesetTypeGroup.add(SingleRulesetFileToggleButton);
        SingleRulesetFileToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-view-list-text-32.png"))); // NOI18N
        SingleRulesetFileToggleButton.setSelected(true);
        SingleRulesetFileToggleButton.setToolTipText(lang.getString("DialogOpenRuleset.SingleRulesetFileToggleButton.tooltip"));

        RulesetTypeGroup.add(SplitRulesetFileToggleButton);
        SplitRulesetFileToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ro/audiozone/OxcRulesetEditor/Images/icon-oxygen-view-list-tree-32.png"))); // NOI18N
        SplitRulesetFileToggleButton.setToolTipText(lang.getString("DialogOpenRuleset.SplitRulesetFileToggleButton.tooltip"));
        SplitRulesetFileToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SplitRulesetFileToggleButtonItemStateChanged(evt);
            }
        });

        LabelRulesetType.setText(lang.getString("DialogOpenRuleset.LabelRulesetType.Text"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FileChooserOpen, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Separator)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelRulesetType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SingleRulesetFileToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SplitRulesetFileToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(FileChooserOpen, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SingleRulesetFileToggleButton)
                            .addComponent(SplitRulesetFileToggleButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancel)
                            .addComponent(BtnOpen)))
                    .addComponent(LabelRulesetType, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Save the position and size of the dialog in the session for a better UX
        int[] positionAndSize = {getX(), getY(), getWidth(), getHeight()};
        config.setSessionData("DialogRulesetOpen.PositionAndSize", positionAndSize);
        
        // Save the current directory in the session (also for a better UX)
        config.setSessionData("DialogRulesetOpen.LastOpenDirectory", FileChooserOpen.getCurrentDirectory().toString());
        
        // And save the current ruleset selection type (this too for a better UX)
        config.setSessionData("DialogRulesetOpen.LastOpenSplitRule", SplitRulesetFileToggleButton.isSelected());
    }//GEN-LAST:event_formWindowClosing

    private void SplitRulesetFileToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SplitRulesetFileToggleButtonItemStateChanged
        if (SplitRulesetFileToggleButton.isSelected()) {
            FileChooserOpen.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
            setFyleTypeComboBoxEnabled(FileChooserOpen, false);
        } else {
            FileChooserOpen.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            setFyleTypeComboBoxEnabled(FileChooserOpen, true);
        }
    }//GEN-LAST:event_SplitRulesetFileToggleButtonItemStateChanged

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
            java.util.logging.Logger.getLogger(DialogRulesetOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogRulesetOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogRulesetOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogRulesetOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DialogRulesetOpen dialog = new DialogRulesetOpen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    /**
     * Disable the FileChooser's "file type" combo box
     * 
     * @param container The container
     * @param state The state to set
     * @return 
     */
    private void setFyleTypeComboBoxEnabled(Container container, boolean state) {
        
        for (Component component :container.getComponents()) {
            if (component instanceof JComboBox) {
                ComboBoxModel model = ((JComboBox) component).getModel();

                int size = model.getSize();
                
                for (int i = 0; i < size; i++) {
                    if (model.getElementAt(i).toString().contains("AcceptAllFileFilter")) {
                        ((JComboBox) component).setEnabled(state);
                    }
                }
            } else if (component instanceof Container) {
                setFyleTypeComboBoxEnabled((Container) component, state);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancel;
    private javax.swing.JButton BtnOpen;
    private javax.swing.JFileChooser FileChooserOpen;
    private javax.swing.JLabel LabelRulesetType;
    private javax.swing.ButtonGroup RulesetTypeGroup;
    private javax.swing.JSeparator Separator;
    private javax.swing.JToggleButton SingleRulesetFileToggleButton;
    private javax.swing.JToggleButton SplitRulesetFileToggleButton;
    // End of variables declaration//GEN-END:variables
}
