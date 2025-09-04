package com.simon.documentgeneration.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParagraphFactory {

    private static float singleInterval = 1.2f;


    private Paragraph paragraph;


    public Paragraph getFretboard(String text, Font font) {

        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        return paragraph;

    }


    public Paragraph getHeading(String text, Font font)  {

        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        return paragraph;
    }

    public Paragraph getDefaultParagraph(String text, Font font, float indentCM) {
        paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setFirstLineIndent(indentCM);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        return paragraph;
    }

    public Paragraph getinterlinearParagraph(String text, Font font) {
        paragraph = new Paragraph(text, font);
        paragraph.setLeading(font.getSize() * singleInterval);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        return paragraph;
    }


    public Paragraph getEmptyParagraph() {
        paragraph = new Paragraph(" ");
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);
        return paragraph;
    }





}
