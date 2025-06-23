package clases;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.List;
import persistence.MuSalas;

public class CrearPDF {

    public static void generarPDF(List<MuSalas> salas, String titulo, String nombreArchivo) throws IOException {
        String ruta = "D:\\Progra II\\Proyectos\\MuseoAmericano\\src\\main\\resources\\PDF";
        
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.LETTER);
        doc.addPage(page);

        PDPageContentStream content = new PDPageContentStream(doc, page);
        content.beginText();
        content.setFont(PDType1Font.HELVETICA_BOLD, 16);
        content.newLineAtOffset(50, 750);
        content.showText(titulo);
        content.newLineAtOffset(0, -30);
        content.setFont(PDType1Font.HELVETICA, 12);

        for (MuSalas sala : salas) {
            String linea = sala.getNombre() + " - Valoración: " + sala.getPromedioValoracion();
            content.showText(linea);
            content.newLineAtOffset(0, -20);
        }

        content.endText();
        content.close();
        doc.save(ruta + nombreArchivo);
        doc.close();
    }
}
