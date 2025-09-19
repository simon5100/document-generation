package com.simon.documentgeneration.util;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FontFactoryCustom {

    private final String TYPE_TIMES_NEW_ROMAN = "fonts/Times New Roman.ttf";
    private final String EN_COD = "cp1251";
    private final float NORMAl_SIZE = 14f;
    private final float SMALL_SIZE = 10f;
    private final float FOOTNOTE_SIZE = 8f;

    public Font getNormalFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE);
    }

    public Font getNormalBoldFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE, Font.BOLD);
    }

    public Font getSmallFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, SMALL_SIZE);
    }

    public Font getFootnoteFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, FOOTNOTE_SIZE);
    }

    public Font getNormalUNnderLineFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE, Font.UNDERLINE);
    }
}