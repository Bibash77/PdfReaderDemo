package com.testproj.pdfreader;


import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;

public class AddContentToExistingPDF {
    public static final String SRC = "./src/main/resources/pdfs/existing.pdf";
    public static final String DEST = "./target/test/existingModified.pdf";

    public static void main(String[] args) {
        try {
            File file = new File(DEST);
            file.getParentFile().mkdirs();

            manipulatePdf(DEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void manipulatePdf(String dest) throws IOException {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest))) {
            // Add a second page
            pdfDoc.addNewPage();

            // Access the second page
            PdfPage secondPage = pdfDoc.getPage(2);

            // Create a Document instance for the second page
            Document secondPageDoc = new Document(pdfDoc);

            // Add content to the second page
            secondPageDoc.add(new Paragraph("This is the content of the second page."));

            // Close the PDF document
            secondPageDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
