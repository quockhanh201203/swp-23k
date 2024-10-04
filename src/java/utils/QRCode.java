package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCode {

    public static void generateQR(String name, String path) throws WriterException, IOException {
        String qrCodePath = System.getProperty("user.dir") + File.separator + "web" + File.separator + "qrcode";
        String qrCodeName = qrCodePath + File.separator + name + "-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(path, BarcodeFormat.QR_CODE, 400, 400);
        Path p = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", p);
    }

    public static void main(String[] args) {
        try {
            QRCode q = new QRCode();
            // Content of the QR Code and file path to save
            String qrContent = "http://localhost:8080/SP24/Login.jsp";
            String filePath = "TestQR";

            // Generate QR code
            q.generateQR(filePath, qrContent);
            System.out.println("QR Code generated successfully.");
        } catch (Exception e) {
            System.err.println("Could not generate QR Code: " + e.getMessage());
        }
    }
}
