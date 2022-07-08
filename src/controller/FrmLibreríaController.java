package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Libro;
import repository.LibroRepo;
import util.VentanasUtil;

/**
 * FXML Controller class
 *
 * @author Sosa Luciano
 */
public class FrmLibreríaController implements Initializable {

    @FXML
    private TableView<Libro> tbvLibros;
    @FXML
    private Button btnProveedores;
    @FXML
    private MenuItem mniNuevo;
    @FXML
    private MenuItem mniCerrar;
    @FXML
    private MenuItem mniBorrar;
    @FXML
    private MenuItem mniVerProveedores;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnCategorias;
    @FXML
    private Button btnVentas;
    @FXML
    private TextField txfBuscar;
    @FXML
    private Label lblInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarLibros();
    }

    void cargarLibros() {
        LibroRepo repo = new LibroRepo();
        for (Libro l : repo.getLibros()) {
            tbvLibros.getItems().add(l);
        }
    }

    @FXML
    private void btnNuevo_OnAction(ActionEvent event) {
        VentanasUtil.abrirDialogo(btnNuevo.getScene().getWindow(), "/view/frmNuevoLibro.fxml", "Nuevo libro");

        btnNuevo.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Añadir nuevo libro.");
        });

        btnNuevo.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }

    @FXML
    private void btnModificar_OnAction(ActionEvent event) {
        btnModificar.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Modificar libro seleccionado.");
        });

        btnModificar.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });

    }

    @FXML
    private void btnBorrar_OnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de borrar el libro?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                LibroRepo repo = new LibroRepo();
                repo.borrarLibro(tbvLibros.getSelectionModel().getSelectedItem().getNro());
                tbvLibros.getItems().clear();
                cargarLibros();
            }
        });

        btnBorrar.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Borrar libro seleccionado.");
        });

        btnBorrar.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }

    @FXML
    private void btnProveedores_OnAction(ActionEvent event) {
        VentanasUtil.abrirDialogo(btnProveedores.getScene().getWindow(), "/view/frmProveedores.fxml", "Lista de proveedores");

        btnProveedores.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Ver proveedores.");
        });

        btnProveedores.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }

    @FXML
    private void btnClientes_OnAction(ActionEvent event) {

        btnClientes.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Ver clientes.");
        });

        btnClientes.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }

    @FXML
    private void btnCategorias_OnAction(ActionEvent event) {

        btnCategorias.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Ver categorías.");
        });

        btnCategorias.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }

    @FXML
    private void btnVentas_OnAction(ActionEvent event) {

        btnVentas.setOnMouseEntered((MouseEvent e) -> {
            lblInfo.setText("Ver ventas registradas.");
        });

        btnVentas.setOnMouseExited((MouseEvent e) -> {
            lblInfo.setText("");
        });
    }
    
    @FXML
    private void txfBuscar_OnKeyReleased(KeyEvent event) {
        LibroRepo repo = new LibroRepo();
        tbvLibros.getItems().clear();
        tbvLibros.getItems().addAll(repo.getLibros(txfBuscar.getText()));
    }

    @FXML
    private void mniNuevo_OnAction(ActionEvent event) {
        btnNuevo.fire();
    }

    @FXML
    private void mniCerrar_OnAction(ActionEvent event) {
        System.out.println("Cerrando programa...");
        System.exit(0);
    }

    @FXML
    private void mniBorrar_OnAction(ActionEvent event) {
        btnBorrar.fire();
    }

    @FXML
    private void mniVerProveedores_OnAction(ActionEvent event) {
        btnProveedores.fire();
    }

    @FXML
    private void ctxNuevo_OnAction(ActionEvent event) {
        btnNuevo.fire();
    }

    @FXML
    private void ctxModificar_OnAction(ActionEvent event) {
        btnModificar.fire();
    }

    @FXML
    private void ctxBorrar_OnAction(ActionEvent event) {
        btnBorrar.fire();
    }
}
