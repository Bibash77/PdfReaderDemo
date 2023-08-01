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

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));

        Document doc = new Document(pdfDoc);

        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

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


    private class CreateFormFieldRenderer extends CellRenderer {
        protected String fieldName;

        public CreateFormFieldRenderer(Cell modelElement, String fieldName) {
            super(modelElement);
            this.fieldName = fieldName;
        }
        @Override
        public IRenderer getNextRenderer() {
            return new CreateFormFieldRenderer((Cell) modelElement, fieldName);
        }

        @Override
        public void draw(DrawContext drawContext) {
            super.draw(drawContext);

            PdfTextFormField field = new TextFormFieldBuilder(drawContext.getDocument(), fieldName)
                    .setWidgetRectangle(getOccupiedAreaBBox()).createText();
            field.setValue("");
            PdfAcroForm form = PdfAcroForm.getAcroForm(drawContext.getDocument(), true);
            form.addField(field);
        }
    }
}
