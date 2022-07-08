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
import javafx.scene.control.Alert;
import jdbc.ConnectionFactory;
import model.Categoría;
import model.Libro;
import model.Proveedor;

/**
 *
 * @author Sosa Luciano
 */
public class LibroRepo {
    
    private List<Libro> libros;
    private Libro libro;

    public LibroRepo() {
        libros = new ArrayList<>();
        cargarLibros();
    }
    
    private void cargarLibros() {
        libros.clear();
        
        String sqlCat = "SELECT * FROM categorias";
        String sqlProv = "SELECT * FROM proveedores";
        String sqlLib = "SELECT * FROM libros";

        try {
            //Objetos de conexión:
            Connection cn = ConnectionFactory.getConnection();

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sqlCat);

            //Crear un obj RS
            ResultSet rsCat = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rsCat.next()) {

                //Crear objeto Categoría:
                Categoría c = new Categoría();
                c.setNro(rsCat.getInt(1));
                c.setCategoria(rsCat.getString(2));
                
                ps = cn.prepareStatement(sqlProv);
                ResultSet rsProv = ps.executeQuery();
            
                while (rsProv.next()) {
                    
                    //Crear objeto Proveedor:
                    Proveedor p = new Proveedor();
                    p.setNro(rsProv.getInt(1));
                    p.setProveedor(rsProv.getString(2));
                    p.setDireccion(rsProv.getString(3));
                    
                    ps = cn.prepareStatement(sqlLib);
                    ResultSet rsLib = ps.executeQuery();
                    
                    while (rsLib.next()) {

                        //Crear una nueva instancia de libro y setear sus valores...
                        libro = new Libro();
                        libro.setNro(rsLib.getInt(1));
                        libro.setNombre(rsLib.getString(2)); 
                        libro.setAutor(rsLib.getString(3));
                        libro.setCategoria(c);
                        libro.setProveedor(p);
                        libro.setPaginas(rsLib.getString(4));
                        libro.setFecPublicacion(rsLib.getDate(5));
                        libro.setPrecio(rsLib.getString(6));
                        libro.setStock(rsLib.getString(7));
                        libro.setDisponib(rsLib.getBoolean(8));
                
                        libros.add(libro);
                    }
                }
            }
        } catch (SQLException e) {
            //Mostrar diálogo...
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html

            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }
    
    private void cargarLibros(String nombre) {
        libros.clear();
        
        String sqlCat = "SELECT * FROM categorias";
        String sqlProv = "SELECT * FROM proveedores";
        String sqlLib = "SELECT * FROM libros WHERE nomLibro LIKE '%" + nombre + "%';";

        try {
            //Objetos de conexión:
            Connection cn = ConnectionFactory.getConnection();

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sqlCat);

            //Crear un obj RS
            ResultSet rsCat = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rsCat.next()) {

                //Crear objeto Categoría:
                Categoría c = new Categoría();
                c.setNro(rsCat.getInt(1));
                c.setCategoria(rsCat.getString(2));
                
                ps = cn.prepareStatement(sqlProv);
                ResultSet rsProv = ps.executeQuery();
            
                while (rsProv.next()) {
                    
                    //Crear objeto Proveedor:
                    Proveedor p = new Proveedor();
                    p.setNro(rsProv.getInt(1));
                    p.setProveedor(rsProv.getString(2));
                    p.setDireccion(rsProv.getString(3));
                    
                    ps = cn.prepareStatement(sqlLib);
                    ResultSet rsLib = ps.executeQuery();
                    
                    while (rsLib.next()) {

                        //Crear una nueva instancia de libro y setear sus valores...
                        libro = new Libro();
                        libro.setNro(rsLib.getInt(1));
                        libro.setNombre(rsLib.getString(2)); 
                        libro.setAutor(rsLib.getString(3));
                        libro.setCategoria(c);
                        libro.setProveedor(p);
                        libro.setPaginas(rsLib.getString(4));
                        libro.setFecPublicacion(rsLib.getDate(5));
                        libro.setPrecio(rsLib.getString(6));
                        libro.setStock(rsLib.getString(7));
                        libro.setDisponib(rsLib.getBoolean(8));
                
                        libros.add(libro);
                    }
                }
            }
        } catch (SQLException e) {
            //Mostrar diálogo...
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
            //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html

            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void insertLibro(Libro libro) {
        try {
            Connection cn = ConnectionFactory.getConnection();

            String insert = "INSERT INTO libros (nomLibro, autor, fk_categoria, fk_proveedor, paginas, fecPublicacion, precio, stock, disponib) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setString(1, libro.getNombre());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria().getCategoria());
            ps.setString(4, libro.getProveedor().getProveedor());
            ps.setString(5, libro.getPaginas());
            ps.setString(6, libro.getFecPublicacion());
            ps.setString(7, libro.getPrecio());
            ps.setString(8, libro.getStock());
            ps.setString(9, libro.getDisponib());
            
            ps.execute();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro '" + libro.getNombre() + "' guardado.");
            alert.showAndWait();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void borrarLibro(int nro) {
        try {
            Connection cn = ConnectionFactory.getConnection();

            String del = "DELETE FROM libros WHERE id = ?";

            PreparedStatement ps = cn.prepareStatement(del);
            ps.setInt(1, nro);
            
            ps.execute();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro borrado.");
            alert.showAndWait();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
    
    public List<Libro> getLibros(String nombre) {
        cargarLibros(nombre);
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}