package com.mycompany.interfazmuseo;

import clases.QRManager;
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
import javafx.scene.image.Image;
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

    private MuMuseosJpaController museumJpa = new MuMuseosJpaController();
    private MuPreciosJpaController priceJpa = new MuPreciosJpaController();
    private MuEntradasJpaController ticketJpa = new MuEntradasJpaController();
    private MuTransaccionJpaController transactionJpa = new MuTransaccionJpaController();
    private MuComisionJpaController commissionJpa = new MuComisionJpaController();
    private MuSalasJpaController salasJpa = new MuSalasJpaController();
    private QRManager qrManager = new QRManager();

    private ObservableList<TicketPurchase> cartItems = FXCollections.observableArrayList();
    private double porcentajeComision;
    @FXML
    private Button validate_btn;
    @FXML
    private ImageView qrImage_iv;

   
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
        private String qrImagePath;

        public TicketPurchase(String museumName, String visitorName, String date, String ticketType, String amount, String qrCode, String qrImagePath) {
            this.museumName = new SimpleStringProperty(museumName);
            this.visitorName = new SimpleStringProperty(visitorName);
            this.date = new SimpleStringProperty(date);
            this.ticketType = new SimpleStringProperty(ticketType);
            this.amount = new SimpleStringProperty(amount);
            this.qrCode = new SimpleStringProperty(qrCode);
            this.qrImagePath = qrImagePath;
        }

        // Getters for table properties
        public String getMuseumName() {
            return museumName.get();
        }

        public String getVisitorName() {
            return visitorName.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getTicketType() {
            return ticketType.get();
        }

        public String getAmount() {
            return amount.get();
        }

        public String getQrCode() {
            return qrCode.get();
        }

        public String getQrImagePath() {
            return qrImagePath;
        }

        // Setters for database entities
        public void setTicket(MuEntradas ticket) {
            this.ticket = ticket;
        }

        public void setTransaction(MuTransaccion transaction) {
            this.transaction = transaction;
        }

        public void setCommission(MuComision commission) {
            this.commission = commission;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cardType_cb.getItems().addAll("Visa", "Mastercard", "American Express", "Dinner Club", "Union Pay");

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

     
        availableRooms_tv.getColumns().clear();
        TableColumn<MuSalas, String> roomNameCol = new TableColumn<>("Nombre Sala");
        roomNameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<MuSalas, String> roomDescCol = new TableColumn<>("Descripción");
        roomDescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        availableRooms_tv.getColumns().addAll(roomNameCol, roomDescCol);

       
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

        int basePrice = selectedPrice.getMonto();
        double iva = basePrice * porcentajeComision;
        int totalPrice = (int) (basePrice + iva);
        String qrCode = "QR-" + UUID.randomUUID().toString().substring(0, 8);
        String qrImagePath = qrManager.generarQR(qrCode);

        TicketPurchase purchase = new TicketPurchase(
                selectedMuseum.getNombre(),
                visitorName,
                selectedDate.toString(),
                selectedPrice.getNombrePrecio(),
                String.valueOf(totalPrice),
                qrCode,
                qrImagePath
        );

        ticketPrice_lb.setText(String.valueOf(basePrice));
        IVA_lb.setText(String.format("%.2f", iva));
        totalTicket_lb.setText(String.valueOf(totalPrice));

        cartItems.add(purchase);
        visitorsName_tf.clear();
        museumType_cb.setValue(null);
        priceType_cb.setValue(null);
        availability_dp.setValue(null);
        cardType_cb.setValue(null);

        if (qrImagePath != null) {
            qrImage_iv.setImage(new Image("file:" + qrImagePath));
        }
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

                MuEntradas ticket = new MuEntradas();
                ticket.setIdMuseo(museumJpa.findMuseoEntities().stream()
                        .filter(m -> m.getNombre().equals(purchase.getMuseumName()))
                        .findFirst().orElse(null));
                ticket.setNombreCliente(purchase.getVisitorName());
                ticket.setFecha(java.sql.Date.valueOf(LocalDate.parse(purchase.getDate())));
                ticket.setCodigoQr(purchase.getQrCode());
                int originalAmount = Integer.parseInt(purchase.getAmount());
                ticket.setPrecioTotal(originalAmount);
                ticket.setCodigoQr(purchase.getQrImagePath());
                ticketJpa.create(ticket);
                purchase.setTicket(ticket);

                String selectedCard = cardType_cb.getValue() != null ? cardType_cb.getValue() : "Visa";
                MuTransaccion transaction = new MuTransaccion();
                transaction.setIdEntrada(ticket);
                transaction.setTipoTarjeta(selectedCard);
                transaction.setFecha(java.sql.Date.valueOf(LocalDate.now()));
                transactionJpa.create(transaction);
                purchase.setTransaction(transaction);

                switch (selectedCard.toLowerCase()) {
                    case "mastercard":
                        porcentajeComision = 0.03;
                        break;
                    case "american express":
                        porcentajeComision = 0.04;
                        break;
                    case "dinner club":
                        porcentajeComision = 0.02;
                        break;
                    case "union pay":
                        porcentajeComision = 0.06;
                        break;
                    case "visa":
                    default:
                        porcentajeComision = 0.05;
                        break;
                }

                int montoComision = (int) Math.round(originalAmount * porcentajeComision);
                int montoFinal = originalAmount - montoComision;

                MuComision commission = new MuComision();
                commission.setIdTransaccion(transaction);
                commission.setComision((int) (porcentajeComision * 100) + "%");
                commission.setMontoFinal(montoFinal);
                commissionJpa.create(commission);
                purchase.setCommission(commission);
            }

          
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Compra realizada con éxito. Las entradas han sido guardadas.");
            alert.showAndWait();

        
            cartItems.clear();
            ticketPrice_lb.setText("0");
            IVA_lb.setText("0");
            totalTicket_lb.setText("0");
            qrImage_iv.setImage(null);

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
        String qrCodeInput = qrCode_tf.getText();
        if (qrCodeInput.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, ingrese un código QR.");
            alert.showAndWait();
     
            
        } else {
            MuEntradas ticket = ticketJpa.findEntradasEntities().stream()
                    .filter(t -> t.getCodigoQr().equals(qrCodeInput))
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

            museumInfo_lb.setText("Museo: " + ticket.getIdMuseo().getNombre());
            dateOfVisit_lb.setText("Fecha: " + ticket.getFecha().toString());
            visitorName_lb.setText("Visitante: " + ticket.getNombreCliente());
            ObservableList<MuSalas> rooms = (ObservableList<MuSalas>) FXCollections.observableArrayList(
                    salasJpa.findSalaEntities().stream()
                            .filter(s -> s.getIdMuseo().getIdMuseo().equals(ticket.getIdMuseo().getIdMuseo()))
                            .collect(java.util.stream.Collectors.toList())
            );
            availableRooms_tv.setItems(rooms);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Entrada válida. Puede acceder a las instalaciones.");
            alert.showAndWait();

            if (ticket.getCodigoQr() != null) {
                exhibitionImage_iv.setImage(new Image("file:" + ticket.getCodigoQr()));
            }
        }
    }
}
