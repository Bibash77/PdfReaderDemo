package com.testproj.pdfreader.learnday1;


import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
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

public class LatestPdfLineTest {
    public static final String DEST = "./target/sandbox/acroforms/create_form_in_table.pdf";
    public static final String SRC = "./src/main/resources/pdfs/2page.pdf";
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new LatestPdfLineTest().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        // Create a new page
        PageSize ps = new PageSize(pdfDoc.getFirstPage().getPageSize());
        pdfDoc.addNewPage();

        // Go to the new page and add content
        doc.add(new AreaBreak(AreaBreakType.LAST_PAGE)); // Go to the last added page
        doc.add(new Paragraph("Please Fill below form"));
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
