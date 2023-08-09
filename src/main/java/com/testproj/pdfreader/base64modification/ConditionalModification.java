package com.testproj.pdfreader.base64modification;

import com.itextpdf.kernel.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class ConditionalModification {
    public static void main(String[] args) throws IOException {
        final String SRC = "./src/main/resources/pdfs/hello_encrypted.pdf";

        PdfReader pdfReader = new PdfReader(SRC);
        PdfDocument pdfDocument = new PdfDocument(pdfReader);
        int perm = (int) pdfReader.getPermissions();

//        long permissions = pdfDocument.getReader().getPermissions();

//        if (PdfEncryptor.isFillInAllowed((int) perm)) {
//            System.out.println("Allow degraded printing");
//        }
        String verbose = PdfEncryptor.getPermissionsVerbose(perm);

        System.out.println(verbose);

//        System.out.println(perm);

//        if (true) {
//            System.out.println("PDF has form fill access.");
//        } else {
//            System.out.println("PDF does not have form fill access.");
//        }

        pdfDocument.close();
    }


}
