package com.testproj.pdfreader.base64modification;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.*;

import java.io.IOException;

public class PdfPermissions {
    public static final String DEST = "./target/sandbox/security/encrypt_pdf_without_user_password.pdf";

    public static void main(String[] args) {
        try {
            String password = "123456";
            // Replace "input.pdf" with the actual path to your PDF file
            ReaderProperties readerProperties = new ReaderProperties();
            readerProperties.setPassword(password.getBytes());
            PdfReader pdfReader =new PdfReader("./src/main/resources/pdfs/printingdisallow.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfReader);

            int permissions = (int) pdfDocument.getReader().getPermissions();
            if((EncryptionConstants.ALLOW_FILL_IN & permissions) == EncryptionConstants.ALLOW_FILL_IN) {
                System.out.println("Filling in is allowed");
            } else {
                System.out.println("Filling is not allowed");
            }
            String permissionsVerbose = PdfEncryptor.getPermissionsVerbose(permissions);
            System.out.println(permissionsVerbose);
            // Close the PDF document

            new PdfWriter(DEST,
                    new WriterProperties().setStandardEncryption(new byte[0], "12345678".getBytes(),
                            EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.ENCRYPTION_AES_256));
            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
