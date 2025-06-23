package com.mycompany.interfazmuseo;

import controllers.MuSalasJpaController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


public class ReportController implements Initializable {

    @FXML
    private Button btn_Mejores;
    @FXML
    private Button btn_Peores;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generarPdfMejores(ActionEvent event) throws IOException {
        generarReporte(true);
    }

    @FXML
    private void generarPdfPeores(ActionEvent event) throws IOException {
         generarReporte(false);
    }
    
    private void generarReporte(boolean mejores) throws IOException {
    MuSalasJpaController controller = new MuSalasJpaController();

    String nombreArchivo = mejores ? "mejores_salas.pdf" : "peores_salas.pdf";

    controller.generarReporteSalas(mejores, nombreArchivo);

    // Intentar abrir el archivo automáticamente
    try {
        File file = new File(nombreArchivo);
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        }
    } catch (IOException e) {
         Alert mensaje = new Alert(Alert.AlertType.WARNING);
            mensaje.setTitle("CUIDADO");
            mensaje.setContentText("Error al abrir el PDF");
            mensaje.showAndWait();
    }
}
}
