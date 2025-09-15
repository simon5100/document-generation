package com.simon.documentgeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TablesWarehouse {

    private PdfPTable table;
    private PdfPCell cell;

    public PdfPTable getTableForFretboard() throws DocumentException {

        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{40, 60});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        cell = new PdfPCell(new Phrase(" "));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        return table;
    }

    public PdfPTable getTableFooter() throws DocumentException {

        table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{55, 25, 35});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        return table;
    }
}