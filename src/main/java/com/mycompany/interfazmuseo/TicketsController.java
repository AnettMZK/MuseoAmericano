package com.mycompany.interfazmuseo;

import controllers.MuComisionJpaController;
import controllers.MuEntradasJpaController;
import controllers.MuMuseosJpaController;
import controllers.MuPreciosJpaController;
import controllers.MuSalasJpaController;
import controllers.MuTransaccionJpaController;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import persistence.MuEntradas;
import persistence.MuMuseos;
import persistence.MuPrecios;
import persistence.MuSalas;
import persistence.MuTransaccion;
import persistence.MuComision;

public class TicketsController implements Initializable {

    @FXML
    private TextField visitorsName_tf;
    @FXML
    private ChoiceBox<String> cardType_cb;
    @FXML
    private ChoiceBox<MuMuseos> museumType_cb;
    @FXML
    private ChoiceBox<MuPrecios> priceType_cb;
    @FXML
    private DatePicker availability_dp;
    @FXML
    private TableView<TicketPurchase> shoppingTable_tv;
    @FXML
    private Button buy_btn;
    @FXML
    private Button add_btn;
    @FXML
    private Label ticketPrice_lb;
    @FXML
    private Label IVA_lb;
    @FXML
    private Label totalTicket_lb;
    @FXML
    private ImageView exhibitionImage_iv;
    @FXML
    private TableView<MuSalas> availableRooms_tv;
    @FXML
    private Label dateOfVisit_lb;
    @FXML
    private Label museumInfo_lb;
    @FXML
    private Label visitorName_lb;
    @FXML
    private TextField qrCode_tf;
    @FXML
    private Button validate_btn;

    private MuMuseosJpaController museumJpa = new MuMuseosJpaController();
    private MuPreciosJpaController priceJpa = new MuPreciosJpaController();
    private MuEntradasJpaController ticketJpa = new MuEntradasJpaController();
    private MuTransaccionJpaController transactionJpa = new MuTransaccionJpaController();
    private MuComisionJpaController commissionJpa = new MuComisionJpaController();
    private MuSalasJpaController salasJpa = new MuSalasJpaController();

    private ObservableList<TicketPurchase> cartItems = FXCollections.observableArrayList();
    private final double IVA_RATE = 0.05; // 5% commission as per mu_comision data

    // Inner class to represent items in the shopping cart table
    public static class TicketPurchase {
        private final SimpleStringProperty museumName;
        private final SimpleStringProperty visitorName;
        private final SimpleStringProperty date;
        private final SimpleStringProperty ticketType;
        private final SimpleStringProperty amount;
        private final SimpleStringProperty qrCode;
        private MuEntradas ticket;
        private MuTransaccion transaction;
        private MuComision commission;

        public TicketPurchase(String museumName, String visitorName, String date, String ticketType, String amount, String qrCode) {
            this.museumName = new SimpleStringProperty(museumName);
            this.visitorName = new SimpleStringProperty(visitorName);
            this.date = new SimpleStringProperty(date);
            this.ticketType = new SimpleStringProperty(ticketType);
            this.amount = new SimpleStringProperty(amount);
            this.qrCode = new SimpleStringProperty(qrCode);
        }

        // Getters for table properties
        public String getMuseumName() { return museumName.get(); }
        public String getVisitorName() { return visitorName.get(); }
        public String getDate() { return date.get(); }
        public String getTicketType() { return ticketType.get(); }
        public String getAmount() { return amount.get(); }
        public String getQrCode() { return qrCode.get(); }

        // Setters for database entities
        public void setTicket(MuEntradas ticket) { this.ticket = ticket; }
        public void setTransaction(MuTransaccion transaction) { this.transaction = transaction; }
        public void setCommission(MuComision commission) { this.commission = commission; }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize card types
        cardType_cb.getItems().addAll("Visa", "Mastercard", "American Express", "Dinner Club", "Union Pay");

