package com.simon.documentgeneration.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UnderLineTextWarehouse {

    private final FontFactoryCustom font = new FontFactoryCustom();

    private final Paragraph UNDER_LINE_TEXT1 = new Paragraph("(указываются при необходимости требования к квалификации, полученной " +
            "по результатам освоения дополнительной профессиональной программы профессиональной переподготовки с " +
            "учетом области и вида деятельности)\n",font.getSmallFont());

    private final Paragraph UNDER_LINE_TEXT2 = new Paragraph("(указываются квалификационные требования к стажу гражданской службы или стажу работы по специальности, " +
            "направлению подготовки, необходимому для замещения должности гражданской службы)\n",font.getSmallFont());

    {
        UNDER_LINE_TEXT1.setAlignment(Element.ALIGN_CENTER);
        UNDER_LINE_TEXT1.setLeading(font.getSmallFont().getSize() * 1.2f);
        UNDER_LINE_TEXT1.setIndentationLeft(125);
        UNDER_LINE_TEXT1.setSpacingBefore(2);
        UNDER_LINE_TEXT1.setSpacingAfter(0);

        UNDER_LINE_TEXT2.setAlignment(Element.ALIGN_CENTER);
        UNDER_LINE_TEXT2.setLeading(font.getSmallFont().getSize() * 1.2f);
        UNDER_LINE_TEXT2.setSpacingBefore(2);
        UNDER_LINE_TEXT2.setSpacingAfter(0);
    }
}
