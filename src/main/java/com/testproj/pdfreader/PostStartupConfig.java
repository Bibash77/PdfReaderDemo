package com.testproj.pdfreader;

import com.itextpdf.kernel.pdf.PdfDocument;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PostStartupConfig {


    @PostConstruct
    public void readPdf() {
        System.out.println("----------executing function readPdf()------------");

//        PdfDocument pdfDocument = new PdfDocument(new File(""));
    }
}