        // Initialize museum types
        ObservableList<MuMuseos> museums = FXCollections.observableArrayList(museumJpa.findMuseoEntities());
        museumType_cb.setItems(museums);
        museumType_cb.setConverter(new javafx.util.StringConverter<MuMuseos>() {
            @Override
            public String toString(MuMuseos museum) {
                return museum != null ? museum.getNombre() : "";
            }
            @Override
            public MuMuseos fromString(String string) {
                return museums.stream().filter(m -> m.getNombre().equals(string)).findFirst().orElse(null);
            }
        });

        // Initialize price types
        ObservableList<MuPrecios> prices = FXCollections.observableArrayList(priceJpa.findPreciosEntities());
        priceType_cb.setItems(prices);
        priceType_cb.setConverter(new javafx.util.StringConverter<MuPrecios>() {
            @Override
            public String toString(MuPrecios price) {
                return price != null ? price.getNombrePrecio() + " ($" + price.getMonto() + ")" : "";
            }
            @Override
            public MuPrecios fromString(String string) {
                return prices.stream().filter(p -> (p.getNombrePrecio() + " ($" + p.getMonto() + ")").equals(string)).findFirst().orElse(null);
            }
        });

        // Initialize shopping table columns
        shoppingTable_tv.getColumns().clear();
        TableColumn<TicketPurchase, String> museumCol = new TableColumn<>("Museo");
        museumCol.setCellValueFactory(new PropertyValueFactory<>("museumName"));
        TableColumn<TicketPurchase, String> visitorCol = new TableColumn<>("Nombre Visitante");
        visitorCol.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        TableColumn<TicketPurchase, String> dateCol = new TableColumn<>("Fecha");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<TicketPurchase, String> ticketTypeCol = new TableColumn<>("Tipo de Entrada");
        ticketTypeCol.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        TableColumn<TicketPurchase, String> amountCol = new TableColumn<>("Monto");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<TicketPurchase, String> qrCodeCol = new TableColumn<>("Código QR");
        qrCodeCol.setCellValueFactory(new PropertyValueFactory<>("qrCode"));
        shoppingTable_tv.getColumns().addAll(museumCol, visitorCol, dateCol, ticketTypeCol, amountCol, qrCodeCol);
        shoppingTable_tv.setItems(cartItems);

        // Initialize available rooms table
        availableRooms_tv.getColumns().clear();
        TableColumn<MuSalas, String> roomNameCol = new TableColumn<>("Nombre Sala");
        roomNameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<MuSalas, String> roomDescCol = new TableColumn<>("Descripción");
        roomDescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        availableRooms_tv.getColumns().addAll(roomNameCol, roomDescCol);

        // Set default values for labels
        ticketPrice_lb.setText("0");
        IVA_lb.setText("0");
        totalTicket_lb.setText("0");
        museumInfo_lb.setText("Museo: N/A");
        dateOfVisit_lb.setText("Fecha: N/A");
        visitorName_lb.setText("Visitante: N/A");
    }

    @FXML
    private void addToCard(ActionEvent event) {
        String visitorName = visitorsName_tf.getText();
        MuMuseos selectedMuseum = museumType_cb.getValue();
        MuPrecios selectedPrice = priceType_cb.getValue();
        LocalDate selectedDate = availability_dp.getValue();
        String cardType = cardType_cb.getValue();

        if (visitorName.isEmpty() || selectedMuseum == null || selectedPrice == null || selectedDate == null || cardType == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
            return;
        }

        // Calculate prices
        int basePrice = selectedPrice.getMonto();
        double iva = basePrice * IVA_RATE;
        int totalPrice = (int) (basePrice + iva);

        // Generate QR code (simple UUID for now)
        String qrCode = "QR-" + UUID.randomUUID().toString().substring(0, 8);

        // Create ticket purchase object
        TicketPurchase purchase = new TicketPurchase(
            selectedMuseum.getNombre(),
            visitorName,
            selectedDate.toString(),
            selectedPrice.getNombrePrecio(),
            String.valueOf(totalPrice),
            qrCode
        );

        // Update price labels
        ticketPrice_lb.setText(String.valueOf(basePrice));
        IVA_lb.setText(String.format("%.2f", iva));
        totalTicket_lb.setText(String.valueOf(totalPrice));

        // Add to cart
        cartItems.add(purchase);

        // Clear input fields
        visitorsName_tf.clear();
        museumType_cb.setValue(null);
        priceType_cb.setValue(null);
        availability_dp.setValue(null);
        cardType_cb.setValue(null);
    }

