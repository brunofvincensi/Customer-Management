/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.customerM.screems;

import java.sql.*;
import br.com.customerM.dal.ConnectionModule;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// the line below import the resources of rs2xml.jar library
import net.proteanit.sql.DbUtils;

/**
 *
 * @author f0fp1241
 */
public class CustomerScreen extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form CustomerScreen
     */
    public CustomerScreen() {
        initComponents();
        connection = ConnectionModule.conector();
    }

    private void add() {

        String sql = "insert into tbcustomer(namecust, address, fone, email) values(?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, txtCustName.getText());
            pst.setString(2, txtCustAddress.getText());
            pst.setString(3, txtCustFone.getText());
            pst.setString(4, txtCustEmail.getText());

            if (txtCustName.getText().isEmpty() || txtCustFone.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "fill all fields");

            } else {

                int added = pst.executeUpdate();
                System.out.println(added);

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "registered customer with success");

                }

                clean();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //advanced search, im real time
    private void search_customer() {

        String sql = "select idcust as id, namecust as name, address, fone, email "
                + "from tbcustomer where namecust like ?";
        try {
            pst = connection.prepareStatement(sql);
            //giving the content from the search box to the ?
            // pay attantion for "%" - String sql sequel

            pst.setString(1, txtCustSearch.getText() + "%");
            rs = pst.executeQuery();

            // the line below use the library rs2xml.jar to fill the table
            tblCustomers.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // method to set the fields with the table content
    public void set_fields() {

        int set = tblCustomers.getSelectedRow();
        txtCustId.setText(tblCustomers.getModel().getValueAt(set, 0).toString());
        txtCustName.setText(tblCustomers.getModel().getValueAt(set, 1).toString());
        txtCustAddress.setText(tblCustomers.getModel().getValueAt(set, 2).toString());
        txtCustFone.setText(tblCustomers.getModel().getValueAt(set, 3).toString());
        txtCustEmail.setText(tblCustomers.getModel().getValueAt(set, 4).toString());

        // the line below turn off the add button
        btnAdd.setEnabled(false);

    }

    private void change() {

        String sql = "update tbcustomer set namecust=?, address=?, fone=?, email=? "
                + "where idcust=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, txtCustName.getText());
            pst.setString(2, txtCustAddress.getText());
            pst.setString(3, txtCustFone.getText());
            pst.setString(4, txtCustEmail.getText());
            pst.setString(5, txtCustId.getText());

            if (txtCustName.getText().isEmpty() || txtCustFone.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "fill all fields");

            } else {

                int change = pst.executeUpdate();
                System.out.println(change);

                if (change > 0) {
                    JOptionPane.showMessageDialog(null, "changed customer with success");

                }

                clean();
                btnAdd.setEnabled(true);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delete() {

        int confirm = JOptionPane.showConfirmDialog(null, "Are you shure to exit?", "Attention", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "delete from tbcustomer where idcust=?";

            try {
                pst = connection.prepareStatement(sql);

                pst.setString(1, txtCustId.getText());
                int deleted = pst.executeUpdate();

                if (deleted > 0) {

                    JOptionPane.showMessageDialog(null, "User removed with success");

                    clean();
                    btnAdd.setEnabled(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    //method to clean the form fields
    private void clean() {
        txtCustSearch.setText(null);
        txtCustId.setText(null);
        txtCustName.setText(null);
        txtCustAddress.setText(null);
        txtCustFone.setText(null);
        txtCustEmail.setText(null);
        ((DefaultTableModel) tblCustomers.getModel()).setRowCount(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCustName = new javax.swing.JTextField();
        txtCustAddress = new javax.swing.JTextField();
        txtCustFone = new javax.swing.JTextField();
        txtCustEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        txtCustSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCustId = new javax.swing.JTextField();

        setBorder(new javax.swing.border.MatteBorder(null));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Customers");
        setToolTipText("");

        jLabel1.setText("*Name");

        jLabel2.setText("Address");

        jLabel3.setText("*Fone");

        jLabel4.setText("email");

        txtCustName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustNameActionPerformed(evt);
            }
        });

        txtCustEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustEmailActionPerformed(evt);
            }
        });

        jLabel5.setText("*Required fields");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/add.png"))); // NOI18N
        btnAdd.setToolTipText("Add");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(64, 64));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/remove.png"))); // NOI18N
        btnRemove.setToolTipText("Delete");
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/REDO.png"))); // NOI18N
        btnChange.setToolTipText("Update");
        btnChange.setContentAreaFilled(false);
        btnChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        txtCustSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustSearchActionPerformed(evt);
            }
        });
        txtCustSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustSearchKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/magnifier.png"))); // NOI18N

        tblCustomers = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "name", "address", "fone", "email"
            }
        ));
        tblCustomers.setFocusable(false);
        tblCustomers.getTableHeader().setReorderingAllowed(false);
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomersMouseClicked(evt);
            }
        });
        tblCustomers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCustomersKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomers);

        jLabel7.setText("Id Customer");

        txtCustId.setEnabled(false);
        txtCustId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCustId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCustFone, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(btnRemove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCustEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCustId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCustAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCustFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCustEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCustNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustNameActionPerformed

    private void txtCustEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustEmailActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // call the add method
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // call the delete method
        delete();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // call the change method
        change();
    }//GEN-LAST:event_btnChangeActionPerformed

    private void txtCustSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustSearchActionPerformed

    //the method below is "wile typing"
    private void txtCustSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustSearchKeyReleased

        search_customer();
    }//GEN-LAST:event_txtCustSearchKeyReleased

    private void tblCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomersMouseClicked
        set_fields();
    }//GEN-LAST:event_tblCustomersMouseClicked

    private void txtCustIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustIdActionPerformed

    private void tblCustomersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCustomersKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCustomersKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JTextField txtCustAddress;
    private javax.swing.JTextField txtCustEmail;
    private javax.swing.JTextField txtCustFone;
    private javax.swing.JTextField txtCustId;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtCustSearch;
    // End of variables declaration//GEN-END:variables
}
