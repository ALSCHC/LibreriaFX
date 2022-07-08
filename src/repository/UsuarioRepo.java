package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import jdbc.ConnectionFactory;
import model.Usuario;

/**
 *
 * @author Sosa Luciano
 */
public class UsuarioRepo {
    
    private List<Usuario> usuarios;
    private Usuario usuario;
    
    public UsuarioRepo() {
        usuarios = new ArrayList<>();
        cargarUsuarios();
    }
    
    private void cargarUsuarios() {

        try {
            //Objetos de conexión:
            Connection cn = ConnectionFactory.getConnection();

            //Variable que almacena la sentencia...
            String sql = "SELECT * FROM usuarios";

            //Crear un obj PS
            PreparedStatement ps = cn.prepareStatement(sql);

            //Crear un obj RS
            ResultSet rs = ps.executeQuery();

            //Recorrer el RS y crear los objetos...
            while (rs.next()) {

                //Crear una nueva instancia del usuario...
                //Y setear sus valores...
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setContra(rs.getString(3));
                
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void insertUsuario(Usuario usuario) {
        try {
            Connection cn = new ConnectionFactory().getConnection();

            String insert = "INSERT INTO usuarios (usuario, contra) VALUES (?, ?)";

            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContra());
            
            ps.execute();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuario registrado.");
            alert.showAndWait();
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause() + "||" + e.getMessage());
            alert.showAndWait();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
