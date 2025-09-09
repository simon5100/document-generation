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
    private final TablesWarehouse tables;

    @Override
    public byte[] generatedDocument() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Chunk[] chunksFretboard =  chunks.getChunkFretboard(font);
            Chunk chunkTitle = chunks.getChunkTitle(font);
            Chunk[] chunksSection = chunks.getChunkSection(font);

            PdfPTable tableFretboard = paragraphFactory.getFretboard(chunksFretboard, tables);
            Paragraph title = paragraphFactory.getTitle(chunkTitle);

            Document document = new Document(PageSize.A4,
                    pointsInCM.cm(2.5F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(2F));

            PdfWriter writer = PdfWriter.getInstance(document, baos);
            writer.setPageEvent(header);
            document.open();
            document.add(tableFretboard);
            document.add(title);

            for(int i = 0; i < chunksSection.length; i++) {
                Paragraph section = paragraphFactory.getSection(chunksSection[i]);
                document.add(section);

                List<Paragraph> paragraphs = paragraphFactory.getDefaultParagraph(
                        chunks.getChunksParagraph(
                                i,
                                font.getNormalFont(),
                                font.getNormalBoldFont(),
                                font.getSmallFont(),
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

            PdfPTable tableFooter = paragraphFactory.getFooterParagraph(
                    chunks.getFooter(font.getNormalFont(), font.getSmallFont(), font.getNormalUNnderLineFont())
            );

            document.add(tableFooter);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}