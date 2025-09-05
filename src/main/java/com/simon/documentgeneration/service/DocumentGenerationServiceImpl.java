package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.util.Chunks;
import com.simon.documentgeneration.util.ConvertPointsInCM;
import com.simon.documentgeneration.util.FontFactoryCustom;
import com.simon.documentgeneration.util.ParagraphFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final ConvertPointsInCM pointsInCM;
    private final FontFactoryCustom font;
    private final ParagraphFactory paragraphFactory;
    private final Chunks chunks;

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
            PdfWriter.getInstance(document, baos);
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

            for(int i = 0; i < sections.length - 8; i++) {
                Paragraph section = paragraphFactory.getSection(sections[i]);
                document.add(section);
                List<Paragraph> paragraphs = paragraphFactory.getDefaultParagraph(
                        chunks.getChunksParagraph(
                                i,
                                font.getNormalFont(),
                                font.getNormalBoldFont(),
                                font.getNormalSmallFont(),
                                font.getFootnoteFont()
                        ),
                        pointsInCM.cm(1.25f)
                );

                for (Paragraph paragraph : paragraphs) {
                    document.add(paragraph);
                }
            }

            Chunk chunk = new Chunk("________________________", new Font(Font.FontFamily.TIMES_ROMAN));

            Paragraph snoska = new Paragraph(chunk);
            snoska.setAlignment(Element.ALIGN_LEFT);
            snoska.setSpacingAfter(6);

            document.add(snoska);

            Paragraph paragraphSnoska = new Paragraph();
            paragraphSnoska.setAlignment(Element.ALIGN_LEFT);
            paragraphSnoska.setLeading(10f * 1.2f);

            Phrase phrase = new Phrase();

            chunk = new Chunk("1", new Font(Font.FontFamily.TIMES_ROMAN, 8));
            chunk.setTextRise(5);
            phrase.add(chunk);
            chunk = new Chunk(
                    " В соответствии с Реестром должностей государственной гражданской службы Новосибирской области " +
                    "(приложение к Закону Новосибирской области от 06.04.2005 № 287-ОЗ «О Реестре должностей государственной " +
                    "гражданской службы Новосибирской области»).\n",
                    font.getNormalSmallFont());
            phrase.add(chunk);

            chunk = new Chunk("2", new Font(Font.FontFamily.TIMES_ROMAN, 8));
            chunk.setTextRise(5);
            phrase.add(chunk);
            chunk = new Chunk(
                    " В соответствии со справочником квалификационных требований к специальностям, направлениям подготовки (к " +
                    "укрупненным группам специальностей и направлений подготовки), к профессиональному уровню, которые " +
                            "необходимы для замещения должностей государственной гражданской службы, с учетом области и вида " +
                            "профессиональной служебной деятельности государственных гражданских служащих, предусмотренным частью 8 " +
                            "статьи 12 Федерального закона от 27.07.2004 № 79-ФЗ «О государственной гражданской службе Российской " +
                            "Федерации» (далее – Справочник).\n", font.getNormalSmallFont());
            phrase.add(chunk);

            chunk = new Chunk("3", new Font(Font.FontFamily.TIMES_ROMAN, 8));
            chunk.setTextRise(5);
            phrase.add(chunk);
            chunk = new Chunk(" В соответствии со Справочником.", font.getNormalSmallFont());
            phrase.add(chunk);

            paragraphSnoska.add(phrase);

            document.add(paragraphSnoska);


            document.setPageSize(PageSize.A4);
            document.setMargins(pointsInCM.cm(2.5F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(2F),
                    pointsInCM.cm(2F));
            document.newPage();

            document.add(new Paragraph("osidjfgoisjg", font.getNormalFont()));


            document.setPageSize(PageSize.A4);
            document.setMargins(pointsInCM.cm(2.5F),
                    pointsInCM.cm(1F),
                    pointsInCM.cm(2F),
                    pointsInCM.cm(2F));
            document.newPage();





            document.add(new Paragraph("osidjfgoisjg", font.getNormalFont()));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
