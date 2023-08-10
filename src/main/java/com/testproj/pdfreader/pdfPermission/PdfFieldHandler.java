package com.testproj.pdfreader.pdfPermission;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfFieldHandler {

    public static final String SRC = "./src/main/resources/pdfs/form.pdf";
    public static final String DEST = "./target/sandbox/security/formfilled.pdf";

    public static void main(String[] args) {
        try {
            File file = new File(DEST);
            file.getParentFile().mkdirs();

            PdfDocument pdfDocument = new PdfDocument(new PdfReader(SRC), new PdfWriter(file));

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);

            List<String> invalidFormFields = new ArrayList<>();
            checkAndFillField(form, "ManagerSignature", "sk", invalidFormFields);
            checkAndFillField(form, "ManagerSignatureDate", "12/12/2022", invalidFormFields);
            checkAndFillField(form, "ClientSignature2", "cs", invalidFormFields);
            checkAndFillField(form, "ClientSignature Date2", "cd", invalidFormFields);
            checkAndFillField(form, "characteristicsTextBox2", "12/12/2022", invalidFormFields);
            checkAndFillField(form, "characteristicsTextBox3", "12/12/2022", invalidFormFields);
            checkAndFillField(form, "Level2_Internal", "12/12/2022", invalidFormFields);

            if (!invalidFormFields.isEmpty()) {
                addErrorMessagePage(pdfDocument, invalidFormFields);
            }

            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkAndFillField(PdfAcroForm form, String fieldName, String value, List<String> invalidFields) {
        PdfFormField field = form.getField(fieldName);
        if (field != null) {
            field.setValue(value);
        } else {
            invalidFields.add(fieldName);
        }
    }

    private static void addErrorMessagePage(PdfDocument pdfDocument, List<String> invalidFormFields) throws IOException {
        PdfPage newPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(newPage);

        float x = 50;
        float y = pdfDocument.getDefaultPageSize().getTop() - 50;
        float fontSize = 12;
        float lineHeight = 14; // Adjust this value to control line height

        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(), fontSize)
                .moveText(x, y);

        String errorMessage = "An issue occurred. The following fields have errors:";
        canvas.showText(errorMessage);
        canvas.newlineShowText(errorMessage);

        for (String invalidField : invalidFormFields) {
            canvas.newlineShowText("• " + invalidField); // Bullet-point list item
        }

        canvas.endText();
    }




}
