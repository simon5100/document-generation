package com.simon.documentgeneration.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConvertPointsInCentimeter {

    public float centimeter(float cm) {
        return cm * 28.35f;
    }
}
