package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepo;

/**
 * FXML Controller class
 *
 * @author Sosa Luciano
 */
public class FrmRegistroController implements Initializable {

    @FXML
    private TextField txfUsuario;
    @FXML
    private PasswordField txfContrasenia;
    @FXML
    private PasswordField txfRepetirC;
    @FXML
    private Label lblRegistroError;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void txfUsuario_OnAction(ActionEvent event) {
    }

    @FXML
    private void txfContrasenia_OnAction(ActionEvent event) {
    }

    @FXML
    private void txfRepetirC_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnRegistrar_OnAction(ActionEvent event) {
        String estado = "Éxito";
        String usuario = txfUsuario.getText();
        String contra = txfContrasenia.getText();
        String repetir = txfRepetirC.getText();
        if (usuario.isEmpty() || contra.isEmpty() || repetir.isEmpty()) {
            setLblRegistroError(Color.RED, "Las casillas no pueden estar vacías.");
            estado = "Error";
        } else {
            if (contra.equals(repetir)) {
                UsuarioRepo repo = new UsuarioRepo();
                Usuario u = new Usuario();
                u.setUsuario(txfUsuario.getText());
                u.setContra(txfContrasenia.getText());

                repo.insertUsuario(u);

                Stage st = (Stage) btnRegistrar.getScene().getWindow();
                st.close();
            } else {
                setLblRegistroError(Color.RED, "La contraseña no coincide con la confirmación.");
                estado = "Error";
            }
        }
    }
    
    private void setLblRegistroError(Color color, String text) {
        lblRegistroError.setTextFill(color);
        lblRegistroError.setText(text);
        System.out.println(text);
    }

    @FXML
    private void btnCancelar_OnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}
