/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luciano
 */
public class FrmCargarController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new pantallaCarga().start();
    }
    
    class pantallaCarga extends Thread {
        
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                
                Platform.runLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/view/frmLibrería.fxml"));
                        } catch (IOException e) {
                            Logger.getLogger(FrmCargarController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("JavaFX - Librería");
                        stage.getIcons().add(new Image("/resource/icon.png"));
                        stage.show();
                        
                        rootPane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e) {
                Logger.getLogger(FrmCargarController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}