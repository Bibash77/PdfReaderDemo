package com.testproj.pdfreader.pdfPermission;

import com.itextpdf.kernel.pdf.*;

import java.io.File;

public class EncryptPdfWithoutUserPassword {
    public static final String DEST = "./target/sandbox/security/encrypt_pdf_without_user_password.pdf";
    public static final String SRC = "../src/main/resources/pdfs/passwordprotected.pdf";

    public static final String OWNER_PASSWORD = "World";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new EncryptPdfWithoutUserPassword().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        String password = "123456";
        // Replace "input.pdf" with the actual path to your PDF file
        ReaderProperties readerProperties = new ReaderProperties();
        readerProperties.setPassword(password.getBytes());
        PdfReader pdfReader = new PdfReader("./src/main/resources/pdfs/passwordprotected.pdf", readerProperties);
        PdfWriter pdfWriter = new PdfWriter(dest, new WriterProperties().setStandardEncryption(
                // null user password argument is equal to empty string,
                // this means that no user password required
                null, OWNER_PASSWORD.getBytes(), EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA));

        PdfDocument pdfDoc = new PdfDocument(pdfReader, pdfWriter);
        pdfDoc.close();
    }
}