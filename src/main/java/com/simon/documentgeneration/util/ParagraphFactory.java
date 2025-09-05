package com.simon.documentgeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParagraphFactory {

    private static float singleInterval = 1.2f;
    private Paragraph paragraph;
    private Paragraph line;
    private List<Paragraph> paragraphs;
    private PdfPTable tableForFretboard;
    private PdfPCell cellForFretboard;




    public PdfPTable getFretboard(Chunk[] fretboard) throws DocumentException {

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
            cellForFretboard.addElement(paragraph);
        }

        tableForFretboard.addCell(cellForFretboard);

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

    public List<Paragraph> getDefaultParagraph(List<Chunk> chunks, float indentCM) {
        paragraphs = new LinkedList<>();

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setFirstLineIndent(indentCM);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        line = new Paragraph();
        line.setAlignment(Element.ALIGN_LEFT);
        line.setSpacingAfter(6);

        for (Chunk chunk : chunks) {
            paragraph.setLeading(chunk.getFont().getSize() * singleInterval);
            if (chunk.getContent().lastIndexOf("______") != -1) {
                line.add(chunk);
                continue;
            }

            paragraph.add(chunk);
            if (chunk.getContent().lastIndexOf("\n") != -1) {
                paragraphs.add(paragraph);

                paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(indentCM);
                paragraph.setSpacingAfter(0);
                paragraph.setSpacingBefore(0);
            }
        }

        return paragraphs;
    }

    public Paragraph getInterlinearParagraph(String text, Font font) {
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
