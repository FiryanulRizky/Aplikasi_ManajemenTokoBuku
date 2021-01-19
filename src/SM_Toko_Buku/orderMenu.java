/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM_Toko_Buku;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rasito7659
 */
public class orderMenu extends javax.swing.JFrame {

    String id_peg;
    private DefaultTableModel model;
    /**
     * Creates new form orderMenu
     */
    public orderMenu() {
        initComponents();
    }
    
    public orderMenu(String id_pegawai) {
        initComponents();
        this.setLocationRelativeTo(null);
        id_peg = id_pegawai;
        fillCombo();
        
        model = new DefaultTableModel();
        
        orderTable.setModel(model);
        
        model.addColumn("No Order");
        model.addColumn("Supplier");
        model.addColumn("Tanggal");
        
        loadData();
        
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        
        model.fireTableDataChanged();
        try{
            Connection c = Koneksi.getKoneksi();
            try (Statement s = c.createStatement()) {
                String sql = "SELECT * FROM tb_order INNER JOIN tb_supplier USING(id_supplier)";
                try (ResultSet r = s.executeQuery(sql)) {
                    while(r.next()){
                        Object[] o = new Object[3];
                        o[0] = r.getString("no_order");
                        o[1] = r.getString("nama");
                        o[2] = r.getString("tgl");
                        model.addRow(o);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("Terjadi Error");
        }
    }
    
    public void fillCombo(){
        Connection c = Koneksi.getKoneksi();
        Statement s;
        try {
            s = c.createStatement();
            String sql = "SELECT * FROM tb_supplier";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String nama = rs.getString("nama");
                orderCB.addItem(nama);
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderMenu.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        noText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        orderCB = new javax.swing.JComboBox<>();
        orderDC = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        detailButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(1, 50, 67));
        jPanel1.setForeground(new java.awt.Color(1, 50, 67));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 227, 167));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List Order");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(253, 227, 167));
        jLabel2.setText("NO :");

        noText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noText.setForeground(new java.awt.Color(1, 50, 67));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(253, 227, 167));
        jLabel3.setText("Supplier :");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(253, 227, 167));
        jLabel4.setText("Tanggal :");

        orderCB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        orderDC.setDateFormatString("yyyy-MM-dd");

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orderTable);

        addButton.setBackground(new java.awt.Color(123, 239, 178));
        addButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(1, 50, 67));
        addButton.setText("Tambah");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        upButton.setBackground(new java.awt.Color(123, 239, 178));
        upButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        upButton.setForeground(new java.awt.Color(1, 50, 67));
        upButton.setText("Edit");
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        delButton.setBackground(new java.awt.Color(123, 239, 178));
        delButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        delButton.setForeground(new java.awt.Color(1, 50, 67));
        delButton.setText("Hapus");
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(238, 238, 0));
        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(1, 50, 67));
        backButton.setText("Kembali");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        detailButton.setBackground(new java.awt.Color(123, 239, 178));
        detailButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        detailButton.setForeground(new java.awt.Color(1, 50, 67));
        detailButton.setText("Detail");
        detailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(noText, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(orderCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(orderDC, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(detailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(delButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(backButton)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(orderDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(detailButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        String no = noText.getText();
        String value = orderCB.getSelectedItem().toString();
        String date = ((JTextField)orderDC.getDateEditor().getUiComponent()).getText();
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_supplier FROM tb_supplier WHERE nama='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql= "INSERT INTO `tb_order` VALUES ('"+no+"','"+id+"','"+date+"')";
            s.executeUpdate(sql);
            noText.setText("");
            ((JTextField)orderDC.getDateEditor().getUiComponent()).setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        
        int brs = orderTable.rowAtPoint(evt.getPoint());
        String no = orderTable.getValueAt(brs,0).toString();
        noText.setText(no);
        Connection c = Koneksi.getKoneksi();
        try {
            Statement s = c.createStatement();
            String sql = "Select * From tb_order Where no_order='"+no+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                String date = rs.getString("tgl");
                ((JTextField)orderDC.getDateEditor().getUiComponent()).setText(date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_orderTableMouseClicked

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        
        String no = noText.getText();
        String value = orderCB.getSelectedItem().toString();
        String date = ((JTextField)orderDC.getDateEditor().getUiComponent()).getText();
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_supplier FROM tb_supplier WHERE nama='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql= "UPDATE `tb_order` SET `id_supplier`='"+id+"',`tgl`='"+date+"' WHERE `no_order`='"+no+"'";
            s.executeUpdate(sql);
            noText.setText("");
            ((JTextField)orderDC.getDateEditor().getUiComponent()).setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_upButtonActionPerformed

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        
        String no = noText.getText();
        Connection c = Koneksi.getKoneksi();
        try {
            Statement s = c.createStatement();
            String sql= "DELETE FROM `tb_order` WHERE `no_order`='"+no+"'";
            s.executeUpdate(sql);
            noText.setText("");
            ((JTextField)orderDC.getDateEditor().getUiComponent()).setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_delButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        mainMenu mm = new mainMenu(id_peg);
        mm.setVisible(true);
        setVisible(false);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void detailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailButtonActionPerformed
        
        String no = noText.getText();
        orderDetail od = new orderDetail(no);
        od.setVisible(true);
        
    }//GEN-LAST:event_detailButtonActionPerformed

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
            java.util.logging.Logger.getLogger(orderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orderMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton delButton;
    private javax.swing.JButton detailButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField noText;
    private javax.swing.JComboBox<String> orderCB;
    private com.toedter.calendar.JDateChooser orderDC;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
