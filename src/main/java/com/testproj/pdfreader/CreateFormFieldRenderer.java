package com.testproj.pdfreader;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class CreateFormFieldRenderer extends CellRenderer {
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