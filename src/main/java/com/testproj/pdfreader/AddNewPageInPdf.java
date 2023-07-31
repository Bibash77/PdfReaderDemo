package com.testproj.pdfreader;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewPageInPdf {
    public static final String SRC = "./src/main/resources/pdfs/form.pdf";
    public static final String DEST = "./target/sandbox/acroforms/newpage.pdf";

    public static void main(String[] args) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        int index = pdfDoc.getNumberOfPages();
        PageSize ps = new PageSize(pdfDoc.getFirstPage().getPageSize());
        pdfDoc.addNewPage(index + 1, ps);
        pdfDoc.close();
    }
}
