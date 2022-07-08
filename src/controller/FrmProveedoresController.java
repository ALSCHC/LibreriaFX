/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Proveedor;
import repository.ProveedorRepo;

/**
 * FXML Controller class
 *
 * @author Sosa Luciano
 */
public class FrmProveedoresController implements Initializable {
    
    @FXML
    private TableView<Proveedor> tbvProveedores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarProveedores();
    }
    
    void cargarProveedores() {
        ProveedorRepo repo = new ProveedorRepo();
        for (Proveedor p : repo.getProveedores()) {
            tbvProveedores.getItems().add(p);
        }
    }
}
