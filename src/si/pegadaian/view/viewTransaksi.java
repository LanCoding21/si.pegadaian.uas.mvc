/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.pegadaian.view;

/**
 *
 * @author user-pc
 */
public class viewTransaksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form viewTransaksi
     */
    viewFormTransaksi vFT= new viewFormTransaksi();
    viewDataTransaksi vDT= new viewDataTransaksi();
    view
    public viewTransaksi() {
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

        transaksiBT = new javax.swing.JButton();
        dataTransaksiBT = new javax.swing.JButton();
        transaksiPanel = new javax.swing.JPanel();

        setClosable(true);
        setMaximizable(true);

        transaksiBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/transaksi 2.png"))); // NOI18N
        transaksiBT.setText("Transaksi");
        transaksiBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksiBTMouseClicked(evt);
            }
        });

        dataTransaksiBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bill-32.png"))); // NOI18N
        dataTransaksiBT.setText("Data Transaksi");
        dataTransaksiBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTransaksiBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(transaksiBT)
                .addGap(28, 28, 28)
                .addComponent(dataTransaksiBT)
                .addContainerGap(225, Short.MAX_VALUE))
            .addComponent(transaksiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transaksiBT)
                    .addComponent(dataTransaksiBT))
                .addGap(18, 18, 18)
                .addComponent(transaksiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transaksiBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiBTMouseClicked
        // TODO add your handling code here:
        this.transaksiPanel.remove(vDT);
        this.transaksiPanel.add(vFT);
        vFT.show();
    }//GEN-LAST:event_transaksiBTMouseClicked

    private void dataTransaksiBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataTransaksiBTActionPerformed
        // TODO add your handling code here:
        this.transaksiPanel.remove(vFT);
        this.transaksiPanel.add(vDT);
        vDT.show();
    }//GEN-LAST:event_dataTransaksiBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dataTransaksiBT;
    private javax.swing.JButton transaksiBT;
    private javax.swing.JPanel transaksiPanel;
    // End of variables declaration//GEN-END:variables
}
