package com.simon.documentgeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.awt.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class HeaderNumberPage extends PdfPageEventHelper {

    private  int numberPage = 1;

    private PdfContentByte cb;

    private static Font numberHeader = new Font(
            Font.FontFamily.TIMES_ROMAN,
            10,
            Color.GRAY.getRGB());

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        if (numberPage != 1) {
            cb = writer.getDirectContent();

            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.valueOf(numberPage), numberHeader),
                    (document.left() + document.right()) / 2, document.top() + 10, 0);
        }
        numberPage++;
    }
}
