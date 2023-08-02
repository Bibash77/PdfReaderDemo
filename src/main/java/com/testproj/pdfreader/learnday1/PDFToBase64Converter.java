package com.testproj.pdfreader.learnday1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class PDFToBase64Converter {
    public static final String SRC = "./src/main/resources/pdfs/job_application.pdf";
    public static final String DEST = "./target/sandbox/acroforms/converteddata.pdf";

    public static void main(String[] args) {
        try {
            System.out.println("--------converting pdf from src to base64 data--------------------");

            String base64Data = convertPDFToBase64(SRC);
            System.out.println(base64Data);
            System.out.println("--------converting same base64 to pdf and storing in target --------------------");
            convertBase64ToPDF(base64Data, DEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // function to convert pdf into base64
    public static String convertPDFToBase64(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] pdfBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(pdfBytes);
    }


    // function to convert base64 to pdf
    public static void convertBase64ToPDF(String base64Data, String outputFilePath) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // Create the directory if it doesn't exist
        File outputFile = new File(outputFilePath);
        outputFile.getParentFile().mkdirs();

        // Write the decoded bytes to the new PDF file
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(decodedBytes);
        outputStream.close();
    }
}