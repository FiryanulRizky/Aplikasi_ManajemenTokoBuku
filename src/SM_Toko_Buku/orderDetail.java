/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SM_Toko_Buku;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rasito7659
 */
public class orderDetail extends javax.swing.JFrame {

    String no_ord;
    int max = 0; 
    private DefaultTableModel model;
    /**
     * Creates new form orderDetail
     */
    public orderDetail() {
        initComponents();
    }
    
    public orderDetail(String no_order) {
        initComponents();
        this.setLocationRelativeTo(null);
        no_ord = no_order;
        fillCombo();
        auto_id();
        
        model = new DefaultTableModel();
        
        obTable.setModel(model);
        
        model.addColumn("No");
        model.addColumn("Judul Buku");
        model.addColumn("Harga Per Unit");
        model.addColumn("Jumlah");
        
        loadData();
        
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        
        model.fireTableDataChanged();
        try{
            Connection c = Koneksi.getKoneksi();
            try (Statement s = c.createStatement()) {
                String sql = "SELECT * FROM order_detail INNER JOIN tb_buku USING(id_buku) WHERE no_order='"+no_ord+"'";
                try (ResultSet r = s.executeQuery(sql)) {
                    while(r.next()){
                        Object[] o = new Object[4];
                        o[0] = r.getString("no");
                        o[1] = r.getString("judul");
                        o[2] = r.getString("harga_unit");
                        o[3] = r.getString("jumlah");
                        model.addRow(o);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("Terjadi Error");
        }
    }
    
    public void auto_id(){
        noText.setText("");
        Connection c = Koneksi.getKoneksi();
        try {
            Statement s = c.createStatement();
            String sql = "SELECT MAX(no) FROM order_detail";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                max = rs.getInt(1);
                max += 1;                
            }
            String no = Integer.toString(max);
            noText.setText(no);
        } catch (SQLException ex) {
            Logger.getLogger(orderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillCombo(){
        Connection c = Koneksi.getKoneksi();
        Statement s;
        try {
            s = c.createStatement();
            String sql = "SELECT * FROM tb_buku";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String nama = rs.getString("judul");
                obukuCB.addItem(nama);
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDetail.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        obTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        obukuCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        hpuText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        noText = new javax.swing.JTextField();
        noButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 50, 67));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 300));

        obTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        obTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(obTable);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 227, 167));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Order Buku");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(253, 227, 167));
        jLabel2.setText("Judul Buku :");

        obukuCB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        obukuCB.setForeground(new java.awt.Color(1, 50, 67));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(253, 227, 167));
        jLabel3.setText("Harga per Unit :");

        hpuText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(253, 227, 167));
        jLabel4.setText("Jumlah :");

        jText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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

        backButton.setBackground(new java.awt.Color(236, 100, 75));
        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(1, 50, 67));
        backButton.setText("Tutup");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        noText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        noButton.setBackground(new java.awt.Color(123, 239, 178));
        noButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noButton.setForeground(new java.awt.Color(1, 50, 67));
        noButton.setText("NO");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(noButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noText, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(obukuCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(hpuText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(jText))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(delButton)
                    .addComponent(noButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(obukuCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hpuText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upButton)
                    .addComponent(backButton)
                    .addComponent(noText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        setVisible(false);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        String no = noText.getText();
        String value = obukuCB.getSelectedItem().toString();
        String harga = hpuText.getText();
        String jumlah = jText.getText();
        
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_buku FROM tb_buku WHERE judul='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql= "INSERT INTO `order_detail` VALUES ('"+no+"','"+no_ord+"','"+id+"','"+harga+"','"+jumlah+"')";
            s.executeUpdate(sql);
            hpuText.setText("");
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        auto_id();
        loadData();
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void obTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obTableMouseClicked
        
        noText.setText("");
        int brs = obTable.rowAtPoint(evt.getPoint());
        String no = obTable.getValueAt(brs,0).toString();
        noText.setText(no);
        String harga = obTable.getValueAt(brs,2).toString();
        hpuText.setText(harga);
        String jumlah = obTable.getValueAt(brs,3).toString();
        jText.setText(jumlah);
        
    }//GEN-LAST:event_obTableMouseClicked

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        
        String no = noText.getText();
        String value = obukuCB.getSelectedItem().toString();
        String harga = hpuText.getText();
        String jumlah = jText.getText();
        
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_buku FROM tb_buku WHERE judul='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql= "UPDATE `order_detail` SET `id_buku`='"+id+"',`harga_unit`='"+harga+"',`jumlah`='"+jumlah+"' WHERE `no`='"+no+"'";
            s.executeUpdate(sql);
            noText.setText("");
            hpuText.setText("");
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_upButtonActionPerformed

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        
        String no = noText.getText();
        Connection c = Koneksi.getKoneksi();

        try {
            Statement s = c.createStatement();
            String sql = "DELETE FROM `order_detail` WHERE `no`='"+no+"'";
            s.executeUpdate(sql);
            noText.setText("");
            hpuText.setText("");
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(orderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_delButtonActionPerformed

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        
        auto_id();
        
    }//GEN-LAST:event_noButtonActionPerformed

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
            java.util.logging.Logger.getLogger(orderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orderDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton delButton;
    private javax.swing.JTextField hpuText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jText;
    private javax.swing.JButton noButton;
    private javax.swing.JTextField noText;
    private javax.swing.JTable obTable;
    private javax.swing.JComboBox<String> obukuCB;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
