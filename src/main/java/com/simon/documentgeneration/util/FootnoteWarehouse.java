package com.simon.documentgeneration.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FootnoteWarehouse {

    private final FontFactoryCustom fontFactory = new FontFactoryCustom();

    private final Chunk lineFootnote = new Chunk("________________________\n");

    private final Chunk FOOTNOTE_PAGE_1_NUMBER_1 = new Chunk("1", fontFactory.getFootnoteFont()).setTextRise(5);
    private final Chunk FOOTNOTE_PAGE_1_TEXT_1 = new Chunk(" В соответствии с Реестром должностей государственной гражданской службы Новосибирской области " +
            "(приложение к Закону Новосибирской области от 06.04.2005 № 287-ОЗ «О Реестре должностей государственной " +
            "гражданской службы Новосибирской области»).\n", fontFactory.getSmallFont());
    private final Chunk FOOTNOTE_PAGE_1_NUMBER_2 = new Chunk("2", fontFactory.getFootnoteFont()).setTextRise(5);
    private final Chunk FOOTNOTE_PAGE_1_TEXT_2 = new Chunk(" В соответствии со справочником квалификационных требований к специальностям, направлениям подготовки (к " +
            "укрупненным группам специальностей и направлений подготовки), к профессиональному уровню, которые " +
            "необходимы для замещения должностей государственной гражданской службы, с учетом области и вида " +
            "профессиональной служебной деятельности государственных гражданских служащих, предусмотренным частью 8 " +
            "статьи 12 Федерального закона от 27.07.2004 № 79-ФЗ «О государственной гражданской службе Российской " +
            "Федерации» (далее – Справочник).\n", fontFactory.getSmallFont());
    private final Chunk FOOTNOTE_PAGE_1_NUMBER_3 = new Chunk("3", fontFactory.getFootnoteFont()).setTextRise(5);
    private final Chunk FOOTNOTE_PAGE_1_TEXT_3 = new Chunk(" В соответствии со Справочником.\n");
    private final Chunk FOOTNOTE_PAGE_2_NUMBER_4 = new Chunk("4", fontFactory.getFootnoteFont()).setTextRise(5);
    private final Chunk FOOTNOTE_PAGE_2_TEXT_4 = new Chunk(" В соответствии со статьей 6 Закона Новосибирской области от 01.02.2005 № 265-ОЗ " +
            "«О государственной гражданской службе Новосибирской области».", fontFactory.getSmallFont());
    private final Chunk FOOTNOTE_PAGE_10_NUMBER_5 = new Chunk("5", fontFactory.getFootnoteFont()).setTextRise(5);
    private final Chunk FOOTNOTE_PAGE_10_TEXT_5 = new Chunk(" Указывается полный перечень государственных услуг (видов деятельности), оказываемых по запросам граждан и организаций с указанием наименований " +
            "соответствующих административных регламентов (иных нормативных правовых актов) государственного органа и актов государственного органа, которыми они утверждены, либо делается запись " +
            "«Государственные услуги (виды деятельности) не оказываются».", fontFactory.getSmallFont());

    private final Paragraph FOOTNOTES_PAGE_1;
    private final Paragraph FOOTNOTES_PAGE_2;
    private final Paragraph FOOTNOTES_PAGE_10;

    {
        FOOTNOTES_PAGE_1 = new Paragraph();
        FOOTNOTES_PAGE_1.setAlignment(Element.ALIGN_LEFT);
        FOOTNOTES_PAGE_1.setLeading(fontFactory.getSmallFont().getSize() * 1.2f);
        FOOTNOTES_PAGE_1.setSpacingAfter(0);
        FOOTNOTES_PAGE_1.setSpacingBefore(0);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_NUMBER_1);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_TEXT_1);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_NUMBER_2);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_TEXT_2);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_NUMBER_3);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_1_TEXT_3);

        FOOTNOTES_PAGE_2 = new Paragraph();
        FOOTNOTES_PAGE_2.setAlignment(Element.ALIGN_LEFT);
        FOOTNOTES_PAGE_2.setLeading(fontFactory.getSmallFont().getSize() * 1.2f);
        FOOTNOTES_PAGE_2.setSpacingAfter(0);
        FOOTNOTES_PAGE_2.setSpacingBefore(0);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_2_NUMBER_4);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_2_TEXT_4);

        FOOTNOTES_PAGE_10 = new Paragraph();
        FOOTNOTES_PAGE_10.setAlignment(Element.ALIGN_LEFT);
        FOOTNOTES_PAGE_10.setLeading(fontFactory.getSmallFont().getSize() * 1.2f);
        FOOTNOTES_PAGE_10.setSpacingAfter(0);
        FOOTNOTES_PAGE_10.setSpacingBefore(0);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_10_NUMBER_5);
        FOOTNOTES_PAGE_1.add(FOOTNOTE_PAGE_10_TEXT_5);
    }
}