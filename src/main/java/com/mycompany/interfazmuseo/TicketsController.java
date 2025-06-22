package com.mycompany.interfazmuseo;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TicketsController {

    // Comprar Entrada Tab
    @FXML private TextField visitorsName_tf;
    @FXML private ChoiceBox<String> cardType_cb;
    @FXML private ChoiceBox<String> museumType_cb;
    @FXML private DatePicker availability_dp;
    @FXML private TableView<Hall> shoppingTable_tv;
    @FXML private TableColumn<Hall, String> shoppingHallNameColumn;
    @FXML private TableColumn<Hall, Double> shoppingHallPriceColumn;
    @FXML private Button add_btn;
    @FXML private Button buy_btn;
    @FXML private Label ticketPrice_lb;
    @FXML private Label IVA_lb;
    @FXML private Label totalTicket_lb;
    @FXML private ChoiceBox<Hall> halls_cb; // Added dynamically

    // Validar Entrada Tab
    @FXML private ImageView exhibitionImage_iv;
    @FXML private TableView<Hall> availableRooms_tv;
    @FXML private TableColumn<Hall, String> availableHallNameColumn;
    @FXML private Label dateOfVisit_lb;
    @FXML private Label museumInfo_lb;
    @FXML private TextField qrCode_tf; // Added dynamically
    @FXML private Button validate_btn; // Added dynamically
    @FXML private AnchorPane validationContent_ap; // Added dynamically
    @FXML private Label status_lb; // Added dynamically

    private ObservableList<Hall> selectedHalls = FXCollections.observableArrayList();
    private ObservableList<Hall> availableHalls = FXCollections.observableArrayList();
    private ObservableList<String> cardTypes = FXCollections.observableArrayList("Visa", "MasterCard", "Amex");
    private ObservableList<String> museumTypes = FXCollections.observableArrayList("Historia", "Arte", "Musical", "Militar");
    private Tickets ticketsService = new Tickets();

    @FXML
    public void initialize() {
        // Inicializar compra
        cardType_cb.setItems(cardTypes);
        museumType_cb.setItems(museumTypes);
        shoppingHallNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        shoppingHallPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        shoppingTable_tv.setItems(selectedHalls);

        // agregar salas dinamicamente
        halls_cb = new ChoiceBox<>();
        halls_cb.setLayoutX(15.0);
        halls_cb.setLayoutY(200.0);
        halls_cb.setPrefWidth(156.0);
        shoppingTable_tv.getParent().getChildrenUnmodifiable().get(0).getChildrenUnmodifiable().add(halls_cb);

        // cargar salas depende del tipo de museo
        museumType_cb.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> loadHalls());

        // Iniciar la validacion de la entrada
        availableHallNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        availableRooms_tv.setItems(availableHalls);

        // agregar Qr
        qrCode_tf = new TextField();
        qrCode_tf.setLayoutX(315.0);
        qrCode_tf.setLayoutY(50.0);
        qrCode_tf.setPrefWidth(300.0);
        qrCode_tf.setPromptText("Ingrese el contenido del QR");

        validate_btn = new Button("Validar");
        validate_btn.setLayoutX(620.0);
        validate_btn.setLayoutY(50.0);
        validate_btn.setOnAction(this::validateTicket);

        status_lb = new Label("");
        status_lb.setLayoutX(315.0);
        status_lb.setLayoutY(90.0);
        status_lb.setStyle("-fx-text-fill: red;");

        validationContent_ap = (AnchorPane) availableRooms_tv.getParent();
        validationContent_ap.getChildren().addAll(qrCode_tf, validate_btn, status_lb);

        
        exhibitionImage_iv.setVisible(false);
        availableRooms_tv.setVisible(false);
        dateOfVisit_lb.setVisible(false);
        museumInfo_lb.setVisible(false);

        loadHalls();
    }

    private void loadHalls() {
        try {
            String museumType = museumType_cb.getValue();
            List<Hall> halls = ticketsService.loadHalls(museumType);
            halls_cb.setItems(FXCollections.observableArrayList(halls));
        } catch (Exception e) {
            status_lb.setText("Error al cargar salas: " + e.getMessage());
        }
    }

    @FXML
    private void addToCard(ActionEvent event) {
        Hall selectedHall = halls_cb.getSelectionModel().getSelectedItem();
        if (selectedHall != null && !selectedHalls.contains(selectedHall)) {
            selectedHalls.add(selectedHall);
            updatePriceLabels();
            status_lb.setText("Sala agregada: " + selectedHall.getName());
        } else {
            status_lb.setText("Seleccione una sala válida o no repetida.");
        }
    }

    private void updatePriceLabels() {
        double[] prices = ticketsService.calculatePrices(selectedHalls);
        ticketPrice_lb.setText(String.format("%.2f", prices[0]));
        IVA_lb.setText(String.format("%.2f", prices[1]));
        totalTicket_lb.setText(String.format("%.2f", prices[2]));
    }

    @FXML
    private void buyTickets(ActionEvent event) {
        String visitorName = visitorsName_tf.getText();
        String cardType = cardType_cb.getValue();
        String museumType = museumType_cb.getValue();
        LocalDate visitDate = availability_dp.getValue();
        double totalPrice = Double.parseDouble(totalTicket_lb.getText().isEmpty() ? "0" : totalTicket_lb.getText());

        if (visitorName.isEmpty() || cardType == null || museumType == null || visitDate == null || selectedHalls.isEmpty() || totalPrice == 0) {
            status_lb.setText("Complete todos los campos y seleccione al menos una sala.");
            return;
        }

        try {
            Ticket ticket = new Ticket(visitorName, "", visitDate, selectedHalls, totalPrice, museumType);
            int ticketId = ticketsService.saveTicket(ticket);
            if (ticketId > 0) {
                ticketsService.registerCommission(ticketId, totalPrice);
                ticketsService.generateQRCode(ticketId, ticket);
                status_lb.setText("Entrada comprada. QR generado en ticket_" + ticketId + ".jpg");
                clearFields();
            } else {
                status_lb.setText("Error al comprar entrada.");
            }
        } catch (Exception e) {
            status_lb.setText("Error al comprar entrada: " + e.getMessage());
        }
    }

    @FXML
    private void validateTicket(ActionEvent event) {
        String qrContent = qrCode_tf.getText();
        if (qrContent.isEmpty()) {
            status_lb.setText("Ingrese el contenido del código QR.");
            return;
        }

        try {
            int ticketId = ticketsService.parseTicketId(qrContent);
            if (ticketId == -1) {
                status_lb.setText("Código QR inválido.");
                return;
            }

            Ticket ticket = ticketsService.validateTicket(ticketId);
            if (ticket != null) {
                dateOfVisit_lb.setText(ticket.getVisitDate().toString());
                museumInfo_lb.setText(ticket.getMuseumType());
                availableHalls.setAll(ticket.getHalls());
                exhibitionImage_iv.setVisible(true);
                availableRooms_tv.setVisible(true);
                dateOfVisit_lb.setVisible(true);
                museumInfo_lb.setVisible(true);
                status_lb.setText("Entrada válida para hoy.");
            } else {
                status_lb.setText("Entrada inválida o no válida para hoy.");
                exhibitionImage_iv.setVisible(false);
                availableRooms_tv.setVisible(false);
                dateOfVisit_lb.setVisible(false);
                museumInfo_lb.setVisible(false);
            }
        } catch (Exception e) {
            status_lb.setText("Error al validar entrada: " + e.getMessage());
        }
    }

    private void clearFields() {
        visitorsName_tf.clear();
        cardType_cb.getSelectionModel().clearSelection();
        museumType_cb.getSelectionModel().clearSelection();
        availability_dp.setValue(null);
        selectedHalls.clear();
        ticketPrice_lb.setText("0");
        IVA_lb.setText("0");
        totalTicket_lb.setText("0");
        halls_cb.getSelectionModel().clearSelection();
        status_lb.setText("");
    }
}