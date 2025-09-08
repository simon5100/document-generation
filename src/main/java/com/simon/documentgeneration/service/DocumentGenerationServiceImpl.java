package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final FootnoteWarehouse footnotes;
    private final ConvertPointsInCM pointsInCM;
    private final FontFactoryCustom font;
    private final ParagraphFactory paragraphFactory;
    private final Chunks chunks;
    private final HeaderNumberPage header;
    private final UnderLineTextWarehouse underLineText;

    @Override
    public byte[] generatedDocument2() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            //создаем документ и настраиваем страницу
            Document document = new Document(PageSize.A4,
                    pointsInCM.cm(2.5F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(2F));
            //делаем поток для записи
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            writer.setPageEvent(header);
            //открывем документ для записи

            document.open();

            PdfPTable pdfPTable = paragraphFactory.getFretboard(
                    chunks.getChunkFretboard(font.getNormalFont(), font.getNormalSmallFont())
            );
            document.add(pdfPTable);
            Paragraph title = paragraphFactory.getTitle(
                    chunks.getChunkTitle(font.getNormalBoldFont())
            );
            document.add(title);

            Chunk[] sections = chunks.getChunkSection(font.getNormalBoldFont());

            for(int i = 0; i < sections.length; i++) {
                Paragraph section = paragraphFactory.getSection(sections[i]);
                document.add(section);
                List<Paragraph> paragraphs = paragraphFactory.getDefaultParagraph(
                        chunks.getChunksParagraph(
                                i,
                                font.getNormalFont(),
                                font.getNormalBoldFont(),
                                font.getNormalSmallFont(),
                                font.getNormalUNnderLineFont()
                        ),
                        pointsInCM.cm(1.25f),
                        footnotes,
                        font,
                        underLineText
                );

                for (Paragraph paragraph : paragraphs) {
                    document.add(paragraph);
                }
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
