/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.customerM.screems;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author f0fp1241
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblUser = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        Menu = new javax.swing.JMenuBar();
        menReg = new javax.swing.JMenu();
        menRegCli = new javax.swing.JMenuItem();
        menRegOs = new javax.swing.JMenuItem();
        menRegUse = new javax.swing.JMenuItem();
        menRep = new javax.swing.JMenu();
        menRepSer = new javax.swing.JMenuItem();
        menHelp = new javax.swing.JMenu();
        menHelpAbo = new javax.swing.JMenuItem();
        menOpt = new javax.swing.JMenu();
        menOptExit = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jLabel1.setText("jLabel1");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System to OS control");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUser.setText("UserName");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblDate.setText("Date");

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        menReg.setText("Registration");
        menReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegActionPerformed(evt);
            }
        });

        menRegCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRegCli.setText("Client");
        menReg.add(menRegCli);

        menRegOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRegOs.setText("OS");
        menRegOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegOsActionPerformed(evt);
            }
        });
        menReg.add(menRegOs);

        menRegUse.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRegUse.setText("Users");
        menRegUse.setEnabled(false);
        menRegUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegUseActionPerformed(evt);
            }
        });
        menReg.add(menRegUse);

        Menu.add(menReg);

        menRep.setText("Report");

        menRepSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRepSer.setText("Service");
        menRepSer.setEnabled(false);
        menRepSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRepSerActionPerformed(evt);
            }
        });
        menRep.add(menRepSer);

        Menu.add(menRep);

        menHelp.setText("Help");

        menHelpAbo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menHelpAbo.setText("About");
        menHelpAbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menHelpAboActionPerformed(evt);
            }
        });
        menHelp.add(menHelpAbo);

        Menu.add(menHelp);

        menOpt.setText("Options");

        menOptExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menOptExit.setText("Exit");
        menOptExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOptExitActionPerformed(evt);
            }
        });
        menOpt.add(menOptExit);

        Menu.add(menOpt);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(620, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblUser)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(79, 79, 79))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(desktop)
                    .addContainerGap(231, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblUser)
                .addGap(18, 18, 18)
                .addComponent(lblDate)
                .addContainerGap(373, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(desktop)
                    .addContainerGap()))
        );

        setBounds(0, 0, 817, 559);
    }// </editor-fold>//GEN-END:initComponents

    private void menRegOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menRegOsActionPerformed

    private void menRepSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRepSerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menRepSerActionPerformed

    private void menOptExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOptExitActionPerformed
        // show a dialog text
        int exit = JOptionPane.showConfirmDialog(null, "Are you shure to exit?", "Attention", JOptionPane.YES_NO_OPTION);
        if(exit == JOptionPane.YES_OPTION){
        System.exit(0);
        }
    }//GEN-LAST:event_menOptExitActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // the lines below exchenged the lblDate label to the current system
        // date on form iniciating
        
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        lblDate.setText(format.format(date));
    }//GEN-LAST:event_formWindowActivated

    private void menHelpAboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menHelpAboActionPerformed
        AboutScreen about = new AboutScreen();
        about.setVisible(true);
    }//GEN-LAST:event_menHelpAboActionPerformed

    private void menRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegActionPerformed
    
    }//GEN-LAST:event_menRegActionPerformed

    private void menRegUseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegUseActionPerformed
        UserScreen user = new UserScreen();
        user.setVisible(true);
        desktop.add(user);
    }//GEN-LAST:event_menRegUseActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblDate;
    public static javax.swing.JLabel lblUser;
    private javax.swing.JMenu menHelp;
    private javax.swing.JMenuItem menHelpAbo;
    private javax.swing.JMenu menOpt;
    private javax.swing.JMenuItem menOptExit;
    private javax.swing.JMenu menReg;
    private javax.swing.JMenuItem menRegCli;
    private javax.swing.JMenuItem menRegOs;
    public static javax.swing.JMenuItem menRegUse;
    private javax.swing.JMenu menRep;
    public static javax.swing.JMenuItem menRepSer;
    // End of variables declaration//GEN-END:variables
}
