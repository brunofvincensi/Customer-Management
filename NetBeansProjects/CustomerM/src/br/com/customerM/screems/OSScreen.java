/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.customerM.screems;

import java.sql.*;
import br.com.customerM.dal.ConnectionModule;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author f0fp1241
 */
public class OSScreen extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // the line below create a variable to store a text according to the radion button 
    // selected
    private String type;

    /**
     * Creates new form OSScreen
     */
    public OSScreen() {
        initComponents();
        connection = ConnectionModule.conector();
    }

    private void search_customer() {
        String sql = "select idcust as id, namecust as name, fone "
                + "from tbcustomer where namecust like ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtCustSearch.getText() + "%");
            rs = pst.executeQuery();
            tblCust.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void set_fields() {

        int set = tblCust.getSelectedRow();

        txtCustId.setText(tblCust.getModel().getValueAt(set, 0).toString());

    }

    private void issue_os() {

        String sql = "insert into tbos(type_os, situacion, equipment, defect, service,"
                + "technician, amount, idcust) values(?,?,?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, type);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOsEquip.getText());
            pst.setString(4, txtOsDef.getText());
            pst.setString(5, txtOsServ.getText());
            pst.setString(6, txtOsTech.getText());
            pst.setString(7, txtOsValue.getText().replace(",", "."));
            pst.setString(8, txtCustId.getText());

            if (txtOsEquip.getText().isEmpty() || txtOsDef.getText().isEmpty()
                    || txtCustId.getText().isEmpty() || cboOsSit.getSelectedItem().equals(" ")) {

                JOptionPane.showMessageDialog(null, "fill all the fields");

            } else {

                int add = pst.executeUpdate();
                if (add > 0) {

                    JOptionPane.showMessageDialog(null, "OS issued with success");
                    
                    

                   btnOsCreate.setEnabled(false);
                   btnOsRead.setEnabled(false);
                   btnOsPrint.setEnabled(true);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // method to search a os
    private void search_os() {

        String os_num = JOptionPane.showInputDialog("Os Number");
        String sql = "select * from tbos where os= " + os_num;

        try {

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {

                txtOs.setText(rs.getString(1));
                txtDate.setText(rs.getString(2));

                if (rs.getString(3).equals("Budget")) {
                    rbtBud.setSelected(true);
                    type = "Budget";

                } else {
                    rbtOs.setSelected(true);
                    type = "OS";
                }

                cboOsSit.setSelectedItem(rs.getString(4));
                txtOsEquip.setText(rs.getString(5));
                txtOsDef.setText(rs.getString(6));
                txtOsServ.setText(rs.getString(7));
                txtOsTech.setText(rs.getString(8));
                txtOsValue.setText(rs.getString(9));
                txtCustId.setText(rs.getString(10));

                // avoid problems               
                btnOsCreate.setEnabled(false);
                txtCustSearch.setEnabled(false);
                tblCust.setVisible(false);
                
                // active others buttons
                btnOsDelete.setEnabled(true);
                btnOsPrint.setEnabled(true);
                btnOsUpdate.setEnabled(true);


            } else {
                JOptionPane.showMessageDialog(null, "Os don't registered");
            }

        } catch (java.sql.SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS invalid");
            //System.out.println(e);
        } catch (Exception e2) {

            JOptionPane.showMessageDialog(null, e2);
        }

    }

    //method to change an os
    private void change_os() {

        String sql = "update tbos set type_os=?, situacion=?, equipment=?, defect=?, service=?, technician=?,"
                + "amount=? where os=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, type);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOsEquip.getText());
            pst.setString(4, txtOsDef.getText());
            pst.setString(5, txtOsServ.getText());
            pst.setString(6, txtOsTech.getText());
            pst.setString(7, txtOsValue.getText().replace(",", "."));
            pst.setString(8, txtOs.getText());

            if (txtOsEquip.getText().isEmpty() || txtOsDef.getText().isEmpty()
                    || txtCustId.getText().isEmpty() || cboOsSit.getSelectedItem().equals(" ")) {

                JOptionPane.showMessageDialog(null, "fill all the fields");

            } else {

                int add = pst.executeUpdate();
                if (add > 0) {

                    JOptionPane.showMessageDialog(null, "OS changed with success");

                    clean();

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    

    //method to delete an os
    private void delete_os() {

        int confirm = JOptionPane.showConfirmDialog(null, "Are you shure that tou want to delete this OS?",
                "Attation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            String sql = "delete from tbos where os=?";

            try {

                pst = connection.prepareStatement(sql);
                pst.setString(1, txtOs.getText());

                int deleted = pst.executeUpdate();
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(null, "deleted with success");

                   clean();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }
    // clean fields and enable the buttons
    private void clean(){
    
     txtOs.setText(null);
     
                    txtDate.setText(null);
                    txtCustId.setText(null);
                    txtOsEquip.setText(null);
                    txtOsDef.setText(null);
                    txtOsServ.setText(null);
                    txtOsTech.setText(null);
                    txtOsValue.setText(null);
                    txtCustSearch.setText(null);
                    ((DefaultTableModel) tblCust.getModel()).setRowCount(0);
                    cboOsSit.setSelectedItem(" ");

                    // enable the objects
                    btnOsCreate.setEnabled(true);
                    txtCustSearch.setEnabled(true);
                    tblCust.setVisible(true);
                    btnOsRead.setEnabled(true);
                    
                    // denable the objects
                    btnOsUpdate.setEnabled(false);
                    btnOsDelete.setEnabled(false);
                    btnOsPrint.setEnabled(false);
   
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        btnOsUpdate1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtOs = new javax.swing.JTextField();
        rbtBud = new javax.swing.JRadioButton();
        rbtOs = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCustSearch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCustId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCust = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtOsEquip = new javax.swing.JTextField();
        txtOsDef = new javax.swing.JTextField();
        txtOsServ = new javax.swing.JTextField();
        txtOsTech = new javax.swing.JTextField();
        txtOsValue = new javax.swing.JTextField();
        btnOsCreate = new javax.swing.JButton();
        btnOsRead = new javax.swing.JButton();
        btnOsUpdate = new javax.swing.JButton();
        btnOsDelete = new javax.swing.JButton();
        btnOsPrint = new javax.swing.JButton();

        jButton1.setText("jButton1");

        btnOsUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/update.png"))); // NOI18N
        btnOsUpdate1.setToolTipText("Update");
        btnOsUpdate1.setContentAreaFilled(false);
        btnOsUpdate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("OS");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("N°OS");

        jLabel1.setText("Date");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        txtOs.setEditable(false);
        txtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtBud);
        rbtBud.setSelected(true);
        rbtBud.setText("Budget");
        rbtBud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtBudActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOs);
        rbtOs.setText("Order of Service");
        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtBud, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtOs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDate)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDate)
                    .addComponent(txtOs))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtBud, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(rbtOs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setText("Situacion");

        cboOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "On the table", "Delivery OK", "Budget REPROVED", "Waiting for Aproval", "Waiting parts", "Abandoned by customer", "Returned" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer"));

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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/magnifier.png"))); // NOI18N

        jLabel5.setText("*Id");

        txtCustId.setEditable(false);
        txtCustId.setEnabled(false);
        txtCustId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustIdMouseClicked(evt);
            }
        });
        txtCustId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustIdActionPerformed(evt);
            }
        });

        tblCust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Name", "Fone"
            }
        ));
        tblCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCust);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtCustId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("Total value");

        jLabel7.setText("Technical");

        jLabel8.setText("Service");

        jLabel9.setText("*Defect");

        jLabel10.setText("*Equipment");

        txtOsEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsEquipActionPerformed(evt);
            }
        });

        txtOsDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsDefActionPerformed(evt);
            }
        });

        txtOsServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsServActionPerformed(evt);
            }
        });

        txtOsTech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsTechActionPerformed(evt);
            }
        });

        txtOsValue.setText("0");
        txtOsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsValueActionPerformed(evt);
            }
        });

        btnOsCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/add.png"))); // NOI18N
        btnOsCreate.setToolTipText("Add");
        btnOsCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsCreate.setPreferredSize(new java.awt.Dimension(64, 64));
        btnOsCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsCreateActionPerformed(evt);
            }
        });

        btnOsRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/search.png"))); // NOI18N
        btnOsRead.setToolTipText("Search");
        btnOsRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsReadActionPerformed(evt);
            }
        });

        btnOsUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/REDO.png"))); // NOI18N
        btnOsUpdate.setToolTipText("Update");
        btnOsUpdate.setContentAreaFilled(false);
        btnOsUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsUpdate.setEnabled(false);
        btnOsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsUpdateActionPerformed(evt);
            }
        });

        btnOsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/remove.png"))); // NOI18N
        btnOsDelete.setToolTipText("Delete");
        btnOsDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsDelete.setEnabled(false);
        btnOsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsDeleteActionPerformed(evt);
            }
        });

        btnOsPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/customerM/icons/printer.png"))); // NOI18N
        btnOsPrint.setToolTipText("printer OS");
        btnOsPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsPrint.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtOsServ, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtOsDef)
                                .addComponent(txtOsEquip)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtOsTech, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtOsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(btnOsRead, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOsCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnOsUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnOsPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnOsDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOsServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOsTech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtOsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnOsDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOsCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOsRead, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOsUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOsPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        setBounds(0, 0, 585, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsActionPerformed

    private void rbtBudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtBudActionPerformed
        // assigning a text to the type variable if selected
        type = "Budget";
    }//GEN-LAST:event_rbtBudActionPerformed

    private void rbtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOsActionPerformed
        // assigning a text to the type variable if selected
        type = "OS";
    }//GEN-LAST:event_rbtOsActionPerformed

    private void txtCustSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustSearchActionPerformed

    private void txtCustIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustIdActionPerformed

    private void txtOsEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsEquipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsEquipActionPerformed

    private void txtOsDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsDefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsDefActionPerformed

    private void txtOsServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsServActionPerformed

    private void txtOsTechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsTechActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsTechActionPerformed

    private void txtOsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsValueActionPerformed

    private void txtCustSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustSearchKeyReleased
        // call the method search_customer
        search_customer();
    }//GEN-LAST:event_txtCustSearchKeyReleased

    private void txtCustIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustIdMouseClicked
        // call the set_fields

    }//GEN-LAST:event_txtCustIdMouseClicked

    private void tblCustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustMouseClicked
        set_fields();
    }//GEN-LAST:event_tblCustMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // when open the form mark the radio button Budget
        rbtBud.setSelected(true);
        type = "Budget";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOsCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsCreateActionPerformed
        // call the method issue_os
        issue_os();

    }//GEN-LAST:event_btnOsCreateActionPerformed

    private void btnOsReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsReadActionPerformed
        // TODO add your handling code here:
        search_os();
    }//GEN-LAST:event_btnOsReadActionPerformed

    private void btnOsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsUpdateActionPerformed
        // call the change_os metho
        change_os();
    }//GEN-LAST:event_btnOsUpdateActionPerformed

    private void btnOsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsDeleteActionPerformed
        // call the delete_os method
        delete_os();
    }//GEN-LAST:event_btnOsDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsCreate;
    private javax.swing.JButton btnOsDelete;
    private javax.swing.JButton btnOsPrint;
    private javax.swing.JButton btnOsRead;
    private javax.swing.JButton btnOsUpdate;
    private javax.swing.JButton btnOsUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtBud;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTable tblCust;
    private javax.swing.JTextField txtCustId;
    private javax.swing.JTextField txtCustSearch;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsDef;
    private javax.swing.JTextField txtOsEquip;
    private javax.swing.JTextField txtOsServ;
    private javax.swing.JTextField txtOsTech;
    private javax.swing.JTextField txtOsValue;
    // End of variables declaration//GEN-END:variables
}
