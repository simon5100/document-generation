package com.simon.documentgeneration.util;

import org.springframework.stereotype.Component;

@Component
public class ConvertCentimetersToPoints {

    public float convertCentimetersToPoints(float cm) {
        return cm * 28.35f;
    }
}
