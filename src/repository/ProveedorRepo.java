/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionFactory;
import model.Proveedor;

/**
 *
 * @author Sosa Luciano
 */
public class ProveedorRepo {
    
    private List<Proveedor> proveedores;
    private Proveedor proveedor;
    
    public ProveedorRepo() {
        proveedores = new ArrayList<>();
        cargarProveedores();
    }
    
    private void cargarProveedores() {

        try {
            //Objetos de conexión:
            Connection cn = ConnectionFactory.getConnection();

            //Variable que almacena la sentencia...
            String sql = "SELECT * FROM proveedores";

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Crear un obj RS
            ResultSet rs = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rs.next()) {

                //Crear una nueva instancia de proveedor...
                //Y setear sus valores...
                proveedor = new Proveedor();
                proveedor.setNro(rs.getInt(1));
                proveedor.setProveedor(rs.getString(2));
                proveedor.setDireccion(rs.getString(3));
                
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
