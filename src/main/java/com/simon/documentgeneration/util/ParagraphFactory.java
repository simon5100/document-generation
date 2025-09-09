package com.simon.documentgeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParagraphFactory {

    private static float singleInterval = 1.2f;
    private Paragraph paragraph;
    private Paragraph lineFootnote;
    private List<Paragraph> paragraphs;
    private PdfPTable tableForFretboard;
    private PdfPCell cellForFretboard;

    public PdfPTable getFretboard(Chunk[] fretboard, TablesWarehouse tables) throws DocumentException {

//        tableForFretboard = new PdfPTable(2);
//        tableForFretboard.setWidthPercentage(100);
//        tableForFretboard.setWidths(new int[]{40, 60});
//        tableForFretboard.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//
//        cellForFretboard = new PdfPCell(new Phrase(" "));
//        cellForFretboard.setBorder(Rectangle.NO_BORDER);
//        tableForFretboard.addCell(cellForFretboard);
//
//        cellForFretboard = new PdfPCell();
//        cellForFretboard.setBorder(Rectangle.NO_BORDER);
//        cellForFretboard.setHorizontalAlignment(Element.ALIGN_CENTER);

        tableForFretboard = tables.getTableForFretboard();

        for (Chunk chunk : fretboard) {
            paragraph = new Paragraph();
            paragraph.add(chunk);
            paragraph.setLeading(chunk.getFont().getSize() * singleInterval);
            paragraph.setSpacingBefore(0);
            paragraph.setSpacingAfter(0);
            if (chunk.getFont().getSize() == 14f) {
                paragraph.setAlignment(Element.ALIGN_CENTER);
            } else {
                paragraph.setIndentationLeft(95);
            }
            tables.getCell().addElement(paragraph);
        }

        tableForFretboard.addCell(tables.getCell());

        return tableForFretboard;
    }

    public Paragraph getTitle (Chunk title) {

        paragraph = new Paragraph(title);
        paragraph.setLeading(title.getFont().getSize() * singleInterval);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(8);
        paragraph.setSpacingAfter(0);
        return paragraph;
    }

    public Paragraph getSection(Chunk section)  {

        paragraph = new Paragraph(section);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(section.getFont().getSize() * singleInterval);
        paragraph.setSpacingAfter(8);
        paragraph.setSpacingBefore(8);

        return paragraph;
    }

    public List<Paragraph> getDefaultParagraph(List<Chunk> chunks, float indentCM, FootnoteWarehouse footnotes, FontFactoryCustom font, UnderLineTextWarehouse underLineText) {
        paragraphs = new ArrayList<>();

        lineFootnote = new Paragraph(footnotes.getLineFootnote());
        lineFootnote.setAlignment(Element.ALIGN_LEFT);
        lineFootnote.setSpacingAfter(6);
        lineFootnote.setSpacingBefore(6);


        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setFirstLineIndent(indentCM);
        paragraph.setLeading(font.getNORMAl_SIZE() * singleInterval);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        for (Chunk chunk : chunks) {

            if (chunk.getContent().equals("FootnotePage1")) {
                paragraphs.add(lineFootnote);
                paragraphs.add(footnotes.getFootnotePage1(font));
                continue;
            } else if (chunk.getContent().equals("FootnotePage2")){
                lineFootnote.setSpacingBefore(20);
                paragraphs.add(lineFootnote);
                paragraphs.add(footnotes.getFootnotePage2(font));
                continue;
            } else if (chunk.getContent().equals("FootnotePage10")) {
                paragraphs.add(lineFootnote);
                paragraphs.add(footnotes.getFootnotePage10(font));
                continue;
            }

            if (chunk.getContent().equals("UnderLineText1")) {
                paragraphs.add(underLineText.getUnderLineText1(font.getSmallFont()));
                continue;
            } else if (chunk.getContent().equals("UnderLineText2")) {
                paragraphs.add(underLineText.getUnderLineText2(font.getSmallFont()));
                continue;
            }

            paragraph.add(chunk);

            if (chunk.getContent().lastIndexOf("\n") != -1) {
                paragraphs.add(paragraph);

                paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(indentCM);
                paragraph.setLeading(font.getNORMAl_SIZE() * singleInterval);
                paragraph.setSpacingAfter(0);
                paragraph.setSpacingBefore(0);

            }
        }
        return paragraphs;
    }

    public PdfPTable getFooterParagraph(Chunk[] fretboard) throws DocumentException {

        tableForFretboard = new PdfPTable(3);
        tableForFretboard.setWidthPercentage(100);
        tableForFretboard.setWidths(new int[]{55, 25, 35});
        tableForFretboard.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        for (int i = 0; i < fretboard.length; i+=3) {
            paragraph = new Paragraph(fretboard[i]);
            paragraph.setLeading(fretboard[i].getFont().getSize() * 1.2f);
            paragraph.setSpacingBefore(6);

            PdfPCell cell1 = new PdfPCell(paragraph);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            paragraph = new Paragraph(fretboard[i+1]);
            PdfPCell cell2 = new PdfPCell(paragraph);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);

            paragraph = new Paragraph(fretboard[i+2]);
            PdfPCell cell3 = new PdfPCell(paragraph);
            cell3.setBorder(Rectangle.NO_BORDER);
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setVerticalAlignment(Element.ALIGN_BOTTOM);

            tableForFretboard.addCell(cell1);
            tableForFretboard.addCell(cell2);
            tableForFretboard.addCell(cell3);

        }
        return tableForFretboard;
    }
}
