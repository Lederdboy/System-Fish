package Clases;

import Conexion.Conectar;
import Entidades.Proveedores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Proveedor {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_PROVEEDORES = "INSERT INTO proveedor (prv_ruc,prv_nombre,prv_cel_contacto) values (?,?,?)";
    private final String SQL_SELECT_PROVEEDORES = "SELECT *FROM proveedor";
    
    public Cls_Proveedor(){
        PS = null;
        CN = new Conectar();
        
    }  


    private DefaultTableModel setTitulosProveedor(){
        DT = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DT.addColumn("RUC");
        DT.addColumn("Nombre");
        DT.addColumn("Cel. Contacto");
        return DT;
    }
    
    public DefaultTableModel getDatosProveedor(){
        try {
            setTitulosProveedor();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PROVEEDORES);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while(RS.next()){
                fila[0] = RS.getLong(2);
                fila[1] = RS.getString(3); 
                fila[2] = RS.getString(4);
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
    
    //-- -------------
    
    public int registrarProveedor(Long ruc,String nombre, Long cel_contacto){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_PROVEEDORES);
            PS.setLong(1, ruc);
            PS.setString(2, nombre);
            PS.setLong(3, cel_contacto);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el Proveedor.");
            System.err.println("Error al registrar el Proveedor." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int verificarProveedor(Long ruc){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT count(prv_ruc) from proveedor where prv_ruc='"+ruc+"'");
            RS = PS.executeQuery();
            
            while(RS.next()){
                res = RS.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    
    public int actualizarProveedor(Long ruc,String nombre, Long cel_contacto, Long doc_old){
        System.out.println("entro clase");
        String SQL = "UPDATE proveedor SET prv_ruc='" + ruc + "', prv_nombre='" + nombre + "', prv_cel_contacto='" + cel_contacto + "' WHERE prv_ruc='" + doc_old + "'";

        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor actualizado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el Proveedor.");
            System.err.println("Error al modificar los datos del Proveedor." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int eliminarProveedor(Long ruc) {
    String SQL = "DELETE FROM proveedor WHERE prv_ruc = ?";
    int res = 0;
    try {
        // Usa PreparedStatement para evitar inyecciones SQL y manejar el tipo de dato adecuadamente
        PS = CN.getConnection().prepareStatement(SQL);
        PS.setLong(1, ruc); // Configura el parámetro con el tipo adecuado
        res = PS.executeUpdate();
        if (res > 0) {
            JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No es posible eliminar el Proveedor.");
        System.err.println("Error al eliminar Proveedor: " + e.getMessage());
    } finally {
        // Asegúrate de cerrar los recursos y desconectar
        try {
            if (PS != null) {
                PS.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar PreparedStatement: " + ex.getMessage());
        }
        CN.desconectar();
    }
    return res;
}

       
}
