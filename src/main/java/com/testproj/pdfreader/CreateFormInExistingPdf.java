package com.testproj.pdfreader;


import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.io.File;

public class CreateFormInExistingPdf {
    public static final String DEST = "./target/sandbox/acroforms/create_form_in_table.pdf";
    public static final String SRC = "./src/main/resources/pdfs/blank.pdf";
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new CreateFormInExistingPdf().manipulatePdf(DEST);
    }

    protected static void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));

        Document doc = new Document(pdfDoc);
        pdfDoc.addNewPage();
        doc.add(new AreaBreak(AreaBreakType.LAST_PAGE));
        doc.add(new Paragraph("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n"));

        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        doc.add(new Paragraph("Please Fill below form"));

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        Cell cell = new Cell().add(new Paragraph("Name:"));
        table.addCell(cell);

        cell = new Cell();
        cell.setNextRenderer(new CreateFormFieldRenderer(cell, "name1"));
        table.addCell(cell);

        cell = new Cell().add(new Paragraph("Address"));
        table.addCell(cell);

        cell = new Cell();
        cell.setNextRenderer(new CreateFormFieldRenderer(cell, "address"));
        table.addCell(cell);

        cell = new Cell().add(new Paragraph("Phone Number"));
        table.addCell(cell);

        cell = new Cell();
        cell.setNextRenderer(new CreateFormFieldRenderer(cell, "phoneNumber"));
        table.addCell(cell);


        cell = new Cell().add(new Paragraph("Email"));
        table.addCell(cell);

        cell = new Cell();
        cell.setNextRenderer(new CreateFormFieldRenderer(cell, "email"));
        table.addCell(cell);

        doc.add(table);

        doc.close();
    }



}
