/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.pegadaian.view;


import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import si.pegadaian.controller.controllerTransaksi;
import si.pegadaian.controller.controllerTransaksiKasir;
import si.pegadaian.db.koneksiDatabase;

/**
 *
 * @author user-pc
 */
public class viewFormTransaksiKasir extends javax.swing.JInternalFrame {

    /**
     * Creates new form formTransaksi
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    public controllerTransaksiKasir cTK;
    public DefaultTableModel model;
    public DefaultTableModel modelTebus;
    private String sql="";
    public viewFormTransaksiKasir() {
        initComponents();
        DateFormat dateFormat1 = new SimpleDateFormat("dd MM,yyyy");
        tglSekarangTF.setText(dateFormat1.format(date));
        
        tampil_customer();
        tampil_barang();
        tampil_gadai();
        
        cTK=new controllerTransaksiKasir(this);
        
        model=new DefaultTableModel();
        tabelTransaksi.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Petugas");
        model.addColumn("Nama Nasabah");
        model.addColumn("Kode Barang");
        model.addColumn("Tanggal Gadai");
        model.addColumn("Jatuh Tempo");
        model.addColumn("Jumlah Pinjaman");
        model.addColumn("Jumlah Tebusan");
        model.addColumn("Keterangan");
        
        tampilDataTransaksi("");
        
        modelTebus=new DefaultTableModel();
        tabelTebusan.setModel(modelTebus);
        modelTebus.addColumn("ID");
        modelTebus.addColumn("Nama Petugas");
        modelTebus.addColumn("Nama Nasabah");
        modelTebus.addColumn("Kode Barang");
        modelTebus.addColumn("Jatuh Tempo");
        modelTebus.addColumn("Tanggal Tebusan");
        modelTebus.addColumn("Jumlah Pinjaman");
        modelTebus.addColumn("Jumlah Tebusan");
        modelTebus.addColumn("Denda");
        modelTebus.addColumn("Total Tebusan");
        modelTebus.addColumn("Keterangan");
        
        tampilDataTebusan("");
    }

    public JComboBox<String> getBarangCB() {
        return barangCB;
    }

    public JLabel getBunga() {
        return bunga;
    }

    public JComboBox<String> getCustomerCB() {
        return customerCB;
    }

    public JComboBox<String> getDataGadaiCB() {
        return dataGadaiCB;
    }

    public JTextField getDendaTF() {
        return dendaTF;
    }

    public JTextField getHariTF() {
        return hariTF;
    }

    public JDateChooser getJatuhTempo() {
        return jatuhTempo;
    }

    public JTextField getJumlahTebusanTF() {
        return jumlahTebusanTF;
    }

    public JTextField getTglSekarangTF() {
        return tglSekarangTF;
    }

    public JLabel getTotalTebusan() {
        return totalTebusan;
    }

    public JComboBox<String> getTransaksiCB() {
        return transaksiCB;
    }
    
    public void tampil_customer(){
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            String sql1 = "SELECT * FROM nasabah";
            ResultSet res= stat.executeQuery(sql1);
            customerCB.removeAllItems();
            customerCB.addItem("--Pilih Customer--");
          
            while(res.next()){
                customerCB.addItem(res.getString("Ktp")+". "+res.getString("Nama_nasabah"));
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewFormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void tampil_barang(){
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            String sql1 = "SELECT * FROM barang";
            ResultSet res= stat.executeQuery(sql1);
            barangCB.removeAllItems();
            barangCB.addItem("--Pilih Barang--");
          
            while(res.next()){
                barangCB.addItem(res.getString("Kode_barang")+". "+res.getString("Nama_Barang"));
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewFormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil_gadai(){
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            String sql1 = "SELECT No_gadai,Nama_petugas,Nama_nasabah, Kode_barang, Jatuh_tempo, Tgl_tebusan, Jumlah_pinjaman,Jumlah_tebusan,Denda, Total_tebusan, Keterangan "
                  + "FROM gadai gd, barang br, nasabah ns, petugas pt WHERE gd.Barang_Kode_barang=br.Kode_barang AND gd.Petugas_Nip=pt.Nip AND gd.Nasabah_Ktp=ns.Ktp ";
            ResultSet res= stat.executeQuery(sql1);
            dataGadaiCB.removeAllItems();
            dataGadaiCB.addItem("--Pilih Gadai--");
          
            while(res.next()){
                dataGadaiCB.addItem(res.getString("No_gadai")+". "+res.getString("Nama_nasabah")+". "+res.getString("Tgl_tebusan"));
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewFormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampilDataTransaksi(String data){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        if(data.equals("")){
            sql= "SELECT No_gadai,Nama_petugas,Nama_nasabah, Kode_barang, Tgl_gadai, Jatuh_tempo,Jumlah_pinjaman,Jumlah_tebusan,Keterangan "
                  + "FROM gadai gd, barang br, nasabah ns, petugas pt WHERE gd.Barang_Kode_barang=br.Kode_barang AND gd.Petugas_Nip=pt.Nip AND gd.Nasabah_Ktp=ns.Ktp ";
        }else{
            sql="SELECT No_gadai,Nama_petugas,Nama_nasabah, Kode_barang, Tgl_gadai, Jatuh_tempo,Jumlah_pinjaman,Jumlah_tebusan,Keterangan"
                    + " FROM gadai gd, barang br, nasabah ns, petugas pt "
                    + "WHERE gd.Barang_Kode_barang=br.Kode_barang "
                    + "AND gd.Petugas_Nip=pt.Nip "
                    + "AND gd.Nasabah_Ktp=ns.Ktp "
                    + "AND Keterangan LIKE '"+data+"%'";
        }
        
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            ResultSet res= stat.executeQuery(sql);
            
            
            while(res.next()){
                Object[] hasil;
                hasil =new Object[9];//karena ada 6 field ditabel pelanggan
                hasil[0]=res.getInt("No_gadai");
                hasil[1]=res.getString("Nama_petugas");
                hasil[2]=res.getString("Nama_nasabah");
                hasil[3]=res.getString("Kode_barang");
                hasil[4]=res.getString("Tgl_gadai");
                hasil[5]=res.getString("Jatuh_tempo");
                hasil[6]=res.getString("Jumlah_pinjaman");
                hasil[7]=res.getString("Jumlah_tebusan");
                hasil[8]=res.getString("Keterangan");

                model.addRow(hasil);
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampilDataTebusan(String data){
        modelTebus.getDataVector().removeAllElements();
        modelTebus.fireTableDataChanged();
        
        if(data.equals("")){
            sql= "SELECT No_gadai,Nama_petugas,Nama_nasabah, Kode_barang, Jatuh_tempo, Tgl_tebusan, Jumlah_pinjaman,Jumlah_tebusan,Denda, Total_tebusan, Keterangan "
                  + "FROM gadai gd, barang br, nasabah ns, petugas pt WHERE gd.Barang_Kode_barang=br.Kode_barang AND gd.Petugas_Nip=pt.Nip AND gd.Nasabah_Ktp=ns.Ktp ";
        }else{
            sql="SELECT No_gadai,Nama_petugas,Nama_nasabah, Kode_barang, Tgl_gadai, Jatuh_tempo,Jumlah_pinjaman,Jumlah_tebusan,Keterangan"
                    + " FROM gadai gd, barang br, nasabah ns, petugas pt "
                    + "WHERE gd.Barang_Kode_barang=br.Kode_barang "
                    + "AND gd.Petugas_Nip=pt.Nip "
                    + "AND gd.Nasabah_Ktp=ns.Ktp "
                    + "AND Jatuh_tempo LIKE '"+data+"%'";
        }
        
        try{
            Statement stat=(Statement) koneksiDatabase.getKoneksi().createStatement();
            ResultSet res= stat.executeQuery(sql);
            
            
            while(res.next()){
                Object[] hasil;
                hasil =new Object[11];//karena ada 6 field ditabel pelanggan
                hasil[0]=res.getInt("No_gadai");
                hasil[1]=res.getString("Nama_petugas");
                hasil[2]=res.getString("Nama_nasabah");
                hasil[3]=res.getString("Kode_barang");
                hasil[4]=res.getString("Jatuh_tempo");
                hasil[5]=res.getString("Tgl_tebusan");
                hasil[6]=res.getString("Jumlah_pinjaman");
                hasil[7]=res.getString("Jumlah_tebusan");
                hasil[7]=res.getString("Denda");
                hasil[7]=res.getString("Total_tebusan");
                hasil[10]=res.getString("Keterangan");

                modelTebus.addRow(hasil);
                
            }
        } catch(SQLException ex){
            Logger.getLogger(viewBarang.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        transaksiCB = new javax.swing.JComboBox<>();
        customerCB = new javax.swing.JComboBox<>();
        tglSekarangTF = new javax.swing.JTextField();
        jumlahPinjamanTF = new javax.swing.JTextField();
        hitungHariBT = new javax.swing.JButton();
        hariTF = new javax.swing.JTextField();
        dataGadaiCB = new javax.swing.JComboBox<>();
        jumlahTebusanTF = new javax.swing.JTextField();
        hitungBT = new javax.swing.JButton();
        dendaTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        totalTebusan = new javax.swing.JLabel();
        bunga = new javax.swing.JLabel();
        simpanBT = new javax.swing.JButton();
        deleteBT = new javax.swing.JButton();
        perpanjangBT = new javax.swing.JButton();
        detailBT = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cariTransaksiTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        jatuhTempo = new com.toedter.calendar.JDateChooser();
        tanggalTebusan = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        barangCB = new javax.swing.JComboBox<>();
        simpanTebusBT = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelTebusan = new javax.swing.JTable();

        setClosable(true);
        setTitle("Form Transaksi");

        jLabel1.setText("Transaksi");

        jLabel2.setText("Customer");

        jLabel3.setText("Tanggal Sekarang");

        jLabel4.setText("Jumlah Pinjaman");

        jLabel6.setText("Jatuh Tempo");

        jLabel7.setText("Jumlah Tebusan");

        jLabel8.setText("Data Gadai");

        jLabel9.setText("Tanggal Tebusan");

        jLabel10.setText("Hari");

        jLabel11.setText("Denda");

        transaksiCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "Gadai", "Tebusan", "Perpanjang", " " }));
        transaksiCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiCBActionPerformed(evt);
            }
        });

        customerCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        tglSekarangTF.setEditable(false);
        tglSekarangTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        hitungHariBT.setText("Hitung Hari");

        hariTF.setText("0");

        dataGadaiCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jumlahTebusanTF.setEditable(false);
        jumlahTebusanTF.setText("0");
        jumlahTebusanTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahTebusanTFActionPerformed(evt);
            }
        });

        hitungBT.setText("Hitung");

        dendaTF.setText("0");

        jLabel5.setText("Total Tebusan");

        jLabel12.setText("Bunga");

        jLabel13.setText("Rp. ");

        jLabel14.setText("Rp. ");

        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save-32.png"))); // NOI18N
        simpanBT.setText("Save");

        deleteBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete-property-32.png"))); // NOI18N
        deleteBT.setText("Delete");

        perpanjangBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit-property-32.png"))); // NOI18N
        perpanjangBT.setText("Pepanjang");

        detailBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/view-details-32.png"))); // NOI18N
        detailBT.setText("Detail");

        jLabel16.setText("Cari dengan keterangan");

        cariTransaksiTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariTransaksiTFKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cariTransaksiTFKeyTyped(evt);
            }
        });

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelTransaksi);

        jLabel17.setText("Barang");

        barangCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        simpanTebusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save-32.png"))); // NOI18N
        simpanTebusBT.setText("Save");

        jLabel18.setText("Transaksi Tebusan");

        tabelTebusan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelTebusan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(transaksiCB, 0, 137, Short.MAX_VALUE)
                                .addComponent(customerCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(barangCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tglSekarangTF)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(61, 61, 61)
                        .addComponent(dendaTF))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hariTF)
                            .addComponent(tanggalTebusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hitungBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jumlahTebusanTF, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(dataGadaiCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jatuhTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jumlahPinjamanTF)))
                    .addComponent(hitungHariBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalTebusan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bunga, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(simpanBT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(perpanjangBT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(detailBT)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(cariTransaksiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpanTebusBT))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(transaksiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(barangCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tglSekarangTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13)
                            .addComponent(totalTebusan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel14))
                            .addComponent(bunga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpanBT)
                            .addComponent(deleteBT)
                            .addComponent(perpanjangBT)
                            .addComponent(detailBT)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariTransaksiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jumlahPinjamanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jatuhTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hitungHariBT)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jumlahTebusanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(dataGadaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(tanggalTebusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(simpanTebusBT)
                        .addComponent(jLabel18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(hariTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hitungBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(dendaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jumlahTebusanTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahTebusanTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahTebusanTFActionPerformed

    private void transaksiCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiCBActionPerformed
        // TODO add your handling code here:
        switch(transaksiCB.getSelectedIndex()){
            case 0 :
                customerCB.setEnabled(false);
                barangCB.setEnabled(false);
                jumlahPinjamanTF.setEnabled(false);
                jatuhTempo.setEnabled(false);
                hitungHariBT.setEnabled(false);
                jumlahTebusanTF.setEnabled(false);
                dataGadaiCB.setEnabled(false);
                tanggalTebusan.setEnabled(false);
                hariTF.setEnabled(false);
                hitungBT.setEnabled(false);
                dendaTF.setEnabled(false);
                simpanBT.setEnabled(false);
                perpanjangBT.setEnabled(false);
                break;
            case 1 :
                customerCB.setEnabled(true);
                barangCB.setEnabled(true);
                jumlahPinjamanTF.setEnabled(true);
                jatuhTempo.setEnabled(true);
                hitungHariBT.setEnabled(true);
                simpanBT.setEnabled(true);
                jumlahTebusanTF.setEnabled(false);
                dataGadaiCB.setEnabled(false);
                tanggalTebusan.setEnabled(false);
                hariTF.setEnabled(false);
                hitungBT.setEnabled(false);
                dendaTF.setEnabled(false);
                perpanjangBT.setEnabled(false);
                simpanTebusBT.setEnabled(false);
                break;
            case 2 :
                customerCB.setEnabled(false);
                barangCB.setEnabled(false);
                jumlahPinjamanTF.setEditable(false);
                jatuhTempo.setEnabled(false);
                jumlahTebusanTF.setEditable(false);
                dataGadaiCB.setEnabled(true);
                tanggalTebusan.setEnabled(true);
                hariTF.setEnabled(true);
                hitungBT.setEnabled(true);
                dendaTF.setEnabled(true);
                simpanBT.setEnabled(false);
                hitungHariBT.setEnabled(false);
                perpanjangBT.setEnabled(false);
                deleteBT.setEnabled(false);
                detailBT.setEnabled(true);
                simpanTebusBT.setEnabled(false);
                break;
            case 3 :
                customerCB.setEnabled(false);
                barangCB.setEnabled(false);
                jumlahPinjamanTF.setEditable(false);
                jatuhTempo.setEnabled(true);
                jumlahTebusanTF.setEditable(false);
                hitungHariBT.setEnabled(false);
                dataGadaiCB.setEnabled(true);
                tanggalTebusan.setEnabled(false);
                hariTF.setEditable(false);
                hitungBT.setEnabled(false);
                dendaTF.setEditable(false);
                simpanBT.setEnabled(false);
                deleteBT.setEnabled(false);
                detailBT.setEnabled(false);
                perpanjangBT.setEnabled(true);
                simpanTebusBT.setEnabled(false);
                break;
        }
    }//GEN-LAST:event_transaksiCBActionPerformed

    private void cariTransaksiTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTransaksiTFKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cariTransaksiTFKeyPressed

    private void cariTransaksiTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTransaksiTFKeyTyped
        // TODO add your handling code here:
        tampilDataTransaksi(cariTransaksiTF.getText());
    }//GEN-LAST:event_cariTransaksiTFKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> barangCB;
    private javax.swing.JLabel bunga;
    private javax.swing.JTextField cariTransaksiTF;
    private javax.swing.JComboBox<String> customerCB;
    private javax.swing.JComboBox<String> dataGadaiCB;
    private javax.swing.JButton deleteBT;
    private javax.swing.JTextField dendaTF;
    private javax.swing.JButton detailBT;
    private javax.swing.JTextField hariTF;
    private javax.swing.JButton hitungBT;
    private javax.swing.JButton hitungHariBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jatuhTempo;
    private javax.swing.JTextField jumlahPinjamanTF;
    private javax.swing.JTextField jumlahTebusanTF;
    private javax.swing.JButton perpanjangBT;
    private javax.swing.JButton simpanBT;
    private javax.swing.JButton simpanTebusBT;
    private javax.swing.JTable tabelTebusan;
    private javax.swing.JTable tabelTransaksi;
    private com.toedter.calendar.JDateChooser tanggalTebusan;
    private javax.swing.JTextField tglSekarangTF;
    private javax.swing.JLabel totalTebusan;
    private javax.swing.JComboBox<String> transaksiCB;
    // End of variables declaration//GEN-END:variables
}
