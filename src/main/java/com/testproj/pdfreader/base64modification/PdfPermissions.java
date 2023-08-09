package com.testproj.pdfreader.base64modification;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfEncryptor;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.IOException;

public class PdfPermissions {

    public static void main(String[] args) {
        try {
            // Replace "input.pdf" with the actual path to your PDF file
            PdfDocument pdfDocument = new PdfDocument(new PdfReader("./src/main/resources/pdfs/report.pdf"));

            int permissions = (int) pdfDocument.getReader().getPermissions();
            if((EncryptionConstants.ALLOW_FILL_IN & permissions) == EncryptionConstants.ALLOW_FILL_IN) {
                System.out.println("Filling in is allowed");
            } else {
                System.out.println("Filling is not allowed");
            }
            String permissionsVerbose = PdfEncryptor.getPermissionsVerbose(permissions);
            System.out.println(permissionsVerbose);
            // Close the PDF document
            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
