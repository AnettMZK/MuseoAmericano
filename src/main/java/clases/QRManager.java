package clases;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;


public class QRManager {

    public String generarQR(String idEntrada) {
        int ancho = 300;
        int alto = 300;

      
        String nombreArchivo = "qr_entrada_" + idEntrada + ".png";

   
        File directorio = new File(nombreArchivo);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
     
        Path ruta = FileSystems.getDefault().getPath(nombreArchivo);

        QRCodeWriter qrWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix matriz = qrWriter.encode(idEntrada, BarcodeFormat.QR_CODE, ancho, alto, hints);
            MatrixToImageWriter.writeToPath(matriz, "PNG", ruta);
            System.out.println("QR generado: " + ruta.toAbsolutePath());
            return ruta.toAbsolutePath().toString(); // Return the file path
        } catch (WriterException | IOException e) {
            System.err.println("Error al generar QR: " + e.getMessage());
            return null;
        }
    }

    public String leerQR(String rutaArchivo) throws Exception {
        BufferedImage imagen = ImageIO.read(new File(rutaArchivo));
        LuminanceSource fuente = new BufferedImageLuminanceSource(imagen);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(fuente));

        QRCodeReader lector = new QRCodeReader();
        Result resultado = lector.decode(bitmap);

        return resultado.getText();
    }
}