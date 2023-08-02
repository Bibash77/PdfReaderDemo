package com.testproj.pdfreader.learnday1;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloWorldTestPdf {


    public static final String DEST = "./target/sandbox/acroforms/helloworld.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HelloWorldTestPdf().createPdf(DEST);
    }

    public void createPdf(String destination) throws IOException {

        // initialize pdf writer
        PdfWriter pdfWriter = new PdfWriter(destination);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(pdfWriter);


        Document document = new Document(pdf);

        PdfFont pdfFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        document.add(new Paragraph("hello world"));
        document.close();
    }
}
