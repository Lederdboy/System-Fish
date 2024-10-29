package Formularios;

import Clases.Cls_Cliente;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class Frm_Clientes extends javax.swing.JInternalFrame {

    private final Cls_Cliente CC;
    TableColumnModel columnModel;
    int num = 0;

    public Frm_Clientes() {
        initComponents();
        CC = new Cls_Cliente();
        columnModel = jtb_clientes.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
    }

    private void listar() {
        jtb_clientes.setModel(CC.getDatosClientes());
        columnModel.getColumn(2).setPreferredWidth(600);
    }

    private void iniciar() {
        txt_nombre.setEnabled(false);
        txt_nro_documento.setEnabled(false);
        txt_nro_contacto.setEnabled(false);
        CboTipoDoc.setEnabled(false);
    }

    private void activar() {
        txt_nombre.setEnabled(true);
        txt_nro_documento.setEnabled(true);
        txt_nro_contacto.setEnabled(true);
        CboTipoDoc.setEnabled(true);
        txt_nombre.requestFocus();
    }

    private void limpiar() {
        txt_nombre.setText("");
        txt_nro_documento.setText("");
        txt_nro_contacto.setText("");
        CboTipoDoc.setSelectedItem("DNI");
        txt_nombre.requestFocus();
        jtb_clientes.clearSelection();
    }

    private void guardar() {
        String cli_nombre = txt_nombre.getText();
        String cli_cel_contacto = txt_nro_contacto.getText();
        String nroDocumento = txt_nro_documento.getText();
        String seleccion = (String) CboTipoDoc.getSelectedItem();
        Integer[] cli_tipo_doc = new Integer[1];

        if ("DNI".equals(seleccion)) {
            cli_tipo_doc[0] = 1;
            if (nroDocumento.length() != 8 || !nroDocumento.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 8 números.");
                return;
            }
        } else if ("CARNET DE EXTRANJERIA".equals(seleccion)) {
            cli_tipo_doc[0] = 3;
            if (nroDocumento.length() != 20 || !nroDocumento.matches("\\d{20}")) {
                JOptionPane.showMessageDialog(null, "El Carnet de Extranjería debe tener exactamente 20 números.");
                return;
            }
        } else if ("RUC".equals(seleccion)) {
            cli_tipo_doc[0] = 2;
            if (nroDocumento.length() != 11 || !nroDocumento.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(null, "El RUC debe tener exactamente 11 números.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de documento válido.");
            return;
        }

        if (!cli_cel_contacto.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "El número de contacto debe ser numérico (debe contener solo 9 digitos)");
            return;
        }
        Integer cli_nro_doc = Integer.valueOf(nroDocumento);
        Integer cli_cel_contacto_int = Integer.valueOf(cli_cel_contacto);
        
        
        if (CC.verificarNombreCliente(cli_nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre del cliente ya existe. Por favor, use un nombre diferente.");
            return;
        }
        if (CC.verificarDocumentoCliente(cli_nro_doc)) {
        JOptionPane.showMessageDialog(null, "El número de documento ya existe. Por favor, use un número diferente.");
        return;
    }

        if (num == 0) {
            int respuesta = CC.registrarClientes(cli_nro_doc, cli_tipo_doc[0], cli_nombre, cli_cel_contacto_int);
            if (respuesta > 0) {
                listar();
                limpiar();
                iniciar();
                bt_actualizar.setEnabled(false);
            }
        } else {
            int row = jtb_clientes.getSelectedRow();
            String doc_old = jtb_clientes.getValueAt(row, 0).toString();

            int respuesta = CC.actualizarCliente(cli_nro_doc, cli_tipo_doc[0], cli_nombre, cli_cel_contacto_int, doc_old);
            if (respuesta > 0) {
                listar();
                limpiar();
                iniciar();
                num = 0;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_clientes = new javax.swing.JTable();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_nuevo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_nro_documento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nro_contacto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CboTipoDoc = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(1041, 547));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1025, 495));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre completo");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        jtb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_clientes);

        bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_grabar.png"))); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.setBorderPainted(false);
        bt_guardar.setContentAreaFilled(false);
        bt_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_actualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_modificar.png"))); // NOI18N
        bt_actualizar.setText("Modificar");
        bt_actualizar.setBorderPainted(false);
        bt_actualizar.setContentAreaFilled(false);
        bt_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualizarActionPerformed(evt);
            }
        });

        bt_eliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_eliminar.png"))); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorderPainted(false);
        bt_eliminar.setContentAreaFilled(false);
        bt_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_nuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ic_nuevo.png"))); // NOI18N
        bt_nuevo.setText("Nuevo");
        bt_nuevo.setBorderPainted(false);
        bt_nuevo.setContentAreaFilled(false);
        bt_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nro. documento *");

        txt_nro_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nro_documentoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Registro de Clientes");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel3.setText("Llene la información respectiva de los clientes.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nro contacto");

        txt_nro_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nro_contactoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Tipo de documento");

        CboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "RUC", "CARNET DE ESTRANJERIA" }));
        CboTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboTipoDocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 701, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_nro_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(CboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_nro_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 80, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_nuevo)
                .addGap(62, 62, 62)
                .addComponent(bt_guardar)
                .addGap(75, 75, 75)
                .addComponent(bt_actualizar)
                .addGap(86, 86, 86)
                .addComponent(bt_eliminar)
                .addGap(160, 160, 160))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nro_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nro_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guardar)
                    .addComponent(bt_actualizar)
                    .addComponent(bt_eliminar)
                    .addComponent(bt_nuevo))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_bt_guardarActionPerformed

    private void jtb_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_clientesMouseClicked
        bt_actualizar.setEnabled(true);
        bt_eliminar.setEnabled(true);

        int row = jtb_clientes.getSelectedRow();
        txt_nombre.setText(jtb_clientes.getValueAt(row, 2).toString());
        txt_nro_documento.setText(jtb_clientes.getValueAt(row, 0).toString());
        txt_nro_contacto.setText(jtb_clientes.getValueAt(row, 3).toString());

        switch (jtb_clientes.getValueAt(row, 1).toString()) {
            case "1":
                CboTipoDoc.setSelectedItem("DNI");
                break;
            case "3":
                CboTipoDoc.setSelectedItem("CARNET DE EXTRANJERIA");
                break;
            case "2":
                CboTipoDoc.setSelectedItem("RUC");
                break;
            default:
                break;
        }


    }//GEN-LAST:event_jtb_clientesMouseClicked

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = jtb_clientes.getSelectedRowCount();
        if (fila < 1) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar?", "Eliminar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == 0) {
                if (CC.eliminarCliente(jtb_clientes.getValueAt(jtb_clientes.getSelectedRow(), 0).toString()) > 0) {
                    listar();
                    limpiar();
                    bt_eliminar.setEnabled(false);
                    bt_actualizar.setEnabled(false);
                    bt_guardar.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void txt_nro_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nro_documentoActionPerformed

    }//GEN-LAST:event_txt_nro_documentoActionPerformed

    private void CboTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboTipoDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboTipoDocActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed

    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nro_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nro_contactoActionPerformed

    }//GEN-LAST:event_txt_nro_contactoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboTipoDoc;
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_clientes;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_nro_contacto;
    private javax.swing.JTextField txt_nro_documento;
    // End of variables declaration//GEN-END:variables
}
