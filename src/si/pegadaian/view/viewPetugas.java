/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.pegadaian.view;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import si.pegadaian.controller.controllerPetugas;
import si.pegadaian.db.koneksiDatabase;

/**
 *
 * @author user-pc
 */
public class viewPetugas extends javax.swing.JInternalFrame {

    /**
     * Creates new form viesCustomer
     */
    public controllerPetugas cP;
    public DefaultTableModel model;
    public String sql="";
    public viewPetugas() {
        initComponents();
        cP=new controllerPetugas(this);
        
        model=new DefaultTableModel();
        tabelPetugas.setModel(model);
        model.addColumn("No Ktp");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Level");
        
        tampilDataPetugas("");
    }

    public JTextField getNamaTF() {
        return namaTF;
    }

    public JTextArea getAlamatTA() {
        return alamatTA;
    }

    public JComboBox<String> getHakAksesCB() {
        return hakAksesCB;
    }

    public JTextField getNoKtpTF() {
        return noKtpTF;
    }

    public JTextField getPasswordTF() {
        return passwordTF;
    }

    public JTextField getUsernameTF() {
        return usernameTF;
    }
    
    public void tampilDataPetugas(String data){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
       if(data.equals("")){
            sql= "SELECT * FROM petugas";
        }else{
            sql="SELECT * FROM petugas WHERE Nama LIKE '"+data+"%'";
        }
        
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            ResultSet res= stat.executeQuery(sql);
            
            
            while(res.next()){
                Object[] hasil;
                hasil =new Object[6];//karena ada 6 field ditabel pelanggan
                hasil[0]=res.getInt("Nip");
                hasil[1]=res.getString("Nama");
                hasil[2]=res.getString("alamat");
                hasil[3]=res.getString("username");
                hasil[4]=res.getString("password");
                hasil[5]=res.getString("level");                
                
                model.addRow(hasil);
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ambilDataTabel(){
        int index=tabelPetugas.getSelectedRow();
        String nip=String.valueOf(tabelPetugas.getValueAt(index, 0));
        String nama=String.valueOf(tabelPetugas.getValueAt(index, 1));
        String alamat=String.valueOf(tabelPetugas.getValueAt(index, 2));
        String user=String.valueOf(tabelPetugas.getValueAt(index, 3));
        String pass=String.valueOf(tabelPetugas.getValueAt(index, 4));
        String level=String.valueOf(tabelPetugas.getValueAt(index, 5));
        
        noKtpTF.setText(nip);
        namaTF.setText(nama);
        alamatTA.setText(alamat);
        usernameTF.setText(user);
        passwordTF.setText(pass);
        if(level.equals("Admin")){
            hakAksesCB.setSelectedIndex(1);
        }else if(level.equals("Kasir")){
            hakAksesCB.setSelectedIndex(2);
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

        noKtpTF = new javax.swing.JTextField();
        usernameTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JTextField();
        updateBT = new javax.swing.JButton();
        saveBT = new javax.swing.JButton();
        noKtp = new javax.swing.JLabel();
        deleteBT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        resetBT = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        printTF = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cariPetugasTF = new javax.swing.JTextField();
        noKtp1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPetugas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamatTA = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        hakAksesCB = new javax.swing.JComboBox<>();
        noKtp2 = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        updateBT.setText("Update");
        updateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTActionPerformed(evt);
            }
        });

        saveBT.setText("Save");
        saveBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTActionPerformed(evt);
            }
        });

        noKtp.setText("No Ktp");

        deleteBT.setText("Delete");
        deleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTActionPerformed(evt);
            }
        });

        jLabel3.setText("Username");

        resetBT.setText("Reset");
        resetBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBTActionPerformed(evt);
            }
        });

        jLabel4.setText("Password");

        printTF.setText("Print");
        printTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTFActionPerformed(evt);
            }
        });

        jLabel5.setText("Alamat");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Form Data Petugas");

        cariPetugasTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariPetugasTFKeyPressed(evt);
            }
        });

        noKtp1.setText("Cari data berdasarkan nama");

        tabelPetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPetugas);

        alamatTA.setColumns(20);
        alamatTA.setRows(5);
        jScrollPane2.setViewportView(alamatTA);

        jLabel6.setText("Hak Akses");

        hakAksesCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "Admin", "Kasir" }));

        noKtp2.setText("Nama");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(noKtp)
                                            .addComponent(jLabel6)
                                            .addComponent(noKtp2))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(noKtpTF, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                        .addComponent(passwordTF, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                        .addComponent(namaTF, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                                                    .addComponent(hakAksesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(printTF))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(saveBT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(updateBT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deleteBT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(resetBT)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(noKtp1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cariPetugasTF, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noKtp1)
                            .addComponent(cariPetugasTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noKtp)
                            .addComponent(noKtpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noKtp2)
                            .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(hakAksesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBT)
                            .addComponent(saveBT)
                            .addComponent(deleteBT)
                            .addComponent(resetBT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printTF)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTActionPerformed
        // TODO add your handling code here:
        cP.simpanPetugas();
        cP.bersihkan();
        tampilDataPetugas("");
    }//GEN-LAST:event_saveBTActionPerformed

    private void printTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTFActionPerformed
        // TODO add your handling code here:
        try{
            koneksiDatabase print=new koneksiDatabase();
            Connection con=print.getKoneksi();
            InputStream input=getClass().getResourceAsStream("/report1.jrxml");
            JasperDesign design=JRXmlLoader.load(input);
            JasperReport report=JasperCompileManager.compileReport(design);
            JasperPrint jp=JasperFillManager.fillReport(report,null, con);
            JasperViewer.viewReport(jp,false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_printTFActionPerformed

    private void tabelPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPetugasMouseClicked
        // TODO add your handling code here:
        ambilDataTabel();
    }//GEN-LAST:event_tabelPetugasMouseClicked

    private void updateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTActionPerformed
        // TODO add your handling code here:
        cP.updatePetugas();
        cP.bersihkan();
        tampilDataPetugas("");
    }//GEN-LAST:event_updateBTActionPerformed

    private void deleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTActionPerformed
        // TODO add your handling code here:
        cP.deletePetugas();
        tampilDataPetugas("");
    }//GEN-LAST:event_deleteBTActionPerformed

    private void resetBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBTActionPerformed
        // TODO add your handling code here:
        cP.bersihkan();
    }//GEN-LAST:event_resetBTActionPerformed

    private void cariPetugasTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariPetugasTFKeyPressed
        // TODO add your handling code here:
        tampilDataPetugas(cariPetugasTF.getText());
    }//GEN-LAST:event_cariPetugasTFKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatTA;
    private javax.swing.JTextField cariPetugasTF;
    private javax.swing.JButton deleteBT;
    private javax.swing.JComboBox<String> hakAksesCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField namaTF;
    private javax.swing.JLabel noKtp;
    private javax.swing.JLabel noKtp1;
    private javax.swing.JLabel noKtp2;
    private javax.swing.JTextField noKtpTF;
    private javax.swing.JTextField passwordTF;
    private javax.swing.JButton printTF;
    private javax.swing.JButton resetBT;
    private javax.swing.JButton saveBT;
    private javax.swing.JTable tabelPetugas;
    private javax.swing.JButton updateBT;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
