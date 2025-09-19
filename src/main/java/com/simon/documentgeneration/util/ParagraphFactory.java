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
@Getter
@Setter
public class ParagraphFactory {

    private static float singleInterval = 1.2f;
    private Paragraph paragraph;
    private Paragraph lineFootnote;
    private List<Paragraph> paragraphs;
    private PdfPTable tableForFretboard;
    private PdfPTable tableForFooter;

    public PdfPTable getFretboard(Chunk[] fretboard, TablesWarehouse tables) throws DocumentException {

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
        tableForFretboard.setSpacingAfter(15);

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

            switch (chunk.getContent()) {
                case "FootnotePage1":
                    paragraphs.add(lineFootnote);
                    paragraphs.add(footnotes.getFOOTNOTES_PAGE_1());
                    continue;
                case "FootnotePage2":
                    lineFootnote.setSpacingBefore(20);
                    paragraphs.add(lineFootnote);
                    paragraphs.add(footnotes.getFOOTNOTES_PAGE_2());
                    continue;
                case "FootnotePage10":
                    paragraphs.add(lineFootnote);
                    paragraphs.add(footnotes.getFOOTNOTES_PAGE_10());
                case "UnderLineText1":
                    paragraphs.add(underLineText.getUNDER_LINE_TEXT1());
                    continue;
                case "UnderLineText2":
                    paragraphs.add(underLineText.getUNDER_LINE_TEXT2());
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

    public PdfPTable getFooterParagraph(Chunk[] footer, TablesWarehouse tableFooter) throws DocumentException {

        tableForFooter = tableFooter.getTableFooter();

        PdfPCell cell;

        for (int i = 0; i < 33; i++) {

            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);

            switch (i) {

                case 0:
                    //мировой судья ...
                    paragraph = new Paragraph(footer[0]);
                    paragraph.setLeading(footer[0].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 2:
                    //ФИО мирового судьи
                    paragraph = new Paragraph(footer[2]);
                    paragraph.setLeading(footer[2].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 6:
                    //мировой судья организатор...
                    paragraph = new Paragraph(footer[3]);
                    paragraph.setLeading(footer[3].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    break;

                case 8:
                    //ФИО мирового судьи организатора
                    paragraph = new Paragraph(footer[5]);
                    paragraph.setLeading(footer[5].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 12:
                    //Начальник отдела правового...
                    paragraph = new Paragraph(footer[6]);
                    paragraph.setLeading(footer[6].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 14:
                    //ФИО Начальник отдела правового
                    paragraph = new Paragraph(footer[8]);
                    paragraph.setLeading(footer[8].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 1:
                case 7:
                case 13:
                case 25:
                    //подчеркивание
                    paragraph = new Paragraph(footer[4]);
                    paragraph.setLeading(footer[4].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 26:
                    //подчеркивание
                    paragraph = new Paragraph(footer[4]);
                    paragraph.setLeading(footer[4].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    break;

                case 4:
                case 10:
                case 16:
                case 28:
                    //"(подпись)"
                    paragraph = new Paragraph(footer[7]);
                    paragraph.setLeading(footer[7].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_TOP);
                    break;

                case 5:
                case 11:
                case 17:
                case 29:
                    //"(инициалы, фамилия)"
                    paragraph = new Paragraph(footer[9]);
                    paragraph.setLeading(footer[9].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell.setVerticalAlignment(Element.ALIGN_TOP);
                    cell.addElement(paragraph);
                    break;

                case 3:
                case 9:
                    //"(должность непосредственного руководителя гражданского служащего)"
                    paragraph = new Paragraph(footer[1]);
                    paragraph.setLeading(footer[1].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    cell.setVerticalAlignment(Element.ALIGN_TOP);
                    break;

                case 15:
                case 19:
                case 20:
                case 22:
                case 23:
                case 31:
                case 32:
                    //пустые ячейки
                    break;

                case 21:
                    //С должностным регламентом ознакомлен:
                    paragraph = new Paragraph(footer[11]);
                    paragraph.setLeading(footer[11].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    break;

                case 24:
                    paragraph = new Paragraph(footer[13]);
                    paragraph.setLeading(footer[13].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    break;

                case 27:
                    //"(должность гражданского служащего)"
                    paragraph = new Paragraph(footer[12]);
                    paragraph.setLeading(footer[12].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    break;

                case 18:
                case 30:
                    paragraph = new Paragraph(footer[10]);
                    paragraph.setLeading(footer[10].getFont().getSize() * 1.2f);
                    paragraph.setSpacingBefore(6);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    cell.addElement(paragraph);
                    break;

                default:
                    throw new RuntimeException("В таблице нет ячейки под номером: " + i);
            }

            tableForFooter.addCell(cell);
        }

        return tableForFooter;
    }
}