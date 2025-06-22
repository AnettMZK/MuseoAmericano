
package com.mycompany.interfazmuseo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class WindowController implements Initializable {

    @FXML
    private AnchorPane mainMenu_ap;
    @FXML
    private Button maintenance_btn;
    @FXML
    private Button Tickets_btn;
    @FXML
    private Button Rating_btn;
    @FXML
    private Button report_btn;
    @FXML
    private AnchorPane mainContent_ap;

    /**
     * Initializes the controller class.
     */

      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       loadView("home");
        
    } 

    private void loadView(String nombreFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(nombreFXML + ".fxml"));
            mainContent_ap.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void maintenceOnAction(ActionEvent event) {
        loadView("Maintenance");
    }

    @FXML
    private void ticketsOnAction(ActionEvent event) {
        loadView("tickets");
    }

    @FXML
    private void ratingOnAction(ActionEvent event) {
        loadView("rating");
    }

    @FXML
    private void reportOnAction(ActionEvent event) {
        loadView("report");
    }

   
}
