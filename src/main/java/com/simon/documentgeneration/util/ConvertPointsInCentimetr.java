package com.simon.documentgeneration.util;

import org.springframework.stereotype.Component;

@Component
public class ConvertPointsInCentimetr {

    public float centimetr(float cm) {
        return cm * 28.35f;
    }
}
