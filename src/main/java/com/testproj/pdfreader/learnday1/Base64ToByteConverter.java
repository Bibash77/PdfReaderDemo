package com.testproj.pdfreader.learnday1;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Base64ToByteConverter {

    private byte[] convertFileToPdf(byte[] fileContent) throws Exception {
        // Create a new PDF document using iText
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        // Convert the file content to a PDF
        String fileContentStr = new String(fileContent);
        document.add(new Paragraph(fileContentStr));

        // Close the document
        document.close();

        return outputStream.toByteArray();
    }
}
