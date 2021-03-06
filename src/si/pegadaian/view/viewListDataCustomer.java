/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.pegadaian.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import si.pegadaian.db.koneksiDatabase;

/**
 *
 * @author user-pc
 */
public class viewListDataCustomer extends javax.swing.JFrame {

    /**
     * Creates new form viewListDataPetugas
     */
    public int ktp=0;
    private viewFormTransaksi vFT;
    private DefaultTableModel model;
    private String sql="";
    public viewListDataCustomer(viewFormTransaksi vFT) {
        initComponents();
        this.vFT=vFT;
        
        model=new DefaultTableModel();
        tabelListDataCustomer.setModel(model);
        model.addColumn("No KTP");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No Hp");
        
        tampilDataCustomer("");
    }

    public JTextField getCariTF() {
        return cariTF;
    }
    
    public void tampilDataCustomer(String data){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
       if(data.equals("")){
            sql= "SELECT * FROM nasabah";
        }else{
            sql="SELECT * FROM nasabah WHERE Nama_nasabah LIKE '"+data+"%'";
        }
        
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            ResultSet res= stat.executeQuery(sql);
            
            
            while(res.next()){
                Object[] hasil;
                hasil =new Object[4];//karena ada 6 field ditabel pelanggan
                hasil[0]=res.getInt("Ktp");
                hasil[1]=res.getString("Nama_nasabah");
                hasil[2]=res.getString("Alamat");
                hasil[3]=res.getString("Hp");
                
                model.addRow(hasil);                
               
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewListDataCustomer.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        cariTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelListDataCustomer = new javax.swing.JTable();

        jLabel1.setText("Cari Nama Petugas");

        cariTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTFActionPerformed(evt);
            }
        });
        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariTFKeyPressed(evt);
            }
        });

        tabelListDataCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelListDataCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelListDataCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelListDataCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cariTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTFActionPerformed

    private void cariTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyPressed
        // TODO add your handling code here:
        tampilDataCustomer(cariTF.getText());
    }//GEN-LAST:event_cariTFKeyPressed

    private void tabelListDataCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelListDataCustomerMouseClicked
        // TODO add your handling code here:
        int ambilRow=tabelListDataCustomer.getSelectedRow();
//        vFT.getCustomerTF().setText(tabelListDataPetugas.getValueAt(ambilRow, 0).toString());
        vFT.getCustomerTF().setText(tabelListDataCustomer.getValueAt(ambilRow,1).toString()/*+". "+tabelListDataCustomer.getValueAt(ambilRow,1).toString()*/);
        String n = tabelListDataCustomer.getValueAt(ambilRow,0).toString();
        ktp = Integer.parseInt(n);
        vFT.getCustomerTF().requestFocus();
        dispose();
    }//GEN-LAST:event_tabelListDataCustomerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cariTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelListDataCustomer;
    // End of variables declaration//GEN-END:variables
}
