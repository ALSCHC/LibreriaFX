package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jdbc.ConnectionFactory;
import util.VentanasUtil;

/**
 *
 * @author Sosa Luciano
 */
public class FrmInicioController implements Initializable {
    
    //Definir offsets...
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnMinimizar;
    @FXML
    private TextField txfUsuario;
    @FXML
    private TextField txfContrasenia;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Hyperlink hypRegistrarse;
    @FXML
    private Label lblLogInError;
    @FXML
    private Label lblServerInfo;
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    @FXML
    public void btnIngresar_OnAction(ActionEvent event) {

        if (event.getSource() == btnIngresar) {
            //LogIn
            if (logIn().equals("Éxito")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/frmCargar.fxml")));
                    stage.setScene(scene);
                    stage.show();
                    stage.getIcons().add(new Image("/resource/icon.png"));
                    //Centrar ventana de carga manualmente:
                    javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                    
                    scene.setOnMousePressed((MouseEvent e) -> {
                        xOffset = e.getSceneX();
                        yOffset = e.getSceneY();
                    });

                    scene.setOnMouseDragged((MouseEvent e) -> {
                        stage.setX(e.getScreenX() - xOffset);
                        stage.setY(e.getScreenY() - yOffset);
                    });
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    
    @FXML
    private void btnCancelar_OnAction(ActionEvent event) {
        System.out.println("Cerrando programa...");
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblServerInfo.setTextFill(Color.RED);
            lblServerInfo.setText("Desconectado");
        } else {
            lblServerInfo.setTextFill(Color.LIMEGREEN);
            lblServerInfo.setText("Conectado");
        }
    }
    
    public FrmInicioController() {
        con = ConnectionFactory.getConnection();
    }
    
    //Uso de String para verificar el estado
    private String logIn() {
        String estado = "Éxito";
        String usuario = txfUsuario.getText();
        String contra = txfContrasenia.getText();
        if(usuario.isEmpty() || contra.isEmpty()) {
            setLblLogInError(Color.RED, "Las casillas no pueden estar vacías.");
            estado = "Error";
        } else {
            //Consulta
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contra = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, contra);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblLogInError(Color.RED, "El usuario o contraseña es incorrecto.");
                    estado = "Error";
                } else {
                    setLblLogInError(Color.GREEN, "Cuenta ingresada correctamente. Redireccionando...");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                estado = "Excepción";
            }
        }
        return estado;
    }
    
    private void setLblLogInError(Color color, String text) {
        lblLogInError.setTextFill(color);
        lblLogInError.setText(text);
        System.out.println(text);
    }
    
    @FXML
    private void hypRegistrarse_OnAction(ActionEvent event) {
        VentanasUtil.abrirDialogo(hypRegistrarse.getScene().getWindow(), "/view/frmRegistro.fxml", "Crear cuenta");
    }
    
    @FXML
    private void btnMinimizar_OnAction(ActionEvent event) {
        Stage stage = (Stage)(btnMinimizar.getScene().getWindow());
        stage.setIconified(true);
    }
    
    @FXML
    private void btnCerrar_OnAction(ActionEvent event) {
        System.out.println("Cerrando programa...");
        System.exit(0);
//      Stage stage;
//      stage = (Stage) ((ImageView)event.getSource()).getScene().getWindow();
//      stage.close();
    }
}