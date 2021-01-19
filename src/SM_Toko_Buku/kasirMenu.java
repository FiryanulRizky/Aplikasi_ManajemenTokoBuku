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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rasito7659
 */
public class kasirMenu extends javax.swing.JFrame {

    String id_peg;
    int max = 0; 
    java.sql.Date sDate;
    private DefaultTableModel model;
    /**
     * Creates new form kasirMenu
     */
    public kasirMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public kasirMenu(String id_pegawai) {
        initComponents();
        id_peg = id_pegawai;
        fillCombo();
        String nama = null;
        Date uDate = new Date();
        sDate = convertUtilToSql(uDate);
        this.setLocationRelativeTo(null);
        
        Connection c = Koneksi.getKoneksi();
        try {
            Statement s = c.createStatement();
            String sql = "SELECT MAX(no_transaksi) FROM tb_transaksi";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                max = rs.getInt(1);
                max += 1;                
            }
            sql = "SELECT nama FROM tb_pegawai WHERE id_pegawai='"+id_peg+"'";
            rs = s.executeQuery(sql);
            if(rs.next()){
                nama = rs.getString(1);             
            }
            
            sql = "INSERT INTO `tb_transaksi` VALUES ('"+max+"','"+id_peg+"','"+sDate+"')";
            s.executeUpdate(sql);
            
            model = new DefaultTableModel();
        
            kasirTable.setModel(model);

            model.addColumn("No");
            model.addColumn("Judul Buku");
            model.addColumn("Harga");
            model.addColumn("Jumlah");

            loadData();

            } catch (SQLException ex) {
                Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            pegText.setText(nama);
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate){
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        
        model.fireTableDataChanged();
        totalText.setText("");
        try{
            Connection c = Koneksi.getKoneksi();
            try (Statement s = c.createStatement()) {
                String sql = "SELECT * FROM transaksi_detail INNER JOIN tb_buku USING(id_buku) WHERE no_transaksi='"+max+"'";
                try (ResultSet r = s.executeQuery(sql)) {
                    int n = 1;
                    int total = 0, harga = 0, jumlah = 0;
                    while(r.next()){
                        Object[] o = new Object[4];
                        o[0] = n++ ;
                        o[1] = r.getString("judul");
                        o[2] = r.getString("harga");
                        harga = r.getInt(9);
                        o[3] = r.getString("jumlah");
                        jumlah = r.getInt(4);
                        total += (harga*jumlah);
                        model.addRow(o);
                    }
                    String ttl = Integer.toString(total);
                    totalText.setText(ttl);
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
            String sql = "SELECT * FROM tb_buku WHERE stok!=0";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String nama = rs.getString("judul");
                kasirCB.addItem(nama);
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        batalButton = new javax.swing.JButton();
        pegText = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        kasirCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        kasirTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        totalText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bayarText = new javax.swing.JTextField();
        bayarButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        kembalian = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(1, 50, 67));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 227, 167));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KASIR");

        batalButton.setBackground(new java.awt.Color(236, 100, 75));
        batalButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        batalButton.setForeground(new java.awt.Color(1, 50, 67));
        batalButton.setText("Batal");
        batalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalButtonActionPerformed(evt);
            }
        });

        pegText.setEditable(false);
        pegText.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pegText.setForeground(new java.awt.Color(1, 50, 67));
        pegText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pegText.setDisabledTextColor(new java.awt.Color(253, 227, 167));

        saveButton.setBackground(new java.awt.Color(123, 239, 178));
        saveButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        saveButton.setForeground(new java.awt.Color(1, 50, 67));
        saveButton.setText("Simpan");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pegText, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(18, 18, 18)
                        .addComponent(batalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pegText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(253, 227, 167));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 50, 67));
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 50, 67));
        jLabel3.setText("Jumlah");

        jText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        kasirTable.setModel(new javax.swing.table.DefaultTableModel(
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
        kasirTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kasirTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(kasirTable);

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL :");

        totalText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Bayar :");

        bayarText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bayarText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        bayarButton.setBackground(new java.awt.Color(123, 239, 178));
        bayarButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        bayarButton.setForeground(new java.awt.Color(1, 50, 67));
        bayarButton.setText("Bayar");
        bayarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Kembalian :");

        kembalian.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        kembalian.setForeground(new java.awt.Color(1, 50, 67));
        kembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kasirCB, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalText)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bayarButton))
                            .addComponent(bayarText)
                            .addComponent(jLabel7)
                            .addComponent(kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kasirCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bayarButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bayarText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        
        Connection c = Koneksi.getKoneksi();
        try {
            Statement s = c.createStatement();
            String sql = "DELETE FROM `tb_transaksi` WHERE `no_transaksi`='"+max+"'";
            s.executeUpdate(sql);
            
            mainMenu mm = new mainMenu(id_peg);
            mm.setVisible(true);
            setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_batalButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        String value = kasirCB.getSelectedItem().toString();
        String jumlah = jText.getText();
        
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0, no_bu = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_buku FROM tb_buku WHERE judul='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql = "SELECT MAX(no) FROM transaksi_detail";
            rs = s.executeQuery(sql);
            if(rs.next()){
                no_bu = rs.getInt(1);
                no_bu++;
            }
            sql = "INSERT INTO `transaksi_detail` VALUES ('"+no_bu+"','"+max+"','"+id+"','"+jumlah+"')";
            s.executeUpdate(sql);
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        
        String value = kasirCB.getSelectedItem().toString();
        String jumlah = jText.getText();
        
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0, no_bu = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_buku FROM tb_buku WHERE judul='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql = "UPDATE `transaksi_detail` SET `jumlah`='"+jumlah+"' WHERE `id_buku`='"+id+"'";
            s.executeUpdate(sql);
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_upButtonActionPerformed

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        
        String value = kasirCB.getSelectedItem().toString();
        
        Connection c = Koneksi.getKoneksi();
        try {
            int id = 0, no_bu = 0;
            Statement s = c.createStatement();
            String sql = "SELECT id_buku FROM tb_buku WHERE judul='"+value+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt(1);
            }
            sql = "DELETE FROM `transaksi_detail` WHERE `id_buku`='"+id+"'";
            s.executeUpdate(sql);
            jText.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        
    }//GEN-LAST:event_delButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        max++;
        Connection c = Koneksi.getKoneksi();
        Statement s;
        try {
            s = c.createStatement();
            String sql = "INSERT INTO `tb_transaksi` VALUES ('"+max+"','"+id_peg+"','"+sDate+"')";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(kasirMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadData();
        bayarText.setText("");
        kembalian.setText("");
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        mainMenu mm = new mainMenu(id_peg);
        mm.setVisible(true);
        setVisible(false);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void kasirTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kasirTableMouseClicked
        
        int brs = kasirTable.rowAtPoint(evt.getPoint());
        String value = kasirTable.getValueAt(brs,1).toString();
        kasirCB.setSelectedItem(value);
        String jumlah = kasirTable.getValueAt(brs,3).toString();
        jText.setText(jumlah);
        
    }//GEN-LAST:event_kasirTableMouseClicked

    private void bayarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarButtonActionPerformed
        
        int tot = Integer.parseInt(this.totalText.getText());
        int pay = Integer.parseInt(this.bayarText.getText());
        
        if(pay >= tot){
            int change = pay - tot;
            String km = Integer.toString(change);
            this.kembalian.setText(km);
        }else{
            JOptionPane.showMessageDialog(null, "Uang Kurang!");
        }
        
    }//GEN-LAST:event_bayarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(kasirMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kasirMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kasirMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kasirMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kasirMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton batalButton;
    private javax.swing.JButton bayarButton;
    private javax.swing.JTextField bayarText;
    private javax.swing.JButton delButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jText;
    private javax.swing.JComboBox<String> kasirCB;
    private javax.swing.JTable kasirTable;
    private javax.swing.JLabel kembalian;
    private javax.swing.JTextField pegText;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField totalText;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
