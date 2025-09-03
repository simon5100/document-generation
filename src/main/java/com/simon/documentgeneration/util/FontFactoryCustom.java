package com.simon.documentgeneration.util;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class FontFactoryCustom {

    private static final String TYPE_TIMES_NEW_ROMAN = "fonts/Times New Roman.ttf";
    private static final String EN_COD = "cp1251";
    private static final float NORMAl_SIZE = 14f;
    private static final float SMALL_SIZE = 10f;

    public Font getNormalFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE);
    }

    public Font getNormalBoldFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE, Font.BOLD);
    }

    public Font getNormalUNnderLineFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, NORMAl_SIZE, Font.UNDERLINE);
    }

    public Font getNormalSmallFont() {
        return new FontFactoryImp().getFont(TYPE_TIMES_NEW_ROMAN, EN_COD, SMALL_SIZE, Font.UNDERLINE);
    }
}
