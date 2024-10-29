package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List; 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Cliente {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_CLIENTES = "INSERT INTO cliente (cli_nro_doc,cli_tipo_doc,cli_nombre,cli_cel_contacto) values (?,?,?,?)";
    private final String SQL_SELECT_CLIENTES = "SELECT *FROM cliente";
    private final ClienteDoublyLinkedList clienteList;
    private final String SQL_SELECT_NOMBRE_CLIENTE = "SELECT COUNT(*) FROM cliente WHERE cli_nombre = ?";
        private final String SQL_SELECT_DOCUMENTO_CLIENTE = "SELECT COUNT(*) FROM cliente WHERE cli_nro_doc = ?";


    public Cls_Cliente() {
        PS = null;
        CN = new Conectar();
        clienteList = new ClienteDoublyLinkedList();
        cargarClientesDesdeDB();
    }

    private DefaultTableModel setTitulosClientes() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DT.addColumn("Documento");
        DT.addColumn("Tipo de documento");
        DT.addColumn("Cliente");
        DT.addColumn("Celular");
        return DT;
    }

    public DefaultTableModel getDatosClientes() {
        try {
            setTitulosClientes();
            List<Object[]> clientes = clienteList.getAllClientes();
            for (Object[] cliente : clientes) {
                DT.addRow(cliente);
            }
        } catch (Exception e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        }
        return DT;
    }

    public int registrarClientes(Integer cli_nro_doc, Integer cli_tipo_doc, String cli_nombre, Integer cli_cel_contacto) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_CLIENTES);
            PS.setInt(1, cli_nro_doc);
            PS.setInt(2, cli_tipo_doc);
            PS.setString(3, cli_nombre);
            PS.setInt(4, cli_cel_contacto);
            res = PS.executeUpdate();
            if (res > 0) {
                clienteList.addCliente(cli_nro_doc, cli_tipo_doc, cli_nombre, cli_cel_contacto);
                JOptionPane.showMessageDialog(null, "Cliente registrado con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente.");
            System.err.println("Error al registrar el cliente." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int verificarCliente(Integer cli_nro_doc) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement("SELECT count(cli_nro_doc) from cliente where cli_nro_doc='" + cli_nro_doc + "'");
            RS = PS.executeQuery();
            while (RS.next()) {
                res = RS.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver cantidad de registros." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarCliente(Integer cli_nro_doc, Integer cli_tipo_doc, String cli_nombre, Integer cli_cel_contacto, String cli_nro_doc_old) {
        String SQL = "UPDATE cliente SET cli_nro_doc='" + cli_nro_doc + "', cli_tipo_doc='" + cli_tipo_doc + "', cli_nombre='" + cli_nombre + "', cli_cel_contacto='" + cli_cel_contacto + "' WHERE cli_nro_doc='" + cli_nro_doc_old + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                clienteList.updateCliente(Integer.parseInt(cli_nro_doc_old), cli_nro_doc, cli_tipo_doc, cli_nombre, cli_cel_contacto);
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente.");
            System.err.println("Error al modificar los datos del cliente." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarCliente(String cli_nro_doc) {
        String SQL = "DELETE from cliente WHERE cli_nro_doc ='" + cli_nro_doc + "'";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if (res > 0) {
                clienteList.removeCliente(Integer.parseInt(cli_nro_doc));
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible eliminar el cliente.");
            System.err.println("Error al eliminar cliente." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    private void cargarClientesDesdeDB() {
        try {
            PS = CN.getConnection().prepareStatement(SQL_SELECT_CLIENTES);
            RS = PS.executeQuery();
            while (RS.next()) {
                int nroDocumento = RS.getInt("cli_nro_doc");
                int tipoDocumento = RS.getInt("cli_tipo_doc");
                String nombre = RS.getString("cli_nombre");
                int nroContacto = RS.getInt("cli_cel_contacto");
                clienteList.addCliente(nroDocumento, tipoDocumento, nombre, nroContacto);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar los clientes desde la base de datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
    }
    public boolean verificarNombreCliente(String cli_nombre) {
        boolean existe = false;
        try {
            PS = CN.getConnection().prepareStatement(SQL_SELECT_NOMBRE_CLIENTE);
            PS.setString(1, cli_nombre);
            RS = PS.executeQuery();
            if (RS.next() && RS.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el nombre del cliente." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return existe;
    }
    public boolean verificarDocumentoCliente(Integer cli_nro_doc) {
        boolean existe = false;
        try {
            PS = CN.getConnection().prepareStatement(SQL_SELECT_DOCUMENTO_CLIENTE);
            PS.setInt(1, cli_nro_doc);
            RS = PS.executeQuery();
            if (RS.next() && RS.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el documento del cliente." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return existe;
    }
}

