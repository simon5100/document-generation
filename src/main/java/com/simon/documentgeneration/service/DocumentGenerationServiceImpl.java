package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import com.simon.documentgeneration.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final FootnoteWarehouse footnotes;
    private final ConvertPointsInCentimetr pointsInCM;
    private final FontFactoryCustom font;
    private final ParagraphFactory paragraphFactory;
    private final Chunks chunks;
    private final HeaderNumberPage header;
    private final UnderLineTextWarehouse underLineText;
    private final TablesWarehouse tables;
    private final Declension declension;

    private HttpHeaders headers;
    private byte[] documentBytes;
    private HashMap<HttpHeaders, byte[]> documentContainer;

    @Override
    public HashMap<HttpHeaders, byte[]> generatedDocument(DocumentJobRegulationRequest request) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            String wordGenitiveCase = declension.getGenitiveCase(request.getDistrictName());
            int sectionNumber = request.getSectionNumber();

            Chunk[] chunksFretboard =  chunks.getChunkFretboard(font);
            Chunk chunkTitle = chunks.getChunkTitle(font, wordGenitiveCase.toUpperCase());
            Chunk[] chunksSection = chunks.getChunkSection(font);
            Chunk[] chunksFooter = chunks.getFooter(font, request);

            PdfPTable tableFretboard = paragraphFactory.getFretboard(chunksFretboard, tables);
            Paragraph title = paragraphFactory.getTitle(chunkTitle);
            PdfPTable tableFooter = paragraphFactory.getFooterParagraph(chunksFooter, tables);


            Document document = new Document(PageSize.A4,
                    pointsInCM.centimetr(2.5F),
                    pointsInCM.centimetr(1F),
                    pointsInCM.centimetr(1F),
                    pointsInCM.centimetr(2F));

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
                                font.getNormalUNnderLineFont(),
                                sectionNumber,
                                wordGenitiveCase
                        ),
                        pointsInCM.centimetr(1.25f),
                        footnotes,
                        font,
                        underLineText
                );

                for (Paragraph paragraph : paragraphs) {
                    document.add(paragraph);
                }
            }

            document.add(tableFooter);
            document.close();
            header.setNumberPage(1);
            log.info(baos.toByteArray().length);

            documentBytes = baos.toByteArray();

            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.inline()
                            .filename("Dolznostnoi reglament CCZ .pdf")
                            .build());
            headers.setContentLength(documentBytes.length);

            documentContainer = new HashMap<>();
            documentContainer.put(headers, documentBytes);

            return documentContainer;
        } catch (Exception e) {
            throw new RuntimeException("Непредвиденная ошибка");
        }
    }
}