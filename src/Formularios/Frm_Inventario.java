package Formularios;

import Clases.Cls_Inventario;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Frm_Inventario extends javax.swing.JInternalFrame {

    private final Cls_Inventario CP;
    TableColumnModel columnModel;
    public static int enviar = 0;
    int num = 0;

    public Frm_Inventario() {
        initComponents();
        CP = new Cls_Inventario();
        columnModel = jtb_inventario.getColumnModel();
        listar();
    }

    private void listar() {
        jtb_inventario.setModel(CP.getDatosInventario());
        columnModel.getColumn(1).setPreferredWidth(400);
    }
    private int filaSeleccionadaOriginal = -1;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_inventario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_Buscar = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Productos");
        setPreferredSize(new java.awt.Dimension(1041, 545));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtb_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_inventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_inventario);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Inventario de Productos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setText("Entrada, Salida y Stock de Productos.");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa2.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addGap(656, 656, 656))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtb_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_inventarioMouseClicked

    }//GEN-LAST:event_jtb_inventarioMouseClicked

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // También llamamos al método de búsqueda al presionar Enter en el campo de texto
        String buscar = txt_Buscar.getText();
        // Guardamos la fila seleccionada originalmente antes de buscar
        filaSeleccionadaOriginal = jtb_inventario.getSelectedRow();
        buscar(buscar);
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String buscar = txt_Buscar.getText();
        // Guardamos la fila seleccionada originalmente antes de buscar
        filaSeleccionadaOriginal = jtb_inventario.getSelectedRow();
        buscar(buscar);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void ordenarPorDescripcion() {
        DefaultTableModel model = (DefaultTableModel) jtb_inventario.getModel();
        int n = model.getRowCount();
        Object[] rowData = new Object[model.getColumnCount()];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String descripcion1 = (String) model.getValueAt(j, 1);
                String descripcion2 = (String) model.getValueAt(j + 1, 1);
                if (descripcion1.compareTo(descripcion2) > 0) {
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        rowData[k] = model.getValueAt(j, k);
                        model.setValueAt(model.getValueAt(j + 1, k), j, k);
                        model.setValueAt(rowData[k], j + 1, k);
                    }
                }
            }
        }
    }

    private void buscarPorDescripcion(String descripcion) {
        DefaultTableModel model = (DefaultTableModel) jtb_inventario.getModel();
        int rowCount = model.getRowCount();
        boolean encontrado = false;

      
        for (int i = 0; i < rowCount; i++) {
            String descActual = (String) model.getValueAt(i, 1); 
            if (descActual.equalsIgnoreCase(descripcion)) { 
                jtb_inventario.setRowSelectionInterval(i, i); 
                encontrado = true;
                break; 
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún producto con la descripción: " + descripcion,
                    "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void buscar(String texto) {
        DefaultTableModel model = (DefaultTableModel) jtb_inventario.getModel();
        boolean encontrado = false;
        int filaSeleccionadaAnterior = jtb_inventario.getSelectedRow(); // Guardamos la fila seleccionada originalmente
        for (int i = 0; i < model.getRowCount(); i++) {
            String descripcion = (String) model.getValueAt(i, 1);
            String codigo = (String) model.getValueAt(i, 0);
            // Comparamos tanto la descripción como el código con el texto de búsqueda,
            // ignorando mayúsculas y minúsculas
            if (descripcion.equalsIgnoreCase(texto) || codigo.equalsIgnoreCase(texto)) {
                encontrado = true;
                // Movemos la fila encontrada al principio de la tabla
                model.moveRow(i, i, 0);
                jtb_inventario.setRowSelectionInterval(0, 0); // Seleccionamos la fila movida al principio
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con el código o la descripción: " + texto, "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
        // Restauramos la selección original si se realizó una búsqueda nuevamente
        if (filaSeleccionadaOriginal != -1) {
            jtb_inventario.setRowSelectionInterval(filaSeleccionadaAnterior, filaSeleccionadaAnterior);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_inventario;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