    @FXML
    private void buyTickets(ActionEvent event) {
        if (cartItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("No hay entradas en el carrito para comprar.");
            alert.showAndWait();
            return;
        }

        try {
            for (TicketPurchase purchase : cartItems) {
                // Create MuEntradas
                MuEntradas ticket = new MuEntradas();
                ticket.setIdMuseo(museumJpa.findMuseoEntities().stream()
                    .filter(m -> m.getNombre().equals(purchase.getMuseumName()))
                    .findFirst().orElse(null));
                ticket.setNombreCliente(purchase.getVisitorName());
                ticket.setFecha(java.sql.Date.valueOf(LocalDate.parse(purchase.getDate())));
                ticket.setCodigoQr(purchase.getQrCode());
                ticket.setPrecioTotal(Integer.parseInt(purchase.getAmount()));
                ticketJpa.create(ticket);
                purchase.setTicket(ticket);

                // Create MuTransaccion
                MuTransaccion transaction = new MuTransaccion();
                transaction.setIdEntrada(ticket);
                transaction.setTipoTarjeta(cardType_cb.getValue() != null ? cardType_cb.getValue() : "Visa");
                transaction.setFecha(java.sql.Date.valueOf(LocalDate.now()));
                transactionJpa.create(transaction);
                purchase.setTransaction(transaction);

                // Create MuComision
                MuComision commission = new MuComision();
                commission.setIdTransaccion(transaction);
                commission.setComision("5%");
                commission.setMontoFinal(Integer.parseInt(purchase.getAmount()));
                commissionJpa.create(commission);
                purchase.setCommission(commission);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Compra realizada con éxito. Las entradas han sido guardadas.");
            alert.showAndWait();

            // Clear cart and labels
            cartItems.clear();
            ticketPrice_lb.setText("0");
            IVA_lb.setText("0");
            totalTicket_lb.setText("0");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ocurrió un error al procesar la compra: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void validateTicket(ActionEvent event) {
        String qrCode = qrCode_tf.getText();
        if (qrCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, ingrese un código QR.");
            alert.showAndWait();
            return;
        }

        MuEntradas ticket = ticketJpa.findEntradasEntities().stream()
            .filter(t -> t.getCodigoQr().equals(qrCode))
            .findFirst()
            .orElse(null);

        if (ticket == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Código QR no válido o entrada no encontrada.");
            alert.showAndWait();
            museumInfo_lb.setText("Museo: N/A");
            dateOfVisit_lb.setText("Fecha: N/A");
            visitorName_lb.setText("Visitante: N/A");
            availableRooms_tv.getItems().clear();
            return;
        }

        // Update ticket details
        museumInfo_lb.setText("Museo: " + ticket.getIdMuseo().getNombre());
        dateOfVisit_lb.setText("Fecha: " + ticket.getFecha().toString());
        visitorName_lb.setText("Visitante: " + ticket.getNombreCliente());

        // Load available rooms for the museum
        ObservableList<MuSalas> rooms = FXCollections.observableArrayList(
            salasJpa.findSalaEntities().stream()
                .filter(s -> s.getIdMuseo().getIdMuseo().equals(ticket.getIdMuseo().getIdMuseo()))
                .collect(java.util.stream.Collectors.toList())
        );
        availableRooms_tv.setItems(rooms);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Éxito");
        alert.setContentText("Entrada válida. Puede acceder a las instalaciones.");
        alert.showAndWait();
    }
}