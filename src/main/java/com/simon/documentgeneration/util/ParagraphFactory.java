package com.simon.documentgeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParagraphFactory {

    private static float singleInterval = 1.2f;
    private Paragraph paragraph;
    private PdfPTable tableForFretboard;
    private PdfPCell cellForFretboard;




    public PdfPTable getFretboard(String textMain1, String textInterlinear, String textMain2, Font fontMain, Font fontInterlinear) throws DocumentException {

        tableForFretboard = new PdfPTable(2);
        tableForFretboard.setWidthPercentage(100);
        tableForFretboard.setWidths(new int[]{40, 60});
        tableForFretboard.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        cellForFretboard = new PdfPCell(new Phrase(" "));
        cellForFretboard.setBorder(Rectangle.NO_BORDER);
        tableForFretboard.addCell(cellForFretboard);

        cellForFretboard = new PdfPCell();
        cellForFretboard.setBorder(Rectangle.NO_BORDER);
        cellForFretboard.setHorizontalAlignment(Element.ALIGN_CENTER);

        paragraph = new Paragraph(textMain1, fontMain);
        paragraph.setLeading(fontMain.getSize() * singleInterval);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);

        cellForFretboard.addElement(paragraph);

        paragraph = new Paragraph(textInterlinear, fontInterlinear);
        paragraph.setLeading(fontMain.getSize() * singleInterval);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);
        paragraph.setIndentationLeft(95);

        cellForFretboard.addElement(paragraph);

        paragraph = new Paragraph(textMain2, fontMain);
        paragraph.setLeading(fontMain.getSize() * singleInterval);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);

        cellForFretboard.addElement(paragraph);

        tableForFretboard.addCell(cellForFretboard);

        return tableForFretboard;

    }


    public Paragraph getHeading(String text, Font font)  {

        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setSpacingAfter(8);
        paragraph.setSpacingBefore(8);

        return paragraph;
    }

    public Paragraph getDefaultParagraph(String text, Font font, float indentCM) {
        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setFirstLineIndent(indentCM);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        return paragraph;
    }

    public Paragraph getinterlinearParagraph(String text, Font font) {
        paragraph = new Paragraph(text, font);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        return paragraph;
    }


    public Paragraph getEmptyParagraph() {
        paragraph = new Paragraph(" ");
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        return paragraph;
    }





}
