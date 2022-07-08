/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Categoría;
import model.Libro;
import model.Proveedor;
import repository.LibroRepo;

/**
 * FXML Controller class
 *
 * @author Luciano
 */
public class FrmNuevoLibroController implements Initializable {
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfAutor;
    @FXML
    private TextField txfPaginas;
    @FXML
    private TextField txfPrecio;
    @FXML
    private ComboBox<?> cmbCategoria;
    @FXML
    private ComboBox<?> cmbProveedor;
    @FXML
    private DatePicker dpFechaP;
    @FXML
    private TextField txfStock;
    @FXML
    private CheckBox cbxDisponible;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbCategoria.getSelectionModel().selectFirst();
        cmbProveedor.getSelectionModel().selectFirst();
    }

    @FXML
    private void btnCancelar_OnAction(ActionEvent event) {
    }

    @FXML
    private void btnAceptar_OnAction(ActionEvent event) {
        LibroRepo repo = new LibroRepo();
        Libro l = new Libro();
        l.setNombre(txfNombre.getText());
        l.setAutor(txfAutor.getText());
        l.setPaginas(txfPaginas.getText());
        l.setPrecio(txfPrecio.getText());
        l.setCategoria((Categoría) cmbCategoria.getSelectionModel().getSelectedItem());
        l.setProveedor((Proveedor) cmbProveedor.getSelectionModel().getSelectedItem());
        
        repo.insertLibro(l);
        
        Stage st = (Stage) btnAceptar.getScene().getWindow();
        st.close();
    }
}
