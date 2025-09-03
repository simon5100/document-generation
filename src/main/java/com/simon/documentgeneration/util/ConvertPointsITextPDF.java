package com.simon.documentgeneration.util;

import org.springframework.stereotype.Component;

@Component
public class ConvertPointsITextPDF {

    public float cm(float cm) {
        return cm * 28.35f;
    }
}
