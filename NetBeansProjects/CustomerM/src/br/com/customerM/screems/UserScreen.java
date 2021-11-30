/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package br.com.customerM.screems;

/**
 *
 * @author f0fp1241
 */


import java.sql.*;
import br.com.customerM.dal.ConnectionModule;
import javax.swing.JOptionPane;

public class UserScreen extends javax.swing.JInternalFrame {
    
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /** Creates new form UserScreen */
    public UserScreen() {
        initComponents();
        connection = ConnectionModule.conector();
    }
    
    private void search(){
    
    String sql = "select * from tbusers where iduser=?";
        try {
            
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtUserName.setText(rs.getString(2));
                txtUserFone.setText(rs.getString(3));
                txtUserLog.setText(rs.getString(4));
                txtUserPass.setText(rs.getString(5));
                // the line bellow refer to combobox
                cboUserProfile.setSelectedItem(rs.getString(6));

            } else {
                
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
                //the lines below "clean" the rows
                
                txtUserName.setText(null);
                txtUserFone.setText(null);
                txtUserLog.setText(null);
                txtUserPass.setText(null);

            }
           
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
        }
    
    
    }
    
    private void add() {
    
  String sql = "insert into tbusers(iduser, name_user, fone, login, pass_word, profile_user) values(?, ?, ?, ?, ?, ?)";
        try {
             pst = connection.prepareStatement(sql);
             
             pst.setString(1, txtUserId.getText());
             pst.setString(2, txtUserName.getText());
             pst.setString(3, txtUserFone.getText());
             pst.setString(4, txtUserLog.getText());
             pst.setString(5, txtUserPass.getText());
             pst.setString(6, cboUserProfile.getSelectedItem().toString());
             
             
             if (txtUserId.getText().isEmpty() || txtUserName.getText().isEmpty() || txtUserLog.getText().isEmpty()
                     || txtUserPass.getText().isEmpty() || cboUserProfile.getSelectedItem().toString().isEmpty()) {
                 
                JOptionPane.showMessageDialog(null, "fill all fields");
                
            } else {
            
             
             
             int added = pst.executeUpdate();
             System.out.println(added);
             
             if(added > 0){
             JOptionPane.showMessageDialog(null, "registered user with success");

             }
             
                txtUserId.setText(null);
                txtUserName.setText(null);
                txtUserFone.setText(null);
                txtUserLog.setText(null);
                txtUserPass.setText(null);
                cboUserProfile.setSelectedItem(null);
             
        } }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    // creating the method to change the user dadas
    private void change(){
    
    String sql = "update tbusers set name_user=?, fone=?, login=?, pass_word=?, profile_user=? where iduser=?";
    
    
        try {
            pst = connection.prepareStatement(sql);
            
             pst.setString(1, txtUserName.getText());
             pst.setString(2, txtUserFone.getText());
             pst.setString(3, txtUserLog.getText());
             pst.setString(4, txtUserPass.getText());
             pst.setString(5, cboUserProfile.getSelectedItem().toString());
              pst.setString(6, txtUserId.getText());
             
             if (txtUserId.getText().isEmpty() || txtUserName.getText().isEmpty() || txtUserLog.getText().isEmpty()
                     || txtUserPass.getText().isEmpty() || cboUserProfile.getSelectedItem().toString().isEmpty()) {
                 
                JOptionPane.showMessageDialog(null, "fill all fields");
                
            } else{
             int added = pst.executeUpdate();
             System.out.println(added);
             
             if(added > 0){
             JOptionPane.showMessageDialog(null, "changed user with success");

             }
             
                txtUserId.setText(null);
                txtUserName.setText(null);
                txtUserFone.setText(null);
                txtUserLog.setText(null);
                txtUserPass.setText(null);
                cboUserProfile.setSelectedItem(null);
            
             
             }
             
             
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void delete(){
    
        int confirm = JOptionPane.showConfirmDialog(null, "Are you shure to exit?", "Attention", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION){
            String sql = "delete from tbusers where iduser=?";
            
            try {
                pst = connection.prepareStatement(sql);
                
                pst.setString(1, txtUserId.getText());
               int deleted = pst.executeUpdate();
               
               if (deleted > 0){
               
               JOptionPane.showMessageDialog(null, "User removed with success");
               
                txtUserId.setText(null);
                txtUserName.setText(null);
                txtUserFone.setText(null);
                txtUserLog.setText(null);
                txtUserPass.setText(null);
                cboUserProfile.setSelectedItem(null);
               
               }
  
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        }

    
    
    
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtUserLog = new javax.swing.JTextField();
        txtUserFone = new javax.swing.JTextField();
        cboUserProfile = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtUserPass = new javax.swing.JTextField();
        btnUserCreate = new javax.swing.JButton();
        btnUserRead = new javax.swing.JButton();
        btnUserUpdate = new javax.swing.JButton();
        btnUserDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Users");
        setPreferredSize(new java.awt.Dimension(560, 487));
        setRequestFocusEnabled(false);

        jLabel1.setText("id");

        jLabel2.setText("Name");

        jLabel3.setText("Login");

        jLabel4.setText("Password");

        jLabel5.setText("Profile");

        txtUserLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserLogActionPerformed(evt);
            }
        });

        cboUserProfile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        jLabel6.setText("Fone");

        btnUserCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/add.png"))); // NOI18N
        btnUserCreate.setToolTipText("Add");
        btnUserCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserCreate.setPreferredSize(new java.awt.Dimension(64, 64));
        btnUserCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserCreateActionPerformed(evt);
            }
        });

        btnUserRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/search.png"))); // NOI18N
        btnUserRead.setToolTipText("Search");
        btnUserRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserReadActionPerformed(evt);
            }
        });

        btnUserUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/update.png"))); // NOI18N
        btnUserUpdate.setToolTipText("Update");
        btnUserUpdate.setContentAreaFilled(false);
        btnUserUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserUpdateActionPerformed(evt);
            }
        });

        btnUserDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/remove.png"))); // NOI18N
        btnUserDelete.setToolTipText("Delete");
        btnUserDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Required Fields ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(28, 28, 28)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(txtUserLog, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cboUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btnUserCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnUserRead, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(64, 64, 64)
                                .addComponent(btnUserDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserFone, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(168, 168, 168))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUserCreate, btnUserDelete, btnUserRead, btnUserUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUserLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtUserFone, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUserCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUserUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUserRead))
                .addGap(107, 107, 107))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUserCreate, btnUserDelete, btnUserRead, btnUserUpdate});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserLogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserLogActionPerformed

    private void btnUserUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserUpdateActionPerformed
        // call the change method
        change();
    }//GEN-LAST:event_btnUserUpdateActionPerformed

    private void btnUserReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserReadActionPerformed
        // call the search method
        search();
    }//GEN-LAST:event_btnUserReadActionPerformed

    private void btnUserCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserCreateActionPerformed
        // call the add method
        add();
    }//GEN-LAST:event_btnUserCreateActionPerformed

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed
        // call the delete method
        delete();
    }//GEN-LAST:event_btnUserDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUserCreate;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserRead;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JComboBox<String> cboUserProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUserFone;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUserLog;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserPass;
    // End of variables declaration//GEN-END:variables

}
