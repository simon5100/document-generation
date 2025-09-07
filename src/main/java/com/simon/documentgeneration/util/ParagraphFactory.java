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
    private Paragraph lineFootnote;
    private Paragraph paragraphFootnote;
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

        lineFootnote = new Paragraph();
        lineFootnote.setAlignment(Element.ALIGN_LEFT);
        lineFootnote.setSpacingBefore(6);
        lineFootnote.setSpacingAfter(6);

        paragraphFootnote = new Paragraph();
        paragraphFootnote.setAlignment(Element.ALIGN_LEFT);
        paragraphFootnote.setSpacingAfter(0);
        paragraphFootnote.setSpacingBefore(0);



        for (Chunk chunk : chunks) {
            paragraph.setLeading(chunk.getFont().getSize() * singleInterval);


            if (chunk.getContent().lastIndexOf("______") != -1) {
                lineFootnote.add(chunk);

                paragraphs.add(lineFootnote);
                continue;
            } else if (chunk.getFont().getSize() == 8 ||
                      (chunk.getContent().lastIndexOf("\n") != -1 &&
                       chunk.getFont().getSize() == 10)){
                paragraphFootnote.setLeading(chunk.getFont().getSize() * singleInterval);
                paragraphFootnote.add(chunk);
            }


            paragraph.add(chunk);
            if (chunk.getContent().lastIndexOf("\n") != -1 &&
                    chunk.getFont().getSize() == 10) {

                paragraphs.add(paragraphFootnote);

                paragraphFootnote = new Paragraph();
                paragraphFootnote.setAlignment(Element.ALIGN_LEFT);
                paragraphFootnote.setSpacingAfter(0);
                paragraphFootnote.setSpacingBefore(0);

                paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setFirstLineIndent(indentCM);
                paragraph.setSpacingAfter(0);
                paragraph.setSpacingBefore(0);

            } else if (chunk.getContent().lastIndexOf("\n") != -1) {
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
