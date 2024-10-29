package Clases;

import Conexion.Conectar;
import Entidades.Proveedores;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Entrada {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_ENTRADA = "INSERT INTO entrada (ent_factura, ent_pro_codigo, ent_fecha, ent_cantidad , ent_prv_ruc) values (?,?,?,?,?)";
    private final String SQL_INSERT_MOVIMIENTO = "INSERT INTO movimiento (mov_tipo, mov_factura, mov_pro_codigo) values (?,?,?)";
    private final String SQL_SELECT_ENTRADA = "SELECT ent_factura, ent_fecha, ent_pro_codigo, pro_descripcion, ent_cantidad, prv_nombre FROM entrada INNER JOIN producto ON ent_pro_codigo = pro_codigo INNER JOIN proveedor ON ent_prv_ruc = prv_ruc";
    
    public Cls_Entrada(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosEntrada(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("N° de Factura");
        DT.addColumn("Fecha");
        DT.addColumn("Código de Producto");
        DT.addColumn("Descripción");
        DT.addColumn("Cantidad");
        DT.addColumn("Proveedor");
        return DT;
    }
    
    public DefaultTableModel getDatosEntradas(){
        try {
            setTitulosEntrada();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_ENTRADA);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getLong(6);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos."+e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
    public int registrarEntrada(String nfactura, String codigo, Date fecha, int cantidad, long proveedor){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_ENTRADA);
            PS.setString(1, nfactura);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setLong(5, proveedor);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Entrada realizada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la entrada.");
            System.err.println("Error al registrar la entrada." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public ArrayList<Proveedores> rellenarComboBox() {
    ArrayList<Proveedores> listarProveedores = new ArrayList<>();
    Statement stmt = null;
    ResultSet RS = null;

    try {
        stmt = CN.getConnection().createStatement();
        RS = stmt.executeQuery("SELECT * FROM proveedor");

        while (RS.next()) {
            Proveedores proveedores = new Proveedores();
            // Verificar si 'prv_ruc' es BIGINT y obtener el valor correctamente
            long ruc = RS.getLong("prv_ruc");
            proveedores.setRuc(ruc);
            proveedores.setNombre(RS.getString("prv_nombre"));
            listarProveedores.add(proveedores);
        }
    } catch (SQLException e) {
        System.err.println("No se pudo cargar el combo de proveedores: " + e.getMessage());
    } finally {
        // Cerrar ResultSet y Statement en el bloque finally
        if (RS != null) {
            try {
                RS.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el Statement: " + e.getMessage());
            }
        }
        // Asegúrate de que CN.desconectar() se llame solo si es necesario
        CN.desconectar();
    }

    return listarProveedores;
}

    
    public int registrarMovimiento(String tipo, String mov_factura, String mov_pro_codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_MOVIMIENTO);
            PS.setString(1, tipo);
            PS.setString(2, mov_factura);
            PS.setString(3, mov_pro_codigo);
            res = PS.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el movimiento.");
            System.err.println("Error al registrar el movimiento." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
}
