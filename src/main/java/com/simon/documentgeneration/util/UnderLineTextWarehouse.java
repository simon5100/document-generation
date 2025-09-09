package com.simon.documentgeneration.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UnderLineTextWarehouse {

    private Paragraph paragraph;

    public Paragraph getUnderLineText1 (Font font) {

        paragraph = new Paragraph("(указываются при необходимости требования к квалификации, полученной " +
                "по результатам освоения дополнительной профессиональной программы профессиональной переподготовки с " +
                "учетом области и вида деятельности)\n",font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(font.getSize() * 1.2f);
        paragraph.setIndentationLeft(125);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(0);

        return paragraph;
    }

    public Paragraph getUnderLineText2 (Font font) {

        paragraph = new Paragraph("(указываются квалификационные требования к стажу гражданской службы или стажу работы по специальности, " +
                "направлению подготовки, необходимому для замещения должности гражданской службы)\n",font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(font.getSize() * 1.2f);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(0);

        return paragraph;
    }


}
