package Clases;

import Conexion.Conectar;
import Entidades.Clientes;
import Entidades.Proveedores;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Salida {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_SALIDA = "INSERT INTO salida (sal_factura, sal_pro_codigo, sal_fecha, sal_cantidad, sal_cli_nro_doc) values (?,?,?,?,?)";
    private final String SQL_INSERT_MOVIMIENTO = "INSERT INTO movimientos (mov_tipo, mov_factura, mov_pro_codigo) values (?,?,?)";
    private final String SQL_SELECT_SALIDA = "SELECT sal_factura, sal_fecha, sal_pro_codigo, pro_descripcion, sal_cantidad, cli_nombre FROM salida INNER JOIN producto ON sal_pro_codigo = pro_codigo INNER JOIN cliente ON sal_cli_nro_doc = cli_nro_doc";
    
    public Cls_Salida(){
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosSalida(){
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
        DT.addColumn("Cliente");
        return DT;
    }
    
    public DefaultTableModel getDatosSalida(){
        try {
            setTitulosSalida();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_SALIDA);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while(RS.next()){
                fila[0] = RS.getString(1);
                fila[1] = RS.getDate(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getString(6);
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
    
    public int registrarSalida(String nfactura, String codigo, Date fecha, int cantidad, int nro_doc){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_SALIDA);
            PS.setString(1, nfactura);
            PS.setString(2, codigo);
            PS.setDate(3, fecha);
            PS.setInt(4, cantidad);
            PS.setInt(5, nro_doc);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Salida realizada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la salida.");
            System.err.println("Error al registrar la salida." +e.getMessage());
        } finally{
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
    public int verificarStock(String codigo){
        int res=0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT inv_stock from inventario where inv_pro_codigo='"+codigo+"'");
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
    
    public ArrayList<Clientes> rellenarComboBox(){
               
        Statement stmt;
        ArrayList<Clientes> listarClientes = new ArrayList<>();
        
        try {
            stmt = CN.getConnection().createStatement();
            RS = stmt.executeQuery("SELECT * FROM cliente");
            while (RS.next()) 
            {
                Clientes clientes = new Clientes();
                clientes.setNro_documento(RS.getInt("cli_nro_doc"));
                clientes.setNombre(RS.getString("cli_nombre"));
                listarClientes.add(clientes);
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar el combo de proveedores." +e.getMessage());
        }
        return listarClientes;
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
