package com.testproj.pdfreader.pdfPermission;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.File;
import java.io.IOException;

public class PdfFieldHandler {

    public static final String SRC = "./src/main/resources/pdfs/form.pdf";

    public static final String DEST = "./target/sandbox/security/formfilled.pdf";


    public static void main(String[] args) {
        try {

            File file = new File(DEST);
            file.getParentFile().mkdirs();

            // Load the existing PDF document
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(SRC), new PdfWriter(file));

            // Get the AcroForm from the PDF document
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);

            // Check if the fields exist
            PdfFormField nameField = form.getField("Name");
            PdfFormField signatureField = form.getField("signature");

            if (nameField != null && signatureField != null) {
                // Fields exist, fill them
                nameField.setValue("John Doe");
                signatureField.setValue("Signature Image Here"); // You can add the signature image here

                // Optionally flatten the form fields if needed
                form.flattenFields();
            } else {
                // Fields do not exist, add a paragraph to another page
                PdfPage newPage = pdfDocument.addNewPage();
                PdfCanvas canvas = new PdfCanvas(newPage);
                canvas.beginText().setFontAndSize(PdfFontFactory.createFont(), 12)
                        .moveText(50, pdfDocument.getDefaultPageSize().getTop() - 50)
                        .showText("An issue occurred. Fields not found.")
                        .endText();
            }

            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
