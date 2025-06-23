package com.mycompany.interfazmuseo;

import controllers.MuColeccionesJpaController;
import controllers.MuEspecieJpaController;
import controllers.MuMuseosJpaController;
import controllers.MuPreciosJpaController;
import controllers.MuSalasJpaController;
import controllers.MuTematicasJpaController;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import persistence.MuColecciones;
import persistence.MuEspecie;
import persistence.MuMuseos;
import persistence.MuPrecios;
import persistence.MuSalas;
import persistence.MuTematicas;

public class MaintenanceController implements Initializable {

    private String Bandera = "NUEVO";

    @FXML
    private TextField managername_tf;
    @FXML
    private DatePicker foundationDate_dp;
    @FXML
    private ChoiceBox<String> typeMuseum_cb;
    @FXML
    private TableView<MuMuseos> museumRegister_tv;
    @FXML
    private TableView<MuSalas> roomsRegister_tv;
    @FXML
    private TableView<MuColecciones> collectionsRegister_tv;
    @FXML
    private TableView<MuEspecie> speciesRegister_tv;
    @FXML
    private TableView<MuTematicas> themesRegister_tv;
    @FXML
    private TableView<MuPrecios> pricesAndRates_tv;
    @FXML
    private TextField museumName_tf;
    @FXML
    private TextField museumLocation_tf;
    @FXML
    private TextField museumWebURL_tf;
    @FXML
    private Button museumSave_btn;
    @FXML
    private Button museumCancel_btn;
    @FXML
    private Button museumEdit_btn;
    @FXML
    private Button museumDelete_btn;
    @FXML
    private TextField roomID_tf;
    @FXML
    private TextField roomName_tf;
    @FXML
    private Label museumWarning_lb;
    @FXML
    private TextArea roomDescription_ta;
    @FXML
    private Button roomSave_btn;
    @FXML
    private Button roomEdit_btn;
    @FXML
    private Button roomDelete_btn;
    @FXML
    private Button roomCancel_btn;
    @FXML
    private Label roomWarning_lb;
    @FXML
    private TextField collectionsName_tf;
    @FXML
    private TextField collectionsCentury_tf;
    @FXML
    private TextArea collectionsDescription_ta;
    @FXML
    private Button collectionsDelete_btn1;
    @FXML
    private Button collectionsEdit_btn;
    @FXML
    private Button collectionsSave_btn1;
    @FXML
    private Button collectionsCancel_btn;
    @FXML
    private Button speciesSave_btn;
    @FXML
    private Button speciesCancel_btn;
    @FXML
    private Button speciesDelete_btn;
    @FXML
    private Button speciesEdit_btn;
    @FXML
    private Button themesCancel_btn;
    @FXML
    private Button themesSave_btn;
    @FXML
    private Button themesEdit_btn;
    @FXML
    private Button themesDelete_btn;
    @FXML
    private Button pricesAndRatesDelete_btn;
    @FXML
    private Button pricesAndRatesEdit_btn;
    @FXML
    private Button pricesAndRatesCancel_btn;
    @FXML
    private Button pricesAndRatesSave_btn;
    @FXML
    private TextField scientificNameSpecies_tf;
    @FXML
    private TextField commonNameSpecies_tf;
    @FXML
    private Label collectionsWarning_lb;
    @FXML
    private TextField periodOfLife_tf;
    @FXML
    private TextField extinctionDate_tf;
    @FXML
    private TextArea speciesDescription_ta;
    @FXML
    private Label collectionsWarning_lb1;
    @FXML
    private TextField themesName_tf;
    @FXML
    private TextArea themesDescription_ta;
    @FXML
    private TextField themesPeriod_tf;
    @FXML
    private Label themesWarning_lb;

    private MuMuseosJpaController MuseumJpa = new MuMuseosJpaController();
    private MuSalasJpaController MuSalaJpa = new MuSalasJpaController();
    private MuColeccionesJpaController MuColecionesJpa = new MuColeccionesJpaController();
    private MuEspecieJpaController MuEspecieJpa = new MuEspecieJpaController();
    private MuTematicasJpaController MuTematicasJpa = new MuTematicasJpaController();
    private MuPreciosJpaController MuPrecioJpa = new MuPreciosJpaController();

