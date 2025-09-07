package com.simon.documentgeneration.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Header extends PdfPageEventHelper {

    private int numberPage;

    private static Font numberHeader = new Font(
            Font.FontFamily.TIMES_ROMAN,
            10,
            Color.GRAY.getRGB());

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        String headerContent = "" + numberPage;

        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(headerContent, numberHeader),
                (document.left() + document.right())/2, document.top() + 10, 0);

    }
}
