package com.simon.documentgeneration.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.simon.documentgeneration.util.ConvertPointsITextPDF;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class DocumentGenerationServiceImpl implements DocumentGenerationService  {

    private final ConvertPointsITextPDF convertPointsITextPDF;

    @Override
    public PDDocument generatedDocument1() {
//
//        PDDocument document = new PDDocument();
//        PDFont font = PDType0Font.load(document, new File("fonts/Times New Roman.ttf"));
//        PDPage page = new PDPage(PDRectangle.A4);
//
//
//
        return null;
    }

    @Override
    public byte[] generatedDocument2() {
        Font font =  new FontFactoryImp().getFont("fonts/Times New Roman.ttf", "cp1251", 14);
        Font fontTNR14 = new Font(Font.FontFamily.TIMES_ROMAN, 14);
        Font fontTNR14Bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font fontTNR14Under = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE);
        Font fontTNR10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            //создаем документ и настраиваем страницу
            Document document = new Document(PageSize.A4,
                    convertPointsITextPDF.cm(2.5F),
                    convertPointsITextPDF.cm(1F),
                    convertPointsITextPDF.cm(2F),
                    convertPointsITextPDF.cm(2F));
            //делаем поток для записи
            PdfWriter.getInstance(document, baos);
            //открывем документ для записи
            document.open();

            Paragraph paragraph1 = new Paragraph("УТВЕРЖДАЮ\n" +
                    "Начальник управления по обеспечению\n" +
                    "деятельности мировых судей\n" +
                    "Новосибирской области\n" +
                    "\n" +
                    "_________________ А.В. Артеменко\n" +
                    "                         (подпись)                    \n" +
                    "«____» ___________ 202____ г.\n", font);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph1);
            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
