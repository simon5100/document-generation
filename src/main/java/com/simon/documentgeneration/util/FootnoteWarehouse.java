package com.simon.documentgeneration.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
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
public class FootnoteWarehouse {

    private Chunk lineFootnote = new Chunk("________________________\n");

    private Paragraph paragraph;

    public Paragraph getFootnotePage1(FontFactoryCustom fontFactory) {

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(fontFactory.getNormalSmallFont().getSize() * 1.2f);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        paragraph.add(new Chunk("1", fontFactory.getFootnoteFont()).
                setTextRise(5));
        paragraph.add(new Chunk(" В соответствии с Реестром должностей государственной гражданской службы Новосибирской области " +
                "(приложение к Закону Новосибирской области от 06.04.2005 № 287-ОЗ «О Реестре должностей государственной " +
                "гражданской службы Новосибирской области»).\n", fontFactory.getNormalSmallFont()));
        paragraph.add(new Chunk("2", fontFactory.getFootnoteFont()).
                setTextRise(5));
        paragraph.add(new Chunk(" В соответствии со справочником квалификационных требований к специальностям, направлениям подготовки (к " +
                "укрупненным группам специальностей и направлений подготовки), к профессиональному уровню, которые " +
                "необходимы для замещения должностей государственной гражданской службы, с учетом области и вида " +
                "профессиональной служебной деятельности государственных гражданских служащих, предусмотренным частью 8 " +
                "статьи 12 Федерального закона от 27.07.2004 № 79-ФЗ «О государственной гражданской службе Российской " +
                "Федерации» (далее – Справочник).\n", fontFactory.getNormalSmallFont()));
        paragraph.add(new Chunk("3", fontFactory.getFootnoteFont()).
                setTextRise(5));
        paragraph.add(new Chunk(" В соответствии со Справочником.\n", fontFactory.getNormalSmallFont()));

        return paragraph;
    }

    public Paragraph getFootnotePage2(FontFactoryCustom fontFactory) {

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(fontFactory.getNormalSmallFont().getSize() * 1.2f);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        paragraph.add(new Chunk("4", fontFactory.getFootnoteFont()).
                setTextRise(5));
        paragraph.add(new Chunk(" В соответствии со статьей 6 Закона Новосибирской области от 01.02.2005 № 265-ОЗ " +
                "«О государственной гражданской службе Новосибирской области».", fontFactory.getNormalSmallFont()));

        return paragraph;
    }

    public Paragraph getFootnotePage10(FontFactoryCustom fontFactory) {

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setLeading(fontFactory.getNormalSmallFont().getSize() * 1.2f);
        paragraph.setSpacingAfter(0);
        paragraph.setSpacingBefore(0);

        paragraph.add(new Chunk("5", fontFactory.getFootnoteFont()).
                setTextRise(5));
        paragraph.add(new Chunk(" Указывается полный перечень государственных услуг (видов деятельности), оказываемых по запросам граждан и организаций с указанием наименований " +
                "соответствующих административных регламентов (иных нормативных правовых актов) государственного органа и актов государственного органа, которыми они утверждены, либо делается запись " +
                "«Государственные услуги (виды деятельности) не оказываются».", fontFactory.getNormalSmallFont()));

        return paragraph;
    }
}