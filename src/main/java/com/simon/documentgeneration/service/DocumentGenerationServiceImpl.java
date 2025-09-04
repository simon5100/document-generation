package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.util.ConvertPointsInCM;
import com.simon.documentgeneration.util.FontFactoryCustom;
import com.simon.documentgeneration.util.ParagraphFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final ConvertPointsInCM pointsInCM;
    private final FontFactoryCustom font;
    private final ParagraphFactory paragraphFactory;


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
                    "УТВЕРЖДАЮ\n" +
                            "Начальник управления по обеспечению\n" +
                            "деятельности мировых судей\n" +
                            "Новосибирской области\n" +
                            "\n" +
                            "          ______________  А.В. Артеменко",
                    "(подпись)",
                    "«      »            202      г.",
                    font.getNormalFont(),
                    font.getNormalSmallFont()
                    );

            document.add(pdfPTable);

            Paragraph title = paragraphFactory.getHeading("ДОЛЖНОСТНОЙ РЕГЛАМЕНТ\n" +
                    "СЕКРЕТАРЯ СУДЕБНОГО ЗАСЕДАНИЯ АППАРАТА МИРОВЫХ СУДЕЙ\n" +
                    "ЖЕЛЕЗНОДОРОЖНОГО СУДЕБНОГО РАЙОНА УПРАВЛЕНИЯ ПО\n" +
                    "ОБЕСПЕЧЕНИЮ ДЕЯТЕЛЬНОСТИ МИРОВЫХ СУДЕЙ\n" +
                    "НОВОСИБИРСКОЙ ОБЛАСТИ", font.getNormalBoldFont());

            document.add(title);

            Paragraph heading = paragraphFactory.getHeading(
                    "I. Общие положения",
                    font.getNormalBoldFont()
            );

            document.add(heading);

            Paragraph paragraph4 = paragraphFactory.getDefaultParagraph(
                    "1. Должность государственной гражданской службы Новосибирской области " +
                            "(далее – гражданская служба): ",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );
            paragraph4.add(new Phrase ("секретарь судебного заседания аппарата " +
                    "мировых судей Железнодорожного судебного района ", font.getNormalBoldFont()));
            paragraph4.add(new Phrase ("(далее – секретарь " +
                    "судебного заседания) относится к старшей группе должностей гражданской " +
                    "службы категории «специалисты».", font.getNormalFont()));

            document.add(paragraph4);

            Paragraph paragraph5 = paragraphFactory.getDefaultParagraph(
                    "Регистрационный номер (код) должности",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );

            Chunk chunk = new Chunk("1", font.getNormalSmallFont());
            chunk.setTextRise(5);

            paragraph5.add(chunk);

            paragraph5.add(new Phrase (" – 03-3-4-002-54.", font.getNormalFont()));

            document.add(paragraph5);

            Paragraph paragraph6 = paragraphFactory.getDefaultParagraph(
                    "2. Область профессиональной служебной деятельности государственного " +
                            "гражданского служащего Новосибирской области",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );

            chunk = new Chunk("2", font.getNormalSmallFont());
            chunk.setTextRise(5);

            paragraph6.add(chunk);
            paragraph6.add(new Phrase (" (далее – гражданский служащий): организация судопроизводства.",
                    font.getNormalFont()));

            document.add(paragraph6);

            Paragraph paragraph7 = paragraphFactory.getDefaultParagraph(
                    "3. Вид профессиональной служебной деятельности гражданского служащего",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );
            chunk = new Chunk("3", font.getNormalSmallFont());
            chunk.setTextRise(5);
            paragraph7.add(chunk);
            paragraph7.add(new Phrase (
                    ": организационно – правовое обеспечение деятельности судов.",
                    font.getNormalFont()
            ));

            document.add(paragraph7);

            Paragraph paragraph8 = paragraphFactory.getDefaultParagraph(
                    "4. Назначение на должность и освобождение от должности секретаря " +
                            "судебного заседания аппарата мировых судей Железнодорожного судебного " +
                            "района осуществляются начальником управления по обеспечению деятельности " +
                            "мировых судей Новосибирской области с учетом требований Закона " +
                            "Новосибирской области от 26.09.2005 № 314-ОЗ «О мировых судьях " +
                            "Новосибирской области».",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );

            document.add(paragraph8);

            Paragraph paragraph9 = paragraphFactory.getDefaultParagraph(
                    "5. Секретарь судебного заседания непосредственно подчиняется мировому " +
                            "судье 4-го судебного участка Железнодорожного судебного района (далее – " +
                            "мировой судья).",
                    font.getNormalFont(),
                    pointsInCM.cm(1.25f)
            );

            document.add(paragraph9);

            chunk = new Chunk("________________________", new Font(Font.FontFamily.TIMES_ROMAN));

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
