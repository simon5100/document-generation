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

            Paragraph paragraph1 = paragraphFactory.getFretboard("УТВЕРЖДАЮ\n" +
                    "Начальник управления по обеспечению\n" +
                    "деятельности мировых судей\n" +
                    "Новосибирской области\n" +
                    "\n" +
                    "          ______________  А.В. Артеменко",
                    font.getNormalFont());
            Paragraph paragraph2 = paragraphFactory.getinterlinearParagraph(
                    "(подпись)",
                    font.getNormalSmallFont());
            paragraph2.setIndentationLeft(95);

            Paragraph paragraph3 = paragraphFactory.getFretboard(
                    "«      »            202      г.",
                    font.getNormalFont());

            PdfPCell rightCell = new PdfPCell();
            rightCell.addElement(paragraph1);
            rightCell.addElement(paragraph2);
            rightCell.addElement(paragraph3);
            rightCell.setBorder(Rectangle.NO_BORDER);
            rightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(rightCell);

            document.add(pdfPTable);
            document.add(paragraphFactory.getEmptyParagraph());

            Paragraph title = paragraphFactory.getHeading("ДОЛЖНОСТНОЙ РЕГЛАМЕНТ\n" +
                    "СЕКРЕТАРЯ СУДЕБНОГО ЗАСЕДАНИЯ АППАРАТА МИРОВЫХ СУДЕЙ\n" +
                    "ЖЕЛЕЗНОДОРОЖНОГО СУДЕБНОГО РАЙОНА УПРАВЛЕНИЯ ПО\n" +
                    "ОБЕСПЕЧЕНИЮ ДЕЯТЕЛЬНОСТИ МИРОВЫХ СУДЕЙ\n" +
                    "НОВОСИБИРСКОЙ ОБЛАСТИ\n", font.getNormalBoldFont());

            document.add(title);
            document.add(paragraphFactory.getEmptyParagraph());

            Paragraph heading = paragraphFactory.getHeading(
                    "I. Общие положения",
                    font.getNormalBoldFont()
            );

            document.add(heading);
            document.add(paragraphFactory.getEmptyParagraph());

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






            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
