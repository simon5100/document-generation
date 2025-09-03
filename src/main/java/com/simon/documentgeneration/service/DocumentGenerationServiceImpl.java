package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.util.ConvertPointsInCM;
import com.simon.documentgeneration.util.FontFactoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final ConvertPointsInCM pointsInCM;
    private final FontFactoryCustom font;


    @Override
    public byte[] generatedDocument2() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            //создаем документ и настраиваем страницу
            Document document = new Document(PageSize.A4,
                    pointsInCM.cm(2.5F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(2F),
                    pointsInCM.cm(2F));
            //делаем поток для записи
            PdfWriter.getInstance(document, baos);
            //открывем документ для записи
            document.open();

            PdfPTable pdfPTable = new PdfPTable(2);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new int[]{40, 60});
            pdfPTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell leftCell = new PdfPCell(new Paragraph(" "));
            leftCell.setBorder(Rectangle.NO_BORDER);
            pdfPTable.addCell(leftCell);

            Paragraph paragraph1 = new Paragraph("УТВЕРЖДАЮ\n" +
                    "Начальник управления по обеспечению\n" +
                    "деятельности мировых судей\n" +
                    "Новосибирской области\n" +
                    "\n" +
                    "_________________ А.В. Артеменко\n" +
                    "(подпись)                    \n" +
                    "«____» ___________ 202____ г.\n", font.getNormalFont());
            paragraph1.setSpacingAfter(1f);

            PdfPCell rightCell = new PdfPCell(paragraph1);
            rightCell.setBorder(Rectangle.NO_BORDER);
            rightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(rightCell);

            document.add(pdfPTable);
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
