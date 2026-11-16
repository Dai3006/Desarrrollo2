/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author daira
 */

import Controller.ChargeableController;
import Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Locale;

public class ChargeableView extends javax.swing.JFrame {

    private ChargeableController controller;
    private DefaultTableModel tableModel;
    private int nextTrackingNumber = 1;

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(ChargeableView.class.getName());

    public ChargeableView() {

        initComponents();

        controller = new ChargeableController();

        tableModel = new DefaultTableModel(
                new Object[]{"Tipo", "Código / Detalle", "Cobro"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblCharges.setModel(tableModel);

        cmbType.setModel(new DefaultComboBoxModel<>(new String[]{
            "Envío en Bicicleta",
            "Envío en Furgoneta",
            "Empaque"
        }));

        txtTracking.setEditable(false);

        setLocationRelativeTo(null);

        updateFields();
    }

    // Método para registrar los cobros
    private void registerCharge() {

        try {

            int selected = cmbType.getSelectedIndex();

            if (selected == 0 || selected == 1) {

                double distance = Double.parseDouble(
                        txtDistanceKm.getText().trim()
                );

                if (!Double.isFinite(distance) || distance <= 0) {
                    throw new IllegalArgumentException(
                            "La distancia debe ser mayor que cero."
                    );
                }

                String tracking = String.format(
                        Locale.US, "DEL-%03d", nextTrackingNumber
                );

                if (selected == 0) {

                    controller.registrarBikeDelivery(tracking, distance);

                } else {

                    controller.registrarVanDelivery(tracking, distance);
                }

                nextTrackingNumber++;

            } else {

                int items = Integer.parseInt(
                        txtItems.getText().trim()
                );

                double rate = Double.parseDouble(
                        txtChargePerItem.getText().trim()
                );

                if (items <= 0 || !Double.isFinite(rate) || rate <= 0) {
                    throw new IllegalArgumentException(
                            "La cantidad y la tarifa deben ser mayores que cero."
                    );
                }

                controller.registrarPackaging(items, rate);
            }

            refreshTable();
            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese números válidos.",
                    "Datos incorrectos",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Datos incorrectos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // Método para actualizar la tabla
    private void refreshTable() {

        tableModel.setRowCount(0);

        for (Chargeable item : controller.getElementos()) {

            String detail;

            if (item instanceof Delivery) {

                Delivery d = (Delivery) item;

                detail = d.getTrackingId() + " | "
                        + d.getDistanceKm() + " km";

            } else {

                Packaging p = (Packaging) item;

                detail = p.getItems() + " ítems x ₡"
                        + p.getChargePerItem();
            }

            tableModel.addRow(new Object[]{
                item.label(),
                detail,
                String.format(
                        Locale.US,
                        "₡%.2f",
                        item.calculateCharge()
                )
            });
        }

        lblTotal.setText(
                String.format(
                        Locale.US,
                        "Total general: ₡%.2f",
                        controller.calcularTotalGeneral()
                )
        );
    }

    // Método para limpiar los campos
    private void clearFields() {

        txtDistanceKm.setText("");
        txtItems.setText("");
        txtChargePerItem.setText("");

        updateFields();
    }

    // Método para habilitar los campos según el servicio
    private void updateFields() {

        boolean packaging = cmbType.getSelectedIndex() == 2;

        txtTracking.setEnabled(!packaging);
        txtDistanceKm.setEnabled(!packaging);
        distancia.setEnabled(!packaging);

        txtItems.setEnabled(packaging);
        txtChargePerItem.setEnabled(packaging);

        jLabel3.setEnabled(packaging);
        lblRate.setEnabled(packaging);

        if (packaging) {

            txtTracking.setText("");
            txtDistanceKm.setText("");

        } else {

            txtItems.setText("");
            txtChargePerItem.setText("");

            txtTracking.setText(
                    String.format(
                            Locale.US,
                            "DEL-%03d",
                            nextTrackingNumber
                    )
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTracking = new javax.swing.JTextField();
        lblRate = new javax.swing.JLabel();
        txtChargePerItem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCharges = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        distancia = new javax.swing.JLabel();
        txtDistanceKm = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtItems = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setText("Gestión cobro");

        jLabel1.setText("Tipo entrega:");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Envío en Bicicleta", "Envío en Furgoneta", "Empaque" }));
        cmbType.addActionListener(this::cmbTypeActionPerformed);

        jLabel2.setText("Codigo de seguimiento");

        txtTracking.addActionListener(this::txtTrackingActionPerformed);

        lblRate.setText("Tarifa");

        txtChargePerItem.addActionListener(this::txtChargePerItemActionPerformed);

        tblCharges.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCharges);

        btnClear.setText("Limpiar campos");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnRegister.setText("Registrar  cobro");
        btnRegister.addActionListener(this::btnRegisterActionPerformed);

        lblTotal.setText("Total:");

        distancia.setText("Distancia (KM)");

        txtDistanceKm.addActionListener(this::txtDistanceKmActionPerformed);

        jLabel3.setText("Items");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lblTitle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal))))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(distancia)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClear)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtChargePerItem, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(txtTracking)
                                        .addComponent(txtDistanceKm)
                                        .addComponent(txtItems)))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTracking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChargePerItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRate))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(distancia)
                    .addComponent(txtDistanceKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnClear))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrackingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrackingActionPerformed

    private void txtChargePerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChargePerItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChargePerItemActionPerformed

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        updateFields();
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void txtDistanceKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDistanceKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDistanceKmActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
       registerCharge();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ChargeableView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel distancia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRate;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblCharges;
    private javax.swing.JTextField txtChargePerItem;
    private javax.swing.JTextField txtDistanceKm;
    private javax.swing.JTextField txtItems;
    private javax.swing.JTextField txtTracking;
    // End of variables declaration//GEN-END:variables
}