    private MuMuseos selectedMuseum;
    private MuSalas selectedRoom;
    private MuColecciones selectedCollection;
    private MuEspecie selectedSpecie;
    private MuTematicas selectedThems;
    private MuPrecios selectedPrice;
    @FXML
    private TextField namePrice_tf;
    @FXML
    private TextField amountPrice_tf;
    @FXML
    private ChoiceBox<String> filterRoom_cb;
    @FXML
    private Button filterRoom_btn;
    @FXML
    private ChoiceBox<String> filterCollections_cb;
    @FXML
    private Button filterCollections_btn;
    @FXML
    private ChoiceBox<String> filterSpecies_cb;
    @FXML
    private Button filterSpecie_btn;
    @FXML
    private ChoiceBox<String> filterThemes_cb;
    @FXML
    private Button filterThemes_btn;
    @FXML
    private TextField rangeOne_tf;
    @FXML
    private TextField rangeTwo_tf;
    @FXML
    private Button filterPrice_btn;
    @FXML
    private Button restorePrice_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uploadMuseumData();
        uploadRoomsData();
        uploadCollectionData();
        uploadSpeciesData();
        uploadThemesData();
        uploadPricesAndRatesData();
        typeMuseum_cb.getItems().addAll("Arte", "Historia", "Musical", "Militar");

        filterRoom_cb.getItems().addAll("Todos", "Orden alfabético");
        filterRoom_cb.setValue("Todos");

        filterSpecies_cb.getItems().addAll("Todos", "Orden alfabético");
        filterSpecies_cb.setValue("Todos");

        filterCollections_cb.getItems().addAll("Todos", "Orden alfabético");
        filterCollections_cb.setValue("Todos");

        filterThemes_cb.getItems().addAll("Todos", "Orden alfabético");
        filterThemes_cb.setValue("Todos");
        // TODO

