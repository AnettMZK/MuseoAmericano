
package com.mycompany.interfazmuseo;

import controllers.MuFichasJpaController;
import controllers.MuSalasJpaController;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import persistence.MuFichas;
import persistence.MuSalas;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RatingController implements Initializable {

    @FXML
    private Button addRoomRate_btn;
    @FXML
    private ListView<MuSalas> roomsList_lv;
    @FXML
    private TextArea comments_ta;
    @FXML
    private Label average_lb;
    @FXML
    private Label roomName_lb;
    @FXML
    private Label idRoom_lb;
    @FXML
    private ImageView star1_iv;
    @FXML
    private ImageView star2_iv;
    @FXML
    private ImageView star3_iv;
    @FXML
    private ImageView star4_iv;
    @FXML
    private ImageView star5_iv;
    
    private int selectedRating = 0;

    private Image fullStar;
    private Image emptyStar;

    private List<ImageView> stars;


    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fullStar = new Image(getClass().getResource("/images/colourStar.png").toExternalForm());
        emptyStar = new Image(getClass().getResource("/images/star.png").toExternalForm());

        stars = List.of(star1_iv, star2_iv, star3_iv, star4_iv, star5_iv);
        for (ImageView star : stars) {
            star.setImage(emptyStar);
        }

        MuSalasJpaController controller = new MuSalasJpaController();
        Collection<MuSalas> salas = controller.findMuseoEntities();
        ObservableList<MuSalas> observableSalas = FXCollections.observableArrayList(salas);
        roomsList_lv.setItems(observableSalas);

        roomsList_lv.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MuSalas sala, boolean empty) {
                super.updateItem(sala, empty);
                setText(empty || sala == null ? null : sala.getNombre());
            }
        });

        roomsList_lv.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                roomName_lb.setText(newVal.getNombre());
                idRoom_lb.setText(String.valueOf(newVal.getIdSala()));
                calculateAverage(newVal);
            } else {
                roomName_lb.setText("");
                idRoom_lb.setText("");
                average_lb.setText("N/A");
            }
        });

        average_lb.setText("N/A");
        
    if (!observableSalas.isEmpty()) {
        MuSalas primeraSala = observableSalas.get(0);
        roomsList_lv.getSelectionModel().select(primeraSala);
    }

    }


    @FXML
   private void addRoomRate(ActionEvent event) {
       MuSalas selectedSala = roomsList_lv.getSelectionModel().getSelectedItem();

       if (selectedSala == null) {
            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Debes seleccionar una sala.");
            mensaje.showAndWait();
           return;
       }

       if (selectedRating == 0) {
            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Debes de realizar la valoración.");
            mensaje.showAndWait();
           return;
       }

       String comentario = comments_ta.getText().trim();

       MuFichas nuevaFicha = new MuFichas();
       nuevaFicha.setIdSala(selectedSala);
       nuevaFicha.setValoracion(selectedRating);
       nuevaFicha.setObservacion(comentario.isEmpty() ? null : comentario);

       MuFichasJpaController fichasController = new MuFichasJpaController();
       fichasController.create(nuevaFicha);

     
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Exito");
        mensaje.setContentText("Ficha guardada.");
        mensaje.showAndWait();

       calculateAverage(selectedSala);

       selectedRating = 0;
       for (ImageView star : stars) {
           star.setImage(emptyStar);
       }
       comments_ta.clear();
   }



    @FXML
    private void changeColorStar(MouseEvent event) {
        ImageView clickedStar = (ImageView) event.getSource();

        int clickedIndex = stars.indexOf(clickedStar);
        if (clickedIndex == -1) return;

        selectedRating = clickedIndex + 1;

        for (int i = 0; i < stars.size(); i++) {
            if (i <= clickedIndex) {
                stars.get(i).setImage(fullStar);
            } else {
                stars.get(i).setImage(emptyStar);
            }
        }
    }
    
    private void calculateAverage(MuSalas sala) {
        MuFichasJpaController fichasController = new MuFichasJpaController();
        MuSalasJpaController salasController = new MuSalasJpaController();

        List<MuFichas> fichasDeSala = fichasController.findFichasBySala(sala);

        double promedio = 0.0;

        if (!fichasDeSala.isEmpty()) {
            double suma = 0;
            for (MuFichas ficha : fichasDeSala) {
                suma += ficha.getValoracion();
            }
            promedio = suma / fichasDeSala.size();

            
            sala.setPromedioValoracion(BigDecimal.valueOf(promedio));
            salasController.edit(sala);
        } else {
            sala.setPromedioValoracion(null);
            salasController.edit(sala);
        }

        MuSalas salaActualizada = salasController.findById(sala.getIdSala());

        if (salaActualizada.getPromedioValoracion() != null) {
            average_lb.setText(String.format("%.2f", salaActualizada.getPromedioValoracion().doubleValue()));
         
        } else {
            average_lb.setText("N/A");
        }
    }

}
