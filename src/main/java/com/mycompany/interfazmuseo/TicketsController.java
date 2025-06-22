/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.interfazmuseo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class TicketsController implements Initializable {

    @FXML
    private TextField visitorsName_tf;
    @FXML
    private ChoiceBox<?> cardType_cb;
    @FXML
    private ChoiceBox<?> museumType_cb;
    @FXML
    private DatePicker availability_dp;
    @FXML
    private TableView<?> shoppingTable_tv;
    @FXML
    private Button buy_btn;
    @FXML
    private Label ticketPrice_lb;
    @FXML
    private Label IVA_lb;
    @FXML
    private Label totalTicket_lb;
    @FXML
    private Button add_btn;
    @FXML
    private ImageView exhibitionImage_iv;
    @FXML
    private TableView<?> availableRooms_tv;
    @FXML
    private Label dateOfVisit_lb;
    @FXML
    private Label museumInfo_lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buyTickets(ActionEvent event) {
    }

    @FXML
    private void addToCard(ActionEvent event) {
    }
    
}