        setDoubleClickSelectionHandler(museumRegister_tv, item -> {
            selectedMuseum = item;
        });
        setDoubleClickSelectionHandler(roomsRegister_tv, item -> {
            selectedRoom = item;
        });
        setDoubleClickSelectionHandler(collectionsRegister_tv, item -> {
            selectedCollection = item;
        });
        setDoubleClickSelectionHandler(speciesRegister_tv, item -> {
            selectedSpecie = item;
        });
        setDoubleClickSelectionHandler(themesRegister_tv, item -> {
            selectedThems = item;
        });
        setDoubleClickSelectionHandler(pricesAndRates_tv, item -> {
            selectedPrice = item;
        });

    }

    /* GESTION DE MUSEO */
    @FXML
    private void saveMuseumInfo(ActionEvent event) {
        Date date = null;
        LocalDate dateSelected = foundationDate_dp.getValue();
        if (dateSelected != null) {
            date = java.sql.Date.valueOf(dateSelected);
        }

        String typeSelected = typeMuseum_cb.getValue();

        if (museumName_tf.getText().isEmpty() || museumLocation_tf.getText().isEmpty() || managername_tf.getText().isEmpty()
                || museumWebURL_tf.getText().isEmpty()
                || foundationDate_dp.getValue() == null || typeMuseum_cb.getValue() == null) {

            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Ningun dato del museo puede quedar vacio");
            mensaje.showAndWait();

        } else {
            if (Bandera.equals("NUEVO")) {

                MuMuseos museo = new MuMuseos();
                museo.setNombre(museumName_tf.getText());
                museo.setUbicacion(museumLocation_tf.getText());
                museo.setNombreDirector(managername_tf.getText());
                museo.setSitioWeb(museumWebURL_tf.getText());
                museo.setFechaFundacion(date);
                museo.setTipo(typeSelected);

                MuseumJpa.create(museo);
                uploadMuseumData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Museo creado con exito");
                mensaje.showAndWait();
            }

            if (Bandera.equals("EDITAR")) {

                selectedMuseum.setNombre(museumName_tf.getText());
                selectedMuseum.setUbicacion(museumLocation_tf.getText());
                selectedMuseum.setNombreDirector(managername_tf.getText());
                selectedMuseum.setSitioWeb(museumWebURL_tf.getText());
                selectedMuseum.setFechaFundacion(date);
                selectedMuseum.setTipo(typeSelected);

                MuseumJpa.edit(selectedMuseum);
                uploadMuseumData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Museo editado con exito");
                mensaje.showAndWait();
            }

            museumName_tf.clear();
            museumLocation_tf.clear();
            managername_tf.clear();
            museumWebURL_tf.clear();
            foundationDate_dp.setValue(null);
            typeMuseum_cb.setValue(null);

            Bandera = "NUEVO";
        }
    }

    @FXML
    private void editMuseumInfo(ActionEvent event) {
        if (selectedMuseum != null) {

            museumName_tf.setText(selectedMuseum.getNombre());
            museumLocation_tf.setText(selectedMuseum.getUbicacion());
            managername_tf.setText(selectedMuseum.getNombreDirector());
            museumWebURL_tf.setText(selectedMuseum.getSitioWeb());
            typeMuseum_cb.setValue(selectedMuseum.getTipo());
            foundationDate_dp.setValue(convertToLocalDateViaInstant(selectedMuseum.getFechaFundacion()));

            Bandera = "EDITAR";

        }
    }

    @FXML
    private void deleteMuseumInfo(ActionEvent event) {
        if (selectedMuseum != null) {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmacion");
            mensaje.setContentText("Estas seguro que deseas eliminar la informacion de " + selectedMuseum.getNombre());

            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");

            mensaje.getButtonTypes().setAll(si, no);

            Optional<ButtonType> resultado = mensaje.showAndWait();

            if (resultado.isPresent()) {
                if (resultado.get() == si) {

                    MuseumJpa.delete(selectedMuseum);
                    uploadMuseumData();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exito");
                    alert.setContentText("Museo eliminado con exito");
                    alert.showAndWait();
                }
            }
            if (resultado.get() == no) {
                System.out.println("El museo no fue eliminado");
            }
        }
    }

    @FXML
    private void cancelMuseumInfo(ActionEvent event) {
        museumName_tf.clear();
        museumLocation_tf.clear();
        managername_tf.clear();
        museumWebURL_tf.clear();
        foundationDate_dp.setValue(null);
        typeMuseum_cb.setValue(null);
    }

    public void uploadMuseumData() {
        museumRegister_tv.getColumns().clear();

        TableColumn<MuMuseos, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("idMuseo"));

        TableColumn<MuMuseos, String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<MuMuseos, String> tipo = new TableColumn<>("Tipo");
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<MuMuseos, String> ubicacion = new TableColumn<>("Ubicación");
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

        TableColumn<MuMuseos, String> director = new TableColumn<>("Director");
        director.setCellValueFactory(new PropertyValueFactory<>("nombreDirector"));

        TableColumn<MuMuseos, LocalDate> fecha = new TableColumn<>("Fecha Fundación");
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechaFundacion"));

        TableColumn<MuMuseos, String> sitioWeb = new TableColumn<>("Sitio Web");
        sitioWeb.setCellValueFactory(new PropertyValueFactory<>("sitioWeb"));

        museumRegister_tv.getColumns().addAll(id, nombre, tipo, ubicacion, director, fecha, sitioWeb);

        Collection museos = MuseumJpa.findMuseoEntities();
        ObservableList<MuMuseos> museosFX = FXCollections.observableArrayList(museos);
        museumRegister_tv.setItems(museosFX);
    }

    /* GESTION DE SALAS */
    @FXML
    private void saveRoomInfo(ActionEvent event) {

        if (roomName_tf.getText().isEmpty() || roomDescription_ta.getText().isEmpty()) {

            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Ningun dato de la sala puede quedar vacio");
            mensaje.showAndWait();

        } else {
            if (Bandera.equals("NUEVO")) {

                MuSalas sala = new MuSalas();
                sala.setNombre(roomName_tf.getText());
                sala.setDescripcion(roomDescription_ta.getText());

                MuSalaJpa.create(sala);
                uploadRoomsData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Sala creada con exito");
                mensaje.showAndWait();
            }

            if (Bandera.equals("EDITAR")) {

                selectedRoom.setNombre(roomName_tf.getText());
                selectedRoom.setDescripcion(roomDescription_ta.getText());

                MuSalaJpa.edit(selectedRoom);
                uploadRoomsData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Sala editada con exito");
                mensaje.showAndWait();
            }

            roomName_tf.clear();
            roomDescription_ta.clear();

            Bandera = "NUEVO";
        }
    }

    @FXML
    private void editRoomInfo(ActionEvent event) {
        if (selectedRoom != null) {
            roomName_tf.setText(selectedRoom.getNombre());
            roomDescription_ta.setText(selectedRoom.getDescripcion());
            Bandera = "EDITAR";
        }

    }

    @FXML
    private void deleteRoomInfo(ActionEvent event) {

        if (selectedRoom != null) {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmacion");
            mensaje.setContentText("Estas seguro que deseas eliminar la informacion de " + selectedRoom.getNombre());

            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");

            mensaje.getButtonTypes().setAll(si, no);

            Optional<ButtonType> resultado = mensaje.showAndWait();

            if (resultado.isPresent()) {
                if (resultado.get() == si) {

                    MuSalaJpa.delete(selectedRoom);
                    uploadRoomsData();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exito");
                    alert.setContentText("Sala eliminada con exito");
                    alert.showAndWait();
                }
            }
            if (resultado.get() == no) {
                System.out.println("La sala no fue eliminada");
            }
        }
    }

    @FXML
    private void cancelRoomInfo(ActionEvent event) {
        roomName_tf.clear();
        roomDescription_ta.clear();
    }

    public void uploadRoomsData() {

        roomsRegister_tv.getColumns().clear();

        TableColumn<MuSalas, String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<MuSalas, String> descripcion = new TableColumn<>("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        roomsRegister_tv.getColumns().addAll(nombre, descripcion);

        Collection salas = MuSalaJpa.findSalaEntities();
        ObservableList<MuSalas> salasFX = FXCollections.observableArrayList(salas);
        roomsRegister_tv.setItems(salasFX);
    }

    /* GESTION DE COLECCIONES */
    @FXML
    private void deleteCollectionsInfo(ActionEvent event) {
        if (selectedCollection != null) {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmacion");
            mensaje.setContentText("Estas seguro que deseas eliminar la informacion de " + selectedCollection.getNombre());

            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");

            mensaje.getButtonTypes().setAll(si, no);

            Optional<ButtonType> resultado = mensaje.showAndWait();

            if (resultado.isPresent()) {
                if (resultado.get() == si) {

                    MuColecionesJpa.delete(selectedCollection);
                    uploadCollectionData();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exito");
                    alert.setContentText("Coleccion eliminada con exito");
                    alert.showAndWait();
                }
            }
            if (resultado.get() == no) {
                System.out.println("La coleccion no fue eliminada");
            }
        }
    }

    @FXML
    private void editCollectionsnfo(ActionEvent event) {

        if (selectedCollection != null) {
            collectionsName_tf.setText(selectedCollection.getNombre());
            collectionsDescription_ta.setText(selectedCollection.getDescripcion());
            collectionsCentury_tf.setText(selectedCollection.getSiglo());
            Bandera = "EDITAR";
        }

    }

    @FXML
    private void cancelCollectionsInfo(ActionEvent event) {
        collectionsName_tf.clear();
        collectionsDescription_ta.clear();
        collectionsCentury_tf.clear();
    }

    @FXML
    private void saveCollectionsInfo(ActionEvent event) {

        if (collectionsName_tf.getText().isEmpty() || collectionsCentury_tf.getText().isEmpty() || collectionsDescription_ta.getText().isEmpty()) {

            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Ningun dato de las colecciones puede quedar vacio");
            mensaje.showAndWait();

        } else {
            if (Bandera.equals("NUEVO")) {

                MuColecciones coleccion = new MuColecciones();
                coleccion.setNombre(collectionsName_tf.getText());
                coleccion.setSiglo(collectionsCentury_tf.getText());
                coleccion.setDescripcion(collectionsDescription_ta.getText());

                MuColecionesJpa.create(coleccion);
                uploadCollectionData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Coleccion creada con exito");
                mensaje.showAndWait();
            }

            if (Bandera.equals("EDITAR")) {

                selectedCollection.setNombre(collectionsName_tf.getText());
                selectedCollection.setDescripcion(collectionsDescription_ta.getText());
                selectedCollection.setSiglo(collectionsCentury_tf.getText());

                MuColecionesJpa.edit(selectedCollection);
                uploadCollectionData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Coleccion editada con exito");
                mensaje.showAndWait();
            }

            collectionsName_tf.clear();
            collectionsDescription_ta.clear();
            collectionsCentury_tf.clear();

            Bandera = "NUEVO";
        }
    }

    public void uploadCollectionData() {

        collectionsRegister_tv.getColumns().clear();

        TableColumn<MuColecciones, String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<MuColecciones, String> descripcion = new TableColumn<>("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<MuColecciones, String> siglo = new TableColumn<>("Siglo");
        siglo.setCellValueFactory(new PropertyValueFactory<>("siglo"));

        collectionsRegister_tv.getColumns().addAll(nombre, descripcion, siglo);

        Collection coleccion = MuColecionesJpa.findColeccionEntities();
        ObservableList<MuColecciones> coleccionFX = FXCollections.observableArrayList(coleccion);
        collectionsRegister_tv.setItems(coleccionFX);
    }

    /* GESTION DE ESPECIES */
    @FXML
    private void saveSpeciesInfo(ActionEvent event) {
        if (scientificNameSpecies_tf.getText().isEmpty() || commonNameSpecies_tf.getText().isEmpty() || speciesDescription_ta.getText().isEmpty() || extinctionDate_tf.getText().isEmpty() || periodOfLife_tf.getText().isEmpty()) {

            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Ningun dato de la especie puede quedar vacio");
            mensaje.showAndWait();

        } else {
            if (Bandera.equals("NUEVO")) {

                MuEspecie especie = new MuEspecie();
                especie.setNombreCientifico(scientificNameSpecies_tf.getText());
                especie.setNombreComun(commonNameSpecies_tf.getText());
                especie.setDescripcion(speciesDescription_ta.getText());
                especie.setFechaExtincion(extinctionDate_tf.getText());
                especie.setEpoca(periodOfLife_tf.getText());

                MuEspecieJpa.create(especie);
                uploadSpeciesData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Especie creada con exito");
                mensaje.showAndWait();
            }

            if (Bandera.equals("EDITAR")) {

                selectedSpecie.setNombreCientifico(scientificNameSpecies_tf.getText());
                selectedSpecie.setNombreComun(commonNameSpecies_tf.getText());
                selectedSpecie.setDescripcion(speciesDescription_ta.getText());
                selectedSpecie.setFechaExtincion(extinctionDate_tf.getText());
                selectedSpecie.setEpoca(periodOfLife_tf.getText());

                MuEspecieJpa.edit(selectedSpecie);
                uploadSpeciesData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Especie editada con exito");
                mensaje.showAndWait();
            }

            scientificNameSpecies_tf.clear();
            commonNameSpecies_tf.clear();
            speciesDescription_ta.clear();
            extinctionDate_tf.clear();
            periodOfLife_tf.clear();

            Bandera = "NUEVO";
        }
    }

    @FXML
    private void cancelSpeciesInfo(ActionEvent event) {
        scientificNameSpecies_tf.clear();
        commonNameSpecies_tf.clear();
        speciesDescription_ta.clear();
        extinctionDate_tf.clear();
        periodOfLife_tf.clear();
    }

    @FXML
    private void deleteSpeciesInfo(ActionEvent event) {
        if (selectedSpecie != null) {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmacion");
            mensaje.setContentText("Estas seguro que deseas eliminar la informacion de " + selectedSpecie.getNombreCientifico());

            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");

            mensaje.getButtonTypes().setAll(si, no);

            Optional<ButtonType> resultado = mensaje.showAndWait();

            if (resultado.isPresent()) {
                if (resultado.get() == si) {

                    MuEspecieJpa.delete(selectedSpecie);
                    uploadSpeciesData();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exito");
                    alert.setContentText("Especie eliminada con exito");
                    alert.showAndWait();
                }
            }
            if (resultado.get() == no) {
                System.out.println("La especie no fue eliminada");
            }
        }
    }

    @FXML
    private void editSpeciesInfo(ActionEvent event) {
        if (selectedSpecie != null) {
            scientificNameSpecies_tf.setText(selectedSpecie.getNombreCientifico());
            commonNameSpecies_tf.setText(selectedSpecie.getNombreComun());
            speciesDescription_ta.setText(selectedSpecie.getDescripcion());
            extinctionDate_tf.setText(selectedSpecie.getFechaExtincion());
            periodOfLife_tf.setText(selectedSpecie.getEpoca());
            Bandera = "EDITAR";
        }
    }

    public void uploadSpeciesData() {

        speciesRegister_tv.getColumns().clear();

        TableColumn<MuEspecie, String> nombrecienti = new TableColumn<>("Nombre Cientifico");
        nombrecienti.setCellValueFactory(new PropertyValueFactory<>("nombreCientifico"));

        TableColumn<MuEspecie, String> nombreComun = new TableColumn<>("Nombre Comun");
        nombreComun.setCellValueFactory(new PropertyValueFactory<>("nombreComun"));

        TableColumn<MuEspecie, String> descripcion = new TableColumn<>("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<MuEspecie, String> extincion = new TableColumn<>("Fecha de Extincion");
        extincion.setCellValueFactory(new PropertyValueFactory<>("fechaExtincion"));

        TableColumn<MuEspecie, String> epoca = new TableColumn<>("Epoca de vida");
        epoca.setCellValueFactory(new PropertyValueFactory<>("epoca"));

        speciesRegister_tv.getColumns().addAll(nombrecienti, nombreComun, descripcion, extincion, epoca);

        Collection especie = MuEspecieJpa.findEspecieEntities();
        ObservableList<MuEspecie> especieFX = FXCollections.observableArrayList(especie);
        speciesRegister_tv.setItems(especieFX);
    }

    /* GESTION DE TEMAS */
    @FXML
    private void cancelThemesInfo(ActionEvent event) {
        themesName_tf.clear();
        themesDescription_ta.clear();
        themesPeriod_tf.clear();
    }

    @FXML
    private void saveThemesInfo(ActionEvent event) {
        if (themesName_tf.getText().isEmpty() || themesDescription_ta.getText().isEmpty() || themesPeriod_tf.getText().isEmpty()) {

            Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Ningun dato de la  puede quedar vacio");
            mensaje.showAndWait();

        } else {
            if (Bandera.equals("NUEVO")) {

                MuTematicas tematica = new MuTematicas();
                tematica.setNombre(themesName_tf.getText());
                tematica.setCaracteristicas(themesDescription_ta.getText());
                tematica.setEpoca(themesPeriod_tf.getText());

                MuTematicasJpa.create(tematica);
                uploadThemesData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Tematica creada con exito");
                mensaje.showAndWait();
            }

            if (Bandera.equals("EDITAR")) {

                selectedThems.setNombre(themesName_tf.getText());
                selectedThems.setCaracteristicas(themesDescription_ta.getText());
                selectedThems.setEpoca(themesPeriod_tf.getText());

                MuTematicasJpa.edit(selectedThems);
                uploadThemesData();

                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Exito");
                mensaje.setContentText("Tematica editada con exito");
                mensaje.showAndWait();
            }

            themesName_tf.clear();
            themesDescription_ta.clear();
            themesPeriod_tf.clear();

            Bandera = "NUEVO";
        }
    }

    @FXML
    private void ediTthemesInfo(ActionEvent event) {
        if (selectedThems != null) {
            themesName_tf.setText(selectedThems.getNombre());
            themesDescription_ta.setText(selectedThems.getCaracteristicas());
            themesPeriod_tf.setText(selectedThems.getEpoca());
            Bandera = "EDITAR";
        }
    }

    @FXML
    private void deleteThemesInfo(ActionEvent event) {
        if (selectedThems != null) {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmacion");
            mensaje.setContentText("Estas seguro que deseas eliminar la informacion de " + selectedThems.getNombre());

            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");

            mensaje.getButtonTypes().setAll(si, no);

            Optional<ButtonType> resultado = mensaje.showAndWait();

            if (resultado.isPresent()) {
                if (resultado.get() == si) {

                    MuTematicasJpa.delete(selectedThems);
                    uploadSpeciesData();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exito");
                    alert.setContentText("Tematica eliminada con exito");
                    alert.showAndWait();
                }
            }
            if (resultado.get() == no) {
                System.out.println("La tematica no fue eliminada");
            }
        }
    }

    public void uploadThemesData() {

        themesRegister_tv.getColumns().clear();

        TableColumn<MuTematicas, String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<MuTematicas, String> descripcion = new TableColumn<>("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));

        TableColumn<MuTematicas, String> epoca = new TableColumn<>("Epoca");
        epoca.setCellValueFactory(new PropertyValueFactory<>("epoca"));

        themesRegister_tv.getColumns().addAll(nombre, descripcion, epoca);

        Collection tematica = MuTematicasJpa.findTematicaEntities();
        ObservableList<MuTematicas> tematicaFX = FXCollections.observableArrayList(tematica);
        themesRegister_tv.setItems(tematicaFX);
    }

    /* GESTION DE PRECIOS Y TARIFAS */
    @FXML
    private void deletePricesAndRatesInfo(ActionEvent event) {
        if (selectedPrice != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro que deseas eliminar este precio?",
                    ButtonType.YES, ButtonType.NO);
            alerta.setTitle("Confirmación");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    MuPrecioJpa.delete(selectedPrice);
                    uploadPricesAndRatesData();
                    namePrice_tf.clear();
                    amountPrice_tf.clear();
                    selectedPrice = null;
                    Bandera = "NUEVO";
                }
            });
        }
    }

    @FXML
    private void editPricesAndRatesInfo(ActionEvent event) {
        if (selectedPrice != null) {
            namePrice_tf.setText(selectedPrice.getNombrePrecio());
            amountPrice_tf.setText(String.valueOf(selectedPrice.getMonto()));
            Bandera = "EDITAR";
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Debes seleccionar un precio para editar.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void cancelPricesAndRatesInfo(ActionEvent event) {
        namePrice_tf.clear();
        amountPrice_tf.clear();
        selectedPrice = null;
        Bandera = "NUEVO";
    }

    @FXML
    private void savePricesAndRatesInfo(ActionEvent event) {
        String nombre = namePrice_tf.getText();
        String montoTexto = amountPrice_tf.getText();

        if (nombre.isEmpty() || montoTexto.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Todos los campos deben estar llenos.");
            alerta.showAndWait();
            return;
        }

        try {
            int monto = Integer.parseInt(montoTexto);

            if (Bandera.equals("NUEVO")) {
                persistence.MuPrecios nuevo = new persistence.MuPrecios();
                nuevo.setNombrePrecio(nombre);
                nuevo.setMonto(monto);
                MuPrecioJpa.create(nuevo);
            } else if (Bandera.equals("EDITAR") && selectedPrice != null) {
                selectedPrice.setNombrePrecio(nombre);
                selectedPrice.setMonto(monto);
                MuPrecioJpa.edit(selectedPrice);
            }

            uploadPricesAndRatesData();
            namePrice_tf.clear();
            amountPrice_tf.clear();
            Bandera = "NUEVO";
            selectedPrice = null;

        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "El monto debe ser un número válido.");
            alerta.showAndWait();
        }

    }

    public void uploadPricesAndRatesData() {
        pricesAndRates_tv.getColumns().clear();

        TableColumn<persistence.MuPrecios, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idPrecio"));

        TableColumn<persistence.MuPrecios, String> nameCol = new TableColumn<>("Nombre Precio");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nombrePrecio"));

        TableColumn<persistence.MuPrecios, Integer> amountCol = new TableColumn<>("Monto");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("monto"));

        pricesAndRates_tv.getColumns().addAll(idCol, nameCol, amountCol);

        Collection<persistence.MuPrecios> precios = MuPrecioJpa.findPreciosEntities();
        ObservableList<persistence.MuPrecios> preciosFX = FXCollections.observableArrayList(precios);
        pricesAndRates_tv.setItems(preciosFX);

    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private <T> void setDoubleClickSelectionHandler(TableView<T> tableView, Consumer<T> manejadorSeleccion) {
        tableView.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    T item = row.getItem();
                    manejadorSeleccion.accept(item);
                }
            });
            return row;
        });
    }

    @FXML
    private void filterRoom(ActionEvent event) {
        String filtro = filterRoom_cb.getValue();

        if (filtro == null) {
            return;
        }

        Collection<MuSalas> salas = MuSalaJpa.findSalaEntities();
        List<MuSalas> listaSalas = new ArrayList<>(salas);

        if (filtro.equals("Orden alfabético")) {
            listaSalas.sort(Comparator.comparing(MuSalas::getNombre, String.CASE_INSENSITIVE_ORDER));
        }
        if (filtro.equals("Todos")) {
            uploadRoomsData();
            return;
        }

        ObservableList<MuSalas> salasFX = FXCollections.observableArrayList(listaSalas);
        roomsRegister_tv.setItems(salasFX);
    }

    @FXML
    private void filterCollection(ActionEvent event) {
        String filtro = filterCollections_cb.getValue();

        if (filtro == null || filtro.equals("Todos")) {
            uploadCollectionData();
            return;
        }

        Collection<MuColecciones> colecciones = MuColecionesJpa.findColeccionEntities();
        List<MuColecciones> lista = new ArrayList<>(colecciones);

        if (filtro.equals("Orden alfabético")) {
            lista.sort(Comparator.comparing(MuColecciones::getNombre, String.CASE_INSENSITIVE_ORDER));
        }

        ObservableList<MuColecciones> coleccionesFX = FXCollections.observableArrayList(lista);
        collectionsRegister_tv.setItems(coleccionesFX);
    }

    @FXML
    private void filterSpecies(ActionEvent event) {
        String filtro = filterSpecies_cb.getValue();

        if (filtro == null || filtro.equals("Todos")) {
            uploadSpeciesData();
            return;
        }

        Collection<MuEspecie> especies = MuEspecieJpa.findEspecieEntities();
        List<MuEspecie> lista = new ArrayList<>(especies);

        if (filtro.equals("Orden alfabético")) {
            lista.sort(Comparator.comparing(MuEspecie::getNombreCientifico, String.CASE_INSENSITIVE_ORDER));
        }

        ObservableList<MuEspecie> especiesFX = FXCollections.observableArrayList(lista);
        speciesRegister_tv.setItems(especiesFX);
    }

    @FXML
    private void filterThemes(ActionEvent event) {
        String filtroSeleccionado = filterThemes_cb.getValue();

        if (filtroSeleccionado == null || filtroSeleccionado.equals("Todos")) {
            uploadThemesData();
            return;
        }

        Collection<MuTematicas> tematicas = MuTematicasJpa.findTematicaEntities();
        List<MuTematicas> tematicasFiltradas = new ArrayList<>(tematicas);

        if (filtroSeleccionado.equalsIgnoreCase("Orden alfabético")) {
            tematicasFiltradas.sort(Comparator.comparing(MuTematicas::getNombre));
        }

        ObservableList<MuTematicas> tematicasFX = FXCollections.observableArrayList(tematicasFiltradas);
        themesRegister_tv.setItems(tematicasFX);
    }

    @FXML
    private void filterPrices(ActionEvent event) {
        String rango1Texto = rangeOne_tf.getText();
        String rango2Texto = rangeTwo_tf.getText();

        if (rango1Texto.isEmpty() || rango2Texto.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Debes ingresar ambos valores de rango.");
            alerta.showAndWait();
            return;
        }

        try {
            int rango1 = Integer.parseInt(rango1Texto);
            int rango2 = Integer.parseInt(rango2Texto);

            if (rango1 > rango2) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "El rango inicial no puede ser mayor que el rango final.");
                alerta.showAndWait();
                return;
            }

            Collection<MuPrecios> precios = MuPrecioJpa.findPreciosEntities();
            List<MuPrecios> filtrados = precios.stream()
                    .filter(p -> p.getMonto() >= rango1 && p.getMonto() <= rango2)
                    .collect(Collectors.toList());

            ObservableList<MuPrecios> preciosFX = FXCollections.observableArrayList(filtrados);
            pricesAndRates_tv.setItems(preciosFX);

            rangeOne_tf.clear();
            rangeTwo_tf.clear();

        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Ambos rangos deben ser números enteros válidos.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void restorePrices(ActionEvent event) {
        uploadPricesAndRatesData();
    }

}
