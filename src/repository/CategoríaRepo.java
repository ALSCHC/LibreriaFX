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
import model.Categoría;

/**
 *
 * @author Sosa Luciano
 */
public class CategoríaRepo {
    
    private List<Categoría> categorias;
    private Categoría categoria;
    
    public CategoríaRepo() {
        categorias = new ArrayList<>();
        cargarCategorias();
    }
    
    private void cargarCategorias() {

        try {
            //Objetos de conexión:
            Connection cn = ConnectionFactory.getConnection();

            //Variable que almacena la sentencia...
            String sql = "SELECT * FROM categorias";

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Crear un obj RS
            ResultSet rs = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rs.next()) {

                //Crear una nueva instancia de categoría...
                //Y setear sus valores...
                categoria = new Categoría();
                categoria.setNro(rs.getInt(1));
                categoria.setCategoria(rs.getString(2));
                
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<Categoría> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoría> categorias) {
        this.categorias = categorias;
    }

    public Categoría getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoría categoria) {
        this.categoria = categoria;
    }
}
